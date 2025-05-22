@echo off
echo Compiling Java files...
javac src/ridebooking/*.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    echo.
    echo Running the program...
    echo =====================================
    java -cp src ridebooking.Main
) else (
    echo Compilation failed!
    pause
)
pause 