package mindriven.buildServer.OpenCoverRunner.server;

import jetbrains.buildServer.serverSide.artifacts.BuildArtifact;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.security.util.BigInt;

import javax.swing.text.html.parser.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 17.08.13
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class ReportCoverageReader {
    public BigDecimal readCoverage(BuildArtifact reportArtifact) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setIgnoringComments(false);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(reportArtifact.getInputStream());

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(OpenCoverRunnerConsts.SETTINGS_REPORT_COVERAGE_VALUE_XPATH);
        Element coverageNode = (Element)expr.evaluate(document, XPathConstants.NODE);

        return new BigDecimal(Double.parseDouble(coverageNode.data.toString()));
    }
}
