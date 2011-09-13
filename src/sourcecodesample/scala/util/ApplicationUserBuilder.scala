package sourcecodesample.scala.util

import sourcecodesample.scala.model._
import sourcecodesample.scala.resources._

class ApplicationUserBuilder {
  
    private val details = new UserDetails
  
	def createForApp(appName:String,appOwner:String,appOwnerPwd:String):this.type = {
	  details.appName = appName
	  details.appOwnerName = appOwner
	  details.appOwnerPwd = appOwnerPwd
	  this
	}
    
    def withUserName(n:String) = {
      details.userName = n
      this
    }
    
    def withUserPwd(p:String) = {
      details.userPwd = p
      this
    }
    
    def forProvider(p:String) = {
      details.provider = p
      this
    }
    
    def build = details
  
  
    class UserDetails {
        var appName:String= _
        var appOwnerName:String= _
        var appOwnerPwd:String= _
        var userName:String= _
        var userPwd:String= _
        var provider:String= _
        
        
       def registerUser() = {
            import scala.util.parsing.json._
	   		var user:ApplicationUser = ApplicationUser(userName,userPwd).forOwner(ApplicationOwnerInput(appOwnerName,appOwnerPwd)).forAppName(appName).create
	   		var skey = user.smartKey 
	   		skey match {
              case None => println("Oops - something went wrong while creating user")
              case _ => {
               	println(skey getOrElse "Not Found")
               	println("Please visit this link to authorize")
               	println("https://"+appName+"-api.apigee.com/v1/providers/"+provider+"/authorize?smartkey="+skey+"&app_callback=https://apigee.com/oauthSuccess.jsp")
              }
            } 
	  }
    }
   

}