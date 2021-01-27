package com.example.parser;

import com.example.plane.*;
import com.example.validator.XMLValidator;
import org.xml.sax.SAXException;


import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class ModelParser {
    private ParserXML parser;
    private Planes result;
    private XMLHandler handler;

    public Planes parse(String xmlPath, String xsdPath, String parserName) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

        if(XMLValidator.validateXML(xmlPath,xsdPath)) {

            switch (parserName.toUpperCase()) {
                case "SAX": {
                    handler = new XMLHandler();
                    parser = new SaxXMLParser(handler);
                    parser.parse(xmlPath);
                    result = new Planes(handler.getPlanes());
                    break;
                }
                case "DOM": {
                    handler = new XMLHandler();
                    parser = new DomXMLParser(handler);
                    parser.parse(xmlPath);
                    result = new Planes(handler.getPlanes());
                    break;
                }
                case "STAX": {
                    handler = new XMLHandler();
                    parser = new StaxXMLParser(handler);
                    parser.parse(xmlPath);
                    result = new Planes(handler.getPlanes());
                    break;
                }
                default:
                    break;
            }

            result.sort();
            System.out.println(result);
        }
        return result;
    }
}

