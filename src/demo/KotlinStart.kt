package demo

fun main(args: Array<String>) {
    println("Hello, world!")

    // immutable
    val name = "Patrick"
    // mutable
    var myAge = 33

    var bigInt: Int = Int.MAX_VALUE
    var smallInt: Int = Int.MIN_VALUE

    println("biggest Int:" + bigInt)
    println("smallest Int: $smallInt")

    // other types:  Long Double Float Boolean Short Byte Char

    // Float lost precision
    var dbNum1: Double = 1.111111111111111111
    var dbNum2: Double = 1.111111111111111111

    println("Sum : " + (dbNum1 + dbNum2))

    if (true is Boolean) {
        print("true is boolean \n")
    }

    var letterGrade: Char = 'A'
    println("A is a Char : ${letterGrade is Char}")

    // cast
    println("3.14 to Int : " + (3.14.toInt()))
    println("A to Int : ${'A'.toInt()}")
    println("65 to Char : " + 65.toChar())

    // strings
    val longStr = """    this is a
    very long
        str"""
    println("str length: ${longStr.length}")
    println("str : ${longStr}")
    println("str trim: ${longStr.trim()}")
    println("2nd Index: ${longStr.get(2)}")
    println("2nd Index: ${longStr[2]}")
    println("Index 2 - 7: ${longStr.subSequence(2, 8)}")
    println("Index 2 - 7: ${longStr.contains("is")}")

    // array
    var myArray = arrayOf(1, 2.3, "fjkdf")
    println("myArray: $myArray")

    myArray[1] = "kk"
    println("arr length: ${myArray.size}")
    println("arr contains 1, ${myArray.contains(1)}")

    var partArray = myArray.copyOfRange(0, 1)
    println("first: ${partArray.first()}")
    println("fjkdf index: ${myArray.indexOf("fjkdf")}")


    var sqArray = Array(5, { x -> x * x })
    println("sq arr: ${sqArray[2]}")

    // one type arr
    var arr2: Array<Int> = arrayOf(2, 3, 4)
    for (i in arr2.indices) {
        print("use indices loop: a[$i]=${arr2[i]},")
    }
    println()
    for ((index, value) in arr2.withIndex()) {
        print("use withIndex loop: a[$index]=${value},")
    }

    // range

    // java  int a = b > c ? 4 : 3
    var a = if (4 > 3) { 5} else {6}
    println("三元 a: ${a}")

    //switch
    a = 2
    when (a) {
        0 -> {println("swith a is 0")}
        1 -> {println("swith a is 1")}
        2,3 -> {println("switch a is 2 or 3")}
        else -> {println("swith a is not 0 and 1")}
    }

    // 赋值
    var b = when (a) {
        0 -> 0
        1 -> 2
        2,3 -> 4
        else -> 10 // 必须
    }
    println("b use swith to define, ${b}")

    //is 结合
    var x = hasPrefix("")
    println("is and when in func: ${x}")

    // for int range, similar to shell
    for (i in 1..10) {
        if (i == 4)
        println("in for i is ${i}")
    }


    //collections
    // list
    var list = listOf<Int>(2,3,4)
    var set = setOf<Int>(3,4,5)
    var map = mapOf<Int, Int>(4 to 4, 5 to 5)
    //kotlin's list/set/map is immutable, can't change(add or put)
    // use changeable list/set/map
    var mList = mutableListOf<Int>(2, 3, 4)
    mList.add(5)
    println("mutable list: ${mList}")

    //class constructor test
    Test4()
    Test4(3)
    Test4(4, "f")

    // default constructor params
    Test5()
    Test5(age=10)
    Test5(name="zhe")

    // data class
    println("data class, age:${Test6("zhe", 10).toString()}")
    // copy
    var user = Test6("zhe", 11)
    var user2 = user.copy(age=12)
    println("user2: $user2")


    // class with params get/set
    // use also with param name
    var test7 = Test7()
    //println("get/set, ${test7.userName}") Stackoverflow why?
//    test7.userName = "zhe"
//    println("get/set, ${test7.userName}") // still stackoverflow

    // inner class
    println("inner class, userName:${Test8().Test9().userName}")

    /*
    Kotlin中的可见性修饰符：

private ——本类内部（包含其所有成员）都可见
protected ——只在本类内部+子类中可见
public ——能见到类声明的任何客户端都可以见到其public成员
internal——能见到类声明的本模块内的任何客户端都可以见到其public成员
     */

    var test11 = Test11()
    /*
    output:
    Test10, init, zhe
sub Test11 init called
     */

    test11.changeName("chen")
    println("sub override, ${test11.userName}")

    //interface and impl
    var userImpl = User()
    println("interface impl, name: ${userImpl.getName()}, age:${userImpl.getAge()}")


    //anonymous class try
    AnonymousTry().test()

    //companion test
    Test12.instance.print()
    Test12.instance.print()

    // vararg
    val c = arrayOf(1,2,3)
    val d = asList(-1, 0, *c, 4) // *c address?
    println("vararg, ${d}")

    //局部函数
    test13()

    //尾函数, 标记该递归函数，并将其自身调用作为它执行的最后一个操作
    println("tailrec fun: ${count(2)}")

    /*
    中缀函数
    infix
    它们必须是成员函数或扩展函数；
    它们必须只有一个参数；
    其参数不得接受可变数量的参数。
     */
    //调用
    var str = "hello world"
    //不使用中缀表示法
    println(str.removeLetter("h")) //输出ello world
    //使用中缀表示法
    println("infix: ${str removeLetter "d"}")  //输出hello worl
    //使用中缀表示法调用str removeLetter "d"等同于调用str.removeLetter("d")
    //还可以连续调用
    println(str.removeLetter("h").removeLetter("d").removeLetter("l")) // 输出 eo wor
    println("infix: ${str removeLetter "h" removeLetter "d" removeLetter "l"}") // 输出 eo wor

    /*
    Lambda 表达式总是括在大括号中；
其参数（如果有的话）在 -> 之前声明（参数类型可以省略）；
函数体（如果存在的话）在 -> 后面。
     */
    //这是一个Lambda表达式的完整语法形式
    val sum = { x: Int, y: Int -> x + y } // 其他写法: val sum: (Int, Int) -> Int = { x, y -> x + y }
//Lambda表达式在大括号中
//参数 x 和 y 在 -> 之前声明
//参数声明放在大括号内，并有参数类型标注
//函数体 x + y 在 -> 后面
    println("lamba, { x: Int, y: Int -> x + y }: ${sum(1, 2)}") //输出结果为 3

    val getInt: (Int) -> Int = { x -> x + 1 }
    val sum2: (Int) -> Int = { it + 1 } //并且将隐含地奖这个参数命名为 it

    var sum3: (Int) -> Int = {
        val i: Int = it + 1
        val j: Int = i + 3
        val k: Int = it + j - i
        i
        k
        j
    }
    println("""
        lamba sum3:
        (Int) -> Int = {
        val i: Int = it + 1
        val j: Int = i + 3
        val k: Int = it + j - i
        i
        k
        j
        : ${sum3(1)}
    """ )

    //高阶lamba
    println("lamba fun inject: ${printName("Name:", ::getName)}")
    println(printName("lamba fun 2: Name:", { name -> getName("Czh") }))
    println(printName("lamba fun 3: Name:") { name -> getName("Czh") })

    //匿名函数
    println("annoymous fun: " + printName("Name:", fun(str: String): String { return "Czh" }))

    /*
    内联函数
    使用高阶函数会带来一些运行时的效率损失。每一个函数都是一个对象，并且会捕获一个闭包。
     即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。
     这时可以通过内联函数消除这类的开销
     */
    fun printName(a: String, name: (str: String) -> String): String {
        var str = "$a${name("Czh")}"
        return str
    }

    println(printName("inner connection fun: "+"Name:", { name -> getName("Czh") }))

    // inline fun 使用
    println("inline fun:" + printName2("Name:", { name -> getName("Czh") }))

    // inline attributes

    // 函数扩展
    val mutableList = mutableListOf(1, 2, 3)
    mutableList.swap(1, 2) //调用扩展函数swap()
    println("""fun expand
        fun MutableList<Int>.swap(index1:Int, index2:Int) {
    val tmp = this[index1] // this is the mutable list
    this[index1] = this[index2]
    this[index2] = tmp
        }
        swap:""" + mutableList)

    // 如果扩展和内部函数名冲突，优先使用内部函数
    var a1 = null
    var b1 = "not null"
    println("""
       fun Any?.toString():String{
    if (this == null) return "null string"
    return toString()
}, test:""" + a1.toString() + ", test2:" + b1.toString())


    // 伴生对象
    println("companion obj: " + Test16.foo())

    // class 内部fun 扩展
    println("-----------------")
    println("class fun expand in other class: ")
    Test18().test(Test17())
    println("-----------------")
    println("class fun expand in other class then override in subclass: ")
    Test19().test(Test17())
    println("-----------------")

}

// 函数定义
fun abs() {}

fun abc(): Unit {}
fun abs(str: String) {}
fun abc(str: String): String {
    return ""
}

// Any similar to Object but not, has equals hascode and toString
fun hasPrefix(x :Any): Boolean = when(x) {
    is String -> {true}
    is Int -> {false}
    else -> false
}


class Test
class Test1 {}
// constructor, similar to scala
class Test2(userName:String)
class Test3 constructor(age:Int){}

// no code in constrcutor , could use init
class Test4(var sex:String) {
    init {
        sex = "m"
    }
    // 次要构造, call this
    constructor() : this("f") {
        println("no , sex:$sex")
    }
    constructor(age:Int) : this("m") {
        println("only age, sex:$sex, age:$age")
    }
    var age:Int = 0
    constructor(age:Int, sex:String) : this("m") {
        this.sex = sex
        this.age = age
        println("age and sex, sex:$sex, age:$age")
    }
}
// default params
class Test5(name:String = "test5", age:Int=1) {
    init {
        println("default, name:$name, age:$age")
    }
}

//data class
/*
数据类用关键字data标识，会自动创建下面的方法：
getter/setter方法；
equals()/hashCode() 对；
toString() 格式是 "User(name=Czh, age=22)"；
componentN() 函数 按声明顺序对应于所有属性；
copy() 函数。

主构造方法至少要有一个参数，且参数必须标记为val或var
数据类不能用open、abstract、sealed(封闭类)、inner标识
 */
data class Test6(var userName:String, var age:Int)

// enum
enum class Color {
    RED, GREEN, BLUE
}
// enum with param
enum class Color2(rgb: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF)
}

// class with params
class Test7 {
    var userName:String
        get() = userName
        set(value) {
            println("set func called")
            userName = value
        }
    val sex:String
        get() = "m"

}

// inner class
class Test8 {
    inner class Test9 {
        var userName = "zhe"
    }
}

// extend
open class Test10() {
    var userName = "zhe"
    init {
        println("Test10, init, $userName")
    }
    constructor(name: String): this(){
        println("Test10, constructor called ")
        userName = name
    }
    open fun changeName(name:String) {
        userName = name
    }
}
class Test11 : Test10() {
    init {
        println("sub Test11 init called")
    }

    // how to write constructor
    override fun changeName(name:String) {
        super.changeName(name)
        println("override called")
    }
}

// interface, all fun open
interface UserImpl {
    fun getName(): String
    fun getAge(): Int{
        return 22
    }
}
//实现接口UserImpl，可以不重写getName()
class User :UserImpl{
    override fun getName(): String {
        return "Czh"
    }
}

// 伴生对象，类似scala, 取代static
class Test12 {
    var i:Int = 0
    companion object {
        var instance = Test12()
    }

    fun print(){
        println("companion test: i=$i");
        i++
    }
}

// 可变数量参数
fun <T> asList(vararg ts:T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

//局部函数，函数嵌套函数
fun test13() {
    println("test13")
    fun test14() {
        println("test14")
    }
    test14()
}

//尾函数
tailrec fun count(x: Int = 1): Int = if (x == 10) x else count(x - 1)

//扩展函数
infix fun String.removeLetter(str: String): String {
    //this指调用者
    return this.replace(str, "")
}

/*
lamba 高阶使用
函数注入,函数作为参数传入高阶函数，需要在该函数前加两个冒号::作为标记
Kotlin提供了Lambda表达式来让我们更方便地传递函数参数值。Lambda表达式总是被大括号括着；
如果有参数的话，其参数在 -> 之前声明，参数类型可以省略；如果存在函数体的话，函数体在-> 后面
*/

fun getName(name: String): String {
    return name
}

fun printName(a: String, name: (str: String) -> String): String {
    var str = "$a${name("Czh")}"
    return str
}

/*
printName函数有一个函数类型的参数，通过Lambda表达式向printName函数传入参数值，
Kotlin编译器会为Lambda表达式单独创建一个对象，再将Lambda表达式转换为相应的函数并调用。
如果这种情况出现比较多的时候，就会很消耗资源。这是可以在函数前使用inline关键字，把Lambda函数内联到调用处
 */
inline fun printName2(a: String, name: (str: String) -> String): String {
    var str = "$a${name("Czh")}"
    return str
}

/*
函数扩展
 */
fun MutableList<Int>.swap(index1:Int, index2:Int) {
    val tmp = this[index1] // this is the mutable list
    this[index1] = this[index2]
    this[index2] = tmp
}

//空接收类型
fun Any?.toString():String{
    if (this == null) return "null string"
    return toString()
}

// 扩展属性
class Test15 {
    // public 否则扩展属性不能访问
    var myVal = 0
}
var Test15.value :Int
    get() = myVal
    set(value) {
        myVal = value
    }
// 扩展属性的适用性在哪里？肯定不是这样，都已经public了，完全不需要一个额外的壳子
// 对于不同包的对象的扩展，需要import
// 扩展伴生对象
class Test16 {
    companion object {

    }
}
fun Test16.Companion.foo() {
    println("companion obj expand")
}
// class 内部扩展其他class的
class Test17 {
    fun test17() {
        println("test 17 print")
    }
}
open class Test18 {
    fun test18() {
        println("test 18 print")
    }
    open fun Test17.test17_2() {
        test17()
        // call Test18 fun using this@ClassName
        this@Test18.test18() // more like decoration pattern or proxy?
    }

    open fun test(test17 : Test17) {
        test17.test17_2()
    }
}
// 扩展的fun 都可以在subclass中override, 只要open了
class Test19 : Test18() {
    override fun Test17.test17_2() {
        println("call test17 test17_2 in Test19")
        test17()
        println("call test18 test18() in Test19")
        this@Test19.test18()
    }

    override fun test(test17 : Test17) {
        println("call test in Test19")
        test17.test17_2()
    }
}

