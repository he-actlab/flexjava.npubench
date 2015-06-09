#!/bin/bash

for f in test.data/input/*.rgb
do
	filename=$(basename "$f")
	extension="${filename##*.}"
	filename="${filename%.*}"
	java -cp sobel_nn.jar:lib/ParrotObserver.jar:lib/fannj-0.6.jar:lib/jna-4.1.0.jar Sobel.EdgeDetection ${f}
done