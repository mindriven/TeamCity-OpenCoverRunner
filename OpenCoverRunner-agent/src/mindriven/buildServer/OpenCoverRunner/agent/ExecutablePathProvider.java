package mindriven.buildServer.OpenCoverRunner.agent;

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
    private String checkoutDir = null;
    private DirectoryScanner directoryScanner = null;
    public ExecutablePathProvider(Map<String, String> parameters, String checkoutDir)
    {
        this.parameters = parameters;
        this.checkoutDir = checkoutDir;
    }

    public void setDirectoryScanner(DirectoryScanner scanner)
    {
        this.directoryScanner = scanner;
    }

    public String getExecutablePath() throws FileNotFoundException {
        String rawPath = this.getRawExecutablePath();
        this.directoryScanner.setIncludes(new String[]{rawPath});
        this.directoryScanner.setCaseSensitive(false);
        this.directoryScanner.setBasedir(this.checkoutDir);
        this.directoryScanner.scan();
        if(this.directoryScanner.getIncludedFilesCount()!=1)
        {
            throw new FileNotFoundException("Found multiple or none files matching executable path pattern");
        }

        return this.directoryScanner.getIncludedFiles()[0];
    }

    public String getRawExecutablePath()
    {
        String key = OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH;
        String userProvidedPath = OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_PATH;
        if(this.parameters.containsKey(key))
            userProvidedPath = this.parameters.get(key);

        return userProvidedPath;
    }
}
