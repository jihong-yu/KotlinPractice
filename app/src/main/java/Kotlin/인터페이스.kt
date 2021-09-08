package Kotlin

fun main(array: Array<String>){

    var person2 = Student2()
    person2.run()



}
//생성자가 없다 -> 인스턴스화 시킬 수 없다.
//단순히 함수명과 매개변수만 정의해놓는다.
//interface를 상속받는 클래스에서 반드시 해당 메소드들을 구현해야한다.

//자바와 다른점 : 인터페이스 내부에서도 함수를 구현할 수 있고 또한 인터페이스 내부에서 구현하였을 경우
//              그 메소드를 반드시 implement한 클래스 내부에서 구현할 필요가 없다.
interface Person2 {
    fun eat(){
        println("먹는다")
    }
    fun sleep(){
        println("잔다")
    }
    fun study()
    fun run() = println("hello")
}

interface Person3 {

    fun study()
    fun run() = println("hello")
}

class Student2:Person2,Person3{

    override fun study() { // 반드시 구현해야됨
        println("interface를 구현한 study")
    }

    override fun run() {

    }
}
class Teacher2:Person2{
    override fun study() {
        TODO("Not yet implemented")
    }
}

open class Person{

    fun eat(){

    }

    fun sleep(){

    }
}

class Student : Person(){

}