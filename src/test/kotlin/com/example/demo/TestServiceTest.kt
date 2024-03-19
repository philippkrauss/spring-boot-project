package com.example.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestServiceTest {

    @Test
    fun test() {
        assertEquals("asdf", TestService().test())
    }
}