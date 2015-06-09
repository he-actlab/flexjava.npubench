#!/bin/bash

rm -rf train.data/output/bin
mkdir train.data/output/bin

java -cp simpleRaytracer.jar:lib/ParrotObserver.jar Plane 2 1 30 10
mv parroto.data ./train.data/output/bin/2_1_30_10_simpleRaytracer.bin
