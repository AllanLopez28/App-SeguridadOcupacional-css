package com.coderplus.css.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coderplus.css.ui.components.UcaTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    var selectedProtocol by remember { mutableStateOf<String?>(null) }
    var showRegistroForm by remember { mutableStateOf(false) }
    var showExamenForm by remember { mutableStateOf(false) }

    val navigateToSection: (String) -> Unit = { section ->
        coroutineScope.launch {
            // ⚠️ Los índices bajaron 1 porque eliminamos el item HeroActionsSection
            val index = when (section) {
                "importancia" -> 1
                "que-es"      -> 2
                "protocolos"  -> 3
                "epp"         -> 4
                "riesgos"     -> 5
                "reportes"    -> 6
                "registro"    -> 7
                "examen"      -> 8
                else          -> 0
            }
            scrollState.animateScrollToItem(index)
        }
    }

    // ── Pantalla completa del formulario de registro ──────────────────────────
    if (showRegistroForm) {
        RegistroFormScreen(onBack = { showRegistroForm = false })
        return
    }

    // ── Pantalla completa del examen ──────────────────────────────────────────
    if (showExamenForm) {
        ExamenFormScreen(onBack = { showExamenForm = false })
        return
    }

    // ── Pantalla de detalle de protocolo ──────────────────────────────────────
    if (selectedProtocol != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(selectedProtocol ?: "Protocolo") },
                    navigationIcon = {
                        IconButton(onClick = { selectedProtocol = null }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Atrás"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ProtocolDetailScreen(
                    protocolTitle = selectedProtocol!!,
                    onBack = { selectedProtocol = null }
                )
            }
        }
        return
    }

    // ── Pantalla principal ────────────────────────────────────────────────────
    Scaffold(
        topBar = { UcaTopBar(onNavigate = navigateToSection) }
    ) { paddingValues ->

        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // 0 — Hero con botones integrados
            item {
                HeroScreen(
                    onVerProtocolos = { navigateToSection("protocolos") },
                    onRealizarExamen = { navigateToSection("examen") }
                )
            }

            // 1
            item { ImportanciaScreen() }
            // 2
            item { QueEsScreen() }
            // 3
            item {
                ProtocolosScreen(
                    onProtocolClick = { title -> selectedProtocol = title }
                )
            }
            // 4
            item { EppScreen() }
            // 5
            item { RiesgosScreen() }
            // 6
            item { ReportesScreen() }
            // 7
            item { RegistroScreen(onOpenForm = { showRegistroForm = true }) }
            // 8
            item { ExamenScreen(onOpenExamen = { showExamenForm = true }) }

            // Footer
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "© 2024 Centro de Servicio Social - Universidad Centroamericana José Simeón Cañas",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}