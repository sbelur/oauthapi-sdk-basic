package sourcecodesample.scala.resources

import sourcecodesample.scala.model.AuthenticationTypes._
import sourcecodesample.scala.model._
import sourcecodesample.scala.http._

sealed abstract class ProviderConfiguration extends Resource{
    
	
	
	private var providerName:String = _
  
	override def create() = {
	  
	    val cfgInput = ProviderConfigInput("https://api.apigee.com/v1/apps/"+appName+"/providers/"+providerName+"/credentials.json",HttpMethod.POST)
	    				.withAuthentication(AuthenticationInput(owner.name,owner.pwd))
	    				.asInstanceOf[ProviderConfigInput]				
	    this match {
	      case o1: OAuth1Configuration => cfgInput.withConsumerKey(o1.ckey).withConsumerSecret(o1.csecret).forAuthType(AuthenticationTypes.OAuth1)
	      case o2: OAuth2Configuration => println("need to handle oauth2")
	    }
		val httpClient = new HttpClient {
	     type InputType = ProviderConfigInput
	    }
	    val res = httpClient.invoke(cfgInput)
	    println(res)
	    res
	}
	
	
	  
	
	
	def forProviderName(name:String):this.type = {
	  providerName = name
	  this
	}
	  
}


case class OAuth1Configuration(ckey:String,csecret:String) extends ProviderConfiguration {
		
}
	
case class OAuth2Configuration extends ProviderConfiguration {
	    
}


object ProviderConfiguration {
  
  	case object CONSTANTS extends Enumeration {
  	  type ConstantsType = Value 
  	  val CONSUMER_KEY,CONSUMER_SECRET=Value
  	}
  	
  	def newOAuth1Config(ckey:String,csecret:String) = new OAuth1Configuration(ckey,csecret)
}