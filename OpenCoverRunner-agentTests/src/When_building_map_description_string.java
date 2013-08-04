import jetbrains.buildServer.serverSide.SRunnerContext;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesContainer;
import mindriven.buildServer.OpenCoverRunner.agent.MapDescriptionBuilder;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class When_building_map_description_string {

    @Test
    public void and_input_values_is_null_or_empty__result_string_says_so() throws Exception
    {
        String title = "Config options";
        HashMap<String, String> map = new HashMap<String, String>();
        MapDescriptionBuilder builder = new MapDescriptionBuilder();

        String result = builder.getDescription(map, title);

        Assert.assertTrue(result.contains("Config options:\n\rnull or empty"));
    }

    @Test
    public void and_config_values_are_not_null_and_not_empty__they_are_listed_in_result() throws Exception
    {
        String key = "key";
        String value = "value";
        String title = "Config options";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        MapDescriptionBuilder builder = new MapDescriptionBuilder();

        String result = builder.getDescription(map, title);

        Assert.assertTrue(result.contains("Config options:\n\rkey : value"));
    }
}
