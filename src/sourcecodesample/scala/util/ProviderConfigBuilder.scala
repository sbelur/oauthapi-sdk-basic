package sourcecodesample.scala.util
import sourcecodesample.scala.resources.ProviderConfiguration.CONSTANTS._
import sourcecodesample.scala.model._
import sourcecodesample.scala.resources._
import sourcecodesample.scala.model.AuthenticationTypes._
class ProviderConfigBuilder {

  private val details = new UserDetails

  class UserDetails {
        var appName:String= _
        var appOwnerName:String= _
        var appOwnerPwd:String= _
        var authType:AuthType = _
        var params	 = Map[ConstantsType,String]()
        var provider:String =_
        
        
       def registerProviderConfiguration() = {
		    val config:ProviderConfiguration = authType match {
		      case OAuth1 => ProviderConfiguration.newOAuth1Config(params.get(CONSUMER_KEY).get,params.get(CONSUMER_SECRET).get)
		      case OAuth2 => println("Implement for oauth2"); throw new RuntimeException("Implement for oauth2")  
		    }
		    config
		    .forOwner(new ApplicationOwnerInput(appOwnerName,appOwnerPwd))
		    .forAppName(appName)
		    .forProviderName(provider)
		    .create
	   }
  }
  
  def createForApp(appName:String,appOwner:String,appOwnerPwd:String):this.type = {
	  details.appName = appName
	  details.appOwnerName = appOwner
	  details.appOwnerPwd = appOwnerPwd
	  this
  }
  
  def withOAuth1Params(p:Map[ConstantsType,String]):this.type = {
      details.authType = AuthenticationTypes.OAuth1
      details.params = p
      this
  }
  
  def forProvider(p:String):this.type = {
     details.provider = p
     this
  }
  
  def build = details
  
  
}