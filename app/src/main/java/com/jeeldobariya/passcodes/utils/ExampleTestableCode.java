package com.jeeldobariya.passcodes.utils;

class ExampleTestableCode {
    int checkStrength(String password) {
        if (password == null || password.isEmpty()) {
            return -1;
        }

        int length = password.length();

        if (length < 8) {
            return 0;
        } else {
            return 1;
        }
    }
}
