package tests;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadDataFromXML {

	File xmlFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\userDetails.xml");

	@Test
	public void printRootTag() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilder factory = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document xml = factory.parse(xmlFile);

		String parentNode = xml.getDocumentElement().getNodeName();

		System.out.println(parentNode);

		NodeList employeeList = xml.getDocumentElement().getElementsByTagName("Employee");

		for (int i = 0; i < employeeList.getLength(); i++) {

			Element element = (Element) employeeList.item(i);
			String name = element.getElementsByTagName("Name").item(0).getTextContent();
			String location = element.getElementsByTagName("Location").item(0).getTextContent();
			String designation = element.getElementsByTagName("Designation").item(0).getTextContent();

			System.out.println(name);
			System.out.println(location);
			System.out.println(designation);
			System.out.println();

		}
	}
}
