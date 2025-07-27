package com.jeeldobariya.passcodes.utils;

import org.junit.Test;
import org.junit.Before;
import static com.google.common.truth.Truth.assertThat;

public class ExampleTestableCodeTest {
    private ExampleTestableCode testObj;

    @Before
    public void setup() {
        testObj = new ExampleTestableCode();
    }

    @Test
    public void testCheckStrength_With_EmptyPassword() {
        // Given
        String password = "";

        // When & Then
        assertThat(testObj.checkStrength(password)).isEqualTo(-1);
    }

    @Test
    public void testCheckStrength_Weak_ShortPassword() {
        // Given
        String password = "short"; // Less than 8 characters

        // When & Then
        assertThat(testObj.checkStrength(password)).isEqualTo(0);
    }

    @Test
    public void testCheckStrength_Strong_LongPassword() {
        // Given
        String password = "long password"; // More than 8 characters

        // When & Then
        assertThat(testObj.checkStrength(password)).isEqualTo(1);
    }
}
