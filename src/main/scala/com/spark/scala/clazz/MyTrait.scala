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
 * Time: 18:19
 */
class MyTrait {

}
trait Logger{
  def log(msg:String): Unit ={
    println("msg = " + msg)
  }
}

trait TraitLogger{
  def log(msg:String)
}
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
object  MyTrait extends App{
 val myLogger = new MyLogger
  myLogger.log
  val testConsoleLogger = new TestConsoleLogger
  testConsoleLogger.test()
  val myAccount  = new MyAccount
  myAccount.save
}
