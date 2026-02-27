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
import com.coderplus.css.ui.components.UcaButton
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
                "importancia" -> 2
                "que-es" -> 3
                "protocolos" -> 4
                "epp" -> 5
                "riesgos" -> 6
                "reportes" -> 7
                "registro" -> 8
                "examen" -> 9
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
                // 0
                item { HeroScreen() }

                // 1 ✅ Botones abajo del Hero (ya no van “encima” del carrusel)
                item {
                    HeroActionsSection(
                        onVerProtocolos = { navigateToSection("protocolos") },
                        onHacerExamen = { navigateToSection("examen") }
                    )
                }

                // 2
                item { ImportanciaScreen() }
                // 3
                item { QueEsScreen() }

                // 4
                item {
                    ProtocolosScreen(
                        onProtocolClick = { title ->
                            selectedProtocol = title
                        }
                    )
                }

                // 5
                item { EppScreen() }
                // 6
                item { RiesgosScreen() }
                // 7
                item { ReportesScreen() }
                // 8
                item { RegistroScreen() }
                // 9
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

@Composable
private fun HeroActionsSection(
    onVerProtocolos: () -> Unit,
    onHacerExamen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 12.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        UcaButton(
            text = "Ver protocolos",
            onClick = onVerProtocolos,
            modifier = Modifier.fillMaxWidth(),
            isPrimary = true
        )

        UcaButton(
            text = "Hacer examen",
            onClick = onHacerExamen,
            modifier = Modifier.fillMaxWidth(),
            isPrimary = true
        )
    }
}