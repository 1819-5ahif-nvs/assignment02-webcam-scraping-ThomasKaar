package at.htl.androidapp

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import java.io.File
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val path = "https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        thread {
            while (true) {
                Jsoup.connect(path).header("Host", "brickseek.com")
                        .header("Connection", "keep-alive")
                        .header("Cache-Control", "max-age=0")
                        .header("Origin", "https://brickseek.com/")
                        .header("Upgrade-Insecure-Requests", "1")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        .referrer("http://brickseek.com/walmart-inventory-checker/")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Accept-Language", "en-US,en;q=0.8").get().run {

                            // val html = this.outerHtml()
                            val element = this.select("#fer_video").select("source").attr("src")

                            runOnUiThread {
                                videoView2.setVideoURI(Uri.parse(element))
                            }
                        }

                Thread.sleep(60000)
            }
        }
    }
}
