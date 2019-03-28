%~d0
cd %~dp0
svn update
call mvn release:prepare release:perform
pause
