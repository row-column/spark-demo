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
 * User: wangyue
 * DateTime: 15-7-16下午1:04
 */
class ScalaEqual {

}

object ScalaEqual{

  def main (args: Array[String] ) {
//    scala中"==",看两个对象或者数据是否相等,对所有对象都适用,而不仅仅是基本数据类型
//    集合对象
    println(List(1, 2, 3) == List(1, 2, 3)) //true
    println(List(1, 2, 3) == List(4, 5, 6))//false

    //不同类型的两个对象
    println(1 ==  1.0) //true
    println(List(1, 2, 3) == "hello")//false

//    比较null，或任何可能是null 的东西。不会有任何异常被抛出：
    println( List(1, 2, 3) == null) //false

//  scala中equals 方法是基于内容比较的。
// 例如，以下是恰好都有五个同样字母的两个字串的比较：
    println(( "he" + "llo") == "hello") //true

//    scala 的==与Java的有何差别
//    java 里的既可以比较基本类型也可以比较引用类型。
//    对于基本类型，java的==比较
//      值比较与Scala很类似。然而对于引用类型，Java的==比较了引用的是否为同一个对象（比较内存地址），也就是说这两个变量是否都指向于JVM 堆里的同一个对象。
//    scala也提供了这种机制，名字是eq.不过eq和它的反义词，ne，仅仅应用于 可以直接映射到Java的对象。
    class A {}
    val a = new A
    val b = new A
    println(a == b) //false
    println(a eq b) //false
  }
}



