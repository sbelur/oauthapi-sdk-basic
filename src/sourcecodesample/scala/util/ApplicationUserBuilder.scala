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
	   		var res = new ApplicationUser(userName,userPwd).forOwner(ApplicationOwnerInput(appOwnerName,appOwnerPwd)).forAppName(appName).create
	   		import scala.util.parsing.json._
	   		val json:Option[Any] = JSON.parseFull(res)
	   		var map:Map[Any,Any] = null
	   		var skey ="" 
	   		json match {
	   		  case Some(x) => println("some")
	   		  case None => println("xxx")
	   		}
	   		json match {
	   		  case Some(x) => map = x.asInstanceOf[Map[Any,Any]]  
	   		  case None => map = Map()
	   		}
	   		skey = map.get("smartKey").get.toString
	   		  
	   		if(skey == "") println("Oops - something went wrong while creating user")
	   		else {
	   			println(skey)
		   		println("Please visit this link to authorize")
		   		println("https://"+appName+"-api.apigee.com/v1/providers/"+provider+"/authorize?smartkey="+skey+"&app_callback=https://apigee.com/oauthSuccess.jsp")
	   		}
	  }
    }
   

}