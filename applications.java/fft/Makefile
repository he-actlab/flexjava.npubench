BASE_DIR=${HOME}/flexjava.npubench
PLANG=${BASE_DIR}/parrot.java/src/ParrotObserver/plang.py
PARROT_JSON=${BASE_DIR}/parrot.java/src/ParrotObserver/ParrotC.json
PARROT_LIB=${BASE_DIR}/parrot.java/src/ParrotLib/Parrot

all:
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/FFT.java" -o "./obj/FFT.java" -t
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/Random.java" -o "./obj/Random.java" -t
	#cp -f ${PARROT_LIB}/ParrotObserver.jar ./lib/ParrotObserver.jar
	ant
