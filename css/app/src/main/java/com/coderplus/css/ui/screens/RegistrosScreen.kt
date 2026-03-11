package com.coderplus.css.ui.screens

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

private val formUrl =
    "https://forms.cloud.microsoft/pages/responsepage.aspx?id=hrQc_xLZ40mUNBIunEWJXhq6ZLvlw-JMlT4aQ_OVPWtUQUxJVjEzSzUyOTFYWjBNSzExTjNEM1lGMi4u&route=shorturl"

// ─── Pantalla de entrada (en la LazyColumn) ───────────────────────────────────
@Composable
fun RegistroScreen(
    onOpenForm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF4F6FB))
            .padding(vertical = 60.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Registro / Formulario",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF0F2D40),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Completa el formulario de registro para el programa Campus Seguro. El formulario se abrirá en pantalla completa para que puedas navegar cómodamente.",
            fontSize = 14.sp,
            color = Color(0xFF6B7B93),
            textAlign = TextAlign.Center,
            lineHeight = 21.sp
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onOpenForm,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0F6AB4)
            )
        ) {
            Text(
                text = "Abrir formulario de registro",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

// ─── Pantalla completa con el WebView ─────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroFormScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Formulario de Registro") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            RegistroWebView()
        }
    }
}

// ─── WebView interno ──────────────────────────────────────────────────────────
@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun RegistroWebView() {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                    val cookieManager = CookieManager.getInstance()
                    cookieManager.setAcceptCookie(true)
                    CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)

                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.loadsImagesAutomatically = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.javaScriptCanOpenWindowsAutomatically = true
                    settings.setSupportMultipleWindows(true)
                    settings.cacheMode = WebSettings.LOAD_DEFAULT
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    settings.userAgentString = settings.userAgentString + " CSSUCAWebView"

                    webChromeClient = WebChromeClient()

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            isLoading = true
                            hasError = false
                            errorMsg = ""
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            isLoading = false
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            val target = request?.url?.toString() ?: return false
                            val host = request.url?.host ?: ""
                            val allowed = listOf(
                                "forms.cloud.microsoft",
                                "login.microsoftonline.com",
                                "login.live.com",
                                "microsoft.com",
                                "office.com"
                            )
                            val isAllowed = allowed.any { host.contains(it, ignoreCase = true) }

                            if (!isAllowed || target.startsWith("mailto:") || target.startsWith("tel:")) {
                                runCatching {
                                    val intent = android.content.Intent(
                                        android.content.Intent.ACTION_VIEW,
                                        Uri.parse(target)
                                    )
                                    context.startActivity(intent)
                                }
                                return true
                            }
                            return false
                        }

                        @Deprecated("Deprecated in Java")
                        override fun onReceivedError(
                            view: WebView?,
                            errorCode: Int,
                            description: String?,
                            failingUrl: String?
                        ) {
                            hasError = true
                            isLoading = false
                            errorMsg = description ?: "Error al cargar el formulario."
                        }
                    }

                    loadUrl(formUrl)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Loading
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Cargando formulario...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Error
        if (hasError) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.padding(24.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "No se pudo cargar",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = errorMsg,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = {
                                runCatching {
                                    val intent = android.content.Intent(
                                        android.content.Intent.ACTION_VIEW,
                                        Uri.parse(formUrl)
                                    )
                                    context.startActivity(intent)
                                }
                            }
                        ) {
                            Text("Abrir en navegador")
                        }
                    }
                }
            }
        }
    }
}