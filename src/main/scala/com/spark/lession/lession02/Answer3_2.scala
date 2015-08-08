package com.spark.lession.lession02

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
 * Module Desc:如何让一个字符串，例如"cool", 不eq "cool", scala中的symbol是什么
 * User: wangyue
 * DateTime: 15-7-22下午8:42
 */

object Answer3_2 {
  def main(args: Array[String]): Unit = {

//    方法1

    val str1 ="cool"
    val str2 = new String("cool")

    println(str1 eq str2) //fasle

//  方法2

    val str3 = 'cool
    println(str3.name eq str2) //fasle

//    方法3

    implicit def eq(str1:String,str2:String):Boolean = false
    val str4 = 'cool
    println(str3.name eq str4.name) //fasle


//    scala中的symbol是什么？
    // 1.Symbol 是Scala中所谓literal类型的一种，literal字面意思为文本，表现为可以直接在代码中写为常量值的东西，
    // 官方guide里面又细分为整数文本、字符文本、布尔文本，符号文本等
    // 2.Symbol 本质上是字符串的再封装，相同名字（准确的说是值）的Symbol具有相同的instance，
    // 而相同名字（也是值）的字符串并不一定是同一个instance
    // 3.如某字符串反复出现，而且值不会改变，那么，可以使用Symbol，但其实并不必须，同样可以使用字符串的变量名称。
    // val s ="abc" println(s) println('abc) f2('abc) f2(s)

    // 4.Scala中需要使用Symbol的地方应该不多，源于Scala是静态检查类型语言，不同于动态语言，直接使用值，临时定义变量。
   // 换言之，可以理解为static的字符串，与final无关。

    //Symbol类型的主要特点
    // a. 节省内存
    // 在scala中，Symbol类型的对象是被拘禁的，任意的同名符号字面量或者Symbol对象都指向同一个Symbol对象，
    // 避免了因冗余而造成的内存开销。
    // b. 快速比较
    // 由于Symbol类型的对象是被拘禁的，任意的同名符号字面量或Symbol对象都指向同一个Symbol对象，
    // 而不同名的符号字面量或Symbol对象一定指向不同的Symbol对象，
    // 所以Symbol对象之间可以使用操作符：== 快速地进行相等性比较。而字符串的equals方法需要逐个字符比较两个字符串，
    // 执行时间取决于两个字符串的长度，速度很慢。


    // Symbol类型一般用于快速比较，例如用于Map类型：Map<Symbol,Data>，根据一个Symbol对象，
    // 可以快速查询相应的Data，而Map<String,Data>的查询效率则低很多。
  }

}
