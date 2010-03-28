Instructions to execute the program

1. Please include the log4j-1.2.15.jar (in WSDLTracker/lib) to the class-path.
2. The WSDL files to be processed are to be placed in the inputs (WSDLTracker/inputs) folder.
3. Execute the main class (WSDLTracker/src/com.wsdltracker.main.WSDLTestMain)

Features
--------
Dec 20, 2009
Added the feature to process WSDL files in batch mode. Add all the WSDL files to be processed into 
the WSDLTracker/inputs directory. All the files inside this directory are picked for processing.

Added the feature to generate the Term Matrix. This matrix holds the unique terms and the count of 
the number of times they appear in the WSDL file. This also contains features like STOPWORD removal and
a trivial (for now) STEMMING algorithm.

Action Items
1. Create a mechanism to store the term matrices and calculate the tf and idf values.
2. Test the entire system with more WSDL files as input (doing dev testing currently)