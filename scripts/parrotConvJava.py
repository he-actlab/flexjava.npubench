#!/bin/usr/python

# Amir Yazdanbakhsh
# Jan. 7, 2015

import os
import sys
import shutil
import re

class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'

def printUsage():
    print "python parrotConv.py <bench name>"
    exit(1)

def findFileName(filename):
    matchObj = re.match( r'()', filename)
pass;

def listCppFiles(dir):
    fileList = []
    extList  = []
    for f in os.listdir(dir):
        if(".java" in f):
            extLoc = f.rfind(".")
            fileList.append(f[:-(len(f)-extLoc)])
            extList.append(f[extLoc+1:])
    return (fileList, extList)
pass;

def parseParrotArgs(args):
        args = re.sub(',\s+', ',', args)
        args = re.sub('\s+$', '', args)
        args = args.split(',')

        parrotArgs = []
        for a in args:
            #print '-'*32
            #print a
            m = re.match('^\s*(\[(.+)\])?\s*(<(.+);(.+)>)?\s*(.+)$', a)

            features = [m.group(6), m.group(2), m.group(4), m.group(5)]
            #print features
            for i in range(len(features)):
                if ((features[i] == None) and (i != 1)):
                    features[i] = '0'
                pass
            
                if (features[i] == None):
                    continue
            
                features[i] = re.sub('^\s+', '', features[i])
                features[i] = re.sub('\s+$', '', features[i])
            pass

            parrotArgs.append((features[0], features[1], (features[2], features[3])))
            #print parrotArgs[-1]
        pass
    
        return parrotArgs
pass;

def parseParrotPragma_begin(line, keyword):
    # process pragma
    m = re.match(
                    '#begin_loosen\s+' +
                    'parrot' +
                    '\s*\(\s*' +
                    keyword +
                    '\s*,\s*' +
                    '"(.+)"' +
                    '\s*,\s*' +
                    '(.+)\)',
                    line
                )
                    
    parrotArgs = parseParrotArgs(m.group(2))
    return parrotArgs
pass;
def parseParrotPragma_end(line, keyword):
    # process pragma
    m = re.match(
                    '#end_loosen\s+' +
                    'parrot' +
                    '\s*\(\s*' +
                    keyword +
                    '\s*,\s*' +
                    '"(.+)"' +
                    '\s*,\s*' +
                    '(.+)\)',
                    line
                )
                    
    parrotArgs = parseParrotArgs(m.group(2))
    return parrotArgs
pass;

def main():

    bench_name = sys.argv[1]

    # search src directory for all the c | cpp files
    src_dir = os.getcwd() + "/src"
    obj_dir = os.getcwd() + "/obj"
    nn_dir  = os.getcwd() + "/src.nn"
    (fileList, extList) = listCppFiles(src_dir)
    startPargma = False

    for i in range(len(fileList)):
        parrotoFile = fileList[i] + ".java"
        if(".java" in parrotoFile):
            currFile = open(src_dir + "/" + parrotoFile)
            lines = currFile.readlines()
            fileStr = "\n"
            for line in lines:
                line = line.rstrip()
                # start of pragma
                if(("#begin_loosen" in line) and not startPargma):

                    (varName, varLen, varRange) = parseParrotPragma_begin(line, 'input')[0]

                    fileStr += "%s %s\n" % ("//", line)

                    # add necessary lines for FANN
                    fileStr += "\tFann fann = new Fann(\"fann.config/%s.nn\");\n" % (bench_name)
                    #fileStr += "\tfann_type *parrotOut;\n"

                    # copy data from varName to ParrotIn
                    if(type(varLen) != type(None)): # scalar variable
                        fileStr += "\tfloat[] parrotIn = new float[%s];\n" % (varLen)
                        for j in range(int(varLen)):
                            fileStr += "\tparrotIn[%d] = (float)%s[%d].doubleValue();\n" % (j, varName, j)
                    else: #single variable 
                            fileStr += "\ffloat[] parrotIn = new float[1];\n"
                            fileStr += "\tparrotIn[0] = (float)%s.doubleValue();\n" % (varName)

                    #fileStr += "\tann = fann_create_from_file(\"fann.config/%s.nn\");\n" % (bench_name)
                    fileStr += "\tfloat[] parrotOut = fann.run(parrotIn);\n";
                    #----------------------------
                    startPargma = True
                    continue
                if(("#end_loosen" in line) and startPargma): # start of pragma
                    startPargma = False
                    fileStr += "%s %s\n" % ("//", line)
                    (varName, varLen, varRange) = parseParrotPragma_end(line, 'output')[0]

                    # copy data from varName to ParrotIn
                    if(type(varLen) != type(None)): # scalar variable
                        #print varLen
                        for j in range(int(varLen)):
                            fileStr += "\t%s[%d] = parrotOut[%d];\n" % (varName, j, j)
                    else: #single variable 
                            fileStr += "\t%s = parrotOut[0];\n" % (varName)

                    continue


                # check if we are between pragmas
                if(startPargma):
                    fileStr += "%s %s\n" % ("//", line)
                else:
                    fileStr += line + "\n"

            dstFilename   = fileList[i] + "." + extList[i]
            dstFileHandle = open(nn_dir + "/" + dstFilename, "w")
            dstFileHandle.write(fileStr) 

if __name__ == "__main__":
    main()
