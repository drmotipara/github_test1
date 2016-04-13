# github_test1

Test 1:
___________

Right click on project "testProject2" -> Build Path -> Configure Build Path 
Click "Library" tab
click Add Externel JARs...
and save following these selenium jars from selenium-java-2.53.0.zip (you have to unzip first):
     - selenium-java-2.53.jar
     - selenium-java-2.53.0-src.jar
     - lib/<all the jar files>
click ok once you done.

I have used selenium webdriver in java language using Eclipse IDE. 
Run src/testpack1/BuyAndVerifyProduct.java as "jUnit Test".

you will see the firefox browser will comeup and run the program.


Test 2:
__________

Right click on project "testProject2" -> Build Path -> Configure Build Path 
Click "Library" tab
click Add Externel JARs...
get the json-rpc-1.0.jar and click ok.

Run src/testpack2/GetFuelStationData.java file as "Java Application" and you will see the file will create in your C:\ driver
in Dhruti_workspace/testProject2/src/testpack2/StationNameDetail.json which will save as json format.

second Test is failing because the string I was trying to match is not found because in the link database has state "TX" and comparing string has "Texas".
so that test is failing which you will see the error in Eclipse console.




