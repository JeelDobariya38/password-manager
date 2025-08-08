# Passcodes Project

An app that take down your headache of remembering password and auth related sensitive information in your head.. It is a secure and robust solution that not only, let you store password in a local storage. but also make the experience, much more fun and initiative..

## Outline

-   [Problem Statement](#problem-statement)
-   [Goals](#goals)
-   [Target Users](#target-users)
-   [Priority](#priority)

## Problem Statement

In modern days, data is important, especially data that has concern with auth information.. and store them securely is a serious task (and much of headache). Passcodes try to eliminate this headache. by provide you a simple, sweet and initiative ui that help you store your password in local storage. (and to extend also in cloud). `which means, you have full control of data you share with passcodes app`.

## Goals

-   To make password management process much more intuitive, enjoying, customizable & secure. By give you full (or utter more) control over your auth data. (that is shared or stored in passcodes app).
-   To simplify password management, yet give extensive control to end users.
-   To serve as guide, reference & extensible tool, for all people (nerds) who are interested in making there password management workflow, much more safe, secure and customizable.

## Target Users

1. `Not So Developer, Not So Normal-End-User` First:

    Which means,

    - For those, who wish to optimize, there password management workflow.. but don;t how to...
    - For those, who are willing to spend time learning a tool (specifically for password management) & for those how are willing to uncover (disambiguate) abstraction that app uses to make the experience of storing passwords with passcode much more simpler...
    - For those, who are willing to gain control of there auth data, without that extra headache of remember data that comes with it...
    - For those, who don't mind researching and squirm through our documentation for hrs, just for once in a while, to customize & optimize their password management workflow...

2. `Software Engineer` is our second target users:

    Which means,

    - the source code of the app, will much more likely to be clean, maintainable and extensible...
    - developers can make the app, in way they need... we will also have documentation that one can follow & learn from, about how to extend passcodes app...

3. `End Users` are the least target of application:

    Which means,

    - that the app will made in such way, in which we will try to abstract away the complex concepts in different extensible/decoupled systems... such that end users will more like feel that they are using a simple. but, secure password managing app...
    - if you fall in this category of target users, then it more like that you will either not use the app to it full potential, or more like you will became password management nerd, as app is full optimized to promote customization and optimization of your password management workflow...
    - Also, it more like a way to start using/integrating passcodes app into your daily password management workflow... but if wanna continue, then we recommend you, to try learn about your specific password management workflow and make yourself prompted from just normal end user to a more, better group (as in `Not So Developer, Not So Normal-End-User` group).. which will make you, the first target of app... which also mean that you will likely use app more effectively and will (at somepoint) end up optimizing your password management workflow, to make it more secure and customized to your specific needs...

---

## Priority

### `Customization & Extensibility is our top most priority, While simplicity is just not!!`:

Which means app will be fully (or to it most) be extensible & customizable, without limited by any abstraction and simplification of app. We as developer will try to make the internals of app more abstract and decoupled and thus, customizable. meanwhile doing we might loss simplicity.. and the UI/UX might look more complex then need...

Simplicity is not priority of the passcodes app. but abstracting concepts from users (as much as possible) surely is...

## `Security is our second most priority, While performance is just not!!`

Which clearly mean that app might have a ten different redundant, in software checks for data and user input. to ensure a robust operation.. This check might add performance overhead.. but as performance is not our priority, we might ignore this performance overhead, as we focus more on security, robustness and extensibility.

Also, performance is not even a thing, we consider optimizing for, while making passcodes app. and it's done for better or for worse. as passcodes app is not use daily. and also we believe, that security concerns associated wih password management app is more important then any performance issue ever arise can, and sure in modern work we have high speed computer and three different redundant check won;t hurt. but an app crash or data corruption might, As the data app deals with is highly sensitive, consider end user might end up save all there account form different platform at one place.... And sure a user can wait for 2 or 3 minutes if app is not responding and doing some redundant checks just ensure data is valid...

But that sure doesn't mean we will not focus on performance... if it became a serious issue we will try our best to solve the performance issues... but it just that, we might end up wait until something goes seriously go wrong with performance!! (e.g. app take time, to process a simple single store password action.. for straight 1 minute...)

## Motivation

We have pretty good and decent motivation that drive us forward, there are many previous project that back passcodes app... that were made piously by me(jeeldobariya38) and fellow contributor...

### Kupass

Github REPO: [Kupass](https://github.com/kudanilll/kupass).

#### Overview

It a simple much more intuitive password management solution.. develop by fellow contributor, daniel (@kudanill). It is one of recommended solution if you want to have easy to use app.. and don't need complexity and customization that passcodes offer you. as it simple ant intuitive.. it very much more secure as it all in your local storage...

#### Why Quit?

Daniel, the official owner of project, has stop provide further update as his priority change... there was no real design issue with project... it just that project was more made for eduaitional purpose and prototyping, hobby kind of purpose.

### Smart Manager

Github REPO: [Smart Manager](https://github.com/JeelDobariya38/Smart-Manager)

#### Overview

It was my first project in world of password manager and also in world of software engineering... it mostly a project, where i have made a lots of design mistakes... and try to make the code scalable and extensible and try implementing various stuff, that just don't fit together.. it perfect example, of use everything someone say is a community standard, without ever questioning why am i using it or wether is it worth adding to project.... it like if someone said use dependency injection make code better... then let just use it in our project....

#### Why Quit?

The project suffer from major design issue that, I realized very later, in project timeline. it happens to reach to the point where the code was literally just mess and it just don;t have core features... so i thing to dump the app, and idea as whole... never really thought that i might come back to same idea a year after and use it to build a app in android...
