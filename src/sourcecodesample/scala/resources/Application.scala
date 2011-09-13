package sourcecodesample.scala.resources


import sourcecodesample.scala.resources.ProviderConfiguration.CONSTANTS._
import sourcecodesample.scala.http._
import sourcecodesample.scala.model._
import sourcecodesample.scala.model.AuthenticationTypes._
import sourcecodesample.scala.http.HttpMethod._
import scala.util.parsing.json.JSON._

trait Application extends Resource {
  private[this] var version:String="1"
  private var name:String=""
  private[this] var appKey:Option[Any] = None
  private[this] var appId:Option[Any]= None
  private[this] var callbackURLWhitelist:String = ""
  
  def withVersion(v:String):this.type = { version = v;this}
  def withName(v:String):this.type = { name = v;this}
  def withCallbackURL(v:String):this.type = { callbackURLWhitelist = v;this}
  
  def getAppKey = appKey
  def getAppId = appId
  
  override def toString = "Application instance with Key %s and Id %s".format(appKey getOrElse "Not Found" ,appId getOrElse "Not Found")
  
  override def create():this.type = {
	   val input = ApplicationInput("https://api.apigee.com/v1/apps.json",HttpMethod.POST)
	   				.withAppName(name)
	   				.withDisplayName(name)
	   				.withVersion(version)
	   				.withCallbackURLWhitelist(callbackURLWhitelist)
	   				.withAuthentication(AuthenticationInput(owner.name,owner.pwd))
	   val httpClient = new HttpClient {
	     type InputType = ApplicationInput
	   }
	   try{
		   val res = httpClient.invoke(input.asInstanceOf[ApplicationInput])
		   println(res)
		   val jsonified:Option[Any] = parseFull(res)
		   jsonified match {
		      case Some(x) => {
		    	  appKey = x.asInstanceOf[Map[String,Any]] get("appKey")
		    	  appId = x.asInstanceOf[Map[String,Any]] get("appId")
		      }
		      case _ => appKey = None
		   }
	   }
	   catch {
	      case ex:Throwable => println("Error while processing user creation");ex.printStackTrace; 
	   }
	   this
	 }
}


object Application{
	
	
	  
	def apply(nm:String) = {
	  new Application {
	     name = nm
	  }
	}
	 
}