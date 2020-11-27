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
     * Operator -> JIR_Ifeq | JIR_Ifge
     * Lines    -> 146      | 146
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
     * Operator -> EGE | IPC
     * Lines    -> 199 | 199
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
     * Operator -> JTD | JTI
     * Lines    -> 253 | 253
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
     * Operator -> JTD | JTI
     * Lines    -> 269 | 269
     * 
     */
    public void testSetSerializeNulls() throws IOException {
      JsonWriter jsonWriter = new JsonWriter(new StringWriter());
      
      jsonWriter.setSerializeNulls(false);
      assertFalse(jsonWriter.getSerializeNulls());
    }

    /**
     * This test aims to kill the following mutant:
     * 
     * Operator -> AIR_LeftOperand
     * Lines    -> 355
     * 
     * --------------------------------------------
     * 
     * This test kills the following mutants:
     * 
     * Operator -> JIR_Ifle | AIR_LeftOperand | AIR_RightOperand | AIR_Div | AIR_Rem | AIR_Sub
     * Lines    -> 354      | 355             | 355              | 355     | 355     | 355
     * 
     */
    public void testStackPushBoundaries() throws IOException {
      String expected = "";
      StringWriter stringWriter = new StringWriter();
      JsonWriter jsonWriter = new JsonWriter(stringWriter);

      for(int idx = 0; idx <= 32; idx++) {
        jsonWriter.beginArray();
        expected += "[";
      }
      
      for(int idx = 0; idx <= 32; idx++) {
        jsonWriter.endArray();
        expected += "]";
      }

      assertEquals(expected, stringWriter.toString());
    }

    /**
     * This test aims to kill the following mutant:
     * 
     * Operator -> AIR_LeftOperand
     * Lines    -> 591
     * 
     * --------------------------------------------
     * 
     * This test kills the following mutants:
     * 
     * Operator -> AIR_LeftOperand | AIR_Add
     * Lines    -> 591             | 591
     * 
     */
    public void testStringWhenLastLessThenLength() throws Exception {
      StringWriter stringWriter = new StringWriter();
      JsonWriter jsonWriter = new JsonWriter(stringWriter);
      jsonWriter.beginObject();
   
      jsonWriter.name("\t••\t11\t1••\t•").value(20);
      jsonWriter.endObject();
      if(stringWriter.toString().equals("{\"\t\t\t\t•\":20}")) {
        fail();
      }   
    }

}