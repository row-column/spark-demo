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
 * Module Desc:ComplexStagesTest
 * User: wangyue
 * DateTime: 15-5-9下午6:27
 */
object ComplexStagesTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "complexStages Test")


    val data1 = Array[(Int, Char)](
      (1, 'a'), (2, 'b'),
      (3, 'c'), (4, 'd'),
      (5, 'e'), (3, 'f'),
      (2, 'g'), (1, 'h'))
    val rangePairs1 = sc.parallelize(data1, 3)

    val hashPairs1 = rangePairs1.partitionBy(new HashPartitioner(3))

    val data2 = Array[(Int, String)]((1, "A"), (2, "B"),
      (3, "C"), (4, "D"))

    val pairs2 = sc.parallelize(data2, 2)
    val rangePairs2 = pairs2.map(x => (x._1, x._2.charAt(0)))


    val data3 = Array[(Int, Char)]((1, 'X'), (2, 'Y'))
    val rangePairs3 = sc.parallelize(data3, 2)


    val rangePairs = rangePairs2.union(rangePairs3)

    val result = hashPairs1.join(rangePairs)

    result.foreachWith(i => i)((x, i) => println("[result " + i + "] " + x))
//
//      [result 0] (3,(f,C))
//      [result 1] (4,(d,D))
//      [result 1] (1,(a,A))
//      [result 1] (1,(a,X))
//      [result 1] (1,(h,A))
//      [result 1] (1,(h,X))
//      [result 2] (2,(b,B))
//      [result 2] (2,(b,Y))
//      [result 2] (2,(g,B))
//      [result 2] (2,(g,Y))
    println(result.toDebugString)
//    (3) MapPartitionsRDD[8] at join at ComplexStagesTest.scala:58 []
//    |  MapPartitionsRDD[7] at join at ComplexStagesTest.scala:58 []
//    |  CoGroupedRDD[6] at join at ComplexStagesTest.scala:58 []
//    |  ShuffledRDD[1] at partitionBy at ComplexStagesTest.scala:43 []
//    +-(3) ParallelCollectionRDD[0] at parallelize at ComplexStagesTest.scala:41 []
//    +-(4) UnionRDD[5] at union at ComplexStagesTest.scala:56 []
//    |  MapPartitionsRDD[3] at map at ComplexStagesTest.scala:49 []
//    |  ParallelCollectionRDD[2] at parallelize at ComplexStagesTest.scala:48 []
//    |  ParallelCollectionRDD[4] at parallelize at ComplexStagesTest.scala:53 []

  }
}
