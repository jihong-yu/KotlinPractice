package org.techtown.kotlinpractice

import java.io.Serializable

class Song(
    val title: String? = null,
    val thumbnail:String? = null,
    val song:String? = null
) : Serializable {
}