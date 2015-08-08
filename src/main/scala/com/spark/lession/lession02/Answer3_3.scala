package com.spark.lession.lession02

import scala.util.parsing.json.JSONObject
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
 * Module Desc:将Map("1" -> Map("2" -> 3)) 转化为正确的json
 * User: wangyue
 * DateTime: 15-7-22下午8:42
 */

object Answer3_3 {
  def main(args: Array[String]): Unit = {

    val map = Map("1" -> Map("2" -> 3))

    //方法1， scala内置的JSONObject
    val mapToJson1 = JSONObject(map)
    println("mapToJson1 = " + mapToJson1) // {"1" : Map(2 -> 3)} ,不是我所期望的

    //方法2， spray-json,需要增加spray-json_2.10-1.3.2.jar
    import spray.json._
    import DefaultJsonProtocol._
    println("mapToJson1 = " + println(map.toJson) //{"1":{"2":3}}
    )

  }

}
