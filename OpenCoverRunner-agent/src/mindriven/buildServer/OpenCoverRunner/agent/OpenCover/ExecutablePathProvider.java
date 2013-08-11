package mindriven.buildServer.OpenCoverRunner.agent.OpenCover;

import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 20.07.13
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class ExecutablePathProvider {

    private Map<String, String> parameters = null;
    private OpenCoverRunnerDirectoryScanner directoryScanner = new OpenCoverRunnerDirectoryScanner();
    private ConfigValuesProvider configProvider;

    public ExecutablePathProvider(ConfigValuesProvider configProvider)
    {
        this.configProvider = configProvider;
    }

    public void setDirectoryScanner(OpenCoverRunnerDirectoryScanner scanner)
    {
        this.directoryScanner = scanner;
    }

    public String getExecutablePath() throws FileNotFoundException {
        String pathKey = OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH;
        String path = this.configProvider.getValueOrDefault(pathKey);
        String checkoutDirKey = OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR;
        String checkoutDir = this.configProvider.getValueOrDefault(checkoutDirKey);
        return this.directoryScanner.scanForSinglePath(checkoutDir, path);
    }
}
