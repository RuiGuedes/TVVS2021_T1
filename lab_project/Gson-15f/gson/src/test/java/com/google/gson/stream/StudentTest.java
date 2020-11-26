/**
 */

package com.google.gson.stream;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.StringWriter;

@SuppressWarnings("resource")
public final class StudentTest extends TestCase {
    
    /**
     * This test aims to kill the following mutant:
     * 
     * Operator -> JIR_Ifeq
     * Lines    -> 146
     * 
     * --------------------------------------------
     * 
     * This test kills the following mutants:
     * 
     * Operator -> JIR_Ifeq
     * Lines    -> 146
     * 
     * Operator -> JIR_Ifge
     * Lines    -> 146
     * 
     */
    public void testReplacementCharUnicodeLimit() throws IOException {
      StringWriter stringWriter = new StringWriter();
      JsonWriter jsonWriter = new JsonWriter(stringWriter);
      jsonWriter.beginArray();    
      jsonWriter.value(""); // Unicode character - INFORMATION SEPARATOR
      jsonWriter.endArray();
      assertEquals("[\"\\u001f\"]", stringWriter.toString());
    }  

}