package argo

import java.io.{FileInputStream, InputStream}
import javax.json.{Json, JsonObject}



trait HasJsonObject {
  def json : JsonObject
}

object ArgoJsonUtil {

  def jsonFromFile( fn : String) = jsonFromStream(new FileInputStream(fn))

  def jsonFromStream( is : InputStream) = {
    val p = Json.createReader(is)
    p.readObject()
  }
}
