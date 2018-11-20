class User(var userName:String) {
    fun printName() {
        println(userName)
    }
}
var userCls = User::class // kotlin class
// var user = User::class.java  if User is java class

println(userCls)

// invoke fun
println("invoke fun:")
var p = User::printName
p.invoke(User("zhe"))
// java way
var method = User::class.java.getMethod("printName")
method.invoke(User("chen"))

// fields
println("fields:")

var user = User("chen")
var userName = User::userName
// println(userName.get(user))  // compile err, because userName is final if define this way

class User1() {
    var userName = "zhe"
        get() = field
    set(value) {field = value}
}

var user1 = User1()
var userName1 = User1::userName
println("field reflect: " + userName1.get(user1))
userName1.set(user1, "chenzhe")
println("field reflect set: " + userName1.get(user1))

// java way


