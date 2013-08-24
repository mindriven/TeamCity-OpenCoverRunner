package mindriven.buildServer.OpenCoverRunner.server;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import sun.misc.IOUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.08.13
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class XmlUtils {
    public Document getDocumentFromStream(InputStream stream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setIgnoringComments(false);
        dbf.setIgnoringElementContentWhitespace(true);
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        String documentRaw = new String(IOUtils.readFully(stream, -1, true));
        return db.parse(new ByteArrayInputStream(this.sanitizeInput(documentRaw).getBytes("UTF-8")));
    }

    private String sanitizeInput(String rawInput)
    {
        return rawInput
                // XML parser does not know & entity, so it needs replacement
                .replace("&", "&amp;")
                // this should compensate bug in ReportGenerator that emits fragments like this:
                // <td class="red"data-bind="style: ...
                .replace("\"data-bind", "\" data-bind");
    }
}
