#!/bin/bash

rm -rf train.data/output/bin
mkdir train.data/output/bin

java -cp jmeint.jar:lib/ParrotObserver.jar JMEIntTest 10
mv parroto.data ./train.data/output/bin/100_jmeint.bin
