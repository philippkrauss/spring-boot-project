package com.example.demo

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class TestDto(val test: String)

@RestController
@RequestMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE])
class TestController(private val testService: TestService) {

    @GetMapping("/test")
    fun test(
        @RequestParam("prefix", required = false) prefix: String?,
    ): TestDto {
        val test = testService.test()
        return TestDto(prefix.orEmpty() + test)
    }
}