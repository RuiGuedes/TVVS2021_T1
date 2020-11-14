/**
 */

package com.google.gson.stream;

import junit.framework.TestCase;
import java.io.IOException;
import java.io.StringWriter;

@SuppressWarnings("resource")
public final class StudentTest extends TestCase {
    
    /**
     * Just to validate student class
     */
    public void testInvalidTopLevelTypes() throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        jsonWriter.name("hello");
        try {
          jsonWriter.value("world");
          fail();
        } catch (IllegalStateException expected) {
        }
    }

}