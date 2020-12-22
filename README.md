# The Oppenheimer Project Test Automation

Test Automation Framework to test and verify the correctness of the application



## Getting Started:


###Installation

##### 1. Install JDK(https://www.oracle.com/java/technologies/javase-downloads.html)
##### 2. Download Eclipse from https://www.eclipse.org/downloads
##### 3. Launch Eclipse by running "eclipse.exe" from the Eclipse installed directory.
##### 4. Choose a directory for the workspace


###Setting up the framework in the local

##### 1. Download and clone this repository to the desktop.
##### 2. Open eclipse->Go To ->File->Import
##### 3. Select the Maven->Existing Maven projects
##### 4. Click Next button in the import window
##### 5. Give the source code path under the root directory
##### 6. Click Finish button
##### 7. Right Click on the project folder ->select maven->update project. Check the checkbox 'Force update of Snapshot/Releases'
##### 8. Click OK button
##### 9. Click OK button

###Folder paths to be changed

##### 1. Please the path of config file in the below files to point to respective local config file path as per the workspace
##### 2. Files to be updated:Tax_Dispense_Page.java, UploadCSV_API.java, UploadCSV_UI.java, Upload_CSV_Steps_API.java, Upload_CSV_Steps_UI.java, CSVRowValues.java,DataComparison.java
##### 3. Please open the config.properties file and provide the path for each url/folder. Currently they are pointing to my local machine.

###How to run test cases through eclipse

##### 1.Go to project folder and look for the file testng.xml (scroll down to the bottom)
##### 2.Open the file, right click->run as->TestNG Suite


###How to view the results
##### 1.Go to project folder and look for the 'target\cucumber-report-hmlt' folder. It has the latest execution summary report

