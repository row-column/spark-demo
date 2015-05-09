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
 * Module Desc:CoalesceTest
 * User: wangyue
 * DateTime: 15-5-9下午6:43
 */
object CoalesceTest {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    val sc = new SparkContext("local", "Coalesce Test")

    //val y = sc.parallelize(1 to 10, 5)
    val y = sc.parallelize(List(1, 2, 3, 4, 5, 2, 5, 8, 3, 10), 5)
    // y.foreachWith(i => i)((x, i) => println("[yPartitionIndex " + i + "] " + x))


    y.foreachWith(i => i)((x, i) => println("[yPartitionIndex " + i + "] " + x))
    //      [yPartitionIndex 0] 2
    //      [yPartitionIndex 1] 3
    //      [yPartitionIndex 1] 4
    //      [yPartitionIndex 2] 5
    //      [yPartitionIndex 2] 2
    //      [yPartitionIndex 3] 5
    //      [yPartitionIndex 3] 8
    //      [yPartitionIndex 4] 3
    //      [yPartitionIndex 4] 10

    val z = y.coalesce(10, false)
    z.foreachWith(i => i)((x, i) => println("[zPartitionIndex " + i + "] " + x))

//      [zPartitionIndex 0] 2
//      [zPartitionIndex 1] 3
//      [zPartitionIndex 1] 4
//      [zPartitionIndex 2] 5
//      [zPartitionIndex 2] 2
//      [zPartitionIndex 3] 5
//      [zPartitionIndex 3] 8
//      [zPartitionIndex 4] 3
//      [zPartitionIndex 4] 10

    val x = y.coalesce(10, true)
    x.foreachWith(i => i)((x, i) => println("[xPartitionIndex " + i + "] " + x))
//      [zPartitionIndex 0] 2
//      [zPartitionIndex 1] 1
//      [zPartitionIndex 2] 2
//      [zPartitionIndex 3] 3
//      [zPartitionIndex 4] 10
//      [zPartitionIndex 5] 5
//      [zPartitionIndex 6] 3
//      [zPartitionIndex 6] 8
//      [zPartitionIndex 7] 4
//      [zPartitionIndex 9] 5
    println(z.toDebugString)
  }
}
