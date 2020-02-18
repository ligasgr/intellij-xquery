## IntelliJ XQuery Support + MarkLogic Debugger Plugin

Provides support for [XQuery](http://www.w3schools.com/xquery/) language version 3.0 and 3.1,
and MarkLogic's extended 1.0-ml variant, in [IntelliJ IDEA](http://www.jetbrains.com/idea/).
The plugin should work in other [JetBrains IDEs](https://www.jetbrains.com/products.html) as well.
This project is forked from the excellent [XQuery Support](https://github.com/ligasgr/intellij-xquery) plugin project by
[Grzegorz Ligas](https://github.com/ligasgr).

This is an extension to that XQuery plugin that adds live debugging support on MarkLogic.  It also includes in-IDE documentation for W3C
XQuery and MarkLogic extension function APIs.

This plugin is at version 1.x but is still under development.  There is more work to do on the
parser and additional features are planned.  Please report bugs and request new features via the
[GitHub Issues](https://github.com/overstory/marklogic-intellij-plugin/issues) for this project.
  
If you would like to participate in this project, please contact
[Ron Hitchens (ron@overstory.co.uk)](mailto:ron@overstory.co.uk).  This is a side gig
for me so progress sometimes proceeds in fits and starts.  If you want to help, or sponsor
my work on this, please contact me.

By far the best way to run this plugin is to install it directly from within IntelliJ
(see the [Wiki](https://github.com/overstory/marklogic-intellij-plugin/wiki) for details).
If you want to build the plugin from source you can clone this repository and run
```./gradlew dist``` from the top.  This 
will produce a ```.zip``` file under ```build/distributions```.  That file can be installed from the Plugins section of
IntelliJ Preferences by selecting "Install from disk".  To make any changes and test it, you will need to
configure an IntelliJ Plugin project, which requires a few non-standard steps.  See the file
`readme.txt` in the top-level notes directory.

The GitHub Pages for this project are still under construction.  Until they are ready, useful information can be found on
the [project Wiki](https://github.com/overstory/marklogic-intellij-plugin/wiki) 

[GitHub Issues](https://github.com/overstory/marklogic-intellij-plugin/issues) - request bug fixes, new features, enhancements, track current release

Ignore the build errors noted below, if any.  The TravisCI build config is out of date and
when it was correct it would still fail from excessive IntelliJ debug log output.  The code
builds and runs fine, I'll fix it when I get a chance.

[![Travis CI Build Status](https://travis-ci.org/overstory/marklogic-intellij-plugin.svg?branch=master)](https://travis-ci.org/overstory/marklogic-intellij-plugin)
