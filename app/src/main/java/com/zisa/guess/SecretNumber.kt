package com.zisa.guess

import java.util.*

class SecretNumber {
    val secrent : Int = Random().nextInt(10) + 1
    var count = 0

    fun validate(number : Int) : Int
    {
        count++
        return number - secrent;
    }
}

fun main()
{
    val secretNumber = SecretNumber()
    println(secretNumber.secrent)
    println(secretNumber.validate(2))
    println("${secretNumber.validate(2)},  count: ${secretNumber.count}")
}