/*
annotation in Kotlin
@Target 指定可以用该注解标注的元素的可能的类型（类、函数、属性、表达式等）；
@Retention 指定该注解是否存储在编译后的 class 文件中，以及它在运行时能否通过反射可见 （默认都是 true）；
@Repeatable 允许在单个元素上多次使用相同的该注解；
@MustBeDocumented 指定该注解是公有 API 的一部分，并且应该包含在生成的 API 文档中显示的类或方法的签名中。

 */
@Target(AnnotationTarget.FUNCTION)//表示可以在函数中使用
@Retention(AnnotationRetention.RUNTIME)//表示运行时注解
annotation class MyAnnotation

/*
对应于 Java 原生类型的类型（Int、 Long等）；
字符串；
类（Foo::class）；
枚举；
其他注解；
上面已列类型的数组。
Kotlin中只允许用val声明参数
当参数类型是其他注解时，该注解类的名字前面不能用@
 */
annotation class MyAnnotation2(val value: Int)

@MyAnnotation2(1)
class Foo {}

