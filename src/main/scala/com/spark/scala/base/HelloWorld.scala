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
 * Time: 15:10
 */
class HelloWorld {

}

object HelloWorld{
  /*第一种方式定义方法(有返回值)*/
  def returnHelloStr(str:String):String = {
    "Hello"+str
  }
  /*第二种方式定义方法(直接打印)*/
  def printlnHelloStr(str:String){
    println("Hello"+str)
  }
  /*第三种方式定义方法(直接打印)*/
  def printlnHelloStr(){
    println("Hello Scala.....")
  }
 /*第四种方式定义方法(匿名函数)*/
  def mutiply = (x:Int,y:Int)=> x * y
  val mutiply2 = (x:Int,y:Int)=> x * y
  /*第五种方式定义方法(柯力化)*/
  def  sum (x:Int)(y:Int) = x+y
  /*第六种方式定义方法(可变参数)*/
  def  variableParameter(str:String*) = {
    str.foreach(s => print(s))
  }
  /*第七种方式定义方法(默认参数)*/
  def returnDefaultValue(value:String ="this is defalut value"):String = {
    "default value:"+value
  }

  def main(args: Array[String]) {
    //println("Hello Scala World ....")
    //println(returnHelloStr("Scala"))
   // printlnHelloStr("Scala ......")
    //printlnHelloStr
    //println(mutiply2(521,520))
   // println(sum(521)(520))
    //variableParameter("Life "," is "," short, ","U ","need ","spark");
    println(returnDefaultValue());
  }
}
