# TVVS2021_T1

Project for the Test, Validation and Verification of Sotware (TVVS) class of the Master in Informatics and Computer Engineering (MIEIC) at the Faculty of Engineering of the University of Porto (FEUP). 


## Team Members 


Rui Jorge Leão Guedes <br>
* Student Number: 201603854
* E-Mail: up201603854@fe.up.pt

Duarte Manuel Marques Mano Menezes Frazão <br>
* Student Number: 201605658
* E-Mail: up201605658@fe.up.pt 

Fábio Manuel Neves de Araújo <br>
* Student Number: 201607944
* E-Mail: up201607944@fe.up.pt 

## Getting started

### Notes

* To run any of the following `defects4j` you must set your directory to one the following depending on what subject do you pretend to work with:
    * `lab_project/Gson$`
    * `lab_project/Lang$`
* To any of the following `judy` you must set your directory to:
    * `lab_project/judy-3.0.0-M1$`

## Defects4j

### How to Compile

* `defects4j compile`

### How to Test

* `defects4j test -t com.google.gson.stream.StudentTest::<function-name>`
* `defects4j test -t org.apache.commons.lang.time::<function-name>`

## Judy

### Generic Mutation

* `./bin/judy -p ../Gson-15f/target/classes/ -t ../Gson-15f/target/test-classes/`

### Stream Package Mutation 

* `./bin/judy -p ../Gson-15f/target/classes/ -t ../Gson-15f/target/test-classes/com/google/gson/stream/`

### StudentTest Mutation

* `./bin/judy -p ../Gson-15f/target/classes/ -t ../Gson-15f/target/test-classes/com/google/gson/stream/StudentTest.class`
* `./bin/judy -p ../Lang-53f/target/classes/org/apache/commons/lang/time/DateUtils.class -t ../Lang-53f/target/test-classes/org/apache/commons/lang/time/StudentTest.class`