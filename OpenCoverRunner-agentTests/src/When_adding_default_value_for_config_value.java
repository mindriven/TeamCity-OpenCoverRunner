import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesContainer;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.common.DefaultValuesMap;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class When_adding_default_value_for_config_value {

    @Test
    public void it_is_present_in_result() throws Exception
    {
        String key = "key";
        String defaultValueForKey = "defaultValue";
        DefaultValuesMap map = new DefaultValuesMap();
        map.addDefaultValue(key, defaultValueForKey);

        String result = map.getMapping().get(key);

        Assert.assertEquals(result, defaultValueForKey);
    }
}
