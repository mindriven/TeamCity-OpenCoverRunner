package mindriven.buildServer.OpenCoverRunner.agent;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import mindriven.buildServer.OpenCoverRunner.agent.OpenCover.ArgumentsProvider;
import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 18.07.13
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunnerCommandLine implements ProgramCommandLine {

    private ExecutablePathProvider executablePathProvider;
    private mindriven.buildServer.OpenCoverRunner.agent.OpenCover.ArgumentsProvider openCoverArgumentsProvider;
    private mindriven.buildServer.OpenCoverRunner.agent.ReportsGenerator.ArgumentsProvider reportGeneratorArgumentsProvider;
    private ConfigValuesProvider configValuesProvider;

    public OpenCoverRunnerCommandLine(ConfigValuesProvider configValuesProvider)
    {
        this.executablePathProvider = new ExecutablePathProvider(configValuesProvider);
        this.openCoverArgumentsProvider = new ArgumentsProvider(configValuesProvider);
        this.reportGeneratorArgumentsProvider = new mindriven.buildServer.OpenCoverRunner.agent.ReportsGenerator.ArgumentsProvider(configValuesProvider);
        this.configValuesProvider = configValuesProvider;
    }
    @NotNull
    @Override
    // idea here is to create cmd that will actually execute open cover and then reports generator
    // with their respective parameters
    public String getExecutablePath() throws RunBuildException {
        try {
            String cmdFilePath = new File(
                    this.configValuesProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_TEAM_CITY_CHECKOUT_DIR),
                    ".openCoverRunner.cmd"
                    ).getPath().toString();
            this.fillCmdFileWithContent(cmdFilePath);
            return cmdFilePath;
        } catch (Exception e) {
            throw new RunBuildException(e);
        }
    }

    private void fillCmdFileWithContent(String cmdFilePath) throws FileNotFoundException, UnsupportedEncodingException {
        String openCoverExecutablePath = this.executablePathProvider.getExecutablePath(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH);
        String reportGeneratorExecutablePath = this.executablePathProvider.getExecutablePath(OpenCoverRunnerConsts.SETTINGS_REPORTS_GENERATOR_EXECUTABLE_PATH);
        PrintWriter writer = new PrintWriter(cmdFilePath, "UTF-8");
        String openCoverCommand = this.constructExecutionCommand(openCoverExecutablePath, this.openCoverArgumentsProvider);
        String reportGeneratorCommand = this.constructExecutionCommand(reportGeneratorExecutablePath, this.reportGeneratorArgumentsProvider);
        writer.println(openCoverCommand + " && " + reportGeneratorCommand);
        writer.println("echo %ERRORLEVEL%");
        writer.close();
    }

    private String constructExecutionCommand(String executable, IArgumentsProvider provider) throws FileNotFoundException {
        String result = executable;
        Iterator<String> iterator = provider.getArguments().iterator();
        while (iterator.hasNext())
        {
            String option =iterator.next();
            if(!option.isEmpty())
            {
                result+=" \""+option+"\"";
            }
        }
        return result;
    }
    @NotNull
    @Override
    public String getWorkingDirectory() throws RunBuildException {
        return configValuesProvider.getValueOrDefault(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_WORKING_DIRECTORY);
    }

    @NotNull
    @Override
    public List<String> getArguments() throws RunBuildException {
        return new Vector<String>();
    }

    @NotNull
    @Override
    public Map<String, String> getEnvironment() throws RunBuildException {
        return this.configValuesProvider.getEnvironmentalVariables();
    }
}
