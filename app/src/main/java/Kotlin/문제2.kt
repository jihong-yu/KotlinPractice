package Kotlin

fun main(array: Array<String>){

//    var arithmeticOperation = arithmeticOperation(5,3)
//    println(arithmeticOperation.plus())
//    println(arithmeticOperation.mul())
//    println(arithmeticOperation.minus())
//    println(arithmeticOperation.div())

//    var Jihong = Count("유지홍","19941125")
//    Jihong.deposit(5000)
//    Jihong.deposit(5000)
//    Jihong.withdraw(11000)

    var a = Calculator3(5)
    println(a.plus(3).show())
}


class arithmeticOperation(var i:Int,var j:Int){

    fun plus () : Int{
        return i+j
    }

    fun minus () : Int{
        return i-j
    }

    fun mul () : Int{
        return i*j
    }

    fun div () : Int{
        return i/j
    }
}

class Calculator3(val initialValue: Int){
    fun plus(number:Int) : Calculator3{
        val result = this.initialValue + number
        return Calculator3(result)
    }

    fun show(): Int{
        return initialValue
    }
}

class Count(var name:String,var date:String ){

    var mutableMapCustomer = mutableMapOf<String,String>()
    var mutableMapCount = mutableMapOf<String,Int>()

    init {
        mutableMapCustomer.put(name,date)
        println("계좌 생성 완료")
    }
    fun createCount(){

    }
    fun checkCount(){
        if(mutableMapCount.get(name) != null)
            println(mutableMapCount.get(name))
        else
            println("계좌 잔액이 없습니다.")
    }

    fun deposit(price:Int){
        var oldPrice:Int=0
        if(mutableMapCount.get(name) != null){
         oldPrice = mutableMapCount.get(name) as Int
        }
        var newPrice = oldPrice + price
        mutableMapCount.put(name,newPrice)
        println("현재 잔액은: "+mutableMapCount.get(name) + "입니다.")
    }

    fun withdraw(price:Int){
        var oldPrice:Int=0
        var newPrice:Int=0

        if(mutableMapCount.get(name) != null && mutableMapCount.get(name) as Int >= price){
            oldPrice = mutableMapCount.get(name) as Int
            newPrice = oldPrice - price
        }else if(mutableMapCount.get(name) != null && price > mutableMapCount.get(name) as Int){
            println("출금금액이 예금금액보다 큽니다.")
            newPrice = mutableMapCount.get(name) as Int
        }else {
            newPrice = price
        }
        mutableMapCount.put(name,newPrice)
        println("현재 잔액은: " +mutableMapCount.get(name)+"입니다.")
    }
}