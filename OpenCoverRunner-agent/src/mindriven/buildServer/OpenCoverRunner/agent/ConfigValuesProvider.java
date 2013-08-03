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
        Map<String, String> mapping = this.container.getDefaultValuesMapping();
        if(mapping.containsKey(key))
            return mapping.get(key);
        else
        {
            Map<String, String> defaults = this.container.getDefaultValuesMapping();
            if(defaults.containsKey(key))
                return defaults.get(key);
        }
        throw new IllegalArgumentException();
    }

    public Map<String, String> getEnvironmentalVariables()
    {
        return this.container.getEnvironmentalVariables();
    }
}
