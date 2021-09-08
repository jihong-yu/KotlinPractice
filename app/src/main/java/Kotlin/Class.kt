package Kotlin

import kotlin.reflect.typeOf

//Class

// OOP -> Object Oriented Programming (객체지향 프로그래밍)
// 객체란 뭘까?
// - 이름이 있는 모든것

// 절차지향 프로그래밍
// 코드를 위에서 부터 아래로 실행을 하면 문제가 해결된다.

//객체지향 프로그래밍
// 객체를 생성 후 객체에게 일을 시켜서 문제를 해결한다.
// 선수,심판,경기장,관중 -> 축구게임을 위해 각각 객체를 생성

// 객체를 만드는 방법
// 설명서가 있어야 한다.

fun main(array:Array<String>){

    // 클래스(설명서)를 통해서 실체를 만드는 방법
    // 인스턴스화 -> 객체 생성
    Car("v8 engine","big")

    //우리가 만든 클래스(설명서)는 자료형이 된다.
    val bigcar = Car("v8 engine","big")
    val bigcar2= SuperCar("v8 engine","big","wing")

    //인스턴스가 가지고 있는 기능 사용하기
    val runableCar = RunableCar("simple engine","body")
    runableCar.ride()
    runableCar.navi("부산")
    runableCar.drive()

    //인스턴스의 멤버변수에 접근하는 방법
    val runableCar2 = RunableCar2("nice engine","long body")
    println(runableCar2.body) //"nice engine" 출력
    println(runableCar2.engine) // "long body" 출력

    val testClass = TestClass()

    testClass.test() //Unit형 fun 출력
    testClass.test(1) //Int형 fun 출력
    testClass.test(2,"hello") //Int형,String형 fun 출력

}

// 클래스(설명서) 만드는 방법1(주요 생성자 이용)
//주요 생성자에서는 프로퍼티를 선언 및 초기화를 간소화 할수 있게 해준다.
//주요 생성자에서 val로 생성시 읽기전용(only get)이며 var로 선언시 읽기 쓰기(read, write)가 가능
class Car(var engine:String,var body:String) {


}

//클래스 만드는 방법2(보조 생성자이용(constructor))
class SuperCar{

    var engine:String =""
    var body:String =""
    var door:String =""

    //생성자(멤버 변수는 타입추론이 되지 않으니 타입을 명시해 주어야한다. )
    constructor(endgine:String, body:String, door : String){
        this.body = body
        this.engine = endgine
        this.door  = door
    }
}

//클래스 만드는 방법3 -> 1번 방법의 확장(주 + 보조생성자 이용) (생성자가 2개라고 이해하기)
// 주요생성자에 없는것(door)은 임의로 보조생성자에서 초기화를 시켜줘야한다.
class Car1(var engine: String,var body: String){
    var door : String = ""

    //생성자
    constructor(engine: String,body: String,door: String):this(engine, body){
        this.door = door
    }

    fun print2(){
        print(engine+door+body) //모두 정상적으로 출력
    }
}

//클래스 만드는 방법4
// 주요생성자에 없는것(door)은 임의로 보조생성자에서 초기화를 시켜줘야한다.
// engine,body 둘다 class 내부에서 사용하기 위해서는 변수에 초기화 시켜주어야 한다.
class Car3(engine: String,body: String){
    var door : String = ""
    var engine :String = engine
    //생성자
    constructor(engine: String,body: String,door: String):this(engine, body){
        this.door = door
    }

    fun print2(){
        //print(engine+door+body) //body는 초기화가 되지 않았기 때문에 출력불가
    }
}


//클래스 만드는 방법5 -> 2번 방법의 확장(보조 생성자 2개 이용)
class Car2{
    var engine: String =""
    var body: String =""
    var door: String =""

    constructor(engine: String,body: String){
        this.engine = engine
        this.body = body
    }

    constructor(engine: String,body: String, door:String){
        this.engine = engine
        this.body = body
        this.door = door
    }
}

class RunableCar(engine: String,body: String){

    fun ride(){
        println("탑승 하였습니다.")
    }

    fun drive(){
        println("달립니다 !")
    }

    fun navi(destination:String){
        println("$destination 으로 목적지가 설정되었습니다.")
    }
}

class RunableCar2{
    var engine : String =""
    var body: String =""

    constructor(engine: String,body: String){
        this.body= body
        this.engine = engine
        println("RunableCar2가 생성자에서 만들어 졌습니다.")
    }

    init { //인스턴스화 될때 가장 먼저 호출 된다.(초기값 설정시 유용)
        println("RunableCar2가 만들어 졌습니다.")
    }

    fun ride(){
        println("탑승 하였습니다.")
    }

    fun drive(){
        println("달립니다 !")
    }

    fun navi(destination:String){
        println("$destination 으로 목적지가 설정되었습니다.")
    }

}

//오버로딩(overloading) : 함수의 이름은 같으나 매개변수가 다른 경우
class TestClass(){
    val a: Int = 10

    fun test(){
        println("Unit형 fun")
    }
    fun test(a:Int){
        println("Int형 fun")
    }

    fun test(a:Int,b:String){
        println("Int형,String형 fun")
    }
}


