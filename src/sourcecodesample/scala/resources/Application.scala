package sourcecodesample.scala.resources


import sourcecodesample.scala.resources.ProviderConfiguration.CONSTANTS._
import sourcecodesample.scala.http._
import sourcecodesample.scala.model._
import sourcecodesample.scala.model.AuthenticationTypes._
import sourcecodesample.scala.http.HttpMethod._



class Application(name:String) extends Resource{
	
	private var version:String = "1"
	  
	
  
	def withVersion(v:String) = { version = v;this}
	
	
  
	override def create() = {
	   val input:Input = ApplicationInput("https://api.apigee.com/v1/apps.json",HttpMethod.POST)
	   				.withAppName(name)
	   				.withDisplayName(name)
	   				.withVersion(version)
	   				.withAuthentication(AuthenticationInput(owner.name,owner.pwd))
	   val httpClient = new HttpClient {
	     type InputType = ApplicationInput
	   }
	   val res = httpClient.invoke(input.asInstanceOf[ApplicationInput])
	   println(res)
	   res
	 }
 
	
  
	 
	 
	 
	 
	 
	
	
	 
	 
	 
}