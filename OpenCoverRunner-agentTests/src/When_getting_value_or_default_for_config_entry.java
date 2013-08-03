import com.sun.javaws.exceptions.InvalidArgumentException;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesContainer;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:21
 * To change this template use File | Settings | File Templates.
 */
public class When_getting_value_or_default_for_config_entry {

    @Test(expected = IllegalArgumentException.class)
    public void and_config_value_was_not_specified_and_has_no_default__exception_is_thrown() throws Exception
    {
        ConfigValuesContainer container = mock(ConfigValuesContainer.class);
        when(container.getDefaultValuesMapping()).thenReturn(new HashMap<String, String>());
        when(container.getDefinedConfigValues()).thenReturn(new HashMap<String, String>());
        ConfigValuesProvider provider = new ConfigValuesProvider(container);

        provider.getValueOrDefault("someConst");
    }

    @Test
    public void and_config_value_was_specified__specified_value_gets_returned() throws Exception
    {
        String key = "key";
        String userProvidedValue = "userInput";
        HashMap<String, String> config = new HashMap<String, String>();
        config.put(key, userProvidedValue);
        ConfigValuesContainer container = new ConfigValuesContainer();
        container.setDefaultValuesMapping(config);
        ConfigValuesProvider provider = new ConfigValuesProvider(container);

        String result = provider.getValueOrDefault(key);

        Assert.assertEquals(result, userProvidedValue);
    }

    @Test
    public void and_config_value_was_not_specified_but_has_default__default_is_returned() throws Exception
    {
        String key = "key";
        String defaultValueForKey = "defaultValue";
        ConfigValuesContainer container = mock(ConfigValuesContainer.class);
        HashMap<String, String> defaults = new HashMap<String, String>();
        defaults.put(key, defaultValueForKey);
        when(container.getDefaultValuesMapping()).thenReturn(defaults);
        when(container.getDefinedConfigValues()).thenReturn(new HashMap<String, String>());
        ConfigValuesProvider provider = new ConfigValuesProvider(container);

        String result = provider.getValueOrDefault(key);

        Assert.assertEquals(defaultValueForKey, result);
    }
}
