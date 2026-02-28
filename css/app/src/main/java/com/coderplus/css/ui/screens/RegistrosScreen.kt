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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.coderplus.css.config.AppConfig

/**
 * Muestra Microsoft Forms dentro de la app con WebView.
 * Requiere habilitar cookies + third-party cookies para que el login no quede en blanco.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun RegistroScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // ✅ Usa tu link completo (shorturl)
    val formUrl = remember {
        // Podés dejar AppConfig.EXAM_FORM_URL si ya es el correcto,
        // pero aquí te recomiendo usar el link completo que te funcionó.
        "https://forms.cloud.microsoft/pages/responsepage.aspx?id=hrQc_xLZ40mUNBIunEWJXhq6ZLvlw-JMlT4aQ_OVPWtUQUxJVjEzSzUyOTFYWjBNSzExTjNEM1lGMi4u&route=shorturl"
    }

    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 60.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro / Formulario",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Completa el formulario dentro de la app. Si tu sesión expira, vuelve a cargar la página.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(650.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                        // ✅ Cookies (CLAVE para Microsoft Login)
                        val cookieManager = CookieManager.getInstance()
                        cookieManager.setAcceptCookie(true)
                        // Third-party cookies son necesarias para algunos flujos de Microsoft/AAD
                        CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)

                        // ✅ Settings WebView
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.loadsImagesAutomatically = true
                        settings.mediaPlaybackRequiresUserGesture = false

                        settings.useWideViewPort = true
                        settings.loadWithOverviewMode = true

                        // Popups/ventanas (login a veces lo usa)
                        settings.javaScriptCanOpenWindowsAutomatically = true
                        settings.setSupportMultipleWindows(true)

                        // Mejor compatibilidad
                        settings.cacheMode = WebSettings.LOAD_DEFAULT
                        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                        // User-Agent: a veces ayuda a evitar páginas “lite” raras
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

                                // ✅ Permitir dentro del WebView dominios de Microsoft Forms/Login
                                val allowed = listOf(
                                    "forms.cloud.microsoft",
                                    "login.microsoftonline.com",
                                    "login.live.com",
                                    "microsoft.com",
                                    "office.com"
                                )

                                val host = request?.url?.host ?: ""
                                val isAllowed = allowed.any { host.contains(it, ignoreCase = true) }

                                // Si es algo fuera (por ejemplo WhatsApp/mailto), abrir externo
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

                                return false // que cargue normal
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
                                errorMsg = description ?: "Error cargando el formulario."
                            }
                        }

                        loadUrl(formUrl)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // Loader
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "Cargando formulario...",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Error overlay
            if (hasError) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "No se pudo cargar",
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = errorMsg,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(12.dp))
                            Button(onClick = {
                                // Recargar abriendo externo como fallback rápido
                                val intent = android.content.Intent(
                                    android.content.Intent.ACTION_VIEW,
                                    Uri.parse(formUrl)
                                )
                                context.startActivity(intent)
                            }) {
                                Text("Abrir en navegador")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        TextButton(
            onClick = {
                val intent = android.content.Intent(
                    android.content.Intent.ACTION_VIEW,
                    Uri.parse(formUrl)
                )
                context.startActivity(intent)
            }
        ) {
            Text("Abrir formulario en navegador (alternativa)")
        }
    }
}