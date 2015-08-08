package com.spark.base

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
 * DateTime: 15-5-15下午5:57
 */

import scala.collection.JavaConversions._

/** Prints out environmental information, sleeps, and then exits. Made to
  * test driver submission in the standalone scheduler. */
object DriverSubmissionTest {
  def main(args: Array[String]) {
    if (args.size < 1) {
      println("Usage: DriverSubmissionTest <seconds-to-sleep>")
      System.exit(0)
    }
    val numSecondsToSleep = args(0).toInt

    val env = System.getenv()
    val properties = System.getProperties()

    println("Environment variables containing SPARK_TEST:")
    env.filter{case (k, v) => k.contains("SPARK_TEST")}.foreach(println)

    println("System properties containing spark.test:")
    properties.filter{case (k, v) => k.toString.contains("spark.test")}.foreach(println)

    for (i <- 1 until numSecondsToSleep) {
      println(s"Alive for $i out of $numSecondsToSleep seconds")
      Thread.sleep(1000)
    }
  }
}