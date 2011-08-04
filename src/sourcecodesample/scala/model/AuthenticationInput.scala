package sourcecodesample.scala.model

import org.apache.commons.codec.binary.Base64
import org.apache.http.protocol.HTTP
import org.apache.http.util.CharArrayBuffer
import org.apache.http.util.EncodingUtils

case class AuthenticationInput(name:String,password:String) {

  def asBasicAuthHeader() = {
	  	val tmp = new StringBuilder();
		tmp.append(name);
		tmp.append(":");
		tmp.append(password);
		val base64password = Base64.encodeBase64(EncodingUtils.getBytes(tmp.toString(), HTTP.DEFAULT_PROTOCOL_CHARSET));

		val buffer = new CharArrayBuffer(32);
		buffer.append("Basic ");
		buffer.append(base64password, 0, base64password.length);
		buffer;
  }
  
}