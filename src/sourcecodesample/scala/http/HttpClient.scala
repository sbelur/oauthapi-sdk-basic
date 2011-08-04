package sourcecodesample.scala.http

import org.apache.http._
import org.apache.http.util.EntityUtils
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CookieStore
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods._
import org.apache.http.client.params.HttpClientParams
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.conn.ClientConnectionManager
import org.apache.http.conn.params.ConnManagerParams
import org.apache.http.conn.scheme.PlainSocketFactory
import org.apache.http.conn.scheme.Scheme
import org.apache.http.conn.scheme.SchemeRegistry
import org.apache.http.conn.ssl.SSLSocketFactory
import org.apache.http.conn.ssl.X509HostnameVerifier
import org.apache.http.cookie.Cookie
import org.apache.http.entity.StringEntity
import org.apache.http.entity.mime.HttpMultipartMode
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.entity.mime.content.StringBody
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.HttpResponse
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager
import org.apache.http.message.BasicHeader
import org.apache.http.message.BasicNameValuePair
import org.apache.http.message.BufferedHeader
import org.apache.http.params.BasicHttpParams
import org.apache.http.params.HttpConnectionParams
import org.apache.http.params.HttpParams
import org.apache.http.protocol.HTTP
import org.apache.http.protocol.HttpContext
import org.apache.http.util.CharArrayBuffer
import java.security.cert.X509Certificate
import javax.net.ssl._
import java.util.Properties
import java.io._
import sourcecodesample.scala.model._
abstract class HttpClient {
	
	type InputType <: Input
  
	def invoke(input:InputType):String = {
	    val httpClient = initialize()
		makeRequest(httpClient,input)
	}
  
	
	def makeRequest(httpClient:HttpClient#TestHttpClient,input:InputType) = {
	    var  httpMethod:HttpRequestBase = null
	    input.httpVerb match {
	      case HttpMethod.POST => {
	        httpMethod = new HttpPost(input.target)
	        println(input.getBody)
	        val se = new StringEntity(input.getBody)
	        httpMethod.asInstanceOf[HttpEntityEnclosingRequestBase].setEntity(se)
	      }
	      case HttpMethod.GET => {
	         httpMethod = new HttpGet(input.target)
	      }
	      
	    }
	    println("sending request to target "+httpMethod.getURI)
	    val httpCtx:HttpContext  = httpClient.createHttpContext()
	    if(!input.isProviderRequest){
		    val authheader = new BasicHeader("Authorization", input.auth.asBasicAuthHeader.toString)
		    httpMethod.addHeader(authheader)
		    val contentType = new BasicHeader("Content-Type","application/json")
		    httpMethod.addHeader(contentType)
	    }
	    val httpResp:HttpResponse  = httpClient.execute(httpMethod,httpCtx)
	    onResponseComplete(httpResp,httpClient)
	}
	
	private def onResponseComplete(httpResp:HttpResponse,httpClient:DefaultHttpClient):String = {
	  val resEntity:HttpEntity = httpResp.getEntity()
      val resp = EntityUtils.toString(resEntity)
      if (resEntity != null) {
          resEntity.consumeContent()
      }
      httpClient.getConnectionManager().shutdown()
      resp
	}
	
  
	def initialize() = {
	  var _httpClient = null
	  val params:HttpParams = new BasicHttpParams()
        ConnManagerParams.setMaxTotalConnections(params, 100)
        // Increase default max connection per route to 20
        System.setProperty("http.conn-manager.max-per-route", String.valueOf(20))

        // Create and initialize scheme registry
        val schemeRegistry = new SchemeRegistry()
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80))

        val easyTrustManager:TrustManager = new X509TrustManager() {
            
            
            override  def checkClientTrusted(chain:Array[X509Certificate],
                                            authType:String)   {
                // I accept everything
            }

            
            override  def checkServerTrusted(chain:Array[X509Certificate],
                                            authType:String) {
                // I accept everything
            }

            
            override def getAcceptedIssuers():Array[X509Certificate] = {
                null
            }
        }

        val hostnameVerifier = new X509HostnameVerifier() {

            
            override def verify(s:String, sslSession:SSLSession) = {
                true
            }

            
            override def verify(s:String, sslSocket:SSLSocket)  {

            }

            
            override def verify(s:String, x509Certificate:X509Certificate) {

            }

            
            override def verify(s:String, strings:Array[String], strings1:Array[String])  {

            }
        }
        val sslcontext:SSLContext = SSLContext.getInstance("TLS")
        val ar = Array[TrustManager](easyTrustManager)
        sslcontext.init(null, ar, null)
        val socketFactory = new SSLSocketFactory(sslcontext)
        socketFactory
                .setHostnameVerifier(hostnameVerifier)
        schemeRegistry.register(new Scheme("https", socketFactory, 443))

        // Create an HttpClient with the ThreadSafeClientConnManager.
        // This connection manager must be used if more than one thread will
        // be using the HttpClient.
        //HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_0)
        HttpClientParams.setRedirecting(params, false)
        
        HttpConnectionParams.setSoTimeout(params, 60000)
        HttpConnectionParams.setConnectionTimeout(params, 60000)
        val cm:ClientConnectionManager = new ThreadSafeClientConnManager(params,
                schemeRegistry)
        
        var ref:HttpClient#TestHttpClient = new TestHttpClient(cm, params)
        ref
	}
	
	
	
	
     class TestHttpClient(val cm:ClientConnectionManager, val params:HttpParams ) extends DefaultHttpClient(cm,params) {

                
        override def createHttpContext() =  {
            super.createHttpContext()
        }

    }
  
  
}