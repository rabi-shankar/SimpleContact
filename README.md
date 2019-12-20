![Simple Contact](/images/fly3.png)

# Simple Contact
 Android app that can send an OTP  to a list of contacts using online services Fast2SMS.



### Overview
Android app that can send an OTP to a list of contacts using online services Fast2SMS.
OPT is delivery through Four steps.
1. The android app generates OTP and sends using HTTP request to PHP WebServer.
1. PHP Server Call Fast2SMS API.
1. Fast2SMS send OTP to Phone
1. User verify OTP



![OTP send Process high level abstract](/images/fly1.png)
Fig 1.0 OTP send Process high level abstract




### Project Modules and Config
1. Android app
1. PHP Server
1. Fast2sms
1. Phone


Minimum SDK Version 21
Target SDK Version 28




### Android app

Simple and small android app design base on google material design principle. the app has
five part

' Introduction
' Main Navigation
' OTP Sender
' Message Recorder
' DataBase

![OTP send Process high level abstract](/images/fly4.png)
Fig 2.0 Application Control flow

### Introduction

<img align="right" src="/images/screenshot_1.png" height="50%">

This is application introduction page. This appears when the app is launching first time after onward this part is skipped and directly show the main Navigation page. But if user press back the show introduction page then exit.
