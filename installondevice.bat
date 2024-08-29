@REM Build & install on Android phone

@REM Force continuation after Gradle command by capturing the output
if "%~1"=="prod" (
    echo building a production build
    call gradlew clean assembleProdRelease
    if ERRORLEVEL 1 (
        echo Gradle build failed! Check build_output.txt for details.
        exit /b 1
    )
) else (
    echo building a development build
    call gradlew assembleDevDebug
    if ERRORLEVEL 1 (
        echo Gradle build failed! Check build_output.txt for details.
        exit /b 1
    )
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
if "%~1"=="prod" (
    echo Installing a production apk
    adb install ./app/build/outputs/apk/prod/release/app-prod-universal-release.apk
    if ERRORLEVEL 1 (
        echo Gradle build failed! Check build_output.txt for details.
        exit /b 1
    )
) else (
    echo Installing a dev apk
    adb install ./app/build/outputs/apk/dev/debug/app-dev-universal-debug.apk
    if ERRORLEVEL 1 (
        echo Gradle build failed! Check build_output.txt for details.
        exit /b 1
    )
)
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
