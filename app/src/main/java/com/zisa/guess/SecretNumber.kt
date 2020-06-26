package com.zisa.guess

import java.util.*

class SecretNumber {
    //var secrent : Int = Random().nextInt(10) + 1
    var secrent : Int = 6
    var count = 0

    fun validate(number : Int) : Int
    {
        count++
        return number - secrent;
    }

    fun reset()
    {
        //secrent = Random().nextInt(10) + 1
        secrent = 6
        count = 0

    }
}

fun main()
{
    val secretNumber = SecretNumber()
    println(secretNumber.secrent)
    println(secretNumber.validate(2))
    println("${secretNumber.validate(2)},  count: ${secretNumber.count}")
}