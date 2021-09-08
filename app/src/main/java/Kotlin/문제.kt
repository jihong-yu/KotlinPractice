package Kotlin

fun main(array: Array<String>) {

//    first()
//    second(80)
//    third(52)
    gugudan()
}

fun first() {
    val numberList = mutableListOf<Int>();
    val numberList2 = mutableListOf<Boolean>();

    val list1 = MutableList(10,{0})
    val list2 = MutableList(10,{true})

    println(list1)
    println(list2)

    for (i in 0..9) {
        list1.set(i,i)
        list2[i] = (list1[i] % 2 == 0)
    }
    println(list1)
    println(list2)
}

fun second(num: Int) {

    when (num) {
        in 80..90 -> println("학점: " + "A")
        in 70..79 -> println("학점: " + "B")
        in 60..69 -> println("학점: " + "C")
        else -> println("학점: " + "F")
    }
}

fun third(num: Int) {

    var a = num / 10
    var b = num % 10

    println(a + b)
}

fun gugudan() {
    for (i in 1 until 10)
        for (j in 1 until 10)
            println("$i * $j = ${i*j}")
        println()
}

