package mindriven.buildServer.OpenCoverRunner.agent.OpenCover;

import mindriven.buildServer.OpenCoverRunner.agent.ConfigValuesProvider;
import mindriven.buildServer.OpenCoverRunner.agent.IArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.OpenCoverRunnerDirectoryScanner;
import mindriven.buildServer.OpenCoverRunner.agent.Utils.StringUtils;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 27.07.13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class ArgumentsProvider implements IArgumentsProvider {
    private ConfigValuesProvider configProvider = null;
    private OpenCoverRunnerDirectoryScanner scanner = null;
    private TestsAssembliesPathsProvider assembliesPathsProvider = null;

    public ArgumentsProvider(ConfigValuesProvider configProvider)
    {
        this.configProvider = configProvider;
        this.scanner = new OpenCoverRunnerDirectoryScanner();
        this.assembliesPathsProvider = new TestsAssembliesPathsProvider(this.configProvider, this.scanner);
    }

    public void setDirectoryScanner(OpenCoverRunnerDirectoryScanner directoriesScanner)
    {
        this.scanner = directoriesScanner;
    }

    public void setTestAssembliesPathsProvider(TestsAssembliesPathsProvider provider)
    {
        this.assembliesPathsProvider = provider;
    }

    public List<String> getArguments() throws FileNotFoundException {
        List<String> result = new ArrayList<String>();
        result.add(("-targetargs:"
                +this.assembliesPathsProvider.getTestsAssembliesPaths()
                +" "
                +this.getAdditionalTestsRunnerOptions()).trim());
        result.add("-filter:" + this.getFilters());
        result.add("-target:"+this.getTestsRunnerPath());
        result.add("-output:\""+this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_OUTPUT_FILE_PATH)+"\"");
        result.addAll(this.getAdditionalOptions());
        return result;
    }

    private Vector<String> getAdditionalOptions()
    {
        String userInput = this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS);
        String[] rawOptions = userInput.split(" ");
        Vector<String> result = new Vector<String>();
        for(int i=0;i<rawOptions.length;i++)
        {
            String optionToAppend = rawOptions[i];
            if(optionToAppend.contains("\""))
            {
                int numberOfQuotationMarks = optionToAppend.length() - optionToAppend.replace("\"", "").length();
                if(numberOfQuotationMarks%2==1)
                {
                    for(int j=i+1;j<rawOptions.length;j++)
                    {
                        String followingOption = rawOptions[j];
                        optionToAppend+=" "+followingOption;
                        int numberOfQuotationMarksInFollowingOption = followingOption.length() - followingOption.replace("\"", "").length();
                        if(numberOfQuotationMarksInFollowingOption%2==1)
                        {
                            i=j;
                            break;
                        }
                    }
                }
            }
            if(!optionToAppend.trim().isEmpty())
                result.add(optionToAppend.trim());
        }

        return result;
    }

    private String getFilters()
    {
        String rawFilters = this.configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS);
        String[] parts = rawFilters.split("\\s+");
        return StringUtils.trimAndGlue(" ", parts);
    }

    private String getTestsRunnerPath() throws FileNotFoundException {
        String providedPath = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH);
        String checkoutDir = configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR);
        return this.scanner.scanForSinglePath(checkoutDir, providedPath);
    }

    private String getAdditionalTestsRunnerOptions()
    {
        return configProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_ARGUMENTS);
    }
}
