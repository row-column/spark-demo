package com.spark.lession.lession02

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
  * Module Desc:RDD是一个范型类，在一段程序中大量出现RDD[(String, String, String, String, String)]这个类，换一种更简洁的写法替代它
  * User: wangyue
  * DateTime: 15-7-22下午8:42
  */

class RDD[T](v: T){
  v.toString
}

object Answer2_4 {
   def main(args: Array[String]): Unit = {

     type  myRDD = RDD[(String,String,String,String)]

     //举例实验
     val  rdd = new myRDD("12","23","34","45")
     println("rdd = " + rdd)

   }

 }
