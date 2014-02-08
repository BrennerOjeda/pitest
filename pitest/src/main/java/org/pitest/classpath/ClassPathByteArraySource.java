/*
 * Copyright 2010 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest.classpath;

import java.io.IOException;
import java.util.logging.Logger;

import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.functional.Option;
import org.pitest.util.Log;

public class ClassPathByteArraySource implements ClassByteArraySource {

  private final static Logger LOG   = Log.getLogger();

  private final ClassPath classPath;

  public ClassPathByteArraySource() {
    this(new ClassPath());
  }

  public ClassPathByteArraySource(final ClassPath classPath) {
    this.classPath = classPath;
  }

  public Option<byte[]> getBytes(final String classname) {
    try {
      return Option.some(this.classPath.getClassData(classname));
    } catch (final IOException e) {
      LOG.fine("Could not read class " + classname + ":"  + e.getMessage());
      return Option.none();
    }
  }
}
