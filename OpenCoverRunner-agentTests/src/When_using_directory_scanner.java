import junit.framework.Assert;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import org.apache.tools.ant.DirectoryScanner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.07.13
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */
public class When_using_directory_scanner {

    private OpenCoverRunnerDirectoryScanner openCoverRunnerScanner = null;
    @Before
    public void Initialize()
    {
        this.openCoverRunnerScanner = new OpenCoverRunnerDirectoryScanner();
    }

    @Test
    public void casing_does_not_matter() throws Exception {
        DirectoryScanner scanner = mock(DirectoryScanner.class);
        when(scanner.getIncludedFilesCount()).thenReturn(1);
        when(scanner.getIncludedFiles()).thenReturn(new String[]{""});
        openCoverRunnerScanner.setDirectoryScanner(scanner);

        openCoverRunnerScanner.scanForSinglePath("", "");

        ArgumentCaptor<Boolean> argument = ArgumentCaptor.forClass(Boolean.class);
        verify(scanner).setCaseSensitive(argument.capture());
        Assert.assertFalse(argument.getValue());
    }


    @Test(expected = FileNotFoundException.class)
    public void and_found_not_1_matching_file__exception_is_thrown() throws Exception {
        DirectoryScanner scanner = mock(DirectoryScanner.class);
        openCoverRunnerScanner.setDirectoryScanner(scanner);
        when(scanner.getIncludedFiles()).thenReturn(new String[]{"", ""});

        openCoverRunnerScanner.scanForSinglePath(null, null);
    }

    @Test
    public void and_found_1_matching_file__it_gets_returned() throws Exception {

        DirectoryScanner scanner = mock(DirectoryScanner.class);
        String matchedPath = "matchedPath";
        when(scanner.getIncludedFiles()).thenReturn(new String[]{matchedPath});
        this.openCoverRunnerScanner.setDirectoryScanner(scanner);

        String result = openCoverRunnerScanner.scanForSinglePath(null, null);

        Assert.assertEquals(matchedPath, result);
    }
}
