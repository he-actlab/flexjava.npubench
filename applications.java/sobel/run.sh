#!/bin/bash

javac -d . TextFile.java EdgeDetection.java
java Sobel.EdgeDetection test.rgb
