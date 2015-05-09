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
 * Module Desc:CogroupTest
 * User: wangyue
 * DateTime: 15-5-9下午6:38
 */
object CogroupTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "cogroup Test")
    val a = sc.parallelize(List(1, 2, 3, 3, 4, 5), 3).map(x => (x, 'a'))
    val b = sc.parallelize(List(1, 2, 5, 6), 2).map(y => (y, 'b'))

    val r = a.cogroup(b)

    a.foreachWith(i => i)((x, i) => println("[aIndex " + i + "] " + x))
//      [aIndex 0] (1,a)
//      [aIndex 0] (2,a)
//      [aIndex 1] (3,a)
//      [aIndex 1] (3,a)
//      [aIndex 2] (4,a)
//      [aIndex 2] (5,a)
    b.foreachWith(i => i)((x, i) => println("[bIndex " + i + "] " + x))
//      [bIndex 0] (1,b)
//      [bIndex 0] (2,b)
//      [bIndex 1] (5,b)
//      [bIndex 1] (6,b)
    r.foreachWith(i => i)((x, i) => println("[PartitionIndex " + i + "] " + x))
//      [PartitionIndex 0] (6,(CompactBuffer(),CompactBuffer(b)))
//      [PartitionIndex 0] (3,(CompactBuffer(a, a),CompactBuffer()))
//      [PartitionIndex 1] (4,(CompactBuffer(a),CompactBuffer()))
//      [PartitionIndex 1] (1,(CompactBuffer(a),CompactBuffer(b)))
//      [PartitionIndex 2] (5,(CompactBuffer(a),CompactBuffer(b)))
//      [PartitionIndex 2] (2,(CompactBuffer(a),CompactBuffer(b)))
    println(r.toDebugString)
  }
}
