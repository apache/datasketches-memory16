#!/bin/bash -e

# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

#Java executables for mac environment
export JAVAC=$JAVA16_HOME/bin/javac
export JAR=$JAVA16_HOME/bin/jar
export JAVA=$JAVA16_HOME/bin/java

echo "--- CLEAN & COMPILE ---"

rm -rf target
mkdir target
mkdir target/classes
mkdir target/mods
mkdir target/test-classes

$JAVAC \
    -d target/classes/\
    $(find . -name '*.java')

echo "--- JAR ---"
$JAR \
    --create \
    --file target/mods/org.apache.datasketches.memory.jar \
    --main-class org.apache.datasketches.memory.AllocateDirect \
    -C target/classes .

