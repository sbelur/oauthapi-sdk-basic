package sourcecodesample.scala.model

import sourcecodesample.scala.http.HttpMethod._

case class ProviderRequestInput (targeturl:String,verb:HttpMethodType) extends Input(targeturl,verb) {

   override def isProviderRequest = true
  
  
}