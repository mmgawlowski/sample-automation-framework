package utils.dataproviders;

import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    /**
     * Advanced DataProvider implementation.
     * <p>
     * Uses the reflection to get the name of the class to which the currently run test method belongs.
     * Then it searches in test/resources/test-data for an XML file with the same name that contains the test data.
     * <p>
     * In the file you can find nodes corresponding to the test methods.
     * Each node has its own children, which in turn correspond to the parameters of test methods.
     * If a given test method should be repeated for another data set, another node is added.
     *
     * @param m represents currently run test method.
     * @return an iterator with an array of objects representing one data set.
     * @throws IllegalStateException when test data sets cannot be prepared for some reason.
     * @see <a href="https://www.javatpoint.com/how-to-read-xml-file-in-java">How to use XML files</a>
     * @see java.lang.reflect.Method
     */
    @DataProvider(name = "TestData")
    public static Iterator<Object[]> DataProvider(Method m) {
        String className = m.getDeclaringClass().getSimpleName();
        List<Object[]> testDataSets = new ArrayList<>();
        List<Object> argList = new ArrayList<>();

        File file = new File(String.format("src/test/resources/test-data/%s.xml", className));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName(m.getName());

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NodeList childList = node.getChildNodes();

                for (int j = 0; j < childList.getLength(); j++) {
                    Node child = childList.item(j);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        String arg = child.getTextContent();
                        argList.add(arg);
                    }
                }
                Object[] args = argList.toArray();
                testDataSets.add(args);
                argList.clear();
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        if (!testDataSets.isEmpty()) {
            return testDataSets.iterator();
        } else {
            throw new IllegalStateException(
                    String.format("Unable to prepare test data sets for the %s test method.", m.getName()));
        }
    }
}
