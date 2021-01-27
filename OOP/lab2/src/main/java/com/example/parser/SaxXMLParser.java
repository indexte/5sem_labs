package com.example.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


public class SaxXMLParser implements ParserXML{
    private XMLHandler handler;
    public SaxXMLParser(XMLHandler handler){
        this.handler = handler;
    }

    @Override
    public void parse(String xmlPath) throws IllegalArgumentException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxXMLHandler saxXMLHandler = new SaxXMLHandler(handler);
            saxParser.parse(new File(xmlPath), saxXMLHandler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }
    }
}
