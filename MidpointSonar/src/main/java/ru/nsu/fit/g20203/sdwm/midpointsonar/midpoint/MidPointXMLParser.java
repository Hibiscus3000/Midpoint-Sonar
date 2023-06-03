package ru.nsu.fit.g20203.sdwm.midpointsonar.midpoint;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MidPointXMLParser {

    public static String getReportDataRefOID(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        final NodeList activitiesStates = doc.getElementsByTagName("activityState");
        for (int i = 0; i < activitiesStates.getLength(); ++i) {
            Element activityState = (Element) activitiesStates.item(i);
            final NodeList activities = activityState.getElementsByTagName("activity");
            for (int j = 0; j < activities.getLength(); ++j) {
                Element activity = (Element) activities.item(j);
                final NodeList workStates = activity.getElementsByTagName("workState");
                for (int k = 0; k < workStates.getLength(); ++k) {
                    Element workState = (Element) activities.item(k);
                    final NodeList reportDataRefs = workState.getElementsByTagName("reportDataRef");
                    for (int m = 0; m < reportDataRefs.getLength(); ++m) {
                        Element reportDataRef = (Element) reportDataRefs.item(m);
                        return reportDataRef.getAttribute("oid");
                    }
                }
            }
        }
        return null;
    }

    public static String getFilePath(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        final NodeList reportDatas = doc.getElementsByTagName("reportData");
        for (int i = 0; i < reportDatas.getLength(); ++i) {
            Element reportData = (Element) reportDatas.item(i);
            return reportData.getElementsByTagName("filePath").item(0).getTextContent();
        }
        return null;
    }
}
