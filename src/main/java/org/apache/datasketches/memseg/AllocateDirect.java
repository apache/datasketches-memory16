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

//import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
//import static jdk.incubator.foreign.MemorySegment.*;


/**
 *
 * @author Lee Rhodes
 */
final class AllocateDirect implements AutoCloseable {
  private final MemorySegment directSeg;

  /**
   * Base Constructor for allocate native memory.
   *
   * <p>Allocates and provides access to capacityBytes directly in native (off-heap) memory
   * leveraging the Memory interface.
   * The allocated memory will be 8-byte aligned, but may not be page aligned.
   * @param capacityBytes the the requested capacity of off-heap memory. Cannot be zero.
   */
  AllocateDirect(final long capacityBytes) {
    directSeg = MemorySegment.allocateNative(capacityBytes, 8);
  }

  MemorySegment getMemorySegment() {
    return directSeg;
  }

  long getNativeAddress() {
    return directSeg.address().toRawLongValue();
  }

  @Override
  public void close() {
    directSeg.close();
  }
  
  

}

