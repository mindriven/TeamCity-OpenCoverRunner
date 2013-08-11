import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesContainer;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.MapDescriptionBuilder;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class When_converting_ConfigValuesContainer_to_string {

    @Test
    public void map_description_is_build_for_config_values_with_proper_title() throws Exception
    {
        ConfigValuesContainer container = new ConfigValuesContainer();
        MapDescriptionBuilder builder = mock(MapDescriptionBuilder.class);
        container.setMapDescriptionBuilder(builder);
        HashMap<String, String> configValues = new HashMap<String, String>();
        configValues.put("A", "b");
        container.setDefinedConfigValues(configValues);

        container.toString();

        verify(builder).getDescription(eq(configValues), eq("Config values"));
    }

    @Test
    public void map_description_is_build_for_default_values_with_proper_title() throws Exception
    {
        ConfigValuesContainer container = new ConfigValuesContainer();
        MapDescriptionBuilder builder = mock(MapDescriptionBuilder.class);
        container.setMapDescriptionBuilder(builder);
        HashMap<String, String> defaults = new HashMap<String, String>();
        defaults.put("C", "D");
        container.setDefaultValuesMapping(defaults);

        container.toString();

        verify(builder).getDescription(eq(defaults), eq("Default values"));
    }

    @Test
    public void map_description_is_build_for_environment_values_with_proper_title() throws Exception
    {
        ConfigValuesContainer container = new ConfigValuesContainer();
        MapDescriptionBuilder builder = mock(MapDescriptionBuilder.class);
        container.setMapDescriptionBuilder(builder);
        HashMap<String, String> env = new HashMap<String, String>();
        env.put("E", "f");
        container.setEnvironmentalVariables(env);

        container.toString();

        verify(builder).getDescription(eq(env), eq("Environmental variables"));
    }

    @Test
    public void result_gets_glued_from_map_descriptions() throws Exception
    {
        ConfigValuesContainer container = new ConfigValuesContainer();
        MapDescriptionBuilder builder = mock(MapDescriptionBuilder.class);
        when(builder.getDescription(anyMap(),eq("Config values"))).thenReturn("1");
        when(builder.getDescription(anyMap(),eq("Default values"))).thenReturn("2");
        when(builder.getDescription(anyMap(),eq("Environmental variables"))).thenReturn("3");
        container.setMapDescriptionBuilder(builder);

        String result = container.toString();

        Assert.assertEquals(result, "123");
    }
}
