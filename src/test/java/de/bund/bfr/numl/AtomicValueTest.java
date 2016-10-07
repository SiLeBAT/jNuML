package de.bund.bfr.numl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AtomicValueTest {

    private static Document doc;

    @BeforeClass
    public static void setUp() throws Exception {
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    @Test
    public void testConstructor() {
        AtomicValue value = new AtomicValue(7.0);
        assertEquals(7.0, (double) value.getValue(), 0.0);
    }

    @Test
    public void testConstructorWithElement() {
        Element node = doc.createElement("atomicValue");
        node.setTextContent("7");
        OntologyTerm term = new OntologyTerm("time", "time", "SBO:0000345", "http://www.ebi.ac.uk/sbo");
        AtomicDescription desc = new AtomicDescription("time", term, DataType.INTEGER);

        AtomicValue value = new AtomicValue(node, desc);
        assertTrue(7 == (int) value.getValue());
    }

    @Test
    public void testToNode() {
        Element node = new AtomicValue(7).toNode(doc);
        assertEquals("7", node.getTextContent());
    }
}
