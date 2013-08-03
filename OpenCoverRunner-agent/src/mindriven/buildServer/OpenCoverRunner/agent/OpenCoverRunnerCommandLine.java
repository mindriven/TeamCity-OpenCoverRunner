package mindriven.buildServer.OpenCoverRunner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import mindriven.buildServer.OpenCoverRunner.common.DefaultValuesMap;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

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
    public OpenCoverRunnerCommandLine(ConfigValuesProvider configValuesProvider)
    {
        this.executablePathProvider = new ExecutablePathProvider(configValuesProvider);
        this.argumentsProvider = new ArgumentsProvider(configValuesProvider);
    }
    @NotNull
    @Override
    public String getExecutablePath() throws RunBuildException {
        try {
            return this.executablePathProvider.getExecutablePath();
        } catch (FileNotFoundException e) {
            throw new RunBuildException(e);
        }
    }

    @NotNull
    @Override
    public String getWorkingDirectory() throws RunBuildException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public List<String> getArguments() throws RunBuildException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public Map<String, String> getEnvironment() throws RunBuildException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
