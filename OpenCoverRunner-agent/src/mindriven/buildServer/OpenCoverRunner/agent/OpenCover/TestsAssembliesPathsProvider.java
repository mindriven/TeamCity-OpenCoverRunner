package mindriven.buildServer.OpenCoverRunner.agent.OpenCover;

import jetbrains.buildServer.util.PropertiesUtil;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.StringUtils;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 11.08.13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class TestsAssembliesPathsProvider {

    private ConfigValuesProvider configProvider = null;
    private OpenCoverRunnerDirectoryScanner scanner = null;

    public TestsAssembliesPathsProvider(ConfigValuesProvider provider, OpenCoverRunnerDirectoryScanner scanner)
    {
        this.configProvider = provider;
        this.scanner = scanner;
    }

    public String getTestsAssembliesPaths() throws FileNotFoundException {
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
        if(result.size()==0)
            throw new FileNotFoundException("No test assemblies were found, please specify valid scanning patterns resulting in at least one file");

        String runnerArgs = StringUtils.trimAndGlue("\" \"", result.toArray(new String[result.size()]));
        return "\""+runnerArgs+"\"";
    }
}
