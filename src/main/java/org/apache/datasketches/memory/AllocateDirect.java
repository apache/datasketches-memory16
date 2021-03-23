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

package org.apache.datasketches.memory;

//import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
//import static jdk.incubator.foreign.MemorySegment.*;


/**
 *
 * @author Lee Rhodes
 */
class AllocateDirect {
  private final MemorySegment directSeg;

  /**
   * Construct Memory Segment
   * @param capacityBytes blah
   */
  AllocateDirect(final long capacityBytes) {
    directSeg = MemorySegment.allocateNative(capacityBytes);
  }

  MemorySegment getMemorySegment() {
    return directSeg;
  }

  long getNativeAddress() {
    return directSeg.address().toRawLongValue();
  }

  void close() {
    directSeg.close();
  }

  @SuppressWarnings("resource")
  public static void checkAllocDirect() {
    final long bytesIn = 64;
    AllocateDirect allocateDirect = new AllocateDirect(bytesIn);
    final MemorySegment seg = allocateDirect.getMemorySegment();
    final long bytesOut = seg.byteSize();
    String out = (bytesOut == bytesIn) ? "OK" : "Not OK";
    System.out.println(out);
    allocateDirect.close();
  }

  public static void main(final String[] args) {
    checkAllocDirect();
  }
}

