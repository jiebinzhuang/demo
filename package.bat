%~d0
cd %~dp0
call mvn clean package -Dmaven.test.skip=true
cd target
echo title demo>demo.bat
echo java -jar -Xms128M -Xmx512M -XX:PermSize=128M -XX:MaxPermSize=256M -Dorg.mortbay.util.URI.charset=GBK demo.jar>>demo.bat
jar cf demo.rar demo.jar config lib demo.bat

echo title demo>demo.bat
echo java -jar -Xms128M -Xmx512M -XX:PermSize=128M -XX:MaxPermSize=256M demo-small.jar>demo.bat
jar cf demo-small.rar demo-small.jar config lib demo.bat

del /q config\*.xml
del /q config\*.properties
del /q lib\*.*

echo title demo>demo.bat
echo java -jar -Xms128M -Xmx512M -XX:PermSize=128M -XX:MaxPermSize=256M -Dorg.mortbay.util.URI.charset=GBK demo.jar>>demo.bat
jar cf demo-update.rar demo.jar config lib

echo title demo>demo.bat
echo java -jar -Xms128M -Xmx512M -XX:PermSize=128M -XX:MaxPermSize=256M demo-small.jar>demo.bat
jar cf demo-update-small.rar demo-small.jar config lib
pause
