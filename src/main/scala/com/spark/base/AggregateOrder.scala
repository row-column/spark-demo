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
 * Module Desc:AggregateOrder
 * User: wangyue
 * DateTime: 15-5-9下午7:45
 */
object AggregateOrder {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "AggregateOrder Test")
    val data = List("12", "23", "345", "4567")

    val pairs = sc.parallelize(data, 2)
    pairs.foreach(x => println(x.length))

    val result1 = pairs.aggregate("")((x,y) => math.min(x.length, y.length).toString, (x,y) => x + y)
    result1.foreach(print)

    val result2 = pairs.aggregate("")((x,y) => "[" + x.length + "," + y.length + "] ", (x,y) => x + y)
//    11[6,2] [6,4]
    result2.foreach(print)

  }
}
