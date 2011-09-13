package sourcecodesample.scala.resources

import sourcecodesample.scala.model.AuthenticationTypes._
import sourcecodesample.scala.model._
import sourcecodesample.scala.http._


trait ProviderConfiguration extends Resource{
    protected[this] var providerName:String = _
    val consumerKey:String =""
    val consumerSecret:String = ""
  
	override def create() = {
	  
	    val cfgInput = ProviderConfigInput("https://api.apigee.com/v1/apps/"+appName+"/providers/"+providerName+"/credentials.json",HttpMethod.POST)
	    				.withAuthentication(AuthenticationInput(owner.name,owner.pwd))
	    				.asInstanceOf[ProviderConfigInput]				
	    cfgInput.withConsumerKey(consumerKey).withConsumerSecret(consumerSecret)
		val httpClient = new HttpClient {
	     type InputType = ProviderConfigInput
	    }
	    val res = httpClient.invoke(cfgInput)
	    println(res)
	    this
	}
  
  	def forProviderName(name:String):this.type = {
	  providerName = name
	  this
	}
}


object ProviderConfiguration {

	def apply(ckey:String,csecret:String) = {
	  new ProviderConfiguration {
	     override val consumerKey = ckey
	     override val consumerSecret = csecret
	  }
	}
  
	case object CONSTANTS extends Enumeration {
  	  type ConstantsType = Value 
  	  val CONSUMER_KEY,CONSUMER_SECRET=Value
  	}
  	
}




