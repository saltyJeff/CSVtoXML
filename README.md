CSVtoXML
========
1. Run "run.bat", make sure it is in the same folder as convert.jar
2. Fill in the information the terminal tells you to, if the export file exists it will be overwritten
3. Press y or n to continue conversion


If you don't move the files within the folder type here are the paths

CSV location: classes.csv

XSLT location: transform.xsl

Export Location: file.xml

*Note: the href of the xsl will be written as-is into the xml file, if you input "E:\Donuts\cheese", the xml file will contian

href="E:\Donuts\cheese"

Try to put the xsl location relative to the xml export location

XML: E:\documents\xml.xml

XSLT: E:\documents\folder\xsl.xsl

Input \folder\xsl.xsl when the terminal asks for the xslt location

