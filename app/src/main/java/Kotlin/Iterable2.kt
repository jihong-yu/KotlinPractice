package Kotlin

fun main (array: Array<String>){

    var a: Int = 0
    var b: Int = 4

    while(a<b){ // a<b 가 성립할때 반복문 실행
        a++ //while문이 무한 루프가 돌지 않도록 a값 증가
        print(""+a+" ") //1 2 3 출력
        if(a == 3) break //무한이 돌지 않도록 break문 추가
    }
    println()
    a = 5
    b = 3
    do{
        println("이 식은 조건과 상관없이 최소 1번은 실행 합니다.")
    }while(a<b)


}