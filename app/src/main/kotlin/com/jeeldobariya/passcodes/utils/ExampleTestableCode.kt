package com.jeeldobariya.passcodes.utils

class ExampleTestableCode {
    fun checkStrength(password: String): Int {
        if (password.isEmpty()) {
            return -1;
        }

        val length = password.length;

        if (length < 8) {
            return 0;
        } else {
            return 1;
        }
    }
}
