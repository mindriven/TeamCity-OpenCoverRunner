package mindriven.buildServer.OpenCoverRunner.server;

import jetbrains.buildServer.serverSide.artifacts.BuildArtifact;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import mindriven.buildServer.OpenCoverRunner.server.Utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

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
    private XmlUtils xmlUtils = new XmlUtils();

    public void setXmlUtils(XmlUtils utils)
    {
        this.xmlUtils = utils;
    }
    public BigDecimal readCoverage(BuildArtifact reportArtifact) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {

        Document document = xmlUtils.getDocumentFromStream(reportArtifact.getInputStream());
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(OpenCoverRunnerConsts.SETTINGS_REPORT_COVERAGE_VALUE_XPATH);
        Node coverageNode = (Node)expr.evaluate(document, XPathConstants.NODE);
        String coverageNodeText = coverageNode.getTextContent();
        Double result = 0.0;
        if(!coverageNodeText.isEmpty())
        {
            String coverageValue = coverageNodeText.substring(0, coverageNodeText.length()-1);
            result = Double.parseDouble(coverageValue);
        }

        return new BigDecimal(result);
    }
}
