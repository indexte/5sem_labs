package com.example;

import com.example.parser.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        ModelParser modelParser = new ModelParser();
        modelParser.parse("src/main/resources/Plane.xml", "src/main/resources/Plane.xsd", "dom");

        modelParser.parse("src/main/resources/Plane.xml", "src/main/resources/Plane.xsd", "stax");

        modelParser.parse("src/main/resources/Plane.xml", "src/main/resources/Plane.xsd", "sax");
    }
}
