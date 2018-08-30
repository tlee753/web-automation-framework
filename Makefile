all:
	javac -cp .:res/* util/*.java
	javac -cp .:res/* *.java
	java -cp .:res/* Main

compile:
	javac -cp .:res/* util/*.java
	javac -cp .:res/* *.java

clean:
	rm -rf test-output *Files* *.class util/*.class *.zip *.png *.jpg

superclean:
	rm -rf test-output *Files* *.class util/*.class *.zip *.png *.jpg *.pdf docs/*.pdf *.xml

old:
	javac -cp .:res/* *.java
	java -cp .:res/* org.testng.TestNG build.xml -usedefaultlisteners false

windows:
	javac -cp "res/*;." util/*.java
	javac -cp "res/*;." *.java
	java -cp "res/*;." Main

easteregg:
	echo "\nYou've found a glitch in the matrix...\n"

pkg:
	zip -r web-automation-framework-X.X.zip *
