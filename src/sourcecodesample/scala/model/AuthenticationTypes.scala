package sourcecodesample.scala.model


case object AuthenticationTypes  extends Enumeration {
	   type AuthType = Value
	   val OAuth1,OAuth2 = Value
}