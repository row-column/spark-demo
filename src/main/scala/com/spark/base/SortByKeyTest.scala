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
 * Module Desc:sortByKeyTest
 * User: wangyue
 * DateTime: 15-5-9下午5:26
 */
object SortByKeyTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val sc = new SparkContext("local", "sortByKey Test")
    val data1 = Array[(Char, Int)](('A', 5), ('B', 4),
      ('C', 3), ('B', 2),
      ('C', 1), ('D', 2),
      ('C', 3), ('A', 4))

    val pairs = sc.parallelize(data1, 3)

    val result = pairs.sortByKey(true, 2)
    pairs.foreachWith(i => i)((x, i) => println("[pairsPartitionIndex " + i + "] " + x))
    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))

    println(result.toDebugString)
    //      [pairsPartitionIndex 0] (A,5)
    //      [pairsPartitionIndex 0] (B,4)
    //      [pairsPartitionIndex 1] (C,3)
    //      [pairsPartitionIndex 1] (B,2)
    //      [pairsPartitionIndex 1] (C,1)
    //      [pairsPartitionIndex 2] (D,2)
    //      [pairsPartitionIndex 2] (C,3)
    //      [pairsPartitionIndex 2] (A,4)
    //      [PartitionIndex 0] (A,5)
    //      [PartitionIndex 0] (A,4)
    //      [PartitionIndex 0] (B,4)
    //      [PartitionIndex 0] (B,2)
    //      [PartitionIndex 0] (C,3)
    //      [PartitionIndex 0] (C,1)
    //      [PartitionIndex 0] (C,3)
    //      [PartitionIndex 1] (D,2)
    //    (2) ShuffledRDD[3] at sortByKey at sortByKeyTest.scala:41 []
    //    +-(3) ParallelCollectionRDD[0] at parallelize at sortByKeyTest.scala:39 []
  }
}
