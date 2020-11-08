package com.arya.covidapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.arya.covidapp.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        initView()
    }

    private fun initView() {
        val title = intent.getStringExtra("title")

        swipe.setOnRefreshListener {
            when (title) {
                "Mengenal" -> {
                    setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mengenal-virus-corona.html")
                }
                "Mencegah" -> {
                    setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mencegah-terinfeksi-corona.html")
                }
                "Mengobati" -> {
                    setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mengobati-corona.html")
                }
                "Mengantisipasi" -> {
                    setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mengantisipasi-corona-di-luar-negeri.html")
                }
                "Konsultasi Dokter" -> {
                    setLinkWeb("https://www.halodoc.com/tanya-jawab-seputar-virus-corona/?utm_source=HD_homepage&utm_medium=TILES&utm_campaign=halodoc_web")
                }
                "Rumah Sakit Rujukan" -> {
                    this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    setLinkWeb("https://covid19.go.id/daftar-rumah-sakit-rujukan")
                }
                "Berita Penting" -> {
                    setLinkWeb("https://www.covid19.go.id/info-penting/")
                }
                "Gejala" -> {
                    setLinkWeb("https://www.covid19.go.id/faqs/apa-saja-tanda-atau-gejala-infeksi-virus-corona/")
                }
                "API" -> {
                    setLinkWeb("https://kawalcorona.com/api/")
                }
            }
        }

        when (title) {
            "Mengenal" -> {
                setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mengenal-virus-corona.html")
            }
            "Mencegah" -> {
                setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mencegah-terinfeksi-corona.html")
            }
            "Mengobati" -> {
                setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mengobati-corona.html")
            }
            "Mengantisipasi" -> {
                setLinkWeb("https://www.cnnindonesia.com/longform/gaya-hidup/20200313/laporan-mendalam-rumus-melawan-virus/mengantisipasi-corona-di-luar-negeri.html")
            }
            "Hotline" -> {
                val phoneNumber = "119"
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                Toast.makeText(this@WebViewActivity, "Hotline Covid - 19", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
            "Konsultasi Dokter" -> {
                setLinkWeb("https://www.halodoc.com/tanya-jawab-seputar-virus-corona/?utm_source=HD_homepage&utm_medium=TILES&utm_campaign=halodoc_web")
            }
            "Rumah Sakit Rujukan" -> {
                this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                setLinkWeb("https://covid19.go.id/daftar-rumah-sakit-rujukan")
            }
            "Berita Penting" -> {
                setLinkWeb("https://www.covid19.go.id/info-penting/")
            }
            "Gejala" -> {
                setLinkWeb("https://www.covid19.go.id/faqs/apa-saja-tanda-atau-gejala-infeksi-virus-corona/")
            }
            "API" -> {
                setLinkWeb("https://kawalcorona.com/api/")
            }
        }

        if (title == "Mengenal"
            || title == "Mencegah"
            || title == "Mengobati"
            || title == "Mengantisipasi") {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar1)
        } else if (title == "Rumah Sakit Rujukan"
            || title == "Berita Penting"
            || title == "Gejala") {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar2)
        } else if (title == "Konsultasi Dokter"){
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar3)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setLinkWeb(linkWeb: String) {
        webview.settings.javaScriptEnabled = true
        webview.settings.setAppCacheEnabled(true)
        webview.settings.builtInZoomControls = true
        webview.settings.displayZoomControls = false
        webview.settings.setSupportZoom(true)
        webview.loadUrl(linkWeb)
        swipe.isRefreshing = true
        webview.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                webview.loadUrl("file:///android_asset/error.html")
            }

            override fun onPageFinished(view: WebView, url: String) {
                //Hide the SwipeReefreshLayout
                swipe.isRefreshing = false
            }
        }
    }

    override fun onClick(v: View?) {
        onBackPressed()
    }
}