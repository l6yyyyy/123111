#!/usr/bin/env sh

set -e

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Find java.exe
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Calculate command line arguments for Gradle
GRADLE_OPTS="$GRADLE_OPTS"
JAVA_OPTS="$JAVA_OPTS $DEFAULT_JVM_OPTS"

# Set the GRADLE_HOME variable to point to the directory containing the gradle script
# This location is used by the Gradle wrapper to determine where to find the real Gradle distribution
GRADLE_HOME=$(dirname "$(dirname "$0")")/gradle

# Run Gradle
exec "$JAVACMD" $JAVA_OPTS $GRADLE_OPTS -Dorg.gradle.appname=gradlew -classpath "$GRADLE_HOME/lib/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
