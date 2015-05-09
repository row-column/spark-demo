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
 * Module Desc:Cogroup
 * User: wangyue
 * DateTime: 15-5-8下午3:10
 */
object Cogroup {
  def main(args: Array[String]) {
    val sc = new SparkContext("local","CoGroup-Test")

    val a = sc.parallelize(List(1, 2, 1, 3), 2)
    val b = sc.parallelize(List(1, 2, 3, 4, 5, 6), 3)
    val d = a.map((_, "b"))
    d.foreach(println)
    // output:
    // (1,b)
    // (2,b)
    // (1,b)
    // (3,b)

    val e = b.map((_, "c"))
    e.foreach(println)
    // output:
    // (1,c)
    // (2,c)
    // (3,c)
    // (4,c)
    // (5,c)
    // (6,c)
    /**
     * For each key k in `this` or `other1` or `other2` or `other3`,
     * return a resulting RDD that contains a tuple with the list of values
     * for that key in `this`, `other1`, `other2` and `other3`.
     */
    val result =d.cogroup(e)
    result.foreach(println)
    //output:
    //(6,(CompactBuffer(),CompactBuffer(c)))
    //(3,(CompactBuffer(b),CompactBuffer(c)))
    //(4,(CompactBuffer(),CompactBuffer(c)))
    //(1,(CompactBuffer(b, b),CompactBuffer(c)))
    //(5,(CompactBuffer(),CompactBuffer(c)))
    //(2,(CompactBuffer(b),CompactBuffer(c)))

    println(result.toDebugString)

    /*
    (3) MapPartitionsRDD[5] at cogroup at Cogroup.scala:57 []
    |  CoGroupedRDD[4] at cogroup at Cogroup.scala:57 []
    +-(2) MapPartitionsRDD[2] at map at Cogroup.scala:35 []
    |  |  ParallelCollectionRDD[0] at parallelize at Cogroup.scala:33 []
    +-(3) MapPartitionsRDD[3] at map at Cogroup.scala:43 []
    |  ParallelCollectionRDD[1] at parallelize at Cogroup.scala:34 []*/

  }

}
