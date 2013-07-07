package mindriven.buildServer.OpenCoverRunner.server;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.util.PropertiesUtil;
/**
 * Created with IntelliJ IDEA.
 * User: kposiadala
 * Date: 06.07.13
 * Time: 20:25
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverPropertiesProcessor implements PropertiesProcessor {
    @Override
    public Collection<InvalidProperty> process(Map<String, String> stringStringMap) {
        List<InvalidProperty> result = new Vector<InvalidProperty>();
        return result;
    }
}
