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

    /**
     * This test aims to kill the following mutant:
     * 
     * Operator -> EGE
     * Lines    -> 199
     * 
     * --------------------------------------------
     * 
     * This test kills the following mutants:
     * 
     * Operator -> EGE
     * Lines    -> 199
     * 
     * Operator -> IPC
     * Lines    -> 199
     * 
     */
    public void testJsonWriterGeneralizationCaughtErrors() throws IOException {
      try {
        new JsonWriter(null);
      } catch (NullPointerException e) {
        assertEquals("out == null", e.getMessage());
      }      
    } 

    /**
     * This test aims to kill the following mutant:
     * 
     * Operator -> JIR_Ifgt
     * Lines    -> 213
     * 
     * --------------------------------------------
     * 
     * This test kills the following mutants:
     * 
     * Operator -> JIR_Ifge
     * Lines    -> 213
     * 
     */
    public void testSetIndent() throws IOException {
      StringWriter stringWriter = new StringWriter();
      JsonWriter jsonWriter = new JsonWriter(stringWriter);
      jsonWriter.setIndent("");

      jsonWriter.beginObject();
      jsonWriter.name("a").value(true);
      jsonWriter.name("b").value(false);
      jsonWriter.endObject();

      String expected = "{\"a\":true,\"b\":false}";
      assertEquals(expected, stringWriter.toString());
    }

    /**
     * This test aims to kill the following mutant:
     * 
     * Operator -> JTD
     * Lines    -> 253
     * 
     * --------------------------------------------
     * 
     * This test kills the following mutants:
     * 
     * Operator -> JTD
     * Lines    -> 253
     * 
     * Operator -> JTI
     * Lines    -> 253
     * 
     */
    public void testSetHtmlSafe() throws IOException {
      JsonWriter jsonWriter = new JsonWriter(new StringWriter());
      jsonWriter.beginArray();

      jsonWriter.setHtmlSafe(true);
      assertTrue(jsonWriter.isHtmlSafe());
    }

    /**
     * This test aims to kill the following mutant:
     * 
     * Operator -> JTD
     * Lines    -> 269
     * 
     * --------------------------------------------
     * 
     * This test kills the following mutants:
     * 
     * Operator -> JTD
     * Lines    -> 269
     * 
     * Operator -> JTI
     * Lines    -> 269
     * 
     */
    public void testSetSerializeNulls() throws IOException {
      JsonWriter jsonWriter = new JsonWriter(new StringWriter());
      jsonWriter.beginArray();
      
      jsonWriter.setSerializeNulls(false);
      assertFalse(jsonWriter.getSerializeNulls());
    }


}