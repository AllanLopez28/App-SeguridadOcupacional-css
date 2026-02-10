package com.coderplus.css.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coderplus.css.ui.components.UcaTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

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
                else -> 0
            }
            scrollState.animateScrollToItem(index)
        }
    }

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
            item { ProtocolosScreen() }

            // IMPORTANTE: estas 3 YA NO TIENEN Lazy dentro.
            item { EppScreen() }
            item { RiesgosScreen() }
            item { ReportesScreen() }

            item { RegistroScreen() }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "© 2024 Centro de Servicio Social - Universidad Centroamericana José Simeón Cañas",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
