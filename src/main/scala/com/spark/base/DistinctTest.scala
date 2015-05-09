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
 * Module Desc:DistinctTest
 * User: wangyue
 * DateTime: 15-5-9下午6:22
 */
object DistinctTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "distinct test")

    val pairs = sc.parallelize(List(1, 2, 2, 3, 2, 1, 4, 5), 3)
    /**
     * Return a new RDD containing the distinct elements in this RDD.
     */
    val result = pairs.distinct(2)

    // output
    // [PartitionIndex 0] 1
    // [PartitionIndex 0] 2

    // [PartitionIndex 1] 2
    // [PartitionIndex 1] 3
    // [PartitionIndex 1] 2

    // [PartitionIndex 2] 1
    // [PartitionIndex 2] 4
    // [PartitionIndex 2] 5

    pairs.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))
    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))

    // output
    // [PartitionIndex 0] 4
    // [PartitionIndex 0] 2

    // [PartitionIndex 1] 1
    // [PartitionIndex 1] 3
    // [PartitionIndex 1] 5

    println(result.toDebugString)
  }
}
