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
 * Module Desc:Aggregate
 * User: wangyue
 * DateTime: 15-5-9下午7:06
 */
object Aggregate {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "AggregateAction Test")
    val data = Array[(String, Int)](("A1", 1), ("A2", 2),
      ("B1", 3), ("B2", 4),
      ("C1", 5), ("C2", 6))

    val pairs = sc.parallelize(data, 3)

    // output:
    // 	(A1,1)(A2,2)
    //  (B1,3)(B2,4)
    //	(C1,5)(C2,6)
    pairs.foreach(print)
    /**
     * Aggregate the elements of each partition, and then the results for all the partitions, using
     * given combine functions and a neutral "zero value". This function can return a different result
     * type, U, than the type of this RDD, T. Thus, we need one operation for merging a T into an U
     * and one operation for merging two U's, as in scala.TraversableOnce. Both of these functions are
     * allowed to modify and return their first argument instead of creating a new U to avoid memory
     * allocation.
     */
    val result = pairs.aggregate(("", 0))((U, T) => (U._1 + T._1, U._2 + T._2), (U, T) => ("[" + U._1 + T._1 + "] ", U._2 + T._2))

    // output ([[[A1A2] B1B2] C1C2] ,21)
    println(result)
  }
}
