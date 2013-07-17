package mindriven.buildServer.OpenCoverRunner.agent;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 17.07.13
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.BuildServiceAdapter;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class OpenCoverRunnerService extends BuildServiceAdapter {

    @NotNull
    @Override
    public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {
        return new ProgramCommandLine() {
            @NotNull
            public String getExecutablePath() throws RunBuildException {
                return "executable.exe";
            }

            @NotNull
            public String getWorkingDirectory() throws RunBuildException {
                return ".";
            }

            @NotNull
            public List<String> getArguments() throws RunBuildException {
                return new Vector<String>();
            }

            @NotNull
            public Map<String, String> getEnvironment() throws RunBuildException {
                return getBuildParameters().getEnvironmentVariables();
            }
        };
    }
}
