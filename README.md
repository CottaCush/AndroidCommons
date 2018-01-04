# Android Commons
A library for faster development of android applications by Cotta & Cush Ltd.

[![Build Status](https://travis-ci.org/CottaCush/AndroidCommons.svg?branch=master)](https://travis-ci.org/CottaCush/AndroidCommons)
[![codecov](https://codecov.io/gh/CottaCush/AndroidCommons/branch/master/graph/badge.svg)](https://codecov.io/gh/CottaCush/AndroidCommons)


# Getting Started
  Using gradle:
```
    dependencies {
        compile 'com.github.CottaCush:AndroidCommons:-SNAPSHOT'
    }

    allprojects {
        repositories {
            maven {url 'https://jitpack.io'}
        }
    }

```
   This library requires build tools version 25
##  Running tests and Code coverage
   Steps required to generate coverage reports locally.
#### Unit tests coverage reports
   Open terminal , cd to the project path and run

   ```
      ./gradlew jacocoTestReport
   ```
   The generated reports will be located at `[path_to_the_project]/app/build/reports/jacoco/jacocoTestDebugUnitTestReport/html`
#### Instrumentation tests coverage reports
   Open terminal , cd to the project path and run

   ```
      ./gradlew connectedCheck
   ```
   The generated reports will be located at `[path_to_the_project]/app/build/reports/coverage/debug`

## Versioning
[SemVer](http://semver.org/) is used for versioning.
