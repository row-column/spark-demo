package com.spark.base

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

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
 * Module Desc:
 * User: wangyue
 * DateTime: 15-5-9下午5:40
 */
object PipeTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
   // Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val sc = new SparkContext("local", "cartesian Test")

    val a = sc.parallelize(1 to 9, 3)
    /**
     * Return an RDD created by piping elements to a forked external process.
     */
    val result = a.pipe("head -n 2")

    a.foreachWith(i => i)((x, i) => println("[aIndex " + i + "] " + x))
//      [aIndex 0] 1
//      [aIndex 0] 2
//      [aIndex 0] 3
//      [aIndex 1] 4
//      [aIndex 1] 5
//      [aIndex 1] 6
//      [aIndex 2] 7
//      [aIndex 2] 8
//      [aIndex 2] 9
    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))
//      [PartitionIndex 0] 1
//      [PartitionIndex 0] 2
//      [PartitionIndex 1] 4
//      [PartitionIndex 1] 5
//      [PartitionIndex 2] 7
//      [PartitionIndex 2] 8
    println(result.toDebugString)
//    (3) PipedRDD[1] at pipe at pipeTest.scala:40 []
//    |  ParallelCollectionRDD[0] at parallelize at pipeTest.scala:36 []

  }
}
