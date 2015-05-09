package com.spark.base

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
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
 * Module Desc:broadcast
 * User: wangyue
 * DateTime: 15-5-9下午5:10
 */
object Broadcast {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val bcName = "Http"
    val blockSize = "4096"

    System.setProperty("spark.broadcast.factory", "org.apache.spark.broadcast." + bcName +
      "BroadcastFactory")
    System.setProperty("spark.broadcast.blockSize", blockSize)
    val sparkConf = new SparkConf().setAppName("Broadcast Test").setMaster("local")

    val sc = new SparkContext(sparkConf)

    val slices = 2
    val num = 100

    val arr1 = new Array[Int](num)

    for (i <- 0 until arr1.length) {
      arr1(i) = i
    }

    val data = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)

    val barr1 = sc.broadcast(arr1)
    val observedSizes = sc.parallelize(1 to 4, slices).map(_ => barr1.value.size)
    // Collect the small RDD so we can print the observed sizes locally.
    observedSizes.collect().foreach(i => println(i))

    println(barr1.value.size)
    barr1.value.foreach(println(_))
  }
}
