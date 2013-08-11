package mindriven.buildServer.OpenCoverRunner.common;

/**
 * Created with IntelliJ IDEA.
 * User: kposiadala
 * Date: 06.07.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public interface OpenCoverRunnerConsts {
    String RUNNER_TYPE = "OpenCoverRunner";
    String RUNNER_DISPLAY_NAME = "OpenCover runner";
    String RUNNER_DESCRIPTION = "Runs OpenCover and publishes coverage to TC";

    String SETTINGS_OPEN_COVER_PATH = "openCoverRunner.openCoverPath";
    String SETTINGS_DEFAULT_OPEN_COVER_PATH = "packages/OpenCover*/OpenCover.Console.exe";
    String SETTINGS_OPEN_COVER_FILTERS = "openCoverRunner.openCoverFilters";
    String SETTINGS_DEFAULT_OPEN_COVER_FILTERS = "+[*]*";
    String SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS = "openCoverRunner.openCoverAdditionalOptions";
    String SETTINGS_OPEN_COVER_OUTPUT_FILE_PATH = "openCoverRunner.openCoverOutputFilePath";
    String SETTINGS_DEFAULT_OPEN_COVER_OUTPUT_FILE_PATH = "result.xml";

    String SETTINGS_REPORTS_GENERATOR_EXECUTABLE_PATH = "openCoverRunner.reportGeneratorExecutablePath";
    String SETTINGS_DEFAULT_REPORTS_EXECUTABLE_GENERATOR_PATH = "packages/ReportGenerator*/ReportGenerator.exe";
    String SETTINGS_REPORTS_GENERATOR_OUTPUT_DIR = "openCoverRunner.reportGeneratorOutputDir";
    String SETTINGS_DEFAULT_REPORTS_GENERATOR_OUTPUT_DIR = "CoverageReport";

    String SETTINGS_OPEN_COVER_WORKING_DIRECTORY = "openCoverRunner.openCoverWorkingDirectory";
    String SETTINGS_TESTS_ASSEMBLIES_PATHS = "openCoverRunner.testsAssembliesPath";
    String SETTINGS_TESTS_RUNNER_PATH = "openCoverRunner.testsRunnerPath";
    String SETTINGS_TESTS_RUNNER_ARGUMENTS = "openCoverRunner.testsRunnerArguments";
    String SETTINGS_PASS_ASSEMBLIES_AS_SWITCH = "openCoverRunner.passAssembliesAsSwitch";
    String SETTINGS_TESTS_ASSEMBLIES_COMMAND_LINE_SWITCH = "openCoverRunner.testsAssembliesCommandLineSwitch";

    String SETTINGS_TEAM_CITY_CHECKOUT_DIR = "openCoverRunner.TeamCityCheckoutDir";
}
