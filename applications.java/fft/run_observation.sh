#!/bin/bash

rm -rf train.data/output/bin
mkdir train.data/output/bin

java -cp fft.jar:lib/ParrotObserver.jar FFT 100
mv parroto.data ./train.data/output/bin/100_fft.bin
