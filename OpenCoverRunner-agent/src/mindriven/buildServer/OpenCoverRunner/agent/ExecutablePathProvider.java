package mindriven.buildServer.OpenCoverRunner.agent;

import mindriven.buildServer.OpenCoverRunner.common.DefaultValuesMap;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.apache.tools.ant.DirectoryScanner;

import java.io.FileNotFoundException;
import java.security.InvalidAlgorithmParameterException;
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
    private DirectoryScanner directoryScanner = null;
    private ConfigValuesProvider configProvider;

    public ExecutablePathProvider(ConfigValuesProvider configProvider)
    {
        this.configProvider = configProvider;
    }

    public void setDirectoryScanner(DirectoryScanner scanner)
    {
        this.directoryScanner = scanner;
    }

    public String getExecutablePath() throws FileNotFoundException {
        String pathKey = OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH;
        String rawPath = this.configProvider.getValueOrDefault(pathKey);
        String checkoutDirKey = OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR;
        String checkoutDir = this.configProvider.getValueOrDefault(checkoutDirKey);
        this.directoryScanner.setIncludes(new String[]{rawPath});
        this.directoryScanner.setCaseSensitive(false);
        this.directoryScanner.setBasedir(checkoutDir);
        this.directoryScanner.scan();
        if(this.directoryScanner.getIncludedFilesCount()!=1)
        {
            throw new FileNotFoundException("Found multiple or none files matching executable path pattern");
        }

        return this.directoryScanner.getIncludedFiles()[0];
    }
}
