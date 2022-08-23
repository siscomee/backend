@ECHO OFF
IF "%1"=="start" (
    ECHO start your app name
    start "pfm-backend" java -jar mirestapi.jar
) ELSE IF "%1"=="stop" (
    ECHO stop your app name
    TASKKILL /FI "WINDOWTITLE eq pfm-backend"
) ELSE (
    ECHO please, use "run.bat start" or "run.bat stop"
)
pause