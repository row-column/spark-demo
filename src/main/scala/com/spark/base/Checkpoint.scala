package com.spark.base
import org.apache.spark.SparkContext

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
 * Module Desc:Checkpoint
 * User: wangyue
 * DateTime: 15-5-8下午2:40
 */
object Checkpoint {
  def main(args: Array[String]) {
    val sc = new SparkContext("local","Checkpoint-Test")

    sc.setCheckpointDir("/home/wangyue/opt/spark/spark-1.3.1-bin-hadoop2.3/data/checkpoint");

    val a  = sc.parallelize(List(1 to 10,2))

    /**
     * Mark this RDD for checkpointing. It will be saved to a file inside the checkpoint
     * directory set with SparkContext.setCheckpointDir() and all references to its parent
     * RDDs will be removed. This function must be called before any job has been
     * executed on this RDD. It is strongly recommended that this RDD is persisted in
     * memory, otherwise saving it on a file will require recomputation.
     */
    a.checkpoint()
    a.count()

    a.foreach(print)
  }

}
