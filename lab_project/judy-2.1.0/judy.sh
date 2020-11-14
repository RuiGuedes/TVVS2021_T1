#!/bin/bash

java -XX:MaxPermSize=2048m -Xmx2048m -Xms2048m -Xmn512m -Xss512k -XX:+UseG1GC -jar judy-2.1.0.jar $@
