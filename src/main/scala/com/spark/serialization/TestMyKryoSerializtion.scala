package com.spark.serialization

import org.apache.spark.{SparkContext, SparkConf}

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
 * Module Desc:测试 自定义 Kryo 序列化
 * User: wangyue
 * DateTime: 15-4-29下午6:40
 */
object TestMyKryoSerializtion {
  // user defined class that need to serialized
  class Person(val name: String)

  def main(args: Array[String]) {

    if (args.length < 1) {
      println("Please provide output path")
      return
    }
    val outputPath = args(0)

    val conf = new SparkConf().setMaster("local").setAppName("TestMyKryoSerializtion")
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val sc = new SparkContext(conf)

    //create some dummy data
    val personList = 1 to 10000 map (value => new Person(value + ""))
    val personRDD = sc.makeRDD(personList)

    MyKyroSerializationUtils.saveAsObjectFile(personRDD, outputPath)

    val rdd = MyKyroSerializationUtils.objectFile[Person](sc, outputPath)

    println(rdd.map(person => person.name).collect().toList)
  }

}
