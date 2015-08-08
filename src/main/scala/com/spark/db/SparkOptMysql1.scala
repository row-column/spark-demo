package com.spark.db

import org.apache.spark.SparkContext
import org.apache.spark.rdd.JdbcRDD

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
 * Module Desc:SparkOptMysql1
 * User: wangyue
 * DateTime: 15-4-29下午7:33
 */
object SparkOptMysql1 {
  def main(args: Array[String]) {

    val sc = new SparkContext("local", "SparkOptMysql1")
    val rdd = new JdbcRDD(
      sc,
      () => {
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "hadoop", "hadoop")
      },
      "SELECT name,sex,age FROM t_user WHERE ID >= ? AND ID <= ?", 1, 100, 3,
      r => (r.getString(1), r.getString(2), r.getString(3))).cache()

    print(rdd.toDebugString)

    rdd.foreach(println(_))
    sc.stop()
  }

}

/*

在Spark中提供了一个JdbcRDD类，该RDD就是读取JDBC中的数据并转换成RDD，之后我们就可以对该RDD进行各种的操作。我们先看看该类的构造函数：

JdbcRDD[T: ClassTag](sc: SparkContext,
getConnection: () => Connection,sql: String,lowerBound: Long,upperBound: Long,numPartitions: Int,
mapRow: (ResultSet) => T = JdbcRDD.resultSetToObjectArray _)
　　
这个类带了很多参数，关于这个函数的各个参数的含义，我觉得直接看英文就可以很好的理解，如下：
@param getConnection a function that returns an open Connection.
The RDD takes care of closing the connection.
@param sql the text of the query.
The query must contain two ? placeholders for parameters used to partition the results.
E.g. “select title, author from books where ? < = id and id <= ?”
@param lowerBound the minimum value of the first placeholder
@param upperBound the maximum value of the second placeholder
The lower and upper bounds are inclusive.
@param numPartitions the number of partitions.
Given a lowerBound of 1, an upperBound of 20, and a numPartitions of 2,
the query would be executed twice, once with (1, 10) and once with (11, 20)
@param mapRow a function from a ResultSet to a single row of the desired result type(s).
This should only call getInt, getString, etc; the RDD takes care of calling next.
The default maps a ResultSet to an array of Object.
　　上面英文看不懂？？那好吧，我给你翻译一下。
　　1、getConnection 返回一个已经打开的结构化数据库连接，JdbcRDD会自动维护关闭。
　　2、sql 是查询语句，此查询语句必须包含两处占位符?来作为分割数据库ResulSet的参数，例如：”select title, author from books where ? < = id and id <= ?”
　　3、lowerBound, upperBound, numPartitions 分别为第一、第二占位符，partition的个数。例如，给出lowebound 1，upperbound 20， numpartitions 2，则查询分别为(1, 10)与(11, 20)
　　4、mapRow 是转换函数，将返回的ResultSet转成RDD需用的单行数据，此处可以选择Array或其他，也可以是自定义的case class。默认的是将ResultSet 转换成一个Object数组。

mysql:
create database test;
use test;
create table t_user(id int primary key auto_increment,name varchar(20),sex char(2),age int,mem varchar(100))ENGINE=InnoDB DEFAULT CHARSET=utf-8;
insert into t_user(name,sex,age,mem)values('zhangsan','F',21,'mem');
insert into t_user(name,sex,age,mem)values('lisi','F',22,'mem');
insert into t_user(name,sex,age,mem)values('wangwu','F',23,'mem');
insert into t_user(name,sex,age,mem)values('lilei','F',16,'mem');
insert into t_user(name,sex,age,mem)values('hanmeimei','F',16,'mem');



*/
