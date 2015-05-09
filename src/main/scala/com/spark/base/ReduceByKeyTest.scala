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
 * DateTime: 15-5-9下午5:30
 */
object ReduceByKeyTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val sc = new SparkContext("local", "ReduceByKey Test")
    val data1 = Array[(String, Int)](("A", 1), ("B", 1),
      ("C", 1), ("B", 1),
      ("C", 1), ("D", 1),
      ("C", 1), ("A", 1))
    val pairs = sc.parallelize(data1, 3)

     pairs.foreachWith(i => i)((x, i) => println("[pPartitionIndex " + i + "] " + x))

    // [pPartitionIndex 0] (A,1)
    // [pPartitionIndex 0] (B,1)

    // [pPartitionIndex 1] (C,1)
    // [pPartitionIndex 1] (B,1)
    // [pPartitionIndex 1] (C,1)

    // [pPartitionIndex 2] (D,1)
    // [pPartitionIndex 2] (C,1)
    // [pPartitionIndex 2] (A,1)

    val result1 = pairs.reduce((A, B) => (A._1 + "#" + B._1, A._2 + B._2))
    println("result1 = " + result1)

    /**
     * Aggregate the elements of each partition, and then the results for all the partitions, using a
     * given associative function and a neutral "zero value". The function op(t1, t2) is allowed to
     * modify t1 and return it as its result value to avoid object allocation; however, it should not
     * modify t2.
     */
    val result2 = pairs.fold(("K0",10))((A, B) => (A._1 + "#" + B._1, A._2 + B._2))
    println("result2 = " + result2)
    val result = pairs.reduceByKey(_ + _, 2)
    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))

    println(result.toDebugString)

    // output
    // [PartitionIndex 0] (B,2)
    // [PartitionIndex 0] (D,1)
    // [PartitionIndex 1] (A,2)
    // [PartitionIndex 1] (C,3)

    /*
MapPartitionsRDD[3] at reduceByKey at reduceByKeyTest.scala:17 (2 partitions)
  ShuffledRDD[2] at reduceByKey at reduceByKeyTest.scala:17 (2 partitions)
    MapPartitionsRDD[1] at reduceByKey at reduceByKeyTest.scala:17 (3 partitions)
      ParallelCollectionRDD[0] at parallelize at reduceByKeyTest.scala:14 (3 partitions)
     */
  }
}
