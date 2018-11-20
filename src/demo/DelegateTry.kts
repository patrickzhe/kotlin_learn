import kotlin.properties.Delegates
import kotlin.reflect.KProperty

interface Base {
    fun print()
}

class ImplBase(val i :Int) :Base {
    override fun print() {
        println("impleBase: $i")
    }
}

// 如果不进行委托的话，则要 override Base 接口的 print 方法
//class Drived(b :Base) : Base {
//    override fun print() {
//
//    }
//}

//delegate pattern supports by kotlin
class Drived(b :Base) : Base by b // Derived 类通过使用 by 关键字将 Base 接口的 print 方法委托给对象 b

var b = ImplBase(10)
Drived(b).print()  // 直接可以使用ImplBase的print方法

// delegate fields
// 对于不同类的相似属性，可以放到delegate 共享set/get
class Delegate {
    var userName = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("getValue  类名：$thisRef, 属性名：${property.name}")
        return userName
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("setValue  类名：$thisRef, 属性名：${property.name}，值：$value")
        userName = value
    }
}

//将 userName 委托给 Delegate
class User1 {
    var userName: String by Delegate()  // by 关键字
}
class User2 {
    var userName: String by Delegate()
}

var user1 = User1()
user1.userName = "user1"
println(user1.userName)
var user2 = User2()
user2.userName = "user2"
println(user2.userName)


/* 标准delegate
延迟delegate
可观察属性delegate
Map delegate
 */

/*
延迟
lazy()是接受一个 lambda 表达式作为参数，并返回一个 Lazy <T> 实例的函数，返回的实例作为一个委托，第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 之后再调用 get() 返回记录的结果
默认情况下，对于 lazy 属性的求值是同步锁的（synchronized）：该值只在一个线程中计算，并且所有线程会看到相同的值。如果初始化委托的同步锁不是必需的，
这样多个线程可以同时执行，那么将 LazyThreadSafetyMode.PUBLICATION 作为参数传递给 lazy() 函数

而如果你确定初始化将总是发生在单个线程，那么你可以使用 LazyThreadSafetyMode.NONE 模式， 它不会有任何线程安全的保证和相关的开销


 */
val lazyValue: String by lazy {
    println("computed!") // 只运行一次
    "Hello"
}

//调用两次
println(lazyValue)
println(lazyValue)


val lazyValue2: String by lazy(LazyThreadSafetyMode.PUBLICATION ) {
    "Hello"
}

val lazyValue3: String by lazy(LazyThreadSafetyMode.NONE) {
    "Hello"
}

/*
可观察
    非常好的观察者模式使用方式!!!
    Delegates.observable()接收两个参数，第一个是初始值，第二个是修改时处理程序（handler）。 每当我们给属性赋值时会调用该处理程序，他有三个参数，第一个是被赋值的属性，第二个是旧值，第三个是新值。
如果想拦截属性的赋值操作，并且否决他的赋值操作，可以用vetoable()取代 observable()，传递给vetoable()的修改时处理程序会返回一个boolean类型，如果返回true，允许赋值，返回false则反之

 */
var name: String by Delegates.observable("Czh") {
    property, oldValue, newValue ->
    println("属性名：$property  旧值：$oldValue  新值：$newValue")
}

name = "zhe"
name = "chenzhe"

println("--------------------------")
var name2: String by Delegates.vetoable("Czh") {
    property, oldValue, newValue ->
    if (newValue.equals("zhe")) {
        println("属性名：$property  旧值：$oldValue  新值：$newValue")
        true
    } else {
        println("not allowed")
        false
    }
}

name2 = "zhe"
name2 = "chenzhe"
println("--------------------------")
/*
Map delegate 常用语JSON解析

 */
//新建User类，主构函数要求传入一个Map
class User3(val map: Map<String, Any>) {
    //声明一个 String 委托给 map
    val name: String by map
    //因为 Map 为只读，所以只能用 val 声明
    val age: Int     by map
}
var map = mapOf("name" to "Czh", "age" to 22)
var user = User3(map)
println("map delegate: ${user.name}  ${user.age}")

// 因为Map只有getValue方法而没有setValue方法，所以不能通过User对象设置值，这时可以把User的主构函数改为传入一个MutableMap，并把属性委托给MutableMap

class User4(val map: MutableMap<String, Any>) {
    //因为MutableMap为读写，可以用var声明
    var name: String by map
    var age: Int     by map
}

var map2 = mutableMapOf("name" to "Czh", "age" to 22)
var user4 = User4(map2)
user4.name = "James Harden"
user4.age = 28
println("${user4.name}  ${user4.age}")
//打印结果为  James Harden  28

