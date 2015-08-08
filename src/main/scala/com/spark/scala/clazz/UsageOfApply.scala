package com.spark.scala.clazz

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
 * Date: 2015/1/13
 * Time: 14:41
 */
class UsageOfApply {

}
class ApplyTest{
  def apply() = "This apply() is in class"
  def test: Unit ={
    println("ApplyTest-->>test.....")
  }
}
object ApplyTest{
  var count  = 0
  def apply() = new ApplyTest
  def static: Unit ={
    println("ApplyTest-->>static.....")
  }
  def increment = {
    count  = count +1
  }

}
object UsageOfApply extends App{
  //ApplyTest.static
 /* val applyTest = new ApplyTest()
  applyTest.test
  println("applyTest() = " + applyTest())*/
  for(i <- 1 to 100){
    ApplyTest.increment
  }
  println("ApplyTest.count = " + ApplyTest.count)
}
