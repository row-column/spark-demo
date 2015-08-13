package com.spark.sparksql

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

/**
 * 使用SQL来寻找一段时间内纽约市的平均天 气和降水量
 * Created by wangyue on 15/8/9.
 */

case class Weather(date: String, temp: Int, precipitation: Double)
object NycWeather {
  def main(args: Array[String]) {

    val sparkConf  = new SparkConf().setAppName("nycWeatherSparkSql").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    val sqlContext = new SQLContext(sc)
    //导入以下类来自动将RDD转换成一个DataFrame
    import sqlContext.implicits._

    // 数据集中有三栏,日期,平均摄氏温度,日降水量。因为我们已经知道了schema。我们就用 reflection引用schema
    val nycWeatherDF = sc.textFile("hdfs://master:8020/data/bigdatauniversity/nycweather/nycweather.csv")
      .map(_.split(",")).map(w => Weather(w(0),w(1).trim().toInt,w(2).trim.toDouble)).toDF()

    //把这个RDD注册成一张表
    nycWeatherDF.registerTempTable("weather")

    //你想要返回有降水的最热的那些天的日期
    val hottestWithPrecipitation = sqlContext.sql("select * from weather where precipitation > 0.0 order by temp desc ")


    //将有降水的最热的一天打印到控制台:
    hottestWithPrecipitation.map(x => ("Date: " + x(0), "Temp : " + x(1), "Precip: " + x(2)))
      .top(10).foreach(println)


    sc.stop()

  }

}
