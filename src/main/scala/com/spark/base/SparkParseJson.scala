package com.spark.base

import org.apache.spark.{SparkConf, SparkContext}

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
 * Module Desc:SparkParseJson
 * User: wangyue
 * DateTime: 15-4-29下午6:50
 */
object SparkParseJson {
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SparkParseJson").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val input1 = sc.parallelize(List( """{"name":"张三","sex":"male"}""",
      """{"name":"lisi"}"""))
    //val parsed1 = input1.map(Json.parse)
    //parsed1.collect

    val input2 = sc.parallelize(List( """{"name":"过往记忆","website":"www.iteblog.com"}""",
      """{"name":"过往记忆"}"""))
    //val parsed2 = input2.map(Json.parse)

    case class Info(name: String, website: String) {
      override def toString: String = name + "\t" + website
    }

    /*val result2 = parsed2.flatMap(record => personReads.reads(record).asOpt)
    result2.collect*/

    val data = sc.parallelize(List(Info("过往记忆", "www.iteblog.com")))
    //data.map(Json.toJson(_)).collect.foreach(println)

  }

}
