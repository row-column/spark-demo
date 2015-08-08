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
 * Time: 14:55
 */
class OOPInScala {

}

/*class User{
  var name:String = _ //"_"是代表PlaceHolder
  var age = 18
  private[this] val gender = "中性"
}*/

class User(val name:String,val age:Int){
  println(" this is User class primary constructor! ")
  var gender:String = _
  def this(name:String, age:Int, gender:String){
    this(name,age)
    this.gender = gender
    println(" this is User class secondary constructor! ")
  }
}
object OOPInScala{
  def main(args: Array[String]) {
   /* val user = new User
    user.name = "summer"*/
    val user = new User("summer",18,"male");
    println("user.name = " + user.name)
    println("user.age = " + user.age)
    //println("user.gender = " + user.gender)
  }
}


