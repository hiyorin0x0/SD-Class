@echo off
setlocal
cd /d "%~dp0"
chcp 65001 >nul
where javac >nul 2>&1
if errorlevel 1 (
    echo Khong tim thay javac. Can cai JDK 8 tro len va them vao PATH.
    pause
    exit /b 1
)
if not exist build mkdir build
javac -encoding UTF-8 -d build src\*.java
if errorlevel 1 (
    pause
    exit /b 1
)
echo === Minh hoa bon phuong thuc thanh toan ===
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp build Main
if errorlevel 1 (
    pause
    exit /b 1
)
echo.
echo === Minh hoa tach interface theo ISP ===
java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -cp build IspDemo
if errorlevel 1 (
    pause
    exit /b 1
)
pause
