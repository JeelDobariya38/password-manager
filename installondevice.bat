@REM Build & install on Android phone

@REM Force continuation after Gradle command by capturing the output
call gradle build
if ERRORLEVEL 1 (
    echo Gradle build failed! Check build_output.txt for details.
    exit /b 1
)

@REM Start ADB server
adb start-server
if ERRORLEVEL 1 (
    echo Failed to start ADB server! Exiting...
    exit /b 1
)

@REM Check if a device is connected
adb devices
if ERRORLEVEL 1 (
    echo No device found! Make sure your device is connected and USB debugging is enabled.
    adb kill-server
    exit /b 1
)

@REM Install the APK on the connected device
adb install ./app/build/outputs/apk/release/app-universal-release.apk
if ERRORLEVEL 1 (
    echo APK installation failed! Exiting...
    adb kill-server
    exit /b 1
)

@REM Kill ADB server after installation
adb kill-server
if ERRORLEVEL 1 (
    echo Failed to stop ADB server! Exiting...
    exit /b 1
)

echo Build and installation completed successfully!
exit /b 0
