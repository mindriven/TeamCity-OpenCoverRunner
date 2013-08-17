import jetbrains.buildServer.agent.BuildFinishedStatus;
import jetbrains.buildServer.agent.artifacts.ArtifactsWatcher;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCoverRunnerServiceFactory;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 17.08.13
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
public class When_getting_run_result {

    @Test
    public void and_return_code_is__0__build_result_is__FINISHED_SUCCESS() throws Exception
    {
        ArtifactsWatcher watcher = mock(ArtifactsWatcher.class);
        OpenCoverRunnerServiceFactory factory = new OpenCoverRunnerServiceFactory(watcher);
        factory.setConfigValuesProvider(mock(ConfigValuesProvider.class));

        BuildFinishedStatus result = factory.getRunResult(0);

        Assert.assertEquals(result, BuildFinishedStatus.FINISHED_SUCCESS);
    }

    @Test
    public void and_return_code_is__0__result_gets_added_as_hidden_artifact() throws Exception
    {
        ArtifactsWatcher watcher = mock(ArtifactsWatcher.class);
        ConfigValuesProvider provider = mock(ConfigValuesProvider.class);
        when(provider.getValueOrDefault(eq(OpenCoverRunnerConsts.SETTINGS_REPORTS_GENERATOR_OUTPUT_DIR)))
                        .thenReturn("reportsDirectory");
        OpenCoverRunnerServiceFactory factory = new OpenCoverRunnerServiceFactory(watcher);
        factory.setConfigValuesProvider(provider);

        factory.getRunResult(0);

        verify(watcher).addNewArtifactsPath("reportsDirectory/index.htm=>"+OpenCoverRunnerConsts.SETTINGS_HTM_REPORTS_ARTIFACT_PATH);
    }

    @Test
    public void and_return_code_is_not__0__build_result_is__FINISHED_SUCCESS() throws Exception
    {
        ArtifactsWatcher watcher = mock(ArtifactsWatcher.class);
        OpenCoverRunnerServiceFactory factory = new OpenCoverRunnerServiceFactory(watcher);

        BuildFinishedStatus result = factory.getRunResult(2);

        Assert.assertEquals(result, BuildFinishedStatus.FINISHED_WITH_PROBLEMS);
    }
}
