package sourcecodesample.scala.resources

import sourcecodesample.scala.model._

trait Resource {
	
	var appName:String = _
	
	var owner:ApplicationOwnerInput = _
  
    def create():String = {"Base classes need to override this"}
 
	def forOwner(owner:ApplicationOwnerInput):this.type = {
	   this.owner = owner
	   this	
	}
	
	def forAppName(name:String):this.type = {
	  appName = name
	  this
	}
}