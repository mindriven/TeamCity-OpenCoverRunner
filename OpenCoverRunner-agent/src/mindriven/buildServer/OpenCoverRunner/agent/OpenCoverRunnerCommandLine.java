package mindriven.buildServer.OpenCoverRunner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCover.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCover.ExecutablePathProvider;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.07.13
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunnerCommandLine implements ProgramCommandLine {

    private ExecutablePathProvider executablePathProvider;
    private ArgumentsProvider argumentsProvider;
    private ConfigValuesProvider configValuesProvider;

    public OpenCoverRunnerCommandLine(ConfigValuesProvider configValuesProvider)
    {
        this.executablePathProvider = new ExecutablePathProvider(configValuesProvider);
        this.argumentsProvider = new ArgumentsProvider(configValuesProvider);
        this.configValuesProvider = configValuesProvider;
    }
    @NotNull
    @Override
    // idea here is to create cmd that will actually execute open cover and then reports generatoe
    // with their respective parameters
    public String getExecutablePath() throws RunBuildException {
        try {
            String cmdFilePath = new File(
                    this.configValuesProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR),
                    ".openCoverRunner.cmd"
                    ).getPath().toString();
            String openCoverRun = this.executablePathProvider.getExecutablePath();
            PrintWriter writer = new PrintWriter(cmdFilePath, "UTF-8");
            Iterator<String> iterator = this.argumentsProvider.getArguments().iterator();
            while (iterator.hasNext())
            {
                openCoverRun+=" \""+iterator.next()+"\"";
            }
            writer.println(openCoverRun);
            writer.close();
            return cmdFilePath;

        } catch (Exception e) {
            throw new RunBuildException(e);
        }
    }

    @NotNull
    @Override
    public String getWorkingDirectory() throws RunBuildException {
        return configValuesProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_WORKING_DIRECTORY);
    }

    @NotNull
    @Override
    public List<String> getArguments() throws RunBuildException {
        return new Vector<String>();
    }

    @NotNull
    @Override
    public Map<String, String> getEnvironment() throws RunBuildException {
        return this.configValuesProvider.getEnvironmentalVariables();
    }
}
