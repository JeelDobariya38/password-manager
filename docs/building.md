# Detail Guide On Building Project

Here, you will get the details about how to build the app (in recommended way).

Note: This doc file assume that, you are build the project without android studio and with the 'scripts/installondevice.bat' script provided.

## Prerequisites

You will need `gradle` and `adb` accessible from commandline so, that script can do its job.

something like this...
```
PS C:\Users\HP> gradle help

> Task :help

Welcome to Gradle 8.9.

Directory 'C:\Users\HP' does not contain a Gradle build.

....

BUILD SUCCESSFUL in 1s
1 actionable task: 1 executed

PS C:\Users\HP> adb help
Android Debug Bridge version 1.0.41
Version 35.0.2-12147458
Installed as C:...\cmdline-tools\lib\platform-tools\adb.exe

....
```

## Preparing For A Build.

Here, are the things you required, first you will need a `keystore.properties` & `passwordmanager.jks` in project root. So, that the script can be able to sign the apk file when generated. second thing you might need is a mobile device with use debugging connect to your machine because script install the app dirctly using `adb`.

### Template For keystore.properties

```
keyAlias=passwordmanager
keyPassword=
storeFile=./../passwordmanager.jks
storePassword=
```

**Fill the keyPassword and storePassword** and you are good to go.

## Finial Run Script For Building

you can build the app for production and dev version.

Dev Builds
```bat
installondevice.bat
```

Production Builds
```
installondevice.bat prod
```
