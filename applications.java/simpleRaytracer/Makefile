BASE_DIR=${HOME}/axbench
PLANG=${BASE_DIR}/parrot.java/src/ParrotObserver/plang.py
PARROT_JSON=${BASE_DIR}/parrot.java/src/ParrotObserver/ParrotC.json
PARROT_LIB=${BASE_DIR}/parrot.java/src/ParrotLib/Parrot

all:
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/Plane.java" -o "./obj/Plane.java" -t
	cp -f ${PARROT_LIB}/ParrotObserver.jar ./lib/ParrotObserver.jar
	ant
