package de.bund.bfr.numl;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataTypeTest {

    @Test
    public void testParse() {

        // Parsing null or empty string returns null
        assertNull(DataType.STRING.parse(null));
        assertNull(DataType.STRING.parse(""));

        // Parsing a string returns it
        assertEquals("Hola mundo", DataType.STRING.parse("Hola mundo"));

        // DataType.FLOAT.parse() returns a float if the string contains one
        assertEquals(3.41f, (float) DataType.FLOAT.parse("3.41"), 0.0);

        // DataType.FLOAT.parse() returns null if the string does not contain a float
        assertNull(DataType.FLOAT.parse("Not a float"));

        // DataType.DOUBLE.parse() returns a double if the string contains a real number
        assertEquals(3.41, (double) DataType.DOUBLE.parse("3.41"), 0.0);

        // DataType.DOUBLE.parse() returns null if the string does not contain a real number
        assertNull(DataType.DOUBLE.parse("Not a number"));

        // DataType.INTEGER.parse() returns an integer if the string contains one
        assertTrue(7 == (int) DataType.INTEGER.parse("7"));

        // DataType.INTEGER.parse() returns null if the string does not contain an integer
        assertNull(DataType.INTEGER.parse("Not an integer"));
    }

    @Test
    public void testToString() {
        assertEquals("string", DataType.STRING.toString());
        assertEquals("float", DataType.FLOAT.toString());
        assertEquals("double", DataType.DOUBLE.toString());
        assertEquals("integer", DataType.INTEGER.toString());
    }

    @Test
    public void testFromName() {
        assertEquals(DataType.STRING, DataType.fromName("string"));
        assertEquals(DataType.FLOAT, DataType.fromName("float"));
        assertEquals(DataType.DOUBLE, DataType.fromName("double"));
        assertEquals(DataType.INTEGER, DataType.fromName("integer"));
        assertNull(DataType.fromName("invalid name"));
    }
}
