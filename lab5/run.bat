@echo off
setlocal
cd /d "%~dp0"
where javac >nul 2>&1
if errorlevel 1 (
    echo Khong tim thay javac. Can cai JDK 8 tro len va them vao PATH.
    exit /b 1
)
if not exist build mkdir build
javac -encoding UTF-8 -d build src\*.java
if errorlevel 1 exit /b 1
java -Dfile.encoding=UTF-8 -cp build BenchmarkMain
