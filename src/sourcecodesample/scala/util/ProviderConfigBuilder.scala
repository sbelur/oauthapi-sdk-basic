package sourcecodesample.scala.util
import sourcecodesample.scala.resources.ProviderConfiguration.CONSTANTS._
import sourcecodesample.scala.model._
import sourcecodesample.scala.resources._
import sourcecodesample.scala.model.AuthenticationTypes._
class ProviderConfigBuilder {

  private val details = new ProviderConfigDetails

  class ProviderConfigDetails {
        var appName:String= _
        var appOwnerName:String= _
        var appOwnerPwd:String= _
        var authType:AuthType = _
        var params	 = Map[ConstantsType,String]()
        var provider:String =_
        
        
       def registerProviderConfiguration() = {
		    ProviderConfiguration(params.get(CONSUMER_KEY).get,params.get(CONSUMER_SECRET).get)
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
  
  def withOAuthParams(p:Map[ConstantsType,String]):this.type = {
      details.params = p
      this
  }
  
  def forProvider(p:String):this.type = {
     details.provider = p
     this
  }
  
  def build = details
  
  
}