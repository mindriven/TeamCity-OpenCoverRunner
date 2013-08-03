package mindriven.buildServer.OpenCoverRunner.agent;
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.AgentBuildRunnerInfo;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
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
        DefaultValuesMap defaults = new DefaultValuesMap();
        defaults.addDefaultValue(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_WORKING_DIRECTORY,
                                 this.getCheckoutDirectory().toString());

        ConfigValuesContainer configValues = new ConfigValuesContainer();
        configValues.setEnvironmentalVariables(this.getEnvironmentVariables());
        configValues.setDefaultValuesMapping(defaults.getMapping());
        configValues.setDefinedConfigValues(this.getConfigParameters());

        return new OpenCoverRunnerCommandLine(new ConfigValuesProvider(configValues));
    }


}
