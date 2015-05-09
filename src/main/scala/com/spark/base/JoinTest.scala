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
 * Module Desc:joinTest
 * User: wangyue
 * DateTime: 15-5-9下午5:47
 */
object JoinTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)

    val sc = new SparkContext("local", "join Test")

    val data1 = Array[(Int, Char)]((1, 'a'), (2, 'b'),
      (3, 'c'), (4, 'd'),
      (5, 'e'), (3, 'f'),
      (2, 'g'), (1, 'h'))
    val pairs1 = sc.parallelize(data1, 3)


    val data2 = Array[(Int, Char)]((1, 'A'), (2, 'B'),
      (3, 'C'), (4, 'D'))
    val pairs2 = sc.parallelize(data2, 2)

    val result = pairs1.join(pairs2)

    //pairs1.foreachWith(i => i)((x, i) => println("[pairs1-Index " + i + "] " + x))
    //pairs2.foreachWith(i => i)((x, i) => println("[pairs2-Index " + i + "] " + x))
    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))
//      [PartitionIndex 0] (3,(f,C))
//      [PartitionIndex 1] (4,(d,D))
//      [PartitionIndex 1] (1,(a,A))
//      [PartitionIndex 1] (1,(h,A))
//      [PartitionIndex 2] (2,(b,B))
//      [PartitionIndex 2] (2,(g,B))
    println(result.toDebugString)
//    (3) MapPartitionsRDD[4] at join at joinTest.scala:47 []
//    |  MapPartitionsRDD[3] at join at joinTest.scala:47 []
//    |  CoGroupedRDD[2] at join at joinTest.scala:47 []
//    +-(3) ParallelCollectionRDD[0] at parallelize at joinTest.scala:40 []
//    +-(2) ParallelCollectionRDD[1] at parallelize at joinTest.scala:45 []

  }
}
