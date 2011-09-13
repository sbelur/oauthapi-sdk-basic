package sourcecodesample.scala.model

import sourcecodesample.scala.http.HttpMethod._

case class ApplicationInput(targeturl:String,verb:HttpMethodType) extends Input(targeturl,verb) {

   private var appName:String = _
   private var displayName:String = _
   private var version:String = _
   private var callbackURLWhitelist:String = _
   
   
   def withAppName(name:String) = {appName = name;this}
   def withDisplayName(name:String) = {displayName = name;this}
   def withVersion(v:String) = {version=v;this}
   def withCallbackURLWhitelist(url:String) = {callbackURLWhitelist=url;this}
   
   override def getBody() = {
		"{\"appName\":\""+appName+"\",\"displayName\":\""+displayName+"\",\"version\":\""+version+"\",\"Authorization\":\""+auth.asBasicAuthHeader+"\"}"
   }
  
}