package mindriven.buildServer.OpenCoverRunner.agent;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:45
 * To change this template use File | Settings | File Templates.
 */
public class ConfigValuesProvider {

    private ConfigValuesContainer container;
    public ConfigValuesProvider(ConfigValuesContainer container)
    {
        this.container = container;
    }

    public String getValueOrDefault(String key)
    {
        Map<String, String> mapping = this.container.getDefinedConfigValues();
        if(mapping.containsKey(key))
            return mapping.get(key);
        else
        {
            Map<String, String> defaults = this.container.getDefaultValuesMapping();
            if(defaults.containsKey(key))
                return defaults.get(key);
        }
        String message = "value with key: '" + key + "' was not found neither in config values nor in default values. Configuration was: "+this.container.toString();
        throw new IllegalArgumentException(message);
    }

    public Map<String, String> getEnvironmentalVariables()
    {
        return this.container.getEnvironmentalVariables();
    }
}
