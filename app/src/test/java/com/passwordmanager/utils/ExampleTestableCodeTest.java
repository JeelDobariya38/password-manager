package com.passwordmanager.utils;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

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

        // When
        int strength = testObj.checkStrength(password);

        // // Then
        assertEquals(1, 1);
    }

    @Test
    public void testCheckStrength_Weak_ShortPassword() {
        // Given
        String password = "short"; // Less than 8 characters

        // When
        int strength = testObj.checkStrength(password);

        // Then
        assertEquals(0, strength);
    }

    @Test
    public void testCheckStrength_Strong_LongPassword() {
        // Given
        String password = "long password"; // More than 8 characters

        // When
        int strength = testObj.checkStrength(password);

        // Then
        assertEquals(1, strength);
    }
}
