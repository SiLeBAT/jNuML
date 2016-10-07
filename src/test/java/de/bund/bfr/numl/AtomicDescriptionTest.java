package de.bund.bfr.numl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AtomicDescriptionTest {

    private static Document doc;
    private static OntologyTerm term;

    @BeforeClass
    public static void setUp() throws Exception {
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        term = new OntologyTerm("time", "time", "SBO:0000345", "http://www.ebi.ac.uk/sbo");
    }

    @Test
    public void testConstructor() {
        AtomicDescription desc = new AtomicDescription("desc0", term, DataType.STRING);

        assertEquals("desc0", desc.getName());
        assertEquals(term, desc.getOntologyTerm());
        assertEquals(DataType.STRING, desc.getValueType());
    }

    @Test
    public void testConstructorWithElement() {
        Element node = doc.createElement("atomicDescription");
        node.setAttribute("name", "desc0");
        node.setAttribute("ontologyTerm", "time");
        node.setAttribute("valueType", "string");

        AtomicDescription desc = new AtomicDescription(node, Collections.singletonList(term));
        assertEquals("desc0", desc.getName());
        assertEquals(term, desc.getOntologyTerm());
        assertEquals(DataType.STRING, desc.getValueType());
    }

    @Test
    public void testToNode() {
        AtomicDescription desc = new AtomicDescription("desc0", term, DataType.STRING);
        Element node = desc.toNode(doc);

        assertEquals("desc0", node.getAttribute("name"));
        assertEquals(term.getId(), node.getAttribute("ontologyTerm"));
        assertEquals(DataType.STRING.toString(), node.getAttribute("valueType"));
    }
}
