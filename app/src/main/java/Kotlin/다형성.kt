package Kotlin

import java.lang.Exception

fun main(array: Array<String>){

    val a = eval2(Sum2(Sum2(Num2(1),Num2(2)),Num2(4)))
    println(a) // 7 출력
}

interface Expr2 //다형성 구현을 위해 사용될 부모 expr 인터페이스
class Num2(val value:Int):Expr2 //expr 상속함으로써 다형성 장착 완료
class Sum2(val left:Expr2,val right:Expr2):Expr2 //expr 상속함으로써 다형성 장착 완료

fun eval2(e:Expr2):Int{

    if(e is Num2){
        val n = e
        return n.value
    }
    if(e is Sum2){
        return eval2(e.left) + eval2(e.right)
    }
    throw Exception("Unknown expression")
}