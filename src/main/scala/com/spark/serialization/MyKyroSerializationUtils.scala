package com.spark.serialization

import com.twitter.chill.Input
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.hadoop.io.{NullWritable, BytesWritable}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.serializer.KryoSerializer

import scala.reflect.ClassTag

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
 * Module Desc:自定义 Kyro 序列化工具类
 * User: wangyue
 * DateTime: 15-4-29下午6:35
 */
object MyKyroSerializationUtils {

  /**
   * 自定义 kyro 序列化方式存储文件
   * @param rdd
   * @param path
   * @tparam T
   */
  def saveAsObjectFile[T: ClassTag](rdd: RDD[T], path: String) {
    val kryoSerializer = new KryoSerializer(rdd.context.getConf)

    rdd.mapPartitions(iter => iter.grouped(10)
      .map(_.toArray))
      .map(splitArray => {
      //initializes kyro and calls your registrator class
      val kryo = kryoSerializer.newKryo()

      //convert data to bytes
      val bao = new ByteArrayOutputStream()
      val output = kryoSerializer.newKryoOutput()
      output.setOutputStream(bao)
      kryo.writeClassAndObject(output, splitArray)
      output.close()

      // We are ignoring key field of sequence file
      val byteWritable = new BytesWritable(bao.toByteArray)
      (NullWritable.get(), byteWritable)
    }).saveAsSequenceFile(path)
  }

  /**
   * 自定义 kyro 序列化方式读文件
   * @param sc
   * @param path
   * @param minPartitions
   * @param ct
   * @tparam T
   * @return
   */
  def objectFile[T](sc: SparkContext, path: String, minPartitions: Int = 1)
                   (implicit ct: ClassTag[T]) = {
    val kryoSerializer = new KryoSerializer(sc.getConf)
    sc.sequenceFile(path, classOf[NullWritable], classOf[BytesWritable],
      minPartitions)
      .flatMap(x => {
      val kryo = kryoSerializer.newKryo()
      val input = new Input()
      input.setBuffer(x._2.getBytes)
      val data = kryo.readClassAndObject(input)
      val dataObject = data.asInstanceOf[Array[T]]
      dataObject
    })
  }

}
