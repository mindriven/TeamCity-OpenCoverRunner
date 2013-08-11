package mindriven.buildServer.OpenCoverRunner.server;

import mindriven.buildServer.OpenCoverRunner.common.OpenCoverRunnerConsts;
import org.jetbrains.annotations.NotNull;
/**
 * Created with IntelliJ IDEA.
 * User: kposiadala
 * Date: 07.07.13
 * Time: 11:57
 * To change this template use File | Settings | File Templates.
 */
public class OpenCoverRunnerConstantsBean {
    @NotNull
    public String getOpenCoverPath()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH;
    }

    @NotNull
    public String getOpenCoverDefaultPath()
    {
        return OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_PATH;
    }

    @NotNull
    public String getOpenCoverFilters()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS;
    }

    @NotNull
    public String getOpenCoverDefaultFilters()
    {
        return OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_FILTERS;
    }

    @NotNull
    public String getOpenCoverAdditionalOptions()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS;
    }

    @NotNull
    public String getOpenCoverWorkingDirectory()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_WORKING_DIRECTORY;
    }

    @NotNull
    public String getTestsAssembliesPaths()
    {
        return OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_PATHS;
    }

    @NotNull
    public String getTestsRunnerPath()
    {
        return OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_PATH;
    }

    @NotNull
    public String getTestsRunnerArguments()
    {
        return OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_ARGUMENTS;
    }

    @NotNull
    public String getPassAssembliesAsSwitch()
    {
        return OpenCoverRunnerConsts.SETTINGS_PASS_ASSEMBLIES_AS_SWITCH;
    }

    @NotNull
    public String getTestsAssembliesCommandLineSwitch()
    {
        return OpenCoverRunnerConsts.SETTINGS_TESTS_ASSEMBLIES_COMMAND_LINE_SWITCH;
    }

    @NotNull
    public String getOpenCoverOutputFilePath()
    {
        return OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_OUTPUT_FILE_PATH;
    }

    @NotNull
    public String getOpenCoverOutputDefaultFilePath()
    {
        return OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_OUTPUT_FILE_PATH;
    }

    @NotNull
    public String getReportsGeneratorExecutablePath()
    {
        return OpenCoverRunnerConsts.SETTINGS_REPORTS_GENERATOR_EXECUTABLE_PATH;
    }

    @NotNull
    public String getDefaultReportsGeneratorExecutablePath()
    {
        return OpenCoverRunnerConsts.SETTINGS_DEFAULT_REPORTS_EXECUTABLE_GENERATOR_PATH;
    }
}
