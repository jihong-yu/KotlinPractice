package Kotlin

//변수의 접근 범위
// 1. 지역변수
// 2. 전역변수
var number100:Int = 10 //파일내부의 전역변수

fun main(args:Array<String>){



}

class Reward(name:String){
    var name : String = name // 클래스 내부에서의 전역변수
    var rewardAmount: Int = 1000

    fun fun0(name:String){
        var fun0Name = name //fun0함수에서의 지역변수수
   }
    fun fun1(string: String){
        name = string // 클래수 내부의 name전역변수에 접근가능하다.
        number100 = 10 //파일 내부의 전역 변수에 접근 가능하다.
        //fun0Name = "good" //접근할 수 없다. fun0의 지역변수 이기 때문이다.
    }

}

class Test(private var name:String){

    //name이라는 변수는 외부에서 접근 불가

    private fun testFun(){ //testFun함수는 외부에서 접근 불가
        name = "홍길동"
        var number100:Int = 100
        fun innerFun(){

        }
    }

}

class Running{

    fun runSlow(){
        run("slow") //run함수를 내부에서이용
    }
    fun runFast(){
        run("fast") //run함수를 내부에서이용
    }

    private fun run(Speed:String){ //외부에서는 접근 불가
        //.....생략
    }

}