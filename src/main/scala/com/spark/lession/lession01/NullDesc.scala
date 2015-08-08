package com.spark.lession.lession01

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
 * Module Desc:阐述Null,null,Nil,Nothing,None,Unit的意思
 * User: wangyue
 * DateTime: 15-7-16下午1:54
 */
class NullDesc {
//  Null&null
//  Null是一个Trait，你不能创建她它的实例。但是Scala在语言层面上存在一个Null的实例，那就是null。
//  Java中的null意味着引用并没有指向任何对象。但存在一个悖论，一切都是对象，那没有对象是不是也是对象呢？
//  Scala定义了一个类似于对象语义的Null，和一个值语义的null。这样面向对象在空引用的情况下完备了。
//  如果你写了一个带有Null作为参数的对象，那么你传入的参数只能是null，或者指向Null的引用。

  Nil
//  Nil是一个继承List[Nothing]的对象，我们随后讨论Nothing。它就是一个空的列表

//  Nothing
//  Nothing可能比较难理解。Nothing也是一个Trait，它继承自Any，而Any是整个Scala类型系统的根。
// Nothing是没有实例的，但它时任何对象的子类，他是List的子类，是String的子类，是Int的子类，是任何用户自定义类型的子类。

//  None
//  写Java程序的时候，经常会碰到没有有意义的东西可以返回，我们返回null。但返回null有一些问题，调用方必须检查返回值，不然会有NullPointerException的异常。这逼迫我们去check函数的返回值。
// 还有一种解决办法是使用异常，但增加try/catch块，并不是明智的选择。
//  Scala内置一种解决办法。如果你想返回一个String，但可能有的时候得不到有意义的返回值，我们可以让函数返回Option[String]。

//  Unit
//  Unit跟java的void一样，表示函数没有返回值。


}
