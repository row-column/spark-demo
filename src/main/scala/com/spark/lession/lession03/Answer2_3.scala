package com.spark.lession.lession03

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path, FileSystem}
import org.apache.spark.SparkContext

import scala.collection.mutable.ListBuffer

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　 ┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Module Desc:在spark中实现相当于hadoop的setup，cleanup程序
 * User: wangyue
 * DateTime: 15-7-31下午2:52
 */
object Answer2_3 {

  def main(args: Array[String]) {
    val sc = new SparkContext("local", "Answer2_3")
    val inputData = sc.textFile("hdfs://master:8020/data/spark/user-history-data")
    val lines = inputData.map(line => (line, line.length))


    val result = lines.mapPartitions { valueIterator =>
      if (valueIterator.isEmpty) {
        Iterator[String]()
      } else {
        val transformedItem = new ListBuffer[String]() //setup ListBuffer
        val fs: FileSystem = FileSystem.get(new Configuration()) //setup FileSystem

        valueIterator.map { item =>
          transformedItem += item._1 +":"+item._2
          val outputFile = fs.create(new Path("/home/wangyue/opt/data/spark/" + item._1.substring(0,item._1.indexOf("\t")) + ".txt"))
          outputFile.write((item._1 +":"+item._2).getBytes())
          if (!valueIterator.hasNext) {
            transformedItem.clear() //cleanup transformedItem
            outputFile.close() //cleanup outputFile
            fs.close() //cleanup fs
          }
          transformedItem
        }
      }
    }

    result.foreach(println(_))
    sc.stop()
  }
}
