data class User(var name : String, var age :Int)

var user = User("zhe", 22)
// 解构声明
/*
解构声明能同时创建多个变量，将对象中的数据解析成相对的变量
 */
var (name, age) = user
println("解构, name=$name, age=$age")
/*
里面的逻辑:
//指定变量name的值为user第一个参数的值
var name = user.component1()
//指定变量name的值为user第二个参数的值
var age = user.component2()
 */

//解构Map
var map = mutableMapOf<String, Any>()
map.put("name", "Czh")
map.put("age", 22)
for ((key, value) in map) {
    println("$key：$value")
}


// 区间Range
if ( i in 1..5) {
    println("i in 1-5")
}

if ( i !in 1..5) {
    println("i not in 1-5")
}

// downTo
for (i in 5..1) println(i)
// the same as
for (i in 5 downTo 1) println(i)

// step
for (i in 1..5 step 2) println(i)
for (i in 1 downTo 5 step 2) println(i)

//until  1 <= i < 5
for (i in 1 until 5) println(i)


// 类型检查 is
var a: Any = "a"
if (a is String) {
    println("a是String类型")
}
if (a !is Int) {
    println("a不是Int类型")
}

// 自动转换
/*
val 局部变量——总是可以，局部委托属性除外；
val 属性——如果属性是 private 或 internal，或者该检查在声明属性的同一模块中执行。智能转换不适用于 open 的属性或者具有自定义 getter 的属性；
var 局部变量——如果变量在检查和使用之间没有修改、没有在会修改它的 lambda 中捕获、并且不是局部委托属性；
var 属性——决不可能（因为该变量可以随时被其他代码修改）
 */
var a: Any = "a"
println(a.length) // a 自动转换为String类型

// when
when(a){
    is String -> a.length
    is Int -> a + 1
}

//强制转换 , as
var any: Any = "abc"
var str: String = any as String

// null 转换, 用?
var str = null
// var str2 = str as String // error
var str2 = str as? String // ok, as?可以在转换失败时返回null，避免了抛出异常

// this
/*
为了表示当前的接收者我们使用this表达式。当this在类的成员中，this指的是该类的当前对象;当this在扩展函数或者带接收者的函数字面值中，this表示在点左侧传递的接收者参数。
限定的this 如果this没有限定符，它指的是最内层的包含它的作用域。如果要访问来自外部作用域的this（一个类或者扩展函数， 或者带标签的带接收者的函数字面值）我们使用this@label，其中 @label 是一个代指this来源的标签
 */

class A { // 隐式标签 @A
    inner class B { // 隐式标签 @B
        fun Int.foo() { // 隐式标签 @foo
            val a = this@A // A 的 this
            val b = this@B // B 的 this

            val c = this // foo() 的接收者，一个 Int
            val c1 = this@foo // foo() 的接收者，一个 Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit 的接收者
            }


            val funLit2 = { s: String ->
                // foo() 的接收者，因为它包含的 lambda 表达式
                // 没有任何接收者
                val d1 = this
            }
        }
    }
}

// 相等判断
/*
equals or ==  用来判断结构相等
=== 判断引用相等

 */
var a = User("Czh", 22)
var b = User("Czh", 22)
var c = b
var d = a
if (c == d) {
    println("a 和 b 结构相等")
} else {
    println("a 和 b 结构不相等")
}
if (c === d) {
    println("a 和 b 引用相等")
} else {
    println("a 和 b 引用不相等")
}

// 操作符 overload
/*
Kotlin允许对自己的类型提供预定义的一组操作符的实现，这些操作符具有固定的符号表示 （如 + 或 *）和固定的优先级。为实现这样的操作符，我们为相应的类型
（即二元操作符左侧的类型和一元操作符的参数类型）提供了一个固定名字的成员函数或扩展函数。 重载操作符的函数需要用 operator 修饰符标记。
 */
//用 operator 修饰符标记
operator fun String.unaryPlus(): String {
    return this + this
}

//调用
var a = "a"
println(+a)  //输出结果为：aa
/*
当编译器处理例如表达式 +a 时，它执行以下步骤：

确定 a 的类型，令其为 T；
为接收者 T 查找一个带有 operator 修饰符的无参函数 unaryPlus（），即成员函数或扩展函数；
如果函数不存在或不明确，则导致编译错误；
如果函数存在且其返回类型为 R，那就表达式 +a 具有类型 R；

+a	a.unaryPlus()
-a	a.unaryMinus()
!a	a.not()
a++	a.inc()
a--	a.dec()

二元
a+b	a.plus(b)
a-b	a.minus(b)
a*b	a.times(b)
a/b	a.div(b)
a%b	a.mod(b)
a..b	a.rangeTo(b)

in
a in b	b.contains(a)
a !in b	!b.contains(a)

索引
a[i] a.get(i)

a[i, j] a.get(i, j)

a[i_1, ……, i_n] a.get(i_1, ……, i_n)

a[i] = b a.set(i, b)

a[i, j] = b a.set(i, j, b)

a[i_1, ……, i_n] = b a.set(i_1, ……, i_n, b)

调用
a()	a.invoke()
a(i)	a.invoke(i)
a(i, j)	a.invoke(i, j)
a(i_1, ……, i_n)	a.invoke(i_1, ……, i_n)

赋值
a += b a.plusAssign(b)

a -= b a.minusAssign(b)

a *= b a.timesAssign(b)

a /= b a.divAssign(b)

a %= b a.remAssign(b), a.modAssign(b)（已弃用）

相等
a == b	a?.equals(b) ?: (b === null)
a != b	!(a?.equals(b) ?: (b === null))

比较
a > b	a.compareTo(b) > 0
a < b	a.compareTo(b) < 0
a >= b	a.compareTo(b) >= 0
a <= b	a.compareTo(b) <= 0


 */

// NullPointerException 处理
/*
在Kotlin中，只有下列情况可能导致出现NullPointerException：

显式调用 throw NullPointerException()；
使用了下文描述的 !! 操作符；
有些数据在初始化时不一致；
外部 Java 代码引发的问题。
 */

// 在 Kotlin 中，类型系统区分一个引用可以容纳 null （可空引用）还是不能容纳（非空引用）。 例如，String 类型的常规变量不能容纳 null：
var a = "a"
a = null  // compile err

// 可以用?
var b : String? = "b"
b = null

//调用
b?.length // 避免空指针

// Bob?.department?.head?.name

// 如果该链式调用中任何一个属性为null，整个表达式都会返回null。 如果要只对非空值执行某个操作，安全调用操作符可以与let一起使用，如下所示：
val listWithNulls: List<String?> = listOf("A", null, "B")
for (item in listWithNulls) {
    item?.let { println(it) }
}

// 安全的类型转换
var c :String? = null
var i : Int? = c as? Int

// 非空集合
val nullableList: List<Int?> = listOf(1, 2, null, 4)
val intList: List<Int> = nullableList.filterNotNull()

// elvis 操作符
// 这两行代码表达的都是“如果b不等于null，i = b.length;如果b等于null,i = -1”。第一行代码用的是if表达式，而第二行代码使用了Elvis操作符，写作?:。
// Elvis操作符表示如果?:左侧表达式非空，就使用左侧表达式，否则使用右侧表达式。
//请注意，因为throw和return在Kotlin中都是表达式，所以它们也可以用在Elvis操作符右侧
val i: Int = if (b != null) b.length else -1
val i = b?.length ?: -1
fun foo(node: Node): String? {
    val parent = node.getParent() ?: return null
    val name = node.getName() ?: throw IllegalArgumentException("name expected")
    // ……
}

// !!操作符将任何值转换为非空类型，若该值为空则抛出异常。如下所示：
var a = null
a!!


// exception
// try catch
val a: Int? = try {
    parseInt(input)
} catch (e: NumberFormatException) {
    null
}

// try表达式的返回值是 try块中的最后一个表达式或者是catch块中的最后一个表达式。finally块中的内容不会影响表达式的结果。


// 类型别名
// typealias
typealias Length = (String) -> Int
//调用
fun getLength(l: Length) = l("Czh")
//编译器把 Length 扩展为 (String) -> Int 类型
val l: Length = { it.length }
println(getLength(l)) //输出结果为：3

typealias MyType = (String, Int, Any, MutableList<String> ) -> Unit
//当我们使用的时候
var myType:MyType
//而不需要写他原来的类型
//var myType：(String, Int, Any, MutableList<String> ) -> Unit

