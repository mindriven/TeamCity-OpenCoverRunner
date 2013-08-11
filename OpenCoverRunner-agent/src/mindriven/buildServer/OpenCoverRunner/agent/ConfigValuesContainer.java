package mindriven.buildServer.OpenCoverRunner.agent;

import mindriven.buildServer.OpenCoverRunner.agent.Utils.MapDescriptionBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public class ConfigValuesContainer {

    private Map<String, String> definedConfigValues = new HashMap<String, String>();
    private Map<String, String> defaultValues = new HashMap<String, String>();
    private Map<String, String> environmentalVariables = new HashMap<String, String>();
    private MapDescriptionBuilder mapDescriptionBuilder = new MapDescriptionBuilder();

    public void setDefaultValuesMapping(Map<String, String> defaultValues)
    {
        this.defaultValues = new HashMap<String, String>(defaultValues) ;
    }

    public Map<String, String> getDefaultValuesMapping()
    {
        return new HashMap<String, String>(this.defaultValues);
    }

    public void setDefinedConfigValues(Map<String, String> defaultValues)
    {
        this.definedConfigValues = new HashMap<String, String>(defaultValues) ;
    }

    public Map<String, String> getDefinedConfigValues()
    {
        return new HashMap<String, String>(this.definedConfigValues);
    }

    public void setEnvironmentalVariables(Map<String, String> environmentalVariables)
    {
        this.environmentalVariables = new HashMap<String, String>(environmentalVariables) ;
    }

    public Map<String, String> getEnvironmentalVariables()
    {
        return new HashMap<String, String>(this.environmentalVariables);
    }

    public void setMapDescriptionBuilder(MapDescriptionBuilder builder)
    {
        this.mapDescriptionBuilder = builder;
    }

    @Override
    public String toString()
    {
        String config = this.mapDescriptionBuilder.getDescription(definedConfigValues,"Config values");
        String defaults = this.mapDescriptionBuilder.getDescription(defaultValues,"Default values");
        String env = this.mapDescriptionBuilder.getDescription(environmentalVariables, "Environmental variables");

        return config+defaults+env;
    }
}
