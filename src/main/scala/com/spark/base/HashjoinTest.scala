package com.spark.base

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{HashPartitioner, SparkContext}
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
 * Module Desc:HashjoinTest
 * User: wangyue
 * DateTime: 15-5-9下午5:56
 */
object HashjoinTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "hashjoin Test")
    val data1 = Array[(Int, Char)]((1, 'a'), (2, 'b'),
      (3, 'c'), (4, 'd'),
      (5, 'e'), (3, 'f'),
      (2, 'g'), (1, 'h'))

    val pairs1 = sc.parallelize(data1, 3).partitionBy(new HashPartitioner(3))
    pairs1.foreachWith(i => i)((x, i) => println("[pairs1-Index " + i + "] " + x))
//      [pairs1-Index 0] (3,c)
//      [pairs1-Index 0] (3,f)
//      [pairs1-Index 1] (1,a)
//      [pairs1-Index 1] (4,d)
//      [pairs1-Index 1] (1,h)
//      [pairs1-Index 2] (2,b)
//      [pairs1-Index 2] (5,e)
//      [pairs1-Index 2] (2,g)

    val data2 = Array[(Int, Char)]((1, 'A'), (2, 'B'),
      (3, 'C'), (4, 'D'))
    val pairs2 = sc.parallelize(data2, 2)
    pairs2.foreachWith(i => i)((x, i) => println("[pairs2-Index " + i + "] " + x))
//      [pairs2-Index 0] (1,A)
//      [pairs2-Index 0] (2,B)
//      [pairs2-Index 1] (3,C)
//      [pairs2-Index 1] (4,D)

    /**
     * Return an RDD containing all pairs of elements with matching keys in `this` and `other`. Each
     * pair of elements will be returned as a (k, (v1, v2)) tuple, where (k, v1) is in `this` and
     * (k, v2) is in `other`. Uses the given Partitioner to partition the output RDD.
     */
    val result = pairs1.join(pairs2)
    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))
//      [PartitionIndex 0] (3,(c,C))
//      [PartitionIndex 0] (3,(f,C))
//      [PartitionIndex 1] (4,(d,D))
//      [PartitionIndex 1] (1,(a,A))
//      [PartitionIndex 1] (1,(h,A))
//      [PartitionIndex 2] (2,(b,B))
//      [PartitionIndex 2] (2,(g,B))

    println(result.toDebugString)
//    (3) MapPartitionsRDD[7] at join at HashjoinTest.scala:47 []
//    |  MapPartitionsRDD[6] at join at HashjoinTest.scala:47 []
//    |  CoGroupedRDD[5] at join at HashjoinTest.scala:47 []
//    |  ShuffledRDD[1] at partitionBy at HashjoinTest.scala:38 []
//    +-(3) ParallelCollectionRDD[0] at parallelize at HashjoinTest.scala:38 []
//    +-(2) ParallelCollectionRDD[3] at parallelize at HashjoinTest.scala:44 []
  }
}
