import jetbrains.buildServer.agent.BuildAgentConfiguration;
import jetbrains.buildServer.agent.BuildAgentSystemInfo;
import jetbrains.buildServer.agent.artifacts.ArtifactsWatcher;
import junit.extensions.TestSetup;
import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCoverRunnerServiceFactory;
import mindriven.buildServer.OpenCoverRunner.server.OpenCoverRunType;
import org.junit.Before;
import org.junit.Test;
import jetbrains.buildServer.serverSide.RunTypeRegistry;
import jetbrains.buildServer.util.StringUtil;
import jetbrains.buildServer.web.openapi.PluginDescriptor;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;
/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.07.13
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class When_creating_service {
    OpenCoverRunnerServiceFactory factory;

    @Before
    public void Setup()
    {
        this.factory = new OpenCoverRunnerServiceFactory(mock(ArtifactsWatcher.class));
    }

    @Test
    public void returned_type_is_the_same_as_on_server() throws Exception {
        RunTypeRegistry registry = mock(RunTypeRegistry.class);
        PluginDescriptor descriptor = mock(PluginDescriptor.class);
        OpenCoverRunType serverRunType = new OpenCoverRunType(registry, descriptor);

        String agentType = factory.getType();
        String serverType = serverRunType.getType();

        Assert.assertSame(agentType, serverType);
    }

    @Test
    public void it_can_run_on_windows_machine() throws Exception {
        BuildAgentConfiguration config = mock(BuildAgentConfiguration.class);
        BuildAgentSystemInfo systemInfo = mock(BuildAgentSystemInfo.class);
        when(systemInfo.isWindows()).thenReturn(true);
        when(config.getSystemInfo()).thenReturn(systemInfo);

        boolean canRun = factory.canRun(config);

        Assert.assertTrue(canRun);
    }

    @Test
    public void it_can_not_run_on_not_windows_machine() throws Exception {
        BuildAgentConfiguration config = mock(BuildAgentConfiguration.class);
        BuildAgentSystemInfo systemInfo = mock(BuildAgentSystemInfo.class);
        when(systemInfo.isWindows()).thenReturn(false);
        when(config.getSystemInfo()).thenReturn(systemInfo);

        boolean canRun = factory.canRun(config);

        Assert.assertFalse(canRun);
    }

    @Test
    public void it_logs_if_it_can_run() throws Exception {
        Logger log = mock(Logger.class);
        BuildAgentConfiguration config = mock(BuildAgentConfiguration.class);
        BuildAgentSystemInfo systemInfo = mock(BuildAgentSystemInfo.class);
        when(systemInfo.isWindows()).thenReturn(true);
        when(config.getSystemInfo()).thenReturn(systemInfo);
        factory.setLog(log);

        factory.canRun(config);

        verify(log).log(eq(Level.INFO), anyString());
    }
}
