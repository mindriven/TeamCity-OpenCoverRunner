TeamCity-OpenCoverRunner
========================
This project adds support for OpenCover in TeamCity. Features it includes are:
- Adding new build type to run TC
- Configurable executables paths and parameters for them including support for Ant-style wildcards
- Discovering test assemblies including support for Ant-style wildcards
- Support for report generator (http://nuget.org/packages/ReportGenerator)
- Importing coverage from report as build metric and therefore make possible to add fail build based on it

 
Disclaimer:
I am c# programmer, this is my first java attempt since college, I am very opened to c&c, so don't be shy.

Building this project
---------------------

**Build with IntelliJ IDEA**  

1. Copy the devPackage directory from the TeamCity install location to c:\tmp\devPackage
2. Copy the webapps folder from the TeamCity install location to C:\tmp\webapps\
3. Open project in [IntelliJ IDEA](https://www.jetbrains.com/idea/)

For more info see [Bundled Development Package](https://confluence.jetbrains.com/display/TCD8/Bundled+Development+Package)

If prompted to select a SDK see [Configuring Global, Project and Module SDKs](https://www.jetbrains.com/idea/help/configuring-global-project-and-module-sdks.html#d1278485e69) or [SDKs. Java](https://www.jetbrains.com/idea/help/sdks-java.html)

Deploy plugin
----------------------
**Using TeamCity UI**  
Located the built in out\artifacts\plugin_zip\OpenCoverRunner.zip and upload through the Administration screen in teamcity.  Located under Administration > Plugins List, click Upload plugin zip.
