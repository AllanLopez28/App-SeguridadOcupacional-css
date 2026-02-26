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

    // 🔥 Estado para controlar si estamos viendo detalle
    var selectedProtocol by remember { mutableStateOf<String?>(null) }

    val navigateToSection: (String) -> Unit = { section ->
        coroutineScope.launch {
            val index = when (section) {
                "importancia" -> 1
                "que-es" -> 2
                "protocolos" -> 3
                "epp" -> 4
                "riesgos" -> 5
                "reportes" -> 6
                "registro" -> 7
                "examen" -> 8
                else -> 0
            }
            scrollState.animateScrollToItem(index)
        }
    }

    // 🔥 Si hay protocolo seleccionado mostramos pantalla detalle
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

    } else {

        // 🔥 Pantalla principal normal con scroll
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
                item { HeroScreen(onNavigate = navigateToSection) }
                item { ImportanciaScreen() }
                item { QueEsScreen() }

                item {
                    ProtocolosScreen(
                        onProtocolClick = { title ->
                            selectedProtocol = title
                        }
                    )
                }

                item { EppScreen() }
                item { RiesgosScreen() }
                item { ReportesScreen() }
                item { RegistroScreen() }
                item { ExamenScreen() }

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
}