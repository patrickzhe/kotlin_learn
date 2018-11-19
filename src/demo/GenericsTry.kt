package demo

class Box<T>(t: T) {
    var value = t
}
var box: Box<String> = Box("123")
var box2: Box<Int> = Box(123)

/*
kotlin support out/in
<? extends Object>
<? super Object>
 */
class Box1<in T> {}

fun test(strs: Box1<Any>) {
    var objects:Box1<String> = strs
}

fun test1(strs:Box1<String>) {
//    var objects:Box1<Any> = strs  //compile failed
}

fun test2(strs : Box<Any>) {
    var objects : Box<in String> = strs
}

fun test3(strs: Box<String>) {
    var objects : Box<out Any> = strs
}

// generics fun
fun <T> test4(item : T) : List<T> {
    return listOf(item)
}
val l = test4<Int>(1)

// generics 约束
fun <T : Comparable<T>> sort(list: List<T>) {
//    sort(1) //编译错误
    sort(listOf(1)) //编译通过
}

