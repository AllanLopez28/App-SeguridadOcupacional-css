package com.coderplus.css.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.coderplus.css.config.AppConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamenFormScreen(
    onBack: () -> Unit
) {
    val bg = MaterialTheme.colorScheme.background

    Scaffold(
        containerColor = bg,
        topBar = {
            TopAppBar(
                title = { Text("Examen (Microsoft Forms)") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(padding)
        ) {
            FormsWebView(url = AppConfig.EXAM_FORM_URL)
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun FormsWebView(url: String) {
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(true) }
    var currentUrl by remember { mutableStateOf(url) }

    // ✅ Cookies (clave para Microsoft login)
    LaunchedEffect(Unit) {
        CookieManager.getInstance().apply {
            setAcceptCookie(true)
            // 🔥 Importante para login de Microsoft dentro de WebView
            setAcceptThirdPartyCookies(WebView(context), true)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.databaseEnabled = true
                    settings.loadsImagesAutomatically = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                    // ✅ Para flujos de autenticación / popups
                    settings.javaScriptCanOpenWindowsAutomatically = true
                    settings.setSupportMultipleWindows(true)

                    // ✅ User-Agent más “Chrome” para evitar pantallas en blanco raras
                    settings.userAgentString =
                        "Mozilla/5.0 (Linux; Android 13; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"

                    webChromeClient = WebChromeClient()

                    webViewClient = object : WebViewClient() {

                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            isLoading = true
                            if (url != null) currentUrl = url
                            super.onPageStarted(view, url, favicon)
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading = false
                            super.onPageFinished(view, url)
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            // ✅ Dejamos que el WebView maneje las redirecciones de Microsoft
                            return false
                        }
                    }

                    loadUrl(url)
                }
            },
            update = { webView ->
                // Si cambia la URL por alguna razón, se mantiene
                if (currentUrl != webView.url && currentUrl.isNotBlank()) {
                    // no forzamos reload siempre; solo si ves que no carga
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.08f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}