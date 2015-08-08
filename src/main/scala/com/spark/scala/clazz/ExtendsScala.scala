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
 * Date: 2015/1/12
 * Time: 17:17
 */
class ExtendsScala {

}
class Person(var name:String, val age:Int){
  println("Person-->name = " + name)
  println("Person-->age = " + age)
  val sex = "male"
}
class Student(name:String, age:Int,val major:String) extends Person(name,age){
  println("Student-->major = " + major)
  override def toString()="override toString() method....."
  override val sex = "中性"
}

object ExtendsScala{
  def main(args: Array[String]) {
    val s = new Student("summer",21,"社会学")
    print(s.toString())
    print(s.sex)
  }
}
