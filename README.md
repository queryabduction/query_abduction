# Query Abduction

## Environment

+ Java 1.8
+ Java heap size: 8GB

## Usage

You can run our system with the executale jar file or the  source code. Please run it on Linux, or something may be wrong with the path of input files.

+ Run with the executale jar file

  1. Download `drewer-abd.jar` and the directory `lib` for the external dependencies

  2. Run command and arguments

     ```
     java -Xms8g -Xmx8g -jar drewer-abd.jar -o <ontology file> -d <dataset directory> -q <query file> [-m <abduction mode>]
     ```

     + `-o`: the path of the ontology file, in DLGP format. Please refer to https://graphik-team.github.io/graal/doc/dlgp for more information about DLGP format.
     + `-d`: the directory of the dataset. The dataset is seperated by the predicate, and in CSV format each.
     + `-q`: the path of the query file. Terms started with a upper letter are viewed as variants, otherwise constants. 
     + `-m`: the mode of abduction mode, optional. 0 for Mixed, 1 for Concrete and 2 for Abstract. 0 is the default.

+ Run with the source code

  + Create a Maven project upon the given `pom.xml`
  + Arguments are the same as those of running with the jar file
  + Run `org.gu.dcore.Abduction.java`

## Run ABEL

1. Install XSB

   a. Download and compile:

   ```shell
   wget http://xsb.sourceforge.net/downloads/XSB.tar.gz
   tar xvf XSB.tar.gz
   cd build
   ./configure
   ./makexsb
   ```

   b. Update `PATH`, e.g.,

   ```text
   export PATH=/home/<username>/xsb/XSB/bin:$PATH
   ```

2. Run command and arguments

   ```shell
   java -Xms8g -Xmx8g -jar ABEL.jar -queries <query file> -allU -allB -constantAb <constant abduciables> -tbox <tbox> -abox <abox> -xsb <xsb path> [-limit <time limit>] [-result <result>]
   ```

   + `-queries`: the path of the query file
   + `-constantAb`: the path of the constant abduciables
   + `-tbox`: the path of the TBox file, in OWL format
   + `-abox`: the path of the ABox file, in OWL format
   + `-xsb`: the path of XSB, e.g., `/home/<username>/XSB/config/x86_64-unknown-linux-gnu/bin/xsb`
   + `-limit`: the time limit for searching solutions, in minutes
   + `-result`: the path of the file to store the output

## Reproduce Our Experiments

The raw data to generate Table 1 in our paper are in `results/OQA` and the raw data to generate Figure 1 in out paper are in `results/DBpedia`.

### Evaluation on OQA Benchmarks

1. The directories `lib` and `benchmarks`, the files `drewer-abd.jar` (or the source code) and `run_oqa.sh`are needed. Besides, an empty directory named `results/OQA` is needed to store the redirected outputs.
2. Run commands and the paths of the inputs are specified in `run_oqa.sh`. Run `./run_oqa.sh` and you can get the results of out system in Table 1.

### ABEL

1. The directory `abel` is needed.
2. Run commands and the paths of the inputs are specified in `abel/run_abel.sh`. Run `./run_abel.sh` and you can get the results of ABEL in Table 1.

### Evaluation on the NLP question answering benchmark

1. The directories `lib` and `benchmarks`, the files `drewer-abd.jar` (or the source code) and `run_dbpedia.sh`are needed. Besides, an empty directory named `results/DBpedia` is needed to store the redirected outputs.
2. Run commands and the paths of the inputs are specified in `run_dbpedia.sh`. Run `./run_dbpedia.sh` and you can get the raw results to draw Figure 1.

