package Kotlin

fun main(array: Array<String>){

    val night = Night(20,4)
    val monstert = Monstert(15,3)

    night.attack(monstert)
    monstert.attack(night)

    // night.hp = 10000 //에러 발생

}
class Night(private var hp:Int,private var power: Int){

    fun attack(monstert: Monstert){
        monstert.defense(power)
    }

    fun defense(damage: Int){
        hp -= damage
        if(hp>0) {
            heal()
            println("기사의 체력은 $hp 입니다.")
        }
        else println("기사가 죽었습니다.")
    }

    private fun heal(){ //외부애서 힐에 접근 불가
        hp += 3
    }
}

class Monstert(private var hp:Int,private var power: Int){

    fun attack(night: Night){
        night.defense(power)
    }

    fun defense(damage: Int){
        hp -= damage
        if(hp>0) println("몬스터의 체력은 $hp 입니다.")
        else println("몬스터가 죽었습니다.")
    }

    fun heal(){

    }
}