<h2>PURPOSE:</h2>
To provide a scala client API for Api provided by Apigee with Source feature.
This project provides the APIs to invoke the resources:
<ul>
<li>
Application
</li>
<li>
Application User
</li>
<li>
Application Provider Configuration
</li>
</ul>
<h3>NOTE:</h3>
<ul>
<li>
To make the actual provider request, user is advised to refer to my other project
:- wadl2code which provides a specific interfaces for each provider. The Invocation
in this project is a generic one.
</li>
<li>
The classes in util folder provide an illustration of how the resources can be invoked.
This is just for example purpose and user can look at the types in resources folder also.
</li>
</ul>
<h2>How to run: </h2>
<ul>
<li>
setting CLASSPATH: Run it with the classpath of scala + thirdpartylibs
</li>
<li>sourcecodesample.scala.ScalaSourceSample is the Example launcher which contains some commands to execute the below apis.
<br/><b>The main class is just for illustration purpose, user can directly work on the resources types </b>
</li>
</ul>
<br/>
<h2>REST APIs </h2>
<b>Apigee Provides the following REST APIs to help build an application:</b>

<b>1. Create Application</b> - This is the end point to create a developer application resource.
This would assign a name to the application and associate it to the owner,who should be an
Apigee User.

Example: scala -cp <classpath> sourcecodesample.scala.ScalaSourceSample createApp <app_name> <app_owner> <app_owner_pwd>
where <app_name> is the name of the application you would like to assign
      <app_owner> is the application owner name ,who also should be an Apigee user before trying this.
	  <app_owner_pwd> is the owner's apigee password.
	  
<b>2.Configure Provider </b> -  Configure the Application with the target provider. This assigns the application 
crendetials for this provider - like consumer key,secret which would be generated by the target provider when the owner 
would have registered his application with target provider in the first place.

Example: scala -cp <classpath>  sourcecodesample.scala.ScalaSourceSample configureProvider <app_name> <app_owner> <app_owner_pwd> <provider> <auth_type> <consumer_key> <consumer_secret>
where <app_name> is the name of the application 
      <app_owner> is the application owner name ,who also should be an Apigee user before trying this.
	  <app_owner_pwd> is the owner's apigee password.
	  <provider> is the provider name like twitter - as of now,this provider should already be one of the provider exposed by Apigee Console.
	  <auth_type> is the authentication protocol to use - like oauth1,oauth2.
	  <consumer_key> is the consumer key obtained when the application would have been registered. 
	  <consumer_secret> is the consumer secret obtained when the application would have been registered.
	  
<b>3. Create Application (end) user </b> - this creates a user for this application. 	  
Example: scala -cp <classpath> sourcecodesample.scala.ScalaSourceSample createAppUser <app_name> <app_owner> <app_owner_pwd>  <provider>  <app_user> <app_user_pwd>
where <app_name> is the name of the application 
      <app_owner> is the application owner name ,who also should be an Apigee user before trying this.
	  <app_owner_pwd> is the owner's apigee password.
	  <provider> is the provider name like twitter - as of now,this provider should already be one of the provider exposed by Apigee Console.
	  <app_user> is the name of the user 
	  <app_user_pwd> is the user's password

<b>4. Finally, An api to make provider request.</b>
Example: scala -cp <classpath> sourcecodesample.scala.ScalaSourceSample makeRequest <app_name>  <provider> <http_verb> <path> <user_smart_key>
where <app_name> is the name of the application 
	   <provider> is the target provider -  as of now,this provider should already be one of the provider exposed by Apigee Console.
	   <http_verb> like get,post etc
	   <path> like /1/statuses/home_timeline.xml
	   <user_smart_key> - the identifer of the end user obtained during add user.



