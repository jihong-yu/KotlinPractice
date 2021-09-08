
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NullSafety{

    class Car(val number: Int) {

    }

    lateinit var lateCar: Car //늦게 초기화한다 단,초기화 하지 않을 경우 에러발생생


        val car = Car(20)


        val number: Int = 105
        val number1: Int? = null


        val number5 : Int = number1!! + 10 //number1이 null일수도 있지만 number1이 Null이 아니라고 개발자가 보장함


        val number3 = number1?.plus(number) //number1이 null이 아니면 더하기실행 아니면 null반환


        //삼항연산자 -> 엘비스 연산자(?:)
        //Null safety를 위한 도구
        val number4 = number ?: 10 //number1이 Null이면 뒤에값(10) 대입 아니면 number1값 대입

    fun plus(a: Int, b: Int?): Int? {
        if (b != null) return a + b
        else return a //반환형을 Int로 잡았기 때문에 모든 조건에 대하여 return값을 설정해야됨됨    }
    }

    fun plus2(a: Int, b: Int?): Int? { //?를 사용함으로써 Null일수도 있다라는것을 명시
        return b?.plus(a)
    }


}