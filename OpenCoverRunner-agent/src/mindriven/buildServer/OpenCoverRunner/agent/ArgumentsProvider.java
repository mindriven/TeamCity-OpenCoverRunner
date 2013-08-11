package mindriven.buildServer.OpenCoverRunner.agent;

import jetbrains.buildServer.util.PropertiesUtil;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.apache.tools.ant.util.StringUtils;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 27.07.13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentsProvider {
    private ConfigValuesProvider configProvider = null;
    private OpenCoverRunnerDirectoryScanner scanner = null;

    public ArgumentsProvider(ConfigValuesProvider configProvider)
    {
        this.configProvider = configProvider;
        this.scanner = new OpenCoverRunnerDirectoryScanner();
    }

    public void setDirectoryScanner(OpenCoverRunnerDirectoryScanner directoriesScanner)
    {
        this.scanner = directoriesScanner;
    }

    public List<String> getArguments() throws FileNotFoundException {
        List<String> result = new ArrayList<String>();
        result.addAll(this.getTestsAssembliesPaths());
        result.add(this.getFilters());
        result.add(this.getTestsRunnerPath());
        result.add(this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS));
        return result;
    }

    private String getFilters()
    {
        String rawFilters = this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS);
        String[] parts = rawFilters.split("\\s+");
        return "filter: \""+this.trimAndGlue(" ",parts)+"\"";
    }

    private String getTestsRunnerPath() throws FileNotFoundException {
        String providedPath = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH);
        String checkoutDir = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR);
        return "target: \""+this.scanner.scanForSinglePath(checkoutDir, providedPath)+"\"";
    }

    private Collection<String> getTestsAssembliesPaths()
    {
        String rawInput = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS);
        String checkoutDir = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR);
        String commandLineSwitch = "";
        if(PropertiesUtil.getBoolean(configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_PASS_ASSEMBLIES_AS_SWITCH)))
        {
            commandLineSwitch = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_COMMAND_LINE_SWITCH);
        }

        String[] lines = rawInput.split("\\r?\\n");
        Collection<String> result = new Vector<String>();
        for (int i=0;i<lines.length;i++)
        {
            String cleanPattern = lines[i].trim().replace("\"", "");
            String[] resultForLine = this.scanner.scanForMultiplePaths(checkoutDir, cleanPattern);
            if(resultForLine!=null && resultForLine.length!=0)
            {
                for(int j=0;j<resultForLine.length;j++)
                {
                    result.add((commandLineSwitch+" "+resultForLine[j]).trim());
                }
            }
        }

        return result;
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
