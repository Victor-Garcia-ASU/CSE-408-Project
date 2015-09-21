# CSE408 Project 1 

Authors: Victor Garcia, Jake Krammer, Saul Figueroa, Runyu Jin, Manuel Grill

System Requirements:
* Windows Vista or later (Any other programs/libraries mentioned later should be installed for the appropriate OS)
* Tested using Java Version 1.8.0_11 (recommended) but Java 1.7.X should work 
* OpenCV library for Java/Eclipse version 3.0.0
* Administration privileges to change path variables 
* Eclipse IDE for Java Developers (64 bit) for Windows

Installation Instructions:
1) Download and install the appropriate version of Java 1.7.X for your OS.
2) Add the path to the jdk bin directory to the path variable in system variables.
3. Download and install OpenCV 3.0
4. Add the path to the Opencv bin to the path variable in system variables. example C:\opencv\build\java\x64
5. Make sure “opencv_ffmpeg300_64.dll” file is in that bin directory if not add it from opencv->build->x64->vc12->bin
6. Clone repository to desktop and grab both Program.java and DisplayColorMap.java and place them in src directory of Eclipse.

Execution Instructions: 
1. In eclipse navigate to Window –> Preferences from the menu.
2. Navigate under Java –> Build Path –> User Libraries and click New (give it a name).
3. Click on Add External JARs, traverse through the OpenCV installation and select opencv-300.jar.
4. Finally on go to Native library location and press Edit. Set the location to C:\opencv\build\java\x64.

