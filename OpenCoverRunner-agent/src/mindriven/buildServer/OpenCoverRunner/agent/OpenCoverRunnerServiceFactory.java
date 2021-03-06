package mindriven.buildServer.OpenCoverRunner.agent;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.AgentBuildRunnerInfo;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.artifacts.ArtifactsWatcher;
import jetbrains.buildServer.agent.inspections.InspectionReporter;
import jetbrains.buildServer.agent.runner.BuildServiceAdapter;
import jetbrains.buildServer.agent.runner.CommandLineBuildService;
import jetbrains.buildServer.agent.runner.CommandLineBuildServiceFactory;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import mindriven.buildServer.OpenCoverRunner.common.DefaultValuesMap;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 09.07.13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunnerServiceFactory extends BuildServiceAdapter implements CommandLineBuildServiceFactory, AgentBuildRunnerInfo {

    private Logger Log = Logger.getLogger(OpenCoverRunnerServiceFactory.class.getName());
    private ConfigValuesProvider valuesProvider;
    private final ArtifactsWatcher myArtifactsWatcher;

    public OpenCoverRunnerServiceFactory(@NotNull ArtifactsWatcher myArtifactsWatcher)
    {
        this.myArtifactsWatcher = myArtifactsWatcher;
    }

    public void setConfigValuesProvider(ConfigValuesProvider provider)
    {
        this.valuesProvider = provider;
    }

    public void setLog(Logger log)
    {
        this.Log = log;
    }
    @NotNull
    @Override
    public String getType() {
        return OpenCoverRunnerConsts.RUNNER_TYPE;
    }

    @Override
    public boolean canRun(@NotNull BuildAgentConfiguration buildAgentConfiguration) {
        boolean result = buildAgentConfiguration.getSystemInfo().isWindows();
        Log.log(Level.INFO, String.format("OpenCoverRunner can run only on windows machine. Detected windows machine: %s", result));
        return result;
    }

    @NotNull
    @Override
    public CommandLineBuildService createService() {
        return this;
    }

    @NotNull
    @Override
    public AgentBuildRunnerInfo getBuildRunnerInfo() {
        return this;
    }

    @NotNull
    @Override
    public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {
        this.initValuesProvider();
        return new OpenCoverRunnerCommandLine(this.valuesProvider);
    }

    @NotNull
    @Override
    public BuildFinishedStatus getRunResult(int exitCode) {

        if(exitCode==0)
        {
            myArtifactsWatcher.addNewArtifactsPath(
                    this.valuesProvider.getValueOrDefault(
                            OpenCoverRunnerConsts.SETTINGS_REPORTS_GENERATOR_OUTPUT_DIR)+"/index.htm"
                            + "=>"
                            + OpenCoverRunnerConsts.SETTINGS_HTM_REPORTS_ARTIFACT_PATH);
            return BuildFinishedStatus.FINISHED_SUCCESS;
        }
        return BuildFinishedStatus.FINISHED_WITH_PROBLEMS;
    }

    private void initValuesProvider()
    {
        DefaultValuesMap defaults = new DefaultValuesMap();
        String checkoutDir = this.getCheckoutDirectory().toString();
        defaults.addDefaultValue(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_WORKING_DIRECTORY,
                checkoutDir);
        ConfigValuesContainer configValues = new ConfigValuesContainer();
        configValues.setEnvironmentalVariables(this.getEnvironmentVariables());
        configValues.setDefaultValuesMapping(defaults.getMapping());

        Map<String, String> configuration = new HashMap<String, String>(this.getRunnerParameters());
        configuration.put(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR, checkoutDir);
        configValues.setDefinedConfigValues(configuration);

        this.valuesProvider = new ConfigValuesProvider(configValues);
    }
}
