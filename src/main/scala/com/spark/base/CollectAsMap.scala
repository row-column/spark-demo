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
 * Module Desc:CollectAsMap
 * User: wangyue
 * DateTime: 15-5-8下午3:30
 */
object CollectAsMap {
  def main(args: Array[String]) {
    val sc = new SparkContext("local", "CollectAsMap-Test")

    val a = sc.parallelize(List(1, 2, 1, 3), 1)
    /**
     * Zips this RDD with another one, returning key-value pairs with the first element in each RDD,
     * second element in each RDD, etc. Assumes that the two RDDs have the *same number of
     * partitions* and the *same number of elements in each partition* (e.g. one was made through
     * a map on the other).
     */
    val b = a.zip(a)
    b.foreach(println)
    // (1,1)
    // (2,2)
    // (1,1)
    // (3,3)

    /**
     * Return the key-value pairs in this RDD to the master as a Map.
     *
     * Warning: this doesn't return a multimap (so if you have multiple values to the same key, only
     *          one value per key is preserved in the map returned)
     */
    val result = b.collectAsMap
    result.foreach(println)
    // output
    // (2,2)
    // (1,1)
    // (3,3)


    val data = Array[(String, Int)](("A", 1), ("B", 2),
      ("B", 3), ("C", 4),
      ("C", 5), ("C", 6))

    // as same as "val pairs = sc.parallelize(data, 3)"
    val pairs = sc.makeRDD(data, 3)

    print(pairs.collectAsMap)
    // output Map(A -> 1, C -> 6, B -> 3)

  }


}
