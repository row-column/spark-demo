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
 * Time: 18:13
 */
class Implicit {

}
class Test{

}
class RunTest(test:Test){
  def run: Unit ={
    println("RunTest-->test = " + test)
  }
}
object Implicit extends App{
implicit def test2RunTest(test:Test) = new RunTest(test)
val test  = new Test
test.run
def testParam(implicit name:String): Unit ={
  println("name = " + name)
}
  implicit val name = "Implicited..."
 testParam
 testParam("show...")
  implicit  class Calc(x:Int){
    def add(a:Int):Int = a+x
  }
  println("1.add(2) = " +Calc(1).add(2))
}

