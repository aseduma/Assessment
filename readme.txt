CIB DIGITAL TECH – QA AUTOMATION ASSESSMENT com.assessment-0.0.1-SNAPSHOT.jar
===========================================
	A maven based java 8 project, This a data driven test software and uses Junit, Selenium, Rest Template libraries. 
	It generates the xml file a report which can be easily be converted into html, excel and any other form of report.

	Requirements
	-----------
	OS			:	Windows, Linux Or Unix
	Memory		:	512MB
	Software	: 	Full Java JDK 8 or JRE 8(Runtime)
	
		 
	Execution
	----------


	 1. Open cmd and type=> java -jar "com.assessment-0.0.1-SNAPSHOT.jar"
	 2. Double click on run.bat
	 
	 --------------------------------------------------------------------
	 --------------------------------------------------------------------
	 Folders
	 ----------
	 DATA
	 ----
	 configuration.xml => configer browser
	 users.xml => test data for user
	 
	 Test Results
	 ------------
	 Reports/web/screenshots => screenshots
	 Reports/Report_<timestamp>.xml => Xml report
	 
	 Test Methods
	 -----------
		Class					Methods				Actions						Link
		--------				--------			--------					-----
		UserTest				users				Add User
													Assert User
													Edit User
													Remove User
		
		DogTest					listAllBreeds									https://dog.ceo/api/breeds/list/all
								verifyBreed										https://dog.ceo/api/breeds/list/all
								listSubBreeds									https://dog.ceo/api/breed/retriever/list
								produceRandomImageOrLinkForSubBreed				https://dog.ceo/api/breed/retriever-golden/images/random
