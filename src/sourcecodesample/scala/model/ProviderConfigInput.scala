package sourcecodesample.scala.model

import sourcecodesample.scala.http.HttpMethod._
import sourcecodesample.scala.model.AuthenticationTypes._

case class ProviderConfigInput(targeturl:String,verb:HttpMethodType) extends Input(targeturl,verb)  {

	private var authType:AuthType = _
	
	private var consumerKey:String = _
	private var consumerSecret:String = _
  
	override def getBody():String = {
	  	  getOAuthBody
    }
	
	private def getOAuthBody():String = { 
		"{\"consumerKey\":\""+consumerKey+"\",\"consumerSecret\":\""+consumerSecret+"\",\"Authorization\":\""+auth.asBasicAuthHeader+"\"}"
	}
	
	
	def forAuthType(tp:AuthType) = {
	  authType = tp
	  this
	}
	
	def withConsumerKey(key:String)= {
	   consumerKey = key
	   this
	}
	def withConsumerSecret(secret:String)= {
	   consumerSecret = secret
	   this
	}
	
	
}