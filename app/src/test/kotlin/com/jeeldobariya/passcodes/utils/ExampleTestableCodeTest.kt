package com.jeeldobariya.passcodes.utils

import org.junit.Test
import org.junit.Before
import com.google.common.truth.Truth.assertThat

class ExampleTestableCodeTest {
    private lateinit var testObj: ExampleTestableCode

    @Before
    fun setup() {
        testObj = ExampleTestableCode()
    }

    @Test
    fun `should handle empty passwords`() {
        // Given
        val password = ""

        // When & Then
        assertThat(testObj.checkStrength(password)).isEqualTo(-1)
    }

    @Test
    fun `should detect short password as weak`() {
        // Given
        val password = "short" // Less than 8 characters

        // When & Then
        assertThat(testObj.checkStrength(password)).isEqualTo(0)
    }

    @Test
    fun `should detect log password as weak`() {
        // Given
        val password = "long password" // More than 8 characters

        // When & Then
        assertThat(testObj.checkStrength(password)).isEqualTo(1)
    }
}
