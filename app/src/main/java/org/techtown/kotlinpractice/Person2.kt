package org.techtown.kotlinpractice

import java.io.Serializable

class Person2(
    val id : Int? = null,
    val name : String? = null,
    val age:Int? = null,
    val intro:String? = null
) : Serializable {

}