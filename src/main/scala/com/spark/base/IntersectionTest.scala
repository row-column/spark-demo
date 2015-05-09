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
 * Module Desc:IntersectionTest
 * User: wangyue
 * DateTime: 15-5-8下午5:35
 */
object IntersectionTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val sc = new SparkContext("local","IntersectionTest-Test")
    val a = sc.parallelize(List(1, 2, 3, 3, 4, 5), 3)
    val b = sc.parallelize(List(1, 2, 5, 6), 2)

    /**
     * Return the intersection of this RDD and another one. The output will not contain any duplicate
     * elements, even if the input RDDs did.
     * Note that this method performs a shuffle internally.
     */
    val r = a.intersection(b) //交集
    r.foreach(println)
//    output:
//    1
//    5
//    2

    /**
     * Applies f to each element of this RDD, where f takes an additional parameter of type A.
     * This additional parameter is produced by constructA, which is called in each
     * partition with the index of that partition.
     */
    r.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))
//      [PartitionIndex 1] 1
//      [PartitionIndex 2] 5
//      [PartitionIndex 2] 2
    println(r.toDebugString)
//    (3) MapPartitionsRDD[7] at intersection at IntersectionTest.scala:43 []
//    |  MapPartitionsRDD[6] at intersection at IntersectionTest.scala:43 []
//    |  MapPartitionsRDD[5] at intersection at IntersectionTest.scala:43 []
//    |  CoGroupedRDD[4] at intersection at IntersectionTest.scala:43 []
//    +-(3) MapPartitionsRDD[2] at intersection at IntersectionTest.scala:43 []
//    |  |  ParallelCollectionRDD[0] at parallelize at IntersectionTest.scala:35 []
//    +-(2) MapPartitionsRDD[3] at intersection at IntersectionTest.scala:43 []
//    |  ParallelCollectionRDD[1] at parallelize at IntersectionTest.scala:36 []

  }

}
