package com.spark.lession.lession02

import scala.io.Source

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
 * Module Desc:试举例lazy的用法
 * User: wangyue
 * DateTime: 15-7-22下午8:42
 */

object Answer3_1 {
  def main(args: Array[String]): Unit = {
//    懒加载是在使用的时候加载，但在申明的时候不加载，变量和常量 在第一次使用的使用，会被实例化
//    消耗内存，CPU比较多的情况，可以考虑使用lazy懒加载
//    demo1
    lazy val file = Source.fromFile("/home/wy/wy90147") //file 没有被实例化，当第一次被使用的时候将会实例化，
    // 其实本地/home/wy/wy90147，没有这个文件的
    println("foreach beginning... " ) //这句话会成功打印的，不会报错
    for(line <- file.getLines()) println("line = " + line) //实例化file对象，报错文件找不到,
    // "java.io.FileNotFoundException: /home/wy/wy90147 (没有那个文件或目录)"


  }

}
