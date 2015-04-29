package com.spark.db

import java.sql.{DriverManager, PreparedStatement, Connection}

import org.apache.spark.{SparkContext, SparkConf}


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
 * Module Desc:Rdd to mysql
 * User: wangyue
 * DateTime: 15-4-29下午8:11
 */
object SparkOptMysql2 {

  def myFun(iterator: Iterator[(String,String,Int,String)]): Unit = {
    var conn: Connection = null
    var ps: PreparedStatement = null
    val sql = "insert into t_user(name,sex,age,mem) values (?, ?,?,?)"
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","hadoop", "hadoop")
      iterator.foreach(data => {
        ps = conn.prepareStatement(sql)
        ps.setString(1, data._1)
        ps.setString(2, data._2)
        ps.setInt(3, data._3)
        ps.setString(4, data._4)
        ps.executeUpdate()
      }
      )
    } catch {
      case e: Exception => println("Mysql Exception")
    } finally {
      if (ps != null) {
        ps.close()
      }
      if (conn != null) {
        conn.close()
      }
    }
  }
  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("SparkOptMysql2").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(List(("rddToMysql1","M",21,"rddToMysql1"), ("rddToMysql2","M",21,"rddToMysql2")))
    data.foreachPartition(myFun) //其实是通过foreachPartition遍历RDD的每个分区，并调用普通的Scala方法来写数据库。
  }
}

/*
需要注意的是：
　　1、foreachPartition 函数来遍历RDD，并且在每台Work上面创建数据库的connection。
　　2、如果你的数据库并发受限，可以通过控制数据的分区来减少并发。
　　3、在插入Mysql的时候最好使用批量插入。
　　4、确保你写入数据库过程能够处理失败，因为你插入数据库的过程可能会经过网络，这可能会导致数据插入到数据库失败。
　　5、不建议将你的RDD数据写入到Mysql等关系型数据库中。
*/
