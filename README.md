TeamCity-OpenCoverRunner
========================
This project attempts to add support for OpenCover in TeamCity. Features it will include are:
- Adding new build type to run TC - DONE
- Configurable test runner path and command line options, including support for Ant-like wildcards
- Dynamic discovery of versions of runners (because of NuGet update policy this will allow plugin to not require reconfiguration)
- Support for report generator (http://nuget.org/packages/ReportGenerator)
- Import coverage from generated report as metric and therfore make possible to add build failure condition based on it
Disclaimer:
I am c# programmer, this is my first java attempt since colleage, I am very opened to c&c, so don't be shy.
