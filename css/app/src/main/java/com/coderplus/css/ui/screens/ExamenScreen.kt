package com.coderplus.css.ui.screens

import android.annotation.SuppressLint
import android.content.Context
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
import com.coderplus.css.config.AppConfig
import com.coderplus.css.data.ExamStatusStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

// ─── URLs que Microsoft Forms carga al enviar correctamente ───────────────────
private val FORMS_SUBMITTED_PATTERNS = listOf(
    "thankyou",
    "thank-you",
    "thankYou",
    "submitted=1",
    "confirmation"
)

// ─── Bridge JS → Kotlin ───────────────────────────────────────────────────────
// FIX: El bridge ahora recibe Context y usa su propio CoroutineScope con
// Dispatchers.Main, evitando que el scope del composable (que puede cancelarse)
// impida persistir el dato correctamente.
private class FormSubmitBridge(
    private val context: Context,
    private val onSubmitted: () -> Unit
) {
    @android.webkit.JavascriptInterface
    fun notifySubmitted() {
        // FIX: Usamos Main para que la actualización de estado ocurra en el
        // hilo principal y el composable recomponga correctamente.
        CoroutineScope(Dispatchers.Main).launch {
            // 1. Persistimos primero en DataStore (fuente de verdad)
            ExamStatusStore(context).markSent()
            // 2. Luego notificamos al composable
            onSubmitted()
        }
    }
}

// ─── Pantalla de entrada ──────────────────────────────────────────────────────
@Composable
fun ExamenScreen(onOpenExamen: () -> Unit) {
    val context = LocalContext.current
    val store = remember { ExamStatusStore(context) }

    val sent by store.examSent.collectAsState(initial = false)
    val sentAt by store.sentAt.collectAsState(initial = 0L)

    val dateText = remember(sentAt) {
        if (sentAt == 0L) "—"
        else SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(sentAt))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF4F6FB))
            .padding(vertical = 60.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Examen obligatorio",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF0F2D40)
        )

        Surface(
            shape = RoundedCornerShape(14.dp),
            color = Color.White,
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Estado del examen",
                        fontSize = 13.sp,
                        color = Color(0xFF6B7B93)
                    )
                    Text(
                        text = if (sent) "ENVIADO ✅" else "PENDIENTE ⏳",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (sent) Color(0xFF1E7A43) else Color(0xFFB07A2A)
                    )
                    if (sent) {
                        Text(
                            text = "Completado el $dateText",
                            fontSize = 12.sp,
                            color = Color(0xFF6B7B93)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = if (sent) Color(0xFFDFF3E6) else Color(0xFFFFF3CD),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = if (sent) "✅" else "📋", fontSize = 22.sp)
                }
            }
        }

        if (!sent) {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFFEEF2F7),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Pasos a seguir",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E3A5F)
                    )
                    listOf(
                        "Lee los protocolos de seguridad del CSS.",
                        "Presiona el botón para abrir el examen.",
                        "Completa y envía el formulario.",
                        "Al enviarlo, tu estado se actualizará automáticamente."
                    ).forEachIndexed { i, paso ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${i + 1}.",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F6AB4)
                            )
                            Text(
                                text = paso,
                                fontSize = 13.sp,
                                color = Color(0xFF6B7B93),
                                lineHeight = 19.sp
                            )
                        }
                    }
                }
            }

            Button(
                onClick = onOpenExamen,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0F6AB4))
            ) {
                Text(text = "Realizar examen", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
        } else {
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFFDFF3E6),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "¡Examen completado!",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E5C3A)
                    )
                    Text(
                        text = "Tu participación ha sido registrada en el programa Campus Seguro. Los resultados serán enviados a ${AppConfig.RESULTS_EMAIL}.",
                        fontSize = 13.sp,
                        color = Color(0xFF2C7A50),
                        lineHeight = 19.sp
                    )
                }
            }
        }
    }
}

// ─── Pantalla completa del examen con WebView ─────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ExamenFormScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val store = remember { ExamStatusStore(context) }
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(true) }

    // FIX: Eliminamos el `submitted` local que causaba la race condition.
    // Ahora observamos directamente el StateFlow del store como fuente de verdad.
    val submitted by store.examSent.collectAsState(initial = false)

    // FIX: Cuando submitted se vuelve true (persisted en DataStore), volvemos.
    // Antes, submitted podía ponerse true sin haberse guardado aún en DataStore.
    LaunchedEffect(submitted) {
        if (submitted) {
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Examen — Campus Seguro") },
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
                .padding(padding)
        ) {
            AndroidView(
                factory = { ctx ->
                    WebView(ctx).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                        val webView = this  // guardamos la referencia al WebView
                        CookieManager.getInstance().apply {
                            setAcceptCookie(true)
                            setAcceptThirdPartyCookies(webView, true)
                        }

                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            databaseEnabled = true
                            loadsImagesAutomatically = true
                            useWideViewPort = true
                            loadWithOverviewMode = true
                            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                            javaScriptCanOpenWindowsAutomatically = true
                            setSupportMultipleWindows(true)
                            userAgentString =
                                "Mozilla/5.0 (Linux; Android 13; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
                        }

                        // FIX: El bridge persiste en DataStore antes de notificar
                        addJavascriptInterface(
                            FormSubmitBridge(ctx) {
                                // onSubmitted: no hace falta hacer nada extra,
                                // el LaunchedEffect(submitted) reacciona al StateFlow
                            },
                            "AndroidBridge"
                        )

                        webChromeClient = WebChromeClient()

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(
                                view: WebView?, url: String?, favicon: Bitmap?
                            ) {
                                isLoading = true
                                super.onPageStarted(view, url, favicon)
                                if (url != null) {
                                    val urlLower = url.lowercase()
                                    if (FORMS_SUBMITTED_PATTERNS.any { urlLower.contains(it) }) {
                                        // FIX: Guardamos directamente en store
                                        scope.launch { store.markSent() }
                                    }
                                }
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                isLoading = false
                                super.onPageFinished(view, url)

                                // FIX: Polling ampliado con más frases de confirmación
                                // de Microsoft Forms (incluye variantes en español/inglés
                                // y detección por selector de clase CSS como fallback)
                                view?.evaluateJavascript(
                                    """
                                    (function startPolling() {
                                        var attempts = 0;
                                        var maxAttempts = 30;
                                        function check() {
                                            attempts++;
                                            var text = document.documentElement.innerText || '';
                                            
                                            // Frases de confirmación de Microsoft Forms
                                            var found =
                                                text.includes('Guardar mi respuesta') ||
                                                text.includes('Enviar otra respuesta') ||
                                                text.includes('Ver resultados') ||
                                                text.includes('Save my response') ||
                                                text.includes('Submit another response') ||
                                                text.includes('View results') ||
                                                text.includes('puntuaci\u00f3n') ||
                                                text.includes('Your response was saved') ||
                                                text.includes('Tu respuesta fue guardada') ||
                                                text.includes('response has been recorded') ||
                                                (text.includes('punto') && !!text.match(/\d+\/\d+/));
                                            
                                            // FIX: También buscamos por selector CSS como fallback
                                            // (Microsoft Forms usa estas clases en la pantalla de éxito)
                                            if (!found) {
                                                var thankYouEl = 
                                                    document.querySelector('[data-automation-id="thankYouPage"]') ||
                                                    document.querySelector('.thank-you-page') ||
                                                    document.querySelector('[class*="thankYou"]') ||
                                                    document.querySelector('[class*="submitSuccess"]');
                                                if (thankYouEl) found = true;
                                            }
                                            
                                            if (found) {
                                                try { window.AndroidBridge.notifySubmitted(); } catch(e) {}
                                            } else if (attempts < maxAttempts) {
                                                setTimeout(check, 1500);
                                            }
                                        }
                                        setTimeout(check, 1000);
                                    })();
                                    """.trimIndent(),
                                    null
                                )
                            }

                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                request: WebResourceRequest?
                            ): Boolean = false
                        }

                        loadUrl(AppConfig.EXAM_FORM_URL)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.06f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}