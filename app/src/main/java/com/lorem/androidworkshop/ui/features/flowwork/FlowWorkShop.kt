package com.lorem.androidworkshop.ui.features.flowwork

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main(){
    println("****************************************************************************")
    println("****************************************************************************")
    println("****************************************************************************")

    runBlocking {
       val flow1 =  flow {
            for (i in 1..5) {
                delay(1000)
                emit(i)
            }
        }
        val flow2 =  flow {
            for (i in 'a'..'g') {
                delay(300)
                emit(i)
            }
        }


        val flow3 =  flow {
            for (i in 100..105) {
                emit(i)
            }
        }

        println("\nmerge ****************")

        merge(flow1,flow2,flow3).collect {
            print(" ".plus(it).plus(","))
        }

        println("\nzip ****************")

        flow1.zip(flow2) { a, b ->
            Pair(a,b)
        }.zip(flow3) { pair, i ->
            "${pair.first} ${pair.second} $i, "
        }.collect {
            println(it)
        }


        println("\ncombine ****************")

        flow1.combine(flow2){ a,b ->
            "$a $b ,"
        }.collect {
            print(it)
        }


    }

}

