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
 * Module Desc:RepartitionTest2
 * User: wangyue
 * DateTime: 15-5-9下午4:31
 */
object RepartitionTest2 {
  def main(args: Array[String]) {
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val sc = new SparkContext("local","RepartitionTest2")

    val data = Array[(Int, Char)]((3, 'a'), (2, 'b'), (1, 'c'), (4, 'd'))

    val pairs1 = sc.parallelize(data,3)

    pairs1.foreachWith(i=>i)((x,i)=>println("[pairs1-Index " + i + "] " + x))
//    output
//      [pairs1-Index 0] (3,a)
//      [pairs1-Index 1] (2,b)
//      [pairs1-Index 2] (1,c)
//      [pairs1-Index 2] (4,d)

    val pairs2 = pairs1.partitionBy(new HashPartitioner(2))
    pairs2.foreachWith(i => i)((x,i)=>println("[pairs1-Index " + i + "] " + x))
//    output
//      [pairs1-Index 0] (2,b)
//      [pairs1-Index 0] (4,d)
//      [pairs1-Index 1] (3,a)
//      [pairs1-Index 1] (1,c)

    val y = sc.parallelize(1 to 100, 5)

    //y.foreach(println)

    val z = y.repartition(2)

    val r = z.takeOrdered(7)
    z.foreachWith(i=>i)((x,i)=>println("[pairs-Index"+i+"]"+x))
    r.foreach(println)

  }

}
