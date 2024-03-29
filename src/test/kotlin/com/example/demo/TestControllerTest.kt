package com.example.demo

import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(
    controllers = [TestController::class]
)
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TestControllerTest(
    private val mockMvc: MockMvc,
    private val testService: TestService,
) {
    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @TestConfiguration
    class MockConfig {
        @Bean
        fun testService(): TestService = mockk()
    }

    @Test
    fun `test test`() {
        every { testService.test() } returns "bsdf"

        mockMvc.get("/test")
            .andExpect { status { isOk() } }
            .andExpect { content { json("{\"test\":  \"bsdf\"}", strict = true) } }

        verify(exactly = 1) { testService.test() }
        confirmVerified(testService)
    }

    @Test
    fun `test test with prefix`() {
        every { testService.test() } returns "bsdf"

        mockMvc.get("/test?prefix=some-prefix_")
            .andExpect { status { isOk() } }
            .andExpect { content { json("{\"test\":  \"some-prefix_bsdf\"}", strict = true) } }

        verify(exactly = 1) { testService.test() }
        confirmVerified(testService)
    }
}