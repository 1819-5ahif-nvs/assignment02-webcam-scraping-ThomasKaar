package at.htl.camscraper

import org.jsoup.Jsoup
import java.io.File

val path = "https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0"

fun main(args: Array<String>) {
    while (true) {
        Jsoup.connect(path).header("Host", "brickseek.com")
                .header("Connection", "keep-alive")
//              .header("Content-Length", ""+c.request().requestBody().length())
                .header("Cache-Control", "max-age=0")
                .header("Origin", "https://brickseek.com/")
                .header("Upgrade-Insecure-Requests", "1")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.48 Safari/537.36")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .referrer("http://brickseek.com/walmart-inventory-checker/")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "en-US,en;q=0.8").get().run {

                    // val html = this.outerHtml()
                    val element = this.select("#fer_video").select("source").attr("src")
                    File("../link.txt").writeText(element)
                }

        Thread.sleep(60000)
    }
}