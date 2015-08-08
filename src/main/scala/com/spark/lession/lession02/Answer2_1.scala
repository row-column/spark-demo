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
  * Module Desc:在scala中，使用java的collection，比如hashmap，并在这个collection对象上调用foreach方法
  * User: wangyue
  * DateTime: 15-7-22下午8:42
  */

object Answer2_1 {
   def main(args: Array[String]): Unit = {

     import java.util.{HashMap => JHashMap}

     val javaMap = new JHashMap[String,String]()
     javaMap.put("1","a")
     javaMap.put("2","b")
     for(key <- javaMap.keySet().toArray){
       println(key+":"+javaMap.get(key))
     }

     // 方法1：隐式转换
    /* implicit def jMapToSMap(javaMap:JHashMap[String,String]):HashMap[String,String] = {

       val tempHashMap = new HashMap[String,String]()
      /* for(JHashMap.Entry[String,String] entry : javaMap.entrySet()){

       }*/
     }
     javaMap.foreach(e => {
       val (k,v) = e
       println(k+":"+v)
     })*/

     // 方法2：scala JavaConversions
     /* Convert from java List to JListWrapper type implicitly. */
    /* javaMap.foreach(e => {
       val (k,v) = e
       println(k+":"+v)
      })*/
   }

 }
