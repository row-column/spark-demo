package com.spark.ml

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.log4j.Logger
import org.apache.log4j.Level
/**
 * 用K­Means聚类来计算三个的士乘客下车地点的经纬度。样本数据 集包括的士行程的hack执照,
 * medallion,接乘客的日期时间,乘客下车的日期时间,
 * 乘客信息等 等。你可以从这些信息中来推测哪里是搭乘的士的最佳地点。
 */
object NycTaxiSubKMeans {
  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val sparkConf = new SparkConf().setAppName("NycTaxiSubKMeans").setMaster("local[*]")

    val sc  = new SparkContext(sparkConf)

    val nycTaxiSubFile = sc.textFile("hdfs://master:8020/data/bigdatauniversity/nyctaxisub/nyctaxisub.csv")

    //查看nycTaxiSubFile的行数
    nycTaxiSubFile.count()

    //清理数据

    //第一个过滤函数仅仅保留了2013年的记录,它还会删除文件的header。第三栏和第四栏分别是乘 客下车地点的经纬度。如果这些值为空的时候这个transformation会抛出exception

    val nycTaxiSubData =  nycTaxiSubFile.filter(_.contains("2013")).
      filter(_.split(",")(3) != "").filter(_.split((","))(4) != "")


    //进行另一个count()查看多少记录被删除了
    nycTaxiSubData.count

    //将区域范围缩小到纽约市

    val nycTaxiSubFenceData = nycTaxiSubData.filter(_.split(",")(3).toDouble>40.70).
      filter(_.split(",")(3).toDouble < 40.86).
      filter(_.split(",")(4).toDouble > (-74.02)).
      filter(_.split(",")(4).toDouble < (-73.93))

    //查看nycTaxiSubFenceData剩余的行数:

    nycTaxiSubFenceData.count()

    //用经纬度创建Vectors,它们将被用做K­Means算法的输入数据
    val nycTaxiSubFenceDataVetors = nycTaxiSubFenceData.map(
      line => Vectors.dense(line.split(",").slice(3,5).map(_.toDouble))
    )


    //运行K­Means 算法
    val iterationCount = 10

    val clusterCount = 3

    val model = KMeans.train(nycTaxiSubFenceDataVetors,clusterCount,iterationCount)

    val clusterCenters = model.clusterCenters.map(_.toArray)

    val cost = model.computeCost(nycTaxiSubFenceDataVetors)

    println("cost = " + cost)

    clusterCenters.foreach(lines => println(lines(0),lines(1)))
//    cost = 63.256031419293166
//    (40.725201669312646,-73.9957521872609)
//    (40.75737597200619,-73.980516556164)
//    (40.78759486864085,-73.95685339403191)

//    意料之中,第二个点位于Theater 区和Grand Central之间。第三点在
//    Village NYU Soho和Little Italy区域。第一点在上东区,那里的人应该更倾向于搭乘的士而不是地铁.

    sc.stop()

  }

}
