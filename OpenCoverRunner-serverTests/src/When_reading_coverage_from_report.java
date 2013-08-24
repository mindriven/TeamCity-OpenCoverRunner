import jetbrains.buildServer.serverSide.artifacts.BuildArtifact;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.server.ReportCoverageReader;
import mindriven.buildServer.OpenCoverRunner.server.Utils.XmlUtils;
import org.apache.tools.ant.filters.StringInputStream;
import org.junit.Test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.08.13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class When_reading_coverage_from_report {

    private String reportWithCoverageContent = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta charset=\"utf-8\" />\n" +
            "<title>Summary - Coverage Report</title>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"report.css\" />\n" +
            "</head><body><div class=\"container\">\n" +
            "<h1>Summary</h1>\n" +
            "<table class=\"overview\">\n" +
            "<colgroup>\n" +
            "<col width=\"135\" />\n" +
            "<col />\n" +
            "</colgroup>\n" +
            "<tbody>\n" +
            "<tr><th>Generated on:</th><td>2013-08-18 - 19:26:01</td></tr>\n" +
            "<tr><th>Parser:</th><td>OpenCoverParser</td></tr>\n" +
            "<tr><th>Assemblies:</th><td>3</td></tr>\n" +
            "<tr><th>Classes:</th><td>102</td></tr>\n" +
            "<tr><th>Files:</th><td>99</td></tr>\n" +
            "<tr><th>Coverage:</th><td>2.7%</td></tr>\n" +
            "<tr><th>Covered lines:</th><td>55</td></tr>\n" +
            "<tr><th>Uncovered lines:</th><td>1921</td></tr>\n" +
            "<tr><th>Coverable lines:</th><td>1976</td></tr>\n" +
            "<tr><th>Total lines:</th><td>44</td></tr>\n" +
            "</tbody>\n" +
            "</table>" +
            "</div>" +
            "</body>" +
            "</html>";

    private String reportWithoutCoverageContent = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta charset=\"utf-8\" />\n" +
            "<title>Summary - Coverage Report</title>\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"report.css\" />\n" +
            "</head><body><div class=\"container\">\n" +
            "<h1>Summary</h1>\n" +
            "<table class=\"overview\">\n" +
            "<colgroup>\n" +
            "<col width=\"135\" />\n" +
            "<col />\n" +
            "</colgroup>\n" +
            "<tbody>\n" +
            "<tr><th>Generated on:</th><td>2013-08-18 - 19:26:01</td></tr>\n" +
            "<tr><th>Parser:</th><td>OpenCoverParser</td></tr>\n" +
            "<tr><th>Assemblies:</th><td>3</td></tr>\n" +
            "<tr><th>Classes:</th><td>102</td></tr>\n" +
            "<tr><th>Files:</th><td>99</td></tr>\n" +
            "<tr><th>Coverage:</th><td></td></tr>\n" +
            "<tr><th>Covered lines:</th><td>55</td></tr>\n" +
            "<tr><th>Uncovered lines:</th><td>1921</td></tr>\n" +
            "<tr><th>Coverable lines:</th><td>1976</td></tr>\n" +
            "<tr><th>Total lines:</th><td>44</td></tr>\n" +
            "</tbody>\n" +
            "</table>" +
            "</div>" +
            "</body>" +
            "</html>";

    @Test
    public void and_report_has_coverage_defined__this_coverage_is_result() throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        BuildArtifact artifact = mock(BuildArtifact.class);
        XmlUtils xmlUtils = mock(XmlUtils.class);
        when(xmlUtils.getDocumentFromStream(any(InputStream.class)))
                     .thenReturn(db.parse(new StringInputStream(this.reportWithCoverageContent)));
        ReportCoverageReader reader = new ReportCoverageReader();
        reader.setXmlUtils(xmlUtils);

        BigDecimal result = reader.readCoverage(artifact);

        Assert.assertEquals(2.7, result.doubleValue());
    }

    @Test
    public void and_report_has_no_coverage_defined__result_is__0() throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        BuildArtifact artifact = mock(BuildArtifact.class);
        XmlUtils xmlUtils = mock(XmlUtils.class);
        when(xmlUtils.getDocumentFromStream(any(InputStream.class)))
                .thenReturn(db.parse(new StringInputStream(this.reportWithoutCoverageContent)));
        ReportCoverageReader reader = new ReportCoverageReader();
        reader.setXmlUtils(xmlUtils);

        BigDecimal result = reader.readCoverage(artifact);

        Assert.assertEquals(0.0, result.doubleValue());
    }

    @Test
    public void it_is_read_correctly_from_report() throws Exception
    {
        BuildArtifact artifact = mock(BuildArtifact.class);
        ReportCoverageReader reader = new ReportCoverageReader();
        when(artifact.getInputStream()).thenReturn(this.getReportStream());

        BigDecimal result = reader.readCoverage(artifact);

        Assert.assertEquals(2.7, result.doubleValue());
    }

    private InputStream getReportStream() throws Exception {
        String filePath = getClass().getResource("exampleReport.htm").getFile();
        return new FileInputStream(new File(filePath));
    }
}
