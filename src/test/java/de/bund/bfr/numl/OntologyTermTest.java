package de.bund.bfr.numl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OntologyTermTest {

    private static Document doc;

    @BeforeClass
    public static void setUp() throws Exception {
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    @Test
    public void testConstructor() {
        OntologyTerm term = new OntologyTerm("time", "time", "SBO:0000345", "http://www.ebi.ac.uk/sbo");
        assertEquals("time", term.getId());
        assertEquals("time", term.getTerm());
        assertEquals("SBO:0000345", term.getSourceTermId());
        assertEquals("http://www.ebi.ac.uk/sbo", term.getOntologyURI());
    }

    @Test
    public void testConstructorWithElement() {
        Element node = doc.createElement("ontologyTerm");
        node.setAttribute("id", "time");
        node.setAttribute("term", "time");
        node.setAttribute("sourceTermId", "SBO:0000345");
        node.setAttribute("ontologyURI", "http://www.ebi.ac.uk/sbo");

        OntologyTerm term = new OntologyTerm(node);
        assertEquals("time", term.getId());
        assertEquals("time", term.getTerm());
        assertEquals("SBO:0000345", term.getSourceTermId());
        assertEquals("http://www.ebi.ac.uk/sbo", term.getOntologyURI());

        // Element with missing fields produce an OntologyTerm with uninitialized fields
        node = doc.createElement("ontologyTerm");
        term = new OntologyTerm(node);
        assertNull(term.getId());
        assertNull(term.getTerm());
        assertNull(term.getSourceTermId());
        assertNull(term.getOntologyURI());
    }

    @Test
    public void testToNode() {
        OntologyTerm term = new OntologyTerm("time", "time", "SBO:0000345", "http://www.ebi.ac.uk/sbo");
        Element node = term.toNode(doc);

        assertEquals("time", node.getAttribute("id"));
        assertEquals("time", node.getAttribute("term"));
        assertEquals("SBO:0000345", node.getAttribute("sourceTermId"));
        assertEquals("http://www.ebi.ac.uk/sbo", node.getAttribute("ontologyURI"));
    }
}
