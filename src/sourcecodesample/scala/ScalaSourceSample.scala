package sourcecodesample.scala

import sourcecodesample.scala.resources._
import sourcecodesample.scala.http._
import sourcecodesample.scala.model._
import sourcecodesample.scala.resources.ProviderConfiguration.CONSTANTS._
import sourcecodesample.scala.util._

object ScalaSourceSample {

	def main(args:Array[String]){
	   args(0) match {
	     case "createApp" => {
	       val application = Application(args(1)).forOwner(new ApplicationOwnerInput(args(2),args(3)))
	       var resp = application.create()
	       println(resp)
	     }
	     
	     case "configureProvider" => {
	       
	       
	       var params = Map[ConstantsType,String]()
	       params += (CONSUMER_KEY -> args(6))
		   params += (CONSUMER_SECRET -> args(7))
		   params
	       
	       val res = new ProviderConfigBuilder()
	       						 .createForApp(appName=args(1),appOwner=args(2),appOwnerPwd=args(3))
	       						 .forProvider(args(4))
	       						 .withOAuthParams(params)
								 .build
								 .registerProviderConfiguration
		   println(res)						 	
								  
	     }
	   
	     case "createAppUser" => {
	    	val res = new ApplicationUserBuilder()
	    					.createForApp(appName=args(1),appOwner=args(2),appOwnerPwd=args(3))
	    					.forProvider(args(4))
	    					.withUserName(args(5))
	    					.withUserPwd(args(6))
	    					.build
	    					.registerUser
           println(res) 						 
	        	    						
	     }
	     
	     case "makeRequest" => {
	       val res = new ProviderRequestBuilder()
	       						 .forApp(args(1))
	       						 .forProvider(args(2))
	       						 .usingHttpVerb(args(3))
	       						 .usingRequestPath(args(4))
	       						 .usingSmartKey(args(5))	
	       						 .build
	       						 .fireRequest
	       println(res) 						 
	       
	       
	     }
	   
	   }
	   
	   
	   
	}
  
}