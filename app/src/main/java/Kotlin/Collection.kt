package Kotlin

import java.util.function.BiFunction

//Collection
// -> list , set , map

fun main(args: Array<String>){

    //Immutable Collection : 변경불가능한 Collection(한번 설정하면 원소의 값을 추가,변경 할 수 없다.
    // set,add 등의 함수가 없음

    //List
    val numberList = listOf<Int>(1,2,3,3,3)
    println(numberList) // List는 중복을 허용한다.
    println(numberList.get(0)) // 첫번째 원소 출력 1
    println(numberList[0]) // 첫번째 원소 출력 1


    //set
    val numberSet = setOf<Int>(1,2,3,3,3)
    println(numberSet) // set은 중복을 허용하지 않는다.
    //println(numberSet.get(0)) // 에러발생 set은 index가 없다.
//    numberSet.forEach {
//        print(""+it + " ") //1 2 3 출력
//    }
    for (i in numberSet) {
        print(""+i+" ")
    }

    println()

    //map -> Key,Value 방식으로 관리한다.
    var numberMap = mapOf<String,Int>("one" to 1,"two" to 2) //key는 String형 Value는 Int형 저장
    println(numberMap.get("one")) // 인덱스가아닌 Key값을 입력하면 출력가능 : 1출력
    println(numberMap["one"]) // 1출력

    var tempSet = setOf<Int>(0,1,2)



    //mutable Collection (변경 가능)

    val mNumberList = mutableListOf<Int>(1,2,3)
    mNumberList.add(3,4) //3번 index에 4 추가
    println(mNumberList) // 1,2,3,4 출력

    val mNumberSet = mutableSetOf<Int>(1,2,3,4,4,4)
    mNumberSet.add(5) //set은 index가 없기 때문에 값만 추가해주면 된다.
    println(mNumberSet) // 1,2,3,4,5 출력

    val mNumberMap = mutableMapOf<String,Int>("one" to 1,"two" to 2)
    mNumberMap.put("three",3) //put함수를 이용해 원소추가
    println(mNumberMap) // {one=1, two=2, three=3} 출력


    val a = mutableListOf<Int>(1,2,3)
    a.add(4) // 자동으로 마지막 인덱스 다음에 4 추가
    println(a) // 1,2,3,4 출력
    a.add(0,200) // 0번째 index의 값을 200으로 변경
    println(a) // 200,1,2,3,4 출력
    a.removeAt(1) //1번째 index의 값을 삭제
    println(a) //200,2,3,4 출력 (1번의 index가 삭제 되었기 때문에 2번부터 index가 다 한칸씩 앞으로 당겨짐)
    println(a[1]) // 2출력

    val b = mutableSetOf<Int>(1,2,3,4,4)
    b.add(2) //2추가
    println(b) // 1,2,3,4 출력(집합은 중복을 허용하지 않는다)
    b.remove(2) //2 값을 지워줌(없는 값을 입력해도 에러발생x)
    println(b) // 1,3,4 출력

    val c = mutableMapOf<String,Int>("one" to 1,"two" to 2)
    c.put("three",3)
    println(c) //{one=1, two=2, three=3} 출력
    c.replace("three",4) // "three"라는 키값을 가진 원소의 값을 4로 변경

    println(c)//{one=1, two=2, three=4} 출력
    println(c.keys) //key만 모두 출력 [one, two, three] 출력
    println(c.values) // value만 모두 출력 [1, 2, 4] 출력






}