@ECHO OFF

IF "%~1"=="" GOTO HELP
IF NOT "%~2"=="" GOTO HELP

SET JAVA_PATH=%~1\bin
ECHO %JAVA_PATH%

"%JAVA_PATH%\javac.exe" -cp "lib/*" "-Acom.google.java.contract.classpath=lib/cofoja.asm-1.2-20140817.jar" "-Acom.google.java.contract.classoutput=bin" -d bin src/*.java

"%JAVA_PATH%\java.exe" "-javaagent:lib/cofoja.asm-1.2-20140817.jar" -cp "bin;lib/*" TestRunner

GOTO END	

:HELP
ECHO "USAGE: .\testrun.bat <path to JDK>"

:END