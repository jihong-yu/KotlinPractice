package Kotlin

//제너릭
//목적 : 다양한 타입의 객체들을 다루는 메서드나 컬렉션
//      타입을 체크하는 기능
fun main(args: Array<String>) {

    var a = null

    //제너릭을 사용하지 않은 경우
    //형 변환을 해줘야 한다.
    val list1 = listOf(1, 2, "가")
    val b: String = list1[2].toString() //형변환(타입변환)

    //제너릭을 사용하는 경우
    val list2 = listOf<String>("a", "b", "c")
    val c: String = list2[2] //String이라는것을 보장받는다.

    //제너릭을 사용하지 않은 경우
    val list5 = listOf(1, 2, 3, "가") // -> Any타입으로 지정됨(float,Int,String 등의 부모)

    val box:Box<Int> = Box<Int>(1)
    println(box.value) // 1출력

    val box2 = Box(1) //Int형 추론 가능
    //생성자의 인자 값을 통해서나 다른 어떤 방식이든 parameter 의 타입이 추론이 가능할 때는 type argument 의 생략이 가능하다.

    println(_gSum(1,2)) // 3출력

}

class Box<T>(t: T) {
    var value = t
}

fun <T: Number> _gSum(x:T,y:T): T { //Number형을 상속하는 자료형만 받겠다는 의미(T를 Number형으로 선언)
    return (x.toDouble() + y.toDouble()) as T //가장 큰 double형태로 변경후 + 실행 후 T형으로 캐스팅
}



