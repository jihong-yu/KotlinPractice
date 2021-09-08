package Kotlin

//배열

fun main(array:Array<String>){

    //배열을 생성하는 방법(1) : Int 설정
    var group1 = arrayOf<Int>(1,2,3,4,5)
    println(group1.size) // 5출력

    //배열을 생성하는 방법(2) : 어느 자료형이나 넣는것이 가능(위험성 존재)
    var group2 = arrayOf(1,2,3.5,"hello")
    println(group2.size)

    //배열을 생성하는 방법(3) : 미리 타입을 지정하는 방법(추천)
    val a1 = intArrayOf(1,2,3) // int형만 넣을 수 있는 배열 생성
    val a2 = charArrayOf('a','b') // char형만 넣을 수 있는 배열 생성
    val a3 = doubleArrayOf(1.2,3.55) //double형만 넣을 수 있는 배열 생성
    val a4 = booleanArrayOf(true,false) // boolean 형만 넣을 수 있는 배열 생성

    //배열을 생성하는 방법(4) -> lamda를 활용하는 방법
    var a5 = Array(10,{ 0;2;true })
    var a6 = Array(5,{1;"2";3;4;5}) // 콤마가 아닌 세미콜론으로 배열원소 구분


    //Index 란
    // 0부터 시작
    //배열의 값을 꺼내는 방법(1)
    println(group1.get(0)) // 1출력

    //배열의 값을 꺼내는 방법(2)
    println(group2[3]) // "hello" 출력

    // 배열의 값을 바꾸는 방법1
    group1.set(0,100) //1번째 원소를 100으로 변경
    println(group1.get(0)) // 100출력

    //배열의 값을 바꾸는 방법2
    group2[0] = 200
    println(group2[0])


    





}