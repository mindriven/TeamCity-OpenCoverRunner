package PathsManipulations;

import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.ExecutablePathProvider;
import mindriven.buildServer.OpenCoverRunner.agent.PathsHelper;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 21.07.13
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class When_deciding_if_path_needs_discovery {

    private PathsHelper pathsHelper = null;
    @Before
    public void Initialize()
    {
        this.pathsHelper = new PathsHelper();
    }

    @Test
    public void and_path_contains_placeholder__it_requires_discovery() throws Exception {

        String path = "some"+ OpenCoverRunnerConsts.PATH_DISCOVERY_PLACEHOLDER+"thing";

        Boolean result = this.pathsHelper.doesPathUseDiscoveryPattern(path);

        Assert.assertTrue(result);
    }

    @Test
    public void and_path_doesnt_contain_placeholder__it_requires_no_discovery() throws Exception {

        Boolean result = this.pathsHelper.doesPathUseDiscoveryPattern("something");

        Assert.assertFalse(result);
    }
}
