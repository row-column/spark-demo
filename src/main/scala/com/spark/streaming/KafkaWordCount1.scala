package com.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.apache.spark.streaming.kafka._

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
 * Module Desc:基于Receivers的方法
 * User: wangyue
 * DateTime: 15-4-28下午8:00
 */

object KafkaWordCount1 {
  def main(args: Array[String]) {
    if (args.length < 4) {
      System.err.println("Usage: KafkaWordCount1 <zkQuorum> <group> <topics> <numThreads>")
      System.exit(1)
    }

    //StreamingExamples.setStreamingLogLevels()

    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setAppName("KafkaWordCount1")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")

    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap

    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L))
      .reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(2), 2)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }

}

/*
val kafkaStream = KafkaUtils.createStream(streamingContext,[ZK quorum], [consumer group id], [per-topic number of Kafka partitions to consume])
在创建DStream的时候，你也可以指定数据的Key和Value类型，并指定相应的解码类。
需要注意的是：
　　1、Kafka中Topic的分区和Spark Streaming生成的RDD中分区不是一个概念。所以，在 KafkaUtils.createStream()增加特定主题分区数仅仅是增加一个receiver中消费Topic的线程数。
   并不增加Spark并行处理数据的数量；
　　2、对于不同的Group和topic我们可以使用多个receivers创建不同的DStreams来并行接收数据；
　　3、如果你启用了WAL，这些接收到的数据将会被持久化到日志中，因此，我们需要将storage level 设置为StorageLevel.MEMORY_AND_DISK_SER ,也就是：

1KafkaUtils.createStream(..., StorageLevel.MEMORY_AND_DISK_SER)
*/


/*
      spark-submit来启动你的应用程序:
      bin/spark-submit  --master yarn-cluster
　　　　--class com.spark.base.streaming.KafkaTest
　　　　--jars lib/spark-streaming-kafka_2.10-1.3.0.jar,
　　　　lib/spark-streaming_2.10-1.3.0.jar,
　　　　lib/kafka_2.10-0.8.1.1.jar,lib/zkclient-0.3.jar,
　　　　lib/metrics-core-2.2.0.jar ./iteblog-1.0-SNAPSHOT.jar
*/
