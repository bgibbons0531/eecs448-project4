dir /s /B *.java > sources.txt
javac -d ./bin @sources.txt
cd bin
jar cvfm ../Game.jar ../manifest.txt bgibbons/ ../res/
cd ..
del sources.txt
pause