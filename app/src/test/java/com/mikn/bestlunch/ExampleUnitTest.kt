package com.mikn.bestlunch

import com.mikn.bestlunch.model.GurunaviAPiService
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun reqApi() {
        GurunaviAPiService().getRestaurant().apply {
            this?.rest?.forEach {
                print(it.toString())
            }
        }
    }
}