package com.coderplus.css.ui.screens

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coderplus.css.config.AppConfig
import com.coderplus.css.data.ExamStatusStore
import com.coderplus.css.ui.components.UcaCard
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@Composable
fun ExamenScreen() {
    var showForm by remember { mutableStateOf(false) }

    if (showForm) {
        ExamenFormScreen(onBack = { showForm = false })
        return
    }

    // --- tu ExamenScreen normal ---
    val context = LocalContext.current
    val store = remember { ExamStatusStore(context) }
    val scope = rememberCoroutineScope()

    val sent by store.examSent.collectAsState(initial = false)
    val sentAt by store.sentAt.collectAsState(initial = 0L)

    val dateText = remember(sentAt) {
        if (sentAt == 0L) "—"
        else SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date(sentAt))
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Examen obligatorio",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        UcaCard(
            title = "Estado",
            description = if (sent) "ENVIADO ✅" else "PENDIENTE ⏳",
            iconUrl = null
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enviado: $dateText",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "1) Lee los documentos de seguridad.\n2) Realiza el examen en Microsoft Forms.\n3) Regresa y marca: \"Ya lo envié\".",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { showForm = true }, // ✅ ahora abre dentro de la app
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hacer examen (Microsoft Forms)")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { scope.launch { store.markSent() } },
            enabled = !sent,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ya lo envié")
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { scope.launch { store.reset() } },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar estado (solo pruebas)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Resultados (más adelante): se enviarán a ${AppConfig.RESULTS_EMAIL} en Excel automáticamente.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
