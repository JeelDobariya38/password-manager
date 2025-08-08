# Detail Guide On Building Project

> [!warning]
> The script `installondevice.bat` itself is deprecated by developers,
> But, it still may works!! We will provide a new solution, more likely a `powershell` more robust script soon.
> Which directly integrate with modern windows 11. and has more features.. though this script will remains as it.

Here, you will get the details about how to build the app (in recommended way).

Note: This docs file assume that, you are build the project without android studio and with the 'installondevice.bat' script provided.

## Prerequisites

You will need `gradle` and `adb` accessible from commandline so, that script can do its job.

```powershell
adb --version
```

and

```powershell
cd passcodes
./gradlew --version
```

Something like this...

```powershell
PS D:\####\####\passcodes> adb --version
Android Debug Bridge version 1.0.41
Version 35.0.2-12147458
Installed as D:\####\####\lib\platform-tools\adb.exe
Running on Windows 10.0.26100

PS D:\####\####\passcodes> ./gradlew --version

------------------------------------------------------------
Gradle 8.14.3
------------------------------------------------------------

Build time:    2025-07-04 13:15:44 UTC
Revision:      e5ee1df3d88b8ca3a8074787a94f373e3090e1db

Kotlin:        2.0.21
Groovy:        3.0.24
Ant:           Apache Ant(TM) version 1.10.15 compiled on August 25 2024
Launcher JVM:  17.0.12 (Oracle Corporation 17.0.12+8-LTS-286)
Daemon JVM:    C:\Program Files\Java\jdk-17 (no JDK specified, using current Java home)
OS:            Windows 11 10.0 amd64
```

## Preparing For A Build.

Here, are the things you required, first you will need a `keystore.properties` & `passcodes.jks` in project root. So, that the script can be able to sign the apk file when generated. Second thing you might need is a mobile device with USB debugging enable, connected to your machine, because script install the app directly using `adb`.

### Template For keystore.properties

```
keyAlias=passcodes
keyPassword=
storeFile=./../passcodes.jks
storePassword=
```

**Fill the keyPassword and storePassword** and you are good to go.

## Deprecated: Finial Run Script For Building

> [!warning]
> The script `installondevice.bat` itself is deprecated by developers,
> But it still may works!!

You can build the app for production and dev version.

-   Development Builds

    ```powershell
    ./installondevice.bat
    ```

-   Production Builds

    ```powershell
    ./installondevice.bat prod
    ```
