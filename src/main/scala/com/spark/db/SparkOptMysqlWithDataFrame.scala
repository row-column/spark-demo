package com.spark.db

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

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
 * Module Desc:SparkOptMysqlWithDataFrame
 * User: wangyue
 * DateTime: 15-4-29下午8:24
 */
object SparkOptMysqlWithDataFrame {
  def main(args: Array[String]): Unit = {

    val url = "jdbc:mysql://localhost:3306/test?user=hadoop&password=hadoop"
    val conf = new SparkConf().setMaster("local[2]").setAppName("SparkOptMysqlWithDataFrame")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    val schema = StructType(
      StructField("name", StringType) ::
        StructField("sex", StringType) ::
        StructField("age", IntegerType) :: Nil)

    val data = sc.parallelize(List(("test001", "M",30), ("test002","F", 29),
      ("test003","F", 40), ("test004","M", 33), ("test005", "F",23))).
      map(item => Row.apply(item._1, item._2,item._3))

    val df = sqlContext.createDataFrame(data, schema)
    //df.createJDBCTable(url, "t_user_new", false)//table是表的名字，最后一个参数是如果表存在是否删除表的意思，false代表不删除。
    df.insertIntoJDBC(url, "t_user_new", false)  //调用该函数必须保证表事先存在，它只用于插入数据, 前面两个参数和createJDBCTable一致，第三个参数如果设置为true，则在插入数据之前会调用mysql的TRUNCATE TABLE语句先清掉表中的数据。
    sc.stop
  }

}

/*
Spark 1.3.0中对数据库写操作是通过DataFrame类实现的，这个类也是新增的，是将之前的SchemaRDD重命名之后又定义了一些新方法的类。
我们需要通过SQLContext来构造DataFrame对象，在SQLContext类中提供了大量可以构造DataFrame对象的方法，感兴趣的可以去看下。
函数原型如下：
def createDataFrame(rowRDD: RDD[Row], schema: StructType): DataFrame
接收的RDD是Row类型的，他代表的是one row of output from a relational operator。第二个参数就是我们需要写入表的结构，包括了表的字段名和对应的类型

 bin/spark-submit --master local[2]
    --jars lib/mysql-connector-java-5.1.35.jar
    --class  spark.sparkToJDBC ./spark-test_2.10-1.0.jar

    bin/spark-submit --master local[2]
	--driver-class-path lib/mysql-connector-java-5.1.35.jar
	--class  spark.SparkToJDBC ./spark-test_2.10-1.0.jar

	其实，我们还可以在spark安装包的conf/spark-env.sh通过配置SPARK_CLASSPATH来设置driver的环境变量，如下：
export SPARK_CLASSPATH=$SPARK_CLASSPATH:/lib/com/mysql-connector-java-5.1.35.jar
　　这样也可以解决上面出现的异常。但是，我们不能同时在conf/spark-env.sh里面配置SPARK_CLASSPATH和提交作业加上–driver-class-path参数，否则会出现以下异常：

*/
