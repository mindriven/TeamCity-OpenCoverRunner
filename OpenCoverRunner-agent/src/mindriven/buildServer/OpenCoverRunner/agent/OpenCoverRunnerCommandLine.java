package mindriven.buildServer.OpenCoverRunner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    @Override
    public String getExecutablePath() throws RunBuildException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
