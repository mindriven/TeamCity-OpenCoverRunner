package mindriven.buildServer.OpenCoverRunner.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kamil
 * Date: 03.08.13
 * Time: 09:23
 * To change this template use File | Settings | File Templates.
 */
public class DefaultValuesMap {
    private static Map<String, String> defaultsMapping;

    static
    {
        defaultsMapping = new HashMap<String, String>();
        defaultsMapping.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_FILTERS,
                        OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_FILTERS);
        defaultsMapping.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_PATH,
                        OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_PATH);
        defaultsMapping.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_OUTPUT_FILE_PATH,
                OpenCoverRunnerConsts.SETTINGS_DEFAULT_OPEN_COVER_OUTPUT_FILE_PATH);
        defaultsMapping.put(OpenCoverRunnerConsts.SETTINGS_REPORTS_GENERATOR_OUTPUT_DIR,
                OpenCoverRunnerConsts.SETTINGS_DEFAULT_REPORTS_GENERATOR_OUTPUT_DIR);

        defaultsMapping.put(OpenCoverRunnerConsts.SETTINGS_PASS_ASSEMBLIES_AS_SWITCH,
                "false");
        defaultsMapping.put(OpenCoverRunnerConsts.SETTINGS_OPEN_COVER_ADDITIONAL_OPTIONS, "");
        defaultsMapping.put(OpenCoverRunnerConsts.SETTINGS_TESTS_RUNNER_ARGUMENTS, "");
    }

    public Map<String, String> getMapping()
    {
        Map<String, String> map = new HashMap<String, String>(defaultsMapping);
        return map;
    }

    public void addDefaultValue(String key, String value)
    {
        defaultsMapping.put(key, value);
    }
}
