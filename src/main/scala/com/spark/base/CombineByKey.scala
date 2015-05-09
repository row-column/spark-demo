package com.spark.base

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
 * Module Desc:CombineByKey
 * User: wangyue
 * DateTime: 15-5-8下午3:39
 */
object CombineByKey {
  def main(args: Array[String]) {
    val sc = new SparkContext("local","CombineByKey-Test")

    val a = sc.parallelize(List("dog", "cat", "gnu", "salmon", "rabbit", "turkey", "wolf", "bear", "bee"), 3)
    val b = sc.parallelize(List(1, 1, 2, 2, 2, 1, 2, 2, 2), 3)
    val c = b.zip(a)
    c.foreach(println)
    // output
    // (1,dog)
    // (1,cat)
    // (2,gnu)
    // (2,salmon)
    // (2,rabbit)
    // (1,turkey)
    // (2,wolf)
    // (2,bear)
    // (2,bee)

    /**
     * Generic function to combine the elements for each key using a custom set of aggregation
     * functions. Turns an RDD[(K, V)] into a result of type RDD[(K, C)], for a "combined type" C
     * Note that V and C can be different -- for example, one might group an RDD of type
     * (Int, Int) into an RDD of type (Int, Seq[Int]). Users provide three functions:
     *
     * - `createCombiner`, which turns a V into a C (e.g., creates a one-element list)
     * - `mergeValue`, to merge a V into a C (e.g., adds it to the end of a list)
     * - `mergeCombiners`, to combine two C's into a single one.
     *
     * In addition, users can control the partitioning of the output RDD, and whether to perform
     * map-side aggregation (if a mapper can produce multiple items with the same key).
     */
    val d = c.combineByKey(List(_), (x:List[String], y:String) => y :: x, (x:List[String], y:List[String]) => x ::: y)
    val result = d.collect

    result.foreach(println)
    // output
    // (1,List(cat, dog, turkey))
    // (2,List(gnu, rabbit, salmon, bee, bear, wolf))
    println("RDD graph:\n" + d.toDebugString)

  /*  RDD graph:
      (3) ShuffledRDD[3] at combineByKey at CombineByKey.scala:51 []
    +-(3) ZippedPartitionsRDD2[2] at zip at CombineByKey.scala:35 []
    |  ParallelCollectionRDD[1] at parallelize at CombineByKey.scala:34 []
    |  ParallelCollectionRDD[0] at parallelize at CombineByKey.scala:33 []
  */

  }

}
