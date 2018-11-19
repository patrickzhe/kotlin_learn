package demo


open class Login(var userName: String) {
    open fun printlnUserName() {
        println("Login userName:${userName}")
    }
}


class AnonymousTry {
    fun test() {
        // use object key word
        printlnUserName(object : Login("chen zhe") {
            override fun printlnUserName() {
                println("anonymous, userName:$userName")
            }
        })
    }

    fun printlnUserName(login: Login) {
        login.printlnUserName()
    }
}