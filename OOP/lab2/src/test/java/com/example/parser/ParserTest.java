package com.example.parser;

import com.example.plane.*;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ParserTest {
    ModelParser modelParser;
    Plane plane1;

    public ParserTest() {
        modelParser = new ModelParser();
        plane1 = new Plane();

        plane1.setId("341");
        plane1.setModel("SU-24MR");
        plane1.setOrigin("Russia");
        plane1.setPrice("8000");
        plane1.setChars(new Chars(PlaneType.valueOf("reconnaissance"), 1, true));
        plane1.setParameters(new Parameters(24, 6, 35));
    }

    @Test
    public void DOMParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        Planes planes = modelParser.parse("src/main/resources/Plane.xml",
                "src/main/resources/Plane.xsd", "dom");
        assertEquals(planes.getList().get(0), plane1);
    }

    @Test
    public void SAXParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        Planes planes = modelParser.parse("src/main/resources/Plane.xml",
                "src/main/resources/Plane.xsd", "sax");
        assertEquals(planes.getList().get(0), plane1);
    }

    @Test
    public void StAXParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        Planes planes = modelParser.parse("src/main/resources/Plane.xml",
                "src/main/resources/Plane.xsd", "stax");
        assertEquals(planes.getList().get(0), plane1);
    }
}