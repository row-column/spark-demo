package com.spark.lession.lession01

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
 * Module Desc:
 * 隐式转换有3种:
 * 1 隐式参数;
 * 2 参数隐式转换;
 * 3 调用者隐式转换;
 * 下面用程序分别说明:
 * User: wangyue
 * DateTime: 15-7-28上午11:35
 */
/******隐式参数*******/

/******隐式参数*******/
class Answer3_2_1{

}

object Answer3_2_1{
  def testImplicit(a: String)(implicit b: Int): Unit = {println(a + b)}

  implicit val a : Int = 2

  def main(args: Array[String]) {
    testImplicit("Not implicit call:")(1)
    testImplicit("Implicit call:")
  }

}


/******参数隐式转换*******/
object Int2String{
  implicit def int2String(i: Int): String = {println("Called."); return i.toString()}
}

class Answer3_2_2{

}

object Answer3_2_2{
  def testImplicit(a: String){println("Can convert int to string implicit:" + a)}

  import Int2String._

  def main(args: Array[String]) {
    testImplicit(1)

  }

}

/******调用者隐式转换*******/
class A1{
}

object A1 {
  println("Generated A!")
  implicit def convertA2B(a : A1): B1 = {return new B1()}

}

class B1{
  def func(){println{"Called B1 function!"}}
}

object B1{

}

class Answer3_2_3{

}

object Answer3_2_3{
  import A1._
  def main(args: Array[String]) {
    val a = new A1()
    a.func()
  }
}
