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
 * Module Desc: Cartesian
 * User: wangyue
 * DateTime: 15-5-8下午2:22
 */
object Cartesian {
  def main(args: Array[String]) {
    val sc = new SparkContext("local[2]","Cartesian-Test")

    val x = sc.parallelize(List(1,2,3,4,5))
    val y = sc.parallelize(List(6,7,8,9,10))

    (x ++ y ++ x).foreach(println(_))

    /**
     * Return the Cartesian product of this RDD and another one, that is, the RDD of all pairs of
     * elements (a, b) where a is in `this` and b is in `other`.
     */
    val result = x.cartesian(y)
    result.foreach(println)


  }
}
