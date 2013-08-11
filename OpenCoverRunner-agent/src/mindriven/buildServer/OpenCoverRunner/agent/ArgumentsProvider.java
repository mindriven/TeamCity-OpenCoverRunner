package mindriven.buildServer.OpenCoverRunner.agent;

import jetbrains.buildServer.util.PropertiesUtil;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

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
    private TestsAssembliesPathsProvider assembliesPathsProvider = null;

    public ArgumentsProvider(ConfigValuesProvider configProvider)
    {
        this.configProvider = configProvider;
        this.scanner = new OpenCoverRunnerDirectoryScanner();
        this.assembliesPathsProvider = new TestsAssembliesPathsProvider(this.configProvider, this.scanner);
    }

    public void setDirectoryScanner(OpenCoverRunnerDirectoryScanner directoriesScanner)
    {
        this.scanner = directoriesScanner;
    }

    public void setTestAssembliesPathsProvider(TestsAssembliesPathsProvider provider)
    {
        this.assembliesPathsProvider = provider;
    }

    public List<String> getArguments() throws FileNotFoundException {
        List<String> result = new ArrayList<String>();
        result.add(("-targetargs: "
                +this.assembliesPathsProvider.getTestsAssembliesPaths()
                +" "
                +this.getAdditionalTestsRunnerOptions()).trim());
        result.add("-filter: " + this.getFilters());
        result.add("-target: \""+this.getTestsRunnerPath()+"\"");
        result.add(this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS));
        return result;
    }

    private String getFilters()
    {
        String rawFilters = this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS);
        String[] parts = rawFilters.split("\\s+");
        return StringUtils.trimAndGlue(" ", parts);
    }

    private String getTestsRunnerPath() throws FileNotFoundException {
        String providedPath = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH);
        String checkoutDir = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR);
        return this.scanner.scanForSinglePath(checkoutDir, providedPath);
    }

    private String getAdditionalTestsRunnerOptions()
    {
        return configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_ARGUMENTS);
    }
}
