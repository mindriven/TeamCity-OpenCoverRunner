package mindriven.buildServer.OpenCoverRunner.agent.ReportsGenerator;

import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.IArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCover.TestsAssembliesPathsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.StringUtils;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 27.07.13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentsProvider implements IArgumentsProvider {
    private ConfigValuesProvider configProvider = null;

    public ArgumentsProvider(ConfigValuesProvider configProvider)
    {
        this.configProvider = configProvider;
    }

    public List<String> getArguments() throws FileNotFoundException {
        List<String> result = new ArrayList<String>();
        result.add("-reports:\""+this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_OUTPUT_FILE_PATH)+"\"");
        result.add("-targetdir:\""+this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_REPORTS_GENERATOR_OUTPUT_DIR)+"\"");
        return result;
    }
}
