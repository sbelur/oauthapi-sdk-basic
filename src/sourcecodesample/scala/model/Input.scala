package sourcecodesample.scala.model

import sourcecodesample.scala.http.HttpMethod._

abstract case class Input(target:String,httpVerb:HttpMethodType) {
  
   private[sourcecodesample] var auth: AuthenticationInput = _
   private var body: String = _
   
   
   
   def withAuthentication(auth:AuthenticationInput):this.type = {
      this.auth = auth
      this
   }
   
   def getBody() = ""
   
   
   def isProviderRequest = false
    
   
  
}