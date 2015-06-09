#!/bin/bash

rm -rf train.data/output/bin
mkdir train.data/output/bin

for f in train.data/input/*.rgb
do
	filename=$(basename "$f")
	extension="${filename##*.}"
	filename="${filename%.*}"
	java -cp sobel.jar:lib/ParrotObserver.jar Sobel.EdgeDetection ${f}
	mv parroto.data ./train.data/output/bin/${filename}_sobel.bin
done