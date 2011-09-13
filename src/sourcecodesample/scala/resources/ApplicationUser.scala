package sourcecodesample.scala.resources

import sourcecodesample.scala.model._
import sourcecodesample.scala.http._
import scala.util.parsing.json.JSON._

trait ApplicationUser extends Resource{
   def  userName:String
   def  userPwd:String
   
   var smartKey:Option[String] = None
   
   override def toString = "ApplicationUser instance with SmartKey %s and User Name %s".format(smartKey getOrElse "Not Found",userName)
   
   override def create():this.type = {
	  
	    val userInput = ApplicationUserInput("https://api.apigee.com/v1/apps/"+appName+"/users.json",HttpMethod.POST)
	    				.withAuthentication(AuthenticationInput(owner.name,owner.pwd))
	    				.withName(userName)
	    				.withPwd(userPwd)
	    				.asInstanceOf[ApplicationUserInput]				
	  
		val httpClient = new HttpClient {
	     type InputType = ApplicationUserInput
	    }
	    try{
	        val res = httpClient.invoke(userInput)
		    val jsonified:Option[Any] = parseFull(res)
		    jsonified match {
		      case Some(x) => smartKey = x.asInstanceOf[Map[String,String]] get("smartKey")
		      case _ => smartKey = None
		    }
	    }
	    catch {
	      case ex:Throwable => println("Error while processing user creation");ex.printStackTrace; 
	    }
	    this
	}
}


object ApplicationUser {
  
	def apply(nm:String,passwd:String) = {
	   new ApplicationUser {
		   def userName = nm
		   def userPwd = passwd
	   }
	}

}