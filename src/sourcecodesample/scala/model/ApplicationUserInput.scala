package sourcecodesample.scala.model

import sourcecodesample.scala.http.HttpMethod._

case class ApplicationUserInput(targeturl:String,verb:HttpMethodType) extends Input(targeturl,verb) {

    private var name:String = _
    private var password:String = _
 
    def withName(name:String):this.type = {this.name = name;this}
    def withPwd(pwd:String):this.type = {this.password = pwd;this}
    
    
    override def getBody():String = {
       "{\"userName\":\""+name+"\",\"fullName\":\""+name+"\",\"password\":\""+password+"\",\"Authorization\":\""+auth.asBasicAuthHeader+"\"}"
    }
  
}