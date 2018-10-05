package at.htl

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.io.File


fun main(args: Array<String>) {
    val http = HttpServer.create(InetSocketAddress(8080), 0)

    http.createContext("/", MyHandler())
    http.start()
}

class MyHandler : HttpHandler {
    override fun handle(t: HttpExchange?) {
        val url = File("../link.txt").readText()
        val response = " <iframe width=\"420\" height=\"315\"\n" +
                "src=\"${url}\">\n" +
                "</iframe> "
        t?.sendResponseHeaders(200, response.length.toLong())
        val os = t!!.getResponseBody()
        os.write(response.toByteArray())
        os.close()
    }

}