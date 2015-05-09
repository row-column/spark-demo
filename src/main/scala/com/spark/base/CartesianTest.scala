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
 * Module Desc:CartesianTest
 * User: wangyue
 * DateTime: 15-5-9下午6:50
 */
object CartesianTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)

    val sc = new SparkContext("local", "cartesian Test")
    val data1 = Array[(Int, Char)]((1, 'a'), (2, 'b'), (3, 'c'), (4, 'd'))
    val pairs1 = sc.parallelize(data1, 2)

    val data2 = Array[(Int, Char)]((1, 'A'), (2, 'B'))
    val pairs2 = sc.parallelize(data2, 2)
    /**
     * Return the Cartesian product of this RDD and another one, that is, the RDD of all pairs of
     * elements (a, b) where a is in `this` and b is in `other`.
     */
    val result = pairs1.cartesian(pairs2)

    //pairs1.foreachWith(i => i)((x, i) => println("[pairs1-Index " + i + "] " + x))
    //pairs2.foreachWith(i => i)((x, i) => println("[pairs2-Index " + i + "] " + x))
    result.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))
//      [PartitionIndex 0] ((2,b),(1,A))
//      [PartitionIndex 1] ((1,a),(2,B))
//      [PartitionIndex 1] ((2,b),(2,B))
//      [PartitionIndex 2] ((3,c),(1,A))
//      [PartitionIndex 2] ((4,d),(1,A))
//      [PartitionIndex 3] ((3,c),(2,B))
//      [PartitionIndex 3] ((4,d),(2,B))
    //println(result.toDebugString)
  }
}
