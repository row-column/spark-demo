package com.spark.lession.lession03

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

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
 * Module Desc:一个电商网站，商品信息存储在mysql中，用户浏览信息在hdfs上，试着制造符合这种场景的数据，使用spark读取它们并且join
 * User: wangyue
 * DateTime: 15-7-30下午8:22
 */
class Answer2_1 {

}

object Answer2_1 {
  def main(args: Array[String]) {
    val sc = new SparkContext("local", "Answer2_1")

//    读取mysql中商品信息 开始
    //方法1：
 /*  val commodityJdbcRDD = new JdbcRDD(
      sc,
      () => {
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "hadoop", "hadoop")
      },
      "SELECT id,name,price,mem FROM t_commodity  limit ?,?", 1, 100, 3,
      r => (r.getString(1), r.getString(2), r.getString(3),r.getString(4))).map(x=>(x._2,x._1))

    //print(commodityJdbcRDD.toDebugString)

    commodityJdbcRDD.foreach(println(_))*/

    // 方法2：
    val sqlContext =  new SQLContext(sc)
    val jdbcDF = sqlContext.load("jdbc", Map(
      "url" -> "jdbc:mysql://localhost:3306/test?user=hadoop&password=hadoop",
      "dbtable" -> "t_commodity",
      "numPartitions"->"2"))
    val commodityJdbcRDD = jdbcDF.map(x=>(x(0).toString(),x(1).toString))
    commodityJdbcRDD.foreach(println(_))

    //sqLContext.sql()
    // 读取mysql中商品信息 结束

    // 读取hdfs用户浏览信息 开始
    val userDataRDD= sc.textFile("hdfs://master:8020/data/spark/user-history-data").map(_.split("\t")).map(x=>(x(1),x(0)))
    userDataRDD.foreach(println(_))
    // 读取hdfs用户浏览信息 结束

    //join action
    val userCommodityJoinResult = userDataRDD.join(commodityJdbcRDD)
    userCommodityJoinResult.foreach(println(_))
//    (4,(zhangsan,iphone6))
//    (4,(lisi,iphone6))
//    (4,(wangwu,iphone6))
//    (5,(zhangsan,iphone6plus))
//    (5,(lisi,iphone6plus))
//    (5,(wangwu,iphone6plus))
//    (2,(zhangsan,iphone4s))
//    (2,(lisi,iphone4s))
//    (2,(wangwu,iphone4s))
//    (3,(zhangsan,iphone5))
//    (3,(lisi,iphone5))
//    (3,(wangwu,iphone5))
//    (1,(zhangsan,iphone4))
//    (1,(lisi,iphone4))
//    (1,(wangwu,iphone4))
    userCommodityJoinResult.saveAsTextFile("hdfs://master:8020/data/spark/user-commodity-join-result")

    sc.stop()
  }
}

//mysql:
//create table t_commodity(id int primary key auto_increment,name varchar(20),price float,mem varchar(100))
//insert into t_commodity(name,price,mem)values('iphone4',1999,'iphone4-1999');
//insert into t_commodity(name,price,mem)values('iphone4s',2999,'iphone4s-1999');
//insert into t_commodity(name,price,mem)values('iphone5',3999,'iphone4-3999');
//insert into t_commodity(name,price,mem)values('iphone6',4999,'iphone6-4999');
//insert into t_commodity(name,price,mem)values('iphone6plus',1999,'iphone6plus-5999');
//select * from t_commodity;

//hdfs:
//user-history-data
//用户账号  商品ID 浏览时间 ip
//zhangsan	1	2015-07-30 20:01:01	127.0.0.1
//zhangsan	2	2015-07-30 20:01:01	127.0.0.1
//zhangsan	3	2015-07-30 20:01:01	127.0.0.1
//zhangsan	4	2015-07-31 20:01:01	127.0.0.1
//zhangsan	5	2015-07-31 20:21:01	127.0.0.1
//lisi	1	2015-07-30 21:01:01	127.0.0.1
//lisi	2	2015-07-30 22:01:01	127.0.0.1
//lisi	3	2015-07-31 23:31:01	127.0.0.1
//lisi	4	2015-07-31 22:21:01	127.0.0.1
//lisi	5	2015-07-31 23:11:01	127.0.0.1
//wangwu	1	2015-07-30 21:01:01	127.0.0.1
//wangwu	2	2015-07-30 22:01:01	127.0.0.1
//wangwu	3	2015-07-31 23:31:01	127.0.0.1
//wangwu	4	2015-07-31 22:21:01	127.0.0.1
//wangwu	5	2015-07-31 23:11:01	127.0.0.1