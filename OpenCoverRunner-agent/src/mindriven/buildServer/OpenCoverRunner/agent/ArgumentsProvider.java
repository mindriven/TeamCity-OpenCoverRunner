package mindriven.buildServer.OpenCoverRunner.agent;

import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.apache.tools.ant.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 27.07.13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentsProvider {
    private ConfigValuesProvider configProvider = null;

    public ArgumentsProvider(ConfigValuesProvider configProvider)
    {
        this.configProvider = configProvider;
    }

    public List<String> getArguments()
    {
        List<String> result = new ArrayList<String>();
        result.add(this.getFilters());
        result.add(this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS));
        return result;
    }

    private String getFilters()
    {
        String rawFilters = this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS);
        String[] parts = rawFilters.split("\\s+");
        return "filter: \""+this.trimAndGlue(" ",parts)+"\"";
    }

    private String trimAndGlue(String glue, String[] strArray)
    {
        String result = "";
        for(int i=0;i<strArray.length;i++)
        {
            result += strArray[i].trim() + glue;
        }
        return result.trim();
    }
}