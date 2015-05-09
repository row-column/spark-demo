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
 * Module Desc:CountApproxDistinct
 * User: wangyue
 * DateTime: 15-5-8下午4:26
 */
object CountApproxDistinct {
  def main(args: Array[String]) {
    val sc  = new SparkContext("local[2]","CountApproxDistinct-Test")

    val a = sc.parallelize(1 to 10000, 20)
    val b = a++a++a++a++a

    /**
     * Return approximate number of distinct elements in the RDD.
     *
     * The algorithm used is based on streamlib's implementation of "HyperLogLog in Practice:
     * Algorithmic Engineering of a State of The Art Cardinality Estimation Algorithm", available
     * <a href="http://dx.doi.org/10.1145/2452376.2452456">here</a>.
     *
     * @param relativeSD Relative accuracy. Smaller values create counters that require more space.
     *                   It must be greater than 0.000017.
     */
    val result = b.countApproxDistinct(0.1)
    println(result)
    // output
    // 8224
  }

}
