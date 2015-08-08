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
 * Module Desc:用四种不同的方式输出hello world
 * User: wangyue
 * DateTime: 15-7-16上午11:35
 */
class OutputHelloWorld {

}
object OutputHelloWorld{
  def main(args: Array[String]) {
//    方法1：
    print("方法1：")
    println("hello world")

//    方法2
    print("方法2：")
    val hello = "hello"
    val world ="world"
    println(hello+" "+world)

//    方法3
    print("方法3：")
    println( s"""$hello $world""")
  }

//  方法4 ,利用特质实现
  trait HelloWorld {
    def helloWorld: Unit = {
      println("hello world")
    }
  }
  print("方法4：")
  (new Object with HelloWorld).helloWorld

//  方法5，利用半生对象上下水你
  class HelloWorld3 {
    def apply(): Unit ={
      println("hello world")
    }
  }
  print("方法5：")
  new HelloWorld3()()

//  方法6
class HelloWorld6 {
  def helloWorld(s: String): Unit = {
    println(s)
  }
}
  print("方法6：")
  val helloWorld = "hello world"
  new HelloWorld6().helloWorld(helloWorld)

}
