package mindriven.buildServer.OpenCoverRunner.agent;
import jetbrains.buildServer.agent.AgentBuildRunnerInfo;
import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.agent.runner.CommandLineBuildService;
import jetbrains.buildServer.agent.runner.CommandLineBuildServiceFactory;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 09.07.13
 * Time: 19:51
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunnerServiceFactory implements CommandLineBuildServiceFactory, AgentBuildRunnerInfo {

    @NotNull
    @Override
    public String getType() {
        return OpenCoverRunnerConsts.RUNNER_TYPE;
    }

    @Override
    public boolean canRun(@NotNull BuildAgentConfiguration buildAgentConfiguration) {
        //TODO write this
        return true;
    }

    @NotNull
    @Override
    public CommandLineBuildService createService() {
        return new OpenCoverRunnerService();
    }

    @NotNull
    @Override
    public AgentBuildRunnerInfo getBuildRunnerInfo() {
        return this;
    }
}
