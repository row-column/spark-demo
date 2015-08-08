package com.spark.scala.base

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
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * Module Desc:study-scala
 * User: wangyue-ds6 || stark_summer@qq.com
 * Date: 2015/1/9
 * Time: 18:03
 */
class ExpressionDemo {

}
object ExpressionDemo{
  def main(args: Array[String]) {
    val isStop = 1;
    val result = if(1==isStop) true else false
    println("result = " + result)

    var (n,m) = (100,0)
    while(n > 0){
      m = m + n
      n = n -1
    }
    println("m = " + m)

    for(i <- 1 to 10){ // 1 to 10
      print(i+" ")
    }
    println()
    for(i <- 1 until 10){// 1 until 10
      print(i+" ")
    }
    for(i <- 1 to 100 if i%2 == 0){ // 1 to 100
      print(i+" ")
    }
  }
}
