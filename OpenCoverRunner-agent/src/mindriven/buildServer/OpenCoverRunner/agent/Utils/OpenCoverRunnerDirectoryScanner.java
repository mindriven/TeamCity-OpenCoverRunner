package mindriven.buildServer.OpenCoverRunner.agent.Utils;

import org.apache.tools.ant.DirectoryScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 10.08.13
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunnerDirectoryScanner {

    private DirectoryScanner scanner = new DirectoryScanner();

    public void setDirectoryScanner(DirectoryScanner scanner)
    {
        this.scanner = scanner;
    }

    public String scanForSinglePath(String basePath, String pattern) throws FileNotFoundException {
        String[] allFound = this.scanForMultiplePaths(basePath, pattern);
        if(allFound.length!=1)
        {
            this.throwScanningResultInconclusiveException(pattern);
        }
        return allFound[0];
    }

    public String[] scanForMultiplePaths(String basePath, String pattern)
    {
        this.scanner.setCaseSensitive(false);
        this.scanner.setBasedir(basePath);
        this.scanner.setIncludes(new String[]{pattern});
        this.scanner.scan();
        String[] includedPath = this.scanner.getIncludedFiles();
        Vector<String> result = new Vector<String>();
        for(int i=0;i<includedPath.length;i++)
        {
            result.add(new File(this.scanner.getBasedir(), this.scanner.getIncludedFiles()[i]).getPath());
        }
        return result.toArray(new String[result.size()]);

    }

    private void throwScanningResultInconclusiveException(String path) throws FileNotFoundException {
        String message = "Found multiple or none files("+this.scanner.getIncludedFilesCount()+") matching OpenCover executable path pattern";
        message+="\n\rSearched in: "+this.scanner.getBasedir();
        message+="\n\rSearched for: "+path;
        throw new FileNotFoundException(message);
    }
}
