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