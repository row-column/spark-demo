package com.spark.base.streaming

import org.apache.spark.SparkConf
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
 * Module Desc:NetworkWordCount
 * User: wangyue
 * DateTime: 15-4-28下午8:55
 */
object NetworkWordCount {
  def main(args: Array[String]) {
    if(args.length < 2){
      System.err.println("Usage: NetworkWordCount serverIP serverPort")
      System.exit(1)
    }
    val sparkConf = new SparkConf().setAppName("KafkaWordCount2").setMaster("local[2]")
    // 创建StreamingContext，1秒一个批次
    val ssc = new StreamingContext(sparkConf, Seconds(1));
    val Array(serverIP, serverPort)  = args
    // 获得一个DStream负责连接 监听端口:地址
    val lines = ssc.socketTextStream(serverIP, serverPort.toInt);

    // 对每一行数据执行Split操作
    val words = lines.flatMap(_.split(" "));
    // 统计word的数量
    val pairs = words.map(word => (word, 1));
    val wordCounts = pairs.reduceByKey(_ + _);

    // 输出结果
    wordCounts.print();

    ssc.start();             // 开始
    ssc.awaitTermination();  // 计算完毕退出
  }

}

/**
  服务端：nc -lk 9999
  客户端：./bin/run-example org.apache.spark.streaming.examples.NetworkWordCount local[2] localhost 9999

 **/