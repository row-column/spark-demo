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
  * Module Desc:将Array(1, 2, 3)的元素按顺序给x, y, z赋值，让x == 1, y == 2, z == 3，写出实现过程
  * User: wangyue
  * DateTime: 15-7-22下午8:42
  */

object Answer1_1 {
   def main(args: Array[String]): Unit = {

     val array = Array(1,2,3)

     // 方法1
     //val x = array(0)
     //val y = array(1)
     //val z = array(2)

     //方法2
     // val x = array.head
     // val y = array.slice(1,2).head
     // val y1 = array.tail.head
     // val z = array.last

     // 方法3

     //val x = array.take(1).head
     //val y = array.take(2).last
     //val z = array.take(3).last

     //方法4
     array.toList match {
       case List(x,y,z) => println("x,y,z = " + x,y,z)
       case _ => println("can not match ")
     }

     // 方法5
     val size = array.size
     for(i <- 0 to size-1){
       if(1==array(i)) {
         val x = array(i)
         println("x = " + x)
       } else if (2 == array(i)){
         val y = array(i)
         println("y = " + y)
       } else if (3 == array(i)){
         val z = array(i)
         println("z = " + z)
       }

     }

     //方法6
     for(i <- 0 to array.size -1 if 1==array(i)){
       val x =array(i)
     }
     for(i <- 0 to array.size -1 if 2==array(i)){
       val y =array(i)
     }
     for(i <- 0 to array.size -1 if 3==array(i)){
       val z =array(i)
     }

   }

 }
