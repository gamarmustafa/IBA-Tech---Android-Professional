package com.example.myapplication

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MainActivity : AppCompatActivity() {
    private var downloadManager: DownloadManager? = null
    private var downloadId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadButton = findViewById<Button>(R.id.download_button)
        downloadButton.setOnClickListener {
            startDownload()
        }

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            cancelDownload()
        }

        val retryButton = findViewById<Button>(R.id.retry_button)
        retryButton.setOnClickListener {
            retryDownload()
        }
    }

    private fun startDownload() {
        val request =
            DownloadManager.Request(Uri.parse("https://www.opportunitiesforyouth.org/wp-content/uploads/2021/04/Atomic_Habits_by_James_Clear-1.pdf"))
        downloadId = downloadManager?.enqueue(request)!!
    }

    private fun cancelDownload() {
        downloadManager?.remove(downloadId)
    }

    private fun retryDownload() {
        cancelDownload()
        startDownload()
    }

}



