package com.spark.base

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object MyFirstSpark1 {
  def main(args: Array[String]) {
    //屏蔽不必要的日志显示在终端上
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    if (args.length != 2) {
      System.err.println("Usage:MyFirstSpark1 <Input> <Output>")
    }
    val conf = new SparkConf().setAppName("myFirstSpark1").setMaster("local[2]")
    val sc = new SparkContext(conf)
    //sc.textFile(args(0)).map(_.split("\t")).filter(_.length == 6)
    sc.textFile(args(0)).flatMap(_.split(" "))/*.filter(_.length == 6)*/
      .map(x => (x, 1)).reduceByKey(_ + _).saveAsTextFile(args(1))

    sc.stop()
  }
}
