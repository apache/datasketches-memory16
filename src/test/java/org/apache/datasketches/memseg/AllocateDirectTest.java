/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.datasketches.memseg;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import jdk.incubator.foreign.MemorySegment;



/**
 * Test AllocateDirect.
 *
 * @author Lee Rhodes
 *
 */
public class AllocateDirectTest {

  @Test
  @SuppressWarnings("resource")
  public void checkAllocDirect() {
    final long bytesIn = 64;
    try (AllocateDirect allocateDirect = new AllocateDirect(bytesIn)) {
      final MemorySegment seg = allocateDirect.getMemorySegment();
      final long bytesOut = seg.byteSize();
      assertEquals(bytesOut, bytesIn);
      String str = (bytesOut == bytesIn) ? "OK" : "Not OK";
      println(str);
      //allocateDirect.close(); //fast close
    }
  }

  
  static void println(Object o) { System.out.println(o.toString()); }
}

