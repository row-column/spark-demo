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
 * Module Desc:Coalesce
 * User: wangyue
 * DateTime: 15-5-8下午2:46
 */
object Coalesce {
  def main(args: Array[String]) {
    val sc = new SparkContext("local","Coalesce-Test")

    /** Distribute a local Scala collection to form an RDD.
      * @note Parallelize acts lazily. If `seq` is a mutable collection and is altered after the call
      * to parallelize and before the first action on the RDD, the resultant RDD will reflect the
      * modified collection. Pass a copy of the argument to avoid this.
      */
    val y = sc.parallelize(1 to 10, 10)

    y.foreach(println)

    val z = y.coalesce(2, true)

    z.foreach(println)
  }

}
