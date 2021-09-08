package Kotlin

fun main(array: Array<String>){

    val a = mutableListOf<Int>(1,2,3,4,5,6,7,8,9)

    //반복하는 방법(1)

    for ( item in a ){
        print(""+item+" ") //1 2 3 4 5 6 7 8 9 출력
    }
    println()

    //반복하는 방법(2)
    for( (index,item) in a.withIndex()){ //index와 item에 각각 index와 value값 들어감
        println("index: " + index + " item: "+item)
    }

    //index: 0 item: 1
    //index: 1 item: 2
    //index: 2 item: 3
    //index: 3 item: 4
    //index: 4 item: 5
    //index: 5 item: 6
    //index: 6 item: 7
    //index: 7 item: 8
    //index: 8 item: 9 출력

    //반복하는 방법(3)
    a.forEach {
        //it에 a의 원소가 들어감
        print(""+it+" ") //1 2 3 4 5 6 7 8 9
    }
    println()
        //반복하는 방법(4)
        a.forEach { item -> //item에 a의 원소가 들어감
            print(""+item+" ") //1 2 3 4 5 6 7 8 9
        }

    println()

    //반복하는 방법(5) 람다식을 이용
    a.forEachIndexed { index, i -> //자동으로 index에는 index값이 i는 value값이 들어감
        println("index: " + index + " item: "+i)
    }

    //반복하는 방법(6) until로 범위 지정
    for (i in 0 until a.size){ //0~(a.size-1)까지 반복
        print(""+a[i]+" ") //1 2 3 4 5 6 7 8 9 출력
    }
    println()

    //반복하는 방법(7) step추가
    for (i in 0 until a.size step 2){ //0,2,4,6,8 로 2step씩 증가
        print(""+a[i]+" ") //1,3,5,7,9 출력
    }
    println()

    //반복하는 방법(8) downTo사용
    for (i in a.size -1 downTo 0){// 8부터 0까지 반복(0 포함)
        print(""+a[i]+" ") //9,8,7,6,5,4,3,2,1 출력
    }
    println()
    //반복하는 방법(9) downTo에 step추가
    for ( i in a.size -1 downTo 0 step 2){//8 6 4 2 0 인덱스 출력
        print(""+a[i]+" ") //9,7,5,3,1 출력
    }

    println()

    //반복하는 방법(10) ..이용
    for ( i in 0..8){ //0부터 8까지 인덱스 출력(8도포함)
        print(""+a[i]+" ") //1,2,3,4,5,6,7,8,9 출력
    }
    println()


}