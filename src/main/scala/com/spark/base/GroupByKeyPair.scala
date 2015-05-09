package com.spark.base

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{RangePartitioner, SparkContext}

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
 * Module Desc:GroupByKeyPair
 * User: wangyue
 * DateTime: 15-5-8下午4:44
 */
object GroupByKeyPair {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val sc  = new SparkContext("local","GroupByKeyPair-Test");
    val d = sc.parallelize(1 to 100, 10)

    /**
     * Creates tuples of the elements in this RDD by applying `f`.
     */
    val pairs = d.keyBy(x => x % 10)

    /**
     * Group the values for each key in the RDD into a single sequence. Hash-partitions the
     * resulting RDD with the existing partitioner/parallelism level. The ordering of elements
     * within each group is not guaranteed, and may even differ each time the resulting RDD is
     * evaluated.
     *
     * Note: This operation may be very expensive. If you are grouping in order to perform an
     * aggregation (such as a sum or average) over each key, using [[org.apache.spark.rdd.PairRDDFunctions.aggregateByKey]]
     * or [[org.apache.spark.rdd.PairRDDFunctions.reduceByKey]] will provide much better performance.
     */
    val result1 = pairs.groupByKey()
    println("Result 1:")
    result1.foreach(println)
    // output:
    // (0,CompactBuffer(10, 20, 30, 40, 50, 60, 70, 80, 90, 100))
    // (1,CompactBuffer(1, 11, 21, 31, 41, 51, 61, 71, 81, 91))
    // (2,CompactBuffer(2, 12, 22, 32, 42, 52, 62, 72, 82, 92))
    // (3,CompactBuffer(3, 13, 23, 33, 43, 53, 63, 73, 83, 93))
    // (4,CompactBuffer(4, 14, 24, 34, 44, 54, 64, 74, 84, 94))
    // (5,CompactBuffer(5, 15, 25, 35, 45, 55, 65, 75, 85, 95))
    // (6,CompactBuffer(6, 16, 26, 36, 46, 56, 66, 76, 86, 96))
    // (7,CompactBuffer(7, 17, 27, 37, 47, 57, 67, 77, 87, 97))
    // (8,CompactBuffer(8, 18, 28, 38, 48, 58, 68, 78, 88, 98))
    // (9,CompactBuffer(9, 19, 29, 39, 49, 59, 69, 79, 89, 99))

    val result2 = pairs.groupByKey(3)
    println("Result 2:")
    result2.foreach(println)
//    output:
//    (0,CompactBuffer(10, 20, 30, 40, 50, 60, 70, 80, 90, 100))
//    (6,CompactBuffer(6, 16, 26, 36, 46, 56, 66, 76, 86, 96))
//    (3,CompactBuffer(3, 13, 23, 33, 43, 53, 63, 73, 83, 93))
//    (9,CompactBuffer(9, 19, 29, 39, 49, 59, 69, 79, 89, 99))
//    (4,CompactBuffer(4, 14, 24, 34, 44, 54, 64, 74, 84, 94))
//    (1,CompactBuffer(1, 11, 21, 31, 41, 51, 61, 71, 81, 91))
//    (7,CompactBuffer(7, 17, 27, 37, 47, 57, 67, 77, 87, 97))
//    (8,CompactBuffer(8, 18, 28, 38, 48, 58, 68, 78, 88, 98))
//    (5,CompactBuffer(5, 15, 25, 35, 45, 55, 65, 75, 85, 95))
//    (2,CompactBuffer(2, 12, 22, 32, 42, 52, 62, 72, 82, 92))

    val result3 = pairs.groupByKey(new RangePartitioner(3, pairs))
    println("Result 3:")
    result3.foreach(println)
//    output:
//    (0,CompactBuffer(10, 20, 30, 40, 50, 60, 70, 80, 90, 100))
//    (1,CompactBuffer(1, 11, 21, 31, 41, 51, 61, 71, 81, 91))
//    (3,CompactBuffer(3, 13, 23, 33, 43, 53, 63, 73, 83, 93))
//    (2,CompactBuffer(2, 12, 22, 32, 42, 52, 62, 72, 82, 92))
//    (4,CompactBuffer(4, 14, 24, 34, 44, 54, 64, 74, 84, 94))
//    (6,CompactBuffer(6, 16, 26, 36, 46, 56, 66, 76, 86, 96))
//    (5,CompactBuffer(5, 15, 25, 35, 45, 55, 65, 75, 85, 95))
//    (7,CompactBuffer(7, 17, 27, 37, 47, 57, 67, 77, 87, 97))
//    (8,CompactBuffer(8, 18, 28, 38, 48, 58, 68, 78, 88, 98))
//    (9,CompactBuffer(9, 19, 29, 39, 49, 59, 69, 79, 89, 99))

  }

}
