package sourcecodesample.scala.util
import sourcecodesample.scala.model._
import sourcecodesample.scala.http._
class ProviderRequestBuilder {

    class ProviderRequest {
    	var provider:String = _
	    var path:String = _
	    var verb:String = _
	    var smartkey:String = _
	    var appName:String = _
	   
	    
	  
    	
    	def fireRequest() = {
		     var target = path
		     if(path.contains("?")){
		        target = path + "&smartkey="+smartkey
		     }
		     else{
		       target = path + "?smartkey="+smartkey
		     }
		     target = "https://"+appName+"-api.apigee.com/v1/"+provider+target
		     val verbType =  verb.toLowerCase match {
		         case "get" => HttpMethod.GET
		         case "post" => HttpMethod.POST
		         case "delete" => HttpMethod.DELETE
		         case "put" => HttpMethod.PUT
		     }
		     val input = ProviderRequestInput(target,verbType)
		     val httpClient = new HttpClient {
		    	 type InputType = ProviderRequestInput
		     }
		     val res = httpClient.invoke(input)
		     println(res)
		     res
	     }
	    
    }
    
    private val  details = new ProviderRequest
    
    
    def forProvider(p:String):this.type = {
      details.provider= p
      this
    }
    
    def usingHttpVerb(v:String):this.type = {
      details.verb = v
      this
    }
    
    def usingRequestPath(p:String):this.type = {
      details.path = p
      this
    }
    
    def usingSmartKey(key:String):this.type = {
      details.smartkey = key
      this
    }
    
    def forApp(name:String):this.type={
      details.appName = name
      this
    }
    
    def build = details
  
}