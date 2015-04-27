package com.spark.base

import org.apache.log4j.Logger
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import org.apache.log4j.{Level, Logger}

object MyFirstSpark2 {
  def main(args: Array[String]) {
    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    if (args.length != 2) {
      System.err.println("Usage:MyFirstSpark <Input> <Output>")
    }
    val conf = new SparkConf().setAppName("myFirstSpark").setMaster("local[2]")
    val sc = new SparkContext(conf)
    //sc.textFile(args(0)).map(_.split("\t")).filter(_.length == 6)
    sc.textFile(args(0)).map(_.split(" "))/*.filter(_.length == 6)*/
      .map(x => (x(1), 1)).reduceByKey(_ + _).map(x => (x._2, x._1))
      .sortByKey(false).map(x => (x._2, x._1)).saveAsTextFile(args(1))

    sc.stop()
  }
}
