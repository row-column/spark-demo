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
 * Module Desc:介绍trait
 * User: wangyue
 * DateTime: 15-7-16下午12:51
 */
class IntroduceTrait {

//  trait类似于Java8中的可用带default method的接口。
//  trait中可以带有实现的方法，也可以带有抽象方法，使用trait的方法是with而混入类中。

}

//定义一个特质类 Logger
trait Logger{
  def log(msg:String): Unit ={
    println("msg = " + msg)
  }
}

trait TraitLogger{
  def log(msg:String)
}

//这里使用trait是使用extends，这是因为MyLogger没有继承其他类trait
//子trait可以覆盖实现父trait的方法：
trait ConsoleLogger extends TraitLogger{
  def log(msg:String): Unit ={
    println("ConsoleLogger-->> msg = " + msg)
  }
}
class TestConsoleLogger extends ConsoleLogger{
  def test(): Unit ={
    log("TestConsoleLogger-->>test()...")
  }
}
//定义MyLogger类 实现特质类Logger
class MyLogger extends Logger{
  def log: Unit ={
    log("myLogger....");
  }
}

trait ContentLogger {
  def log(msg:String): Unit ={
    println("Content Logger -->>msg = " + msg)
  }
}

trait  MsgLogger extends ConsoleLogger{
  override def log(msg:String): Unit ={
    println("Msg Logger -->>msg = " + msg)
  }
}

abstract class Account{
  def save
}
class MyAccount extends Account with MsgLogger{
  def save: Unit = {
    log("10000000")
  }
}
object  IntroduceTrait extends App{
  val myLogger = new MyLogger
  myLogger.log
  val testConsoleLogger = new TestConsoleLogger
  testConsoleLogger.test()
  val myAccount  = new MyAccount
  myAccount.save
}