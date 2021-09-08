package Kotlin


// 상속
// 부모의 기능을 이용하기 위해 상속받음

fun main(array: Array<String>){


}

open class ParentCar{

    constructor(speed:Int , model:String){

    }

    constructor(speed:Int , model:String,size:Int){

    }
    open var parentInt:Int = 5


    open fun drive(){
        println("달린다")
    }

    fun stop(){

    }
}

class SuperCar100(speed3: Int,model: String) : ParentCar(speed3,model){ //상속받음

    override var parentInt: Int
        get() = super.parentInt
        set(value) {}

    override fun drive() {
        super.drive()
    }

}

class MyCar{
    fun drive(){

    }

    fun stop(){

    }
}

class MyBus{
    fun drive(){

    }

    fun stop(){

    }
}

class MySuperCar{
    fun drive(){

    }

    fun stop(){

    }
}

class MyTruck{
    fun drive(){

    }

    fun stop(){

    }
}

