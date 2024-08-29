# Detail Guide On Installion

This file give you a deep guide on installion & updation process. we hope that you have already readed the step written in readme but, aren;t satisfied with that and need more info and guide on installion and updation.

## Installion Process

For inital installion of app, you follow step given below it work in most sinerio.

1. Checking whether the app can run on your device or not.
   
    - go to your setting app and check whether you are on android 8+ or not?
   
    - if no, then sorry app is not comatible with your device.
   
    - if yes, then mostly you are good to go but, officially we only support device between android 8 to 13. for more high android version app can be runned but we don;t officially support it.

2. After completing first step, Picking the version of app to install.

    - Go to our [github repository release page](https://github.com/JeelDobariya38/password-manager/releases). There you will fing a list of release avaiable.
  
    - pick up any one of the release you like. (we recommend you pick a good release [how can you find a good release](#Guide-to-find-a-best-release))?
  
    - after pick up a release, you can read the changelog to know what change (optional step)


3. Downloading apk files
    - after pick up a release, you can scroll to the assets and download the apk that better fits your phone cpu architecture.

    - if you don;t know cpu architecture. just download the apk file that has universal in its name.

4. Installing apk files.
    - install the apk file. the apk file will be downloaded in your browser (probably like chrome, firefox or edge).

    - open the app.
  
if any problem occur while performing the above 4 step. report your problem using github issues after checking other support docs avaiable in github repositoy "docs/" folder.

## Updation Process

As you are reading this part, we hope that you have password-manager app installed on your phone and now, you want update it.

you can follow the below step. they work for most senerios.

> [!IMPORTANT]
> updating from one version to another might result into data loss though it occur very rarely. but, it good to take a data backup.
> occur mostly when you update from one major version to another major version meaning from 1.x.x to 2.x.x.

1. Checking Whether The Update Exist Or Not
    - compare your current app version (you can find it in app info in setting app) with lastest app update.
  
    - check whether a update exist or not? 
  
    - if no, then you can;t update but, can surely enquire about the next upadate using github issues.
  
    - if yes, check how big the upadte is and how it affect your expirence as user?
  
    - to find the upadate impact you can check what the difference between your version and new version you updating to.
  
    - eg, if you update from 1.x.x to 2.x.x the the change will be big. if you update from 1.x.0 to 1.x.2 then change might be not visible even.

2. Downloaded the updated apk files.
    - it is same as you did earlier while installing the app.

    - goto release page and download the asset from select release. refer intallion guide above if needed.

3. Update the app.
    - if, you updating to a new major meaning from 1.x.x to 2.x.x then, don;t forget to backup your data.
  
    - now install the new apk file it will ask you to upgrade instead of install.
 
    - open the app and enjoy your updated expirence.

4. Restore the backup if need.
    - if, needed restore the files/data back. or even revert to prevoius release if, problem occurs.

    - we admire the contribution even in form of your exprience with app. meaning, report your data erase problem it help us make better exprience & fix bug that might be missed by us.

## Guide to find a best release

To find a best release you should have knowledge of password-manager versioning system and type of release we release.

### release types

On our github release page you will find majorly `5 (3+2)` type of release. where each type of release label tell you something about release.
Each release on github may have a label but, some even lack them completly. These label tends to tell more about release, so they are IMPORTANT to consider.

Three type that are there in app itself, you find them in release title on github: 
(this tags/labels can;t change after initial release of that version is done).
    - Alpha: special design for development purpose, not intented to use by end users, they comes with bugs and venurablitity.
    - Beta: release that can be used by mass audience, can contain minior bugs.
    - Stable: are likey to be more stable then others release type, contain nearly zero bugs as they are well tested.

Two type are specific to github release. they are label on release itself:
(this tags can label can change with time as they are dynamic and are not tied to app itself. meaning they are no hard coded anywhere)
    - Pre Release: this release are not so good to install or to stick with. they are generally like `marked as deperacted` or more like `yanked release`
    - Latest: this release label make a release as the best up to date release to install.

> [!NOTE]
> this label or flag changes with time. mean a app after it release can marked later as pre-release as there are lots of bug found or as latest release as it all good after test in production enviroment.

So, here is a list in order where starting type are best and ending types are worse.

    - STABLE (the best) > LATEST > BETA > ALPHA > PRE-RELEASE > RELEASE-WITH-NO-FLAGS (the worst)


> [!NOTE]
> Some release can even have two flag at a time. for example, stable and pre-release. I such case consider the github base label in this case pre-release. as they are dynamic and provide the latest infomation from us (developer part).
