package com.spark.streaming

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka._
import org.apache.spark.streaming.{Seconds, StreamingContext}

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
 * Module Desc:基于使用Direct API的方法
 * User: wangyue
 * DateTime: 15-4-28下午8:00
 */

object KafkaWordCount2 {
  def main(args: Array[String]) {
    if (args.length < 4) {
      System.err.println("Usage: KafkaWordCount2 <zkQuorum> <group> <topics> <numThreads>")
      System.exit(1)
    }


    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setAppName("KafkaWordCount2")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")

    // Define the Kafka parameters, broker list must be specified
    val kafkaParams = Map("metadata.broker.list" -> "www.iteblog.com:9092,anotherhost:9092")

    // Define which topics to read from
    val topics2 = Set("sometopic", "sometopic2")

    // Create the direct stream with the Kafka parameters and topics
    val directKafkaStream = KafkaUtils.createDirectStream[String, String, StringDecoder,StringDecoder](ssc, kafkaParams, topics2)

    directKafkaStream.foreachRDD { rdd =>
      val offsetRanges = rdd.asInstanceOf[HasOffsetRanges]
      // offsetRanges.length = # of Kafka partitions being consumed

    }

    // Define the offset ranges to read in the batch job
    val offsetRanges = Array(
      OffsetRange("some-topic", 0, 110, 220),
      OffsetRange("some-topic", 1, 100, 313),
      OffsetRange("another-topic", 0, 456, 789)
    )
    // Create the RDD based on the offset ranges
   // val rdd = KafkaUtils.createRDD[String, String,StringDecoder,StringDecoder](ssc, kafkaParams, offsetRanges)

    ssc.start()
    ssc.awaitTermination()
  }

}

/*

    和基于Receiver接收数据不一样，这种方式定期地从Kafka的topic+partition中查询最新的偏移量，再根据定义的偏移量范围在每个batch里面处理数据。当作业需要处理的数据来临时，spark通过调用Kafka的简单消费者API读取一定范围的数据。这个特性目前还处于试验阶段，而且仅仅在Scala和Java语言中提供相应的API。

　　和基于Receiver方式相比，这种方式主要有一些几个优点：
　　（1）、简化并行。我们不需要创建多个Kafka 输入流，然后union他们。而使用directStream，Spark Streaming将会创建和Kafka分区一样的RDD分区个数，而且会从Kafka并行地读取数据，也就是说Spark分区将会和Kafka分区有一一对应的关系，这对我们来说很容易理解和使用；

　　（2）、高效。第一种实现零数据丢失是通过将数据预先保存在WAL中，这将会复制一遍数据，这种方式实际上很不高效，因为这导致了数据被拷贝两次：一次是被Kafka复制；另一次是写到WAL中。但是本文介绍的方法因为没有Receiver，从而消除了这个问题，所以不需要WAL日志；

　　（3）、恰好一次语义（Exactly-once semantics）。《Spark Streaming和Kafka整合开发指南(一)》文章中通过使用Kafka高层次的API把偏移量写入Zookeeper中，这是读取Kafka中数据的传统方法。虽然这种方法可以保证零数据丢失，但是还是存在一些情况导致数据会丢失，因为在失败情况下通过Spark Streaming读取偏移量和Zookeeper中存储的偏移量可能不一致。而本文提到的方法是通过Kafka低层次的API，并没有使用到Zookeeper，偏移量仅仅被Spark Streaming保存在Checkpoint中。这就消除了Spark Streaming和Zookeeper中偏移量的不一致，而且可以保证每个记录仅仅被Spark Streaming读取一次，即使是出现故障。

　　但是本方法唯一的坏处就是没有更新Zookeeper中的偏移量，所以基于Zookeeper的Kafka监控工具将会无法显示消费的状况。然而你可以通过Spark提供的API手动地将偏移量写入到Zookeeper中。如何使用呢？其实和方法一差不多
*/
