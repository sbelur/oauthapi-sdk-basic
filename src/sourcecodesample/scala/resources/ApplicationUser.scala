package sourcecodesample.scala.resources

import sourcecodesample.scala.model._
import sourcecodesample.scala.http._

class ApplicationUser(nm:String,passwd:String) extends Resource{
  
	
  
	override def create() = {
	  
	    val userInput = ApplicationUserInput("https://api.apigee.com/v1/apps/"+appName+"/users.json",HttpMethod.POST)
	    				.withAuthentication(AuthenticationInput(owner.name,owner.pwd))
	    				.withName(nm)
	    				.withPwd(passwd)
	    				.asInstanceOf[ApplicationUserInput]				
	  
		val httpClient = new HttpClient {
	     type InputType = ApplicationUserInput
	    }
	    val res = httpClient.invoke(userInput)
	    println(res)
	    res
	}
  

}