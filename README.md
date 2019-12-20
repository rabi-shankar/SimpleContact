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

* Introduction
* Main Navigation
* OTP Sender
* Message Recorder
* DataBase

![OTP send Process high level abstract](/images/fly4.png)
Fig 2.0 Application Control flow

### Introduction
![](/images/screenshot_1.png)  Fig 3.0 Introduction screen


This is application introduction page. This appears when the app is launching first time after onward this part is skipped and directly show the main Navigation page. But if user press back the show introduction page then exit.

### Main Navigation
![](/images/screenshot_2.png)  Fig 4.0 Main Navigation Screen


##### The function of Main Navigation

* Navigation between Contact and Message
* Give access to Open library License activity.

### OTP sender
The function of OTP Sender
* Manage List of contacts
* Generation of 6 digit OTP
* Check internet connection before sending OTP and
* Verify OTP


Fig 5.0 Contact detail Screen | Fig 5.1 Send OTP Screen | Fig 5.3 verify OTP Screen
--- | --- | ---
![](/images/screenshot_3.png)| ![](/images/screenshot_4.png) | ![](/images/screenshot_5.png)



### Message Recorder
The function of Send Recorder module
* Display the list of OTP send Messages


Fig 6.0.1 send Message Screen | Fig 6.0.2  Message record Screen 
--- | --- 
![](/images/screenshot_6.png)| ![](/images/screenshot_7.png) 


#### Database
The function of the Database module
* Save send a message with OTP in SQLite
* Communication of data between OTP sender and Message Recorder

![](/images/fly2.png) Fig 7.0 Database connection diagram

####  PHP Server
This intermediate server running in apache server provide by 000webhost (powered by
Hostinger). The script is written in PHP (single file script size approx: 50 lines of code)
* https://in.000webhost.com/

![](/images/fly5.png)


#### Fast2SMS

SMS Gateway API. Our Bulk SMS API work with PHP, JAVA, C#, C, Python, Ruby, Javascript,
NodeJS, etc. Secure, robust and easy to integrate APIs to send Promotional, Transactional &
Quick Transactional SMS via REST API.

https://docs.fast2sms.com/

#### Phone
Get SMS from AX-FSTSMS content OTP.
![Simple](/images/fly6.png)
