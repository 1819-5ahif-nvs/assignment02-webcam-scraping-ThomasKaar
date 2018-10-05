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
                Jsoup.connect(path)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .get().run {

                            // val html = this.outerHtml()
                            val element = this.select("#fer_video").select("source").attr("src")

                            runOnUiThread {
                                videoView2.setVideoURI(Uri.parse(element))
                                videoView2.start()
                            }
                        }

                Thread.sleep(60000)
            }
        }
    }
}
