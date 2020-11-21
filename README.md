# items-on-sale

items-on-sale is being fetch recommended items from on the items category ratings
Below are the technologies I have embedded in API.<br/>
1) Microservices architecture <br/>
2) Application is built upon springboot thus using its multiple dependencies like security, jpa, validation, devtools (all the     dependencies are described in build.gradle file)<br/>
3) Rest API<br/>
4) Spring security for user authentication and JWT authorization<br/>
5) postgress DB for database<br/>
6) gradle for building project<br/>
7) Java 8<br/>
8) git<br/>
9) Junit4 for junit testcase<br/>

**Prerequisites**: <br/>
**1) PostgressSQL db:** Please install postgresssql13 or download postgress docker image before starting application, once postgress is up and running go to pgadmin4 app and use default DB **postgres** . After that, please create table by createTable.sql (present in resource folder)<br/>
 **Please Note:** : Either use postgress password as "password" or update **spring.datasource.password** property value in application.properties for your password.<br/>
**2) postman/soapui to test rest apis:** Please install any of these two tool and import project itemOnSale.postman_collection.json in your testing tool(project present in resource folder), you should be able to see get and add request inside your postman project. <br/>

**Steps to build and deploy application:** Import application in IDE as existing gradle project, let gradle fetch all the dependencies from artifactory. Once gradle build is successful, right click on Project > Properties > Java Build Path > Add Lib > Add Junit4 lib and apply changes, now run application as java application and select type as itemOnSaleApplication. Application should be up and running and log should be rolling in console.

**Steps to test application:** currently application is supporting below two operations.

1) **Authentication:** By this functionality authorization and authentication will happen. go to postman/soapUi and trigger request for authenticate, you will get JWT tocken in the response(same JWT tocken can be used to trigger next request) For authentication user/password is getting used as userName/password in the body<br/>


 2) **getRecommendedItems:** By this functionality, we can fetch multiple items by thier categories ratings. To test this functionality, go to postman/soapUi and trigger request for getRecommendedItems with the JWT tocken returned in first request.<br/>

