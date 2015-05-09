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
 * Module Desc:FlatMap
 * User: wangyue
 * DateTime: 15-5-9下午8:11
 */
object FlatMap {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "FlatMap Test")
    val data = Array[(String, Int)](("A", 1), ("B", 2),
      ("B", 3), ("C", 4),
      ("C", 5), ("C", 6)
    )
    val pairs = sc.makeRDD(data, 3)
    pairs.foreach(println(_))
//    (A,1)
//    (B,2)
//    (B,3)
//    (C,4)
//    (C,5)
//    (C,6)
    val result = pairs.flatMap(T => (T._1 + T._2))

    result.foreach(print)
//    A1B2B3C4C5C6

  }
}
