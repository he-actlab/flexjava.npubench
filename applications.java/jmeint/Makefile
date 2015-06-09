BASE_DIR=${HOME}/axbench
PLANG=${BASE_DIR}/parrot.java/src/ParrotObserver/plang.py
PARROT_JSON=${BASE_DIR}/parrot.java/src/ParrotObserver/ParrotC.json
PARROT_LIB=${BASE_DIR}/parrot.java/src/ParrotLib/Parrot

all:
	rm -rf obj
	mkdir obj
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/JMEIntTest.java" -o "./obj/JMEIntTest.java" -t
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/Intersection.java" -o "./obj/Intersection.java" -t
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/FastMath.java" -o "./obj/FastMath.java" -t
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/Vector2f.java" -o "./obj/Vector2f.java" -t
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/Vector3f.java" -o "./obj/Vector3f.java" -t
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/JmeException.java" -o "./obj/JmeException.java" -t
	python ${PLANG} -c ${PARROT_JSON} -a observe -e "javac -d ./bin" -s "./src/Debug.java" -o "./obj/Debug.java" -t
	cp -f ${PARROT_LIB}/ParrotObserver.jar ./lib/ParrotObserver.jar
	ant
