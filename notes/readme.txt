

Add src/main/java and src/main/groovy to IntelliJ sources

To get the Plugin project to automatically compile the forms, add src/main/resources as a source directory

To set debugging in the connector:

	-Dxquery.debugger.logging=true -Dxquery.debugger.logging.level=trace   [trace, debug, info, warn, error]


Setting up the project

Set variables in build.gradle: project.ext.sdkVersion, project.ext.ideaInstallationSource and project.ext.mlDocumentationSource as appropriate
./gradlew downloadSdk
./gradlew unzipSdk
./gradlew build
./gradlew idea
In File > Project Structure > Platform Settings > SDKs add IntelliJ Platform Plugin SDK
Select the downloaded SDK
In Project Settings > Project set newly created plugin SDK as Project SDK


Running the JetBrains Plugin Verifier

See this page to get/update the verifier: https://github.com/JetBrains/intellij-plugin-verifier

Download the Jar file
Build the plugin and run the verifier on it: ./gradlew -jar verifier-all.jar check-plugin <path-to-plugin-zip-file.zip> <path-to-unpacked-SDK>
