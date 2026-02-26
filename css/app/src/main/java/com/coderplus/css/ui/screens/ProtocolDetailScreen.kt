package com.coderplus.css.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coderplus.css.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProtocolDetailScreen(
    protocolTitle: String,
    onBack: () -> Unit
) {
    when (protocolTitle) {
        "Laboratorios y bioseguridad" -> {
            LabBioseguridadScreen(onBack = onBack)
            return
        }

        "Sismo en campus o sedes" -> {
            SismoScreen(onBack = onBack)
            return
        }

        "Incendio en actividades" -> {
            IncendioScreen(onBack = onBack)
            return
        }

        "Trabajo de campo y visitas" -> {
            CampoYVisitasScreen(onBack = onBack)
            return
        }
    }

    // Fallback
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(protocolTitle) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Diseño detallado pendiente para: $protocolTitle")
        }
    }
}

/* =====================================================================================
   ✅ T R A B A J O   D E   C A M P O   Y   V I S I T A S  (estilo referencia)
   Iconos requeridos en drawable:
   - ic_field_map
   - ic_clipboard_white
   - ic_info_black
   - ic_photo
   - ic_checklist
   - ic_walk
   - ic_first_aid
   - ic_check_green
   - ic_close_red
   - ic_phone_black
   ===================================================================================== */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CampoYVisitasScreen(onBack: () -> Unit) {
    val bg = Color(0xFFEFF4F8)
    val card = Color.White
    val green = Color(0xFF1E7A43)

    Scaffold(
        containerColor = bg,
        topBar = {
            TopAppBar(
                title = { Text("Protocolos de Seguridad") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = card)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header
            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "TRABAJO DE CAMPO\nY VISITAS",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF1E5C3A)
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Prevención en exteriores",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF2C5A6B)
                        )
                    }

                    PngOrFallbackIcon(
                        resId = R.drawable.ic_field_map,
                        fallback = Icons.Filled.Report,
                        size = 72.dp
                    )
                }
            }

            // CLAVE: PLANIFICACIÓN (verde)
            Card(
                colors = CardDefaults.cardColors(containerColor = green),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PngOrFallbackIcon(
                            resId = R.drawable.ic_clipboard_white,
                            fallback = Icons.Filled.Report,
                            size = 22.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "CLAVE: PLANIFICACIÓN",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "La seguridad en campo empieza antes de salir: briefing, roles, ruta y puntos de encuentro, " +
                                "check-in/check-out, EPP, botiquín y comunicación constante con coordinación.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }

            // ¿Cuándo aplica?
            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PngOrFallbackIcon(
                            resId = R.drawable.ic_info_black,
                            fallback = Icons.Filled.Report,
                            size = 22.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "¿Cuándo aplica?",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF163A4A)
                        )
                    }

                    FieldBulletRow(
                        iconRes = R.drawable.ic_photo,
                        text = "En visitas comunitarias, levantamientos, encuestas, actividades en obra o trabajo fuera del campus."
                    )
                    FieldBulletRow(
                        iconRes = R.drawable.ic_photo,
                        text = "Cuando el equipo se traslada y hay riesgos por clima, terreno, tráfico, zonas desconocidas o aglomeraciones."
                    )
                }
            }

            Text(
                text = "FLUJO DE LA ACTIVIDAD",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E5C3A),
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
            )

            // 3 Cards del flujo (responsive: si no cabe, bajan)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FlowStepCard(
                    number = "1.",
                    title = "PREPARACIÓN\n(Antes)",
                    iconRes = R.drawable.ic_checklist,
                    borderColor = Color(0xFF1E7A43),
                    items = listOf("Briefing y roles", "Ruta y punto de encuentro", "Check-in con coordinación"),
                    modifier = Modifier.weight(1f)
                )

                FlowStepCard(
                    number = "2.",
                    title = "EN CAMPO\n(Durante)",
                    iconRes = R.drawable.ic_walk,
                    borderColor = Color(0xFF1E7A43),
                    items = listOf("Buddy system", "Hidratación y botiquín", "Monitoreo del entorno"),
                    modifier = Modifier.weight(1f)
                )

                FlowStepCard(
                    number = "3.",
                    title = "RESPUESTA A\nINCIDENTE",
                    iconRes = R.drawable.ic_first_aid,
                    borderColor = Color(0xFFB07A2A),
                    items = listOf("Asegura la zona", "Primeros auxilios", "Reporta y documenta"),
                    modifier = Modifier.weight(1f)
                )
            }

            // Lo que sí / evitar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DoDontCard(
                    title = "LO QUE SÍ HACER",
                    iconRes = R.drawable.ic_check_green,
                    fallback = Icons.Filled.Shield,
                    bgColor = Color(0xFFDFF3E6),
                    items = listOf(
                        "Llevar teléfono con batería y contactos de emergencia.",
                        "Usar EPP según terreno (calzado, chaleco, gorra).",
                        "Pausar la actividad si cambia el riesgo (clima/zona)."
                    ),
                    modifier = Modifier.weight(1f)
                )

                DoDontCard(
                    title = "LO QUE EVITAR",
                    iconRes = R.drawable.ic_close_red,
                    fallback = Icons.Filled.Report,
                    bgColor = Color(0xFFF7DADA),
                    items = listOf(
                        "Separarte del grupo o perder contacto.",
                        "Tomar atajos por zonas inseguras o desconocidas.",
                        "Ignorar señales del entorno o indicaciones del líder."
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            // Footer: Reporte y comunicación
            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PngOrFallbackIcon(
                        resId = R.drawable.ic_phone_white,
                        fallback = Icons.Filled.Report,
                        size = 24.dp
                    )
                    Column {
                        Text(
                            text = "Reporte y Comunicación",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF163A4A)
                        )
                        Text(
                            text = "Contacta a coordinación CSS ante cambios de ruta, incidentes o emergencias.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF2C5A6B)
                        )
                    }
                }
            }

            Spacer(Modifier.height(6.dp))
        }
    }
}

@Composable
private fun FieldBulletRow(iconRes: Int, text: String) {
    Row(verticalAlignment = Alignment.Top) {
        PngOrFallbackIcon(
            resId = iconRes,
            fallback = Icons.Filled.Report,
            size = 20.dp
        )
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF2C5A6B))
    }
}

@Composable
private fun FlowStepCard(
    number: String,
    title: String,
    iconRes: Int,
    borderColor: Color,
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .heightIn(min = 170.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            // “borde” estilo referencia
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color.Transparent)
                    .padding(0.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                PngOrFallbackIcon(
                    resId = iconRes,
                    fallback = Icons.Filled.Report,
                    size = 26.dp
                )
                Text(
                    text = "$number $title",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF163A4A),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(8.dp))

            items.forEachIndexed { idx, it ->
                Text(
                    text = "${idx + 1}. $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF2C5A6B)
                )
            }

            // Línea inferior “decorativa” como si fuera el conector
            Spacer(Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(borderColor.copy(alpha = 0.55f))
            )
        }
    }
}

/* =====================================================================================
   ✅ I N C E N D I O  (tu pantalla actual - se mantiene)
   ===================================================================================== */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun IncendioScreen(onBack: () -> Unit) {
    val bg = Color(0xFFEFF4F8)
    val card = Color.White

    Scaffold(
        containerColor = bg,
        topBar = {
            TopAppBar(
                title = { Text("Protocolos de Seguridad") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = card)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "INCENDIO EN\nACTIVIDADES",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF8B1A1A)
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Guía de respuesta rápida",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF2C5A6B)
                        )
                    }

                    PngOrFallbackIcon(
                        resId = R.drawable.ic_fire_flame,
                        fallback = Icons.Filled.Report,
                        size = 64.dp
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF39C32)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PngOrFallbackIcon(
                            resId = R.drawable.ic_megaphone_orange,
                            fallback = Icons.Filled.Report,
                            size = 22.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "PRIORIDAD MÁXIMA",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "Prioridad: alertar, evacuar y mantener una vía de escape.\n" +
                                "Solo usa extintor si el fuego es pequeño y es seguro.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PngOrFallbackIcon(
                            resId = R.drawable.ic_info_black,
                            fallback = Icons.Filled.Report,
                            size = 22.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "¿Cuándo aplica?",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF163A4A)
                        )
                    }

                    FireBulletRow("Alerta a los demás y reporta a coordinación.")
                    FireBulletRow("Solo usa extintor si el fuego es pequeño y tienes salida segura.")
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PngOrFallbackIcon(
                        resId = R.drawable.ic_bell,
                        fallback = Icons.Filled.Report,
                        size = 26.dp
                    )
                    Column {
                        Text(
                            text = "ACCIÓN INMEDIATA",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF163A4A)
                        )
                        Text(
                            text = "Alerta a los demás y notifica a coordinación.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF2C5A6B)
                        )
                    }
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PngOrFallbackIcon(
                            resId = R.drawable.ic_extinguisher_red,
                            fallback = Icons.Filled.Report,
                            size = 22.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "PROTOCOLO: USO DEL EXTINTOR",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF8B1A1A)
                            )
                            Text(
                                text = "(Solo si el fuego es pequeño y seguro)",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF2C5A6B)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StepCard(
                            title = "1. POSICIÓNATE",
                            iconRes = R.drawable.ic_step_position,
                            fallback = Icons.Filled.Report,
                            modifier = Modifier.weight(1f)
                        )
                        StepCard(
                            title = "2. QUITA SEGURO",
                            iconRes = R.drawable.ic_step_pin,
                            fallback = Icons.Filled.Report,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StepCard(
                            title = "3. APUNTA BASE",
                            iconRes = R.drawable.ic_step_aim,
                            fallback = Icons.Filled.Report,
                            modifier = Modifier.weight(1f)
                        )
                        StepCard(
                            title = "4. DESCARGA",
                            iconRes = R.drawable.ic_step_squeeze,
                            fallback = Icons.Filled.Report,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF6D9D9)),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PngOrFallbackIcon(
                        resId = R.drawable.ic_alert,
                        fallback = Icons.Filled.Report,
                        size = 22.dp
                    )
                    Text(
                        text = "Si tu ruta de evacuación se ve amenazada o no es segura: evacúa inmediatamente y reporta.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF5B1F1F)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DoDontCard(
                    title = "LO QUE SÍ HACER",
                    iconRes = R.drawable.ic_check_green,
                    fallback = Icons.Filled.Shield,
                    bgColor = Color(0xFFDFF3E6),
                    items = listOf(
                        "Alertar y evacuar con orden.",
                        "Mantener una vía de escape.",
                        "Notificar a coordinación."
                    ),
                    modifier = Modifier.weight(1f)
                )

                DoDontCard(
                    title = "LO QUE EVITAR",
                    iconRes = R.drawable.ic_close_red,
                    fallback = Icons.Filled.Report,
                    bgColor = Color(0xFFF7DADA),
                    items = listOf(
                        "Arriesgarte si el fuego crece.",
                        "Bloquear salidas o rutas.",
                        "Volver por objetos personales."
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PngOrFallbackIcon(
                        resId = R.drawable.ic_pin_black,
                        fallback = Icons.Filled.Report,
                        size = 20.dp
                    )
                    Text(
                        text = "Notas Importantes UCA",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF163A4A)
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE25555)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PngOrFallbackIcon(
                        resId = R.drawable.ic_phone_white,
                        fallback = Icons.Filled.Report,
                        size = 28.dp
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "CONTACTO DE EMERGENCIA",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "UCA: 413 857 2772",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(Modifier.height(6.dp))
        }
    }
}

@Composable
private fun FireBulletRow(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        PngOrFallbackIcon(
            resId = R.drawable.ic_fire_bullet,
            fallback = Icons.Filled.Report,
            size = 18.dp
        )
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF2C5A6B))
    }
}

@Composable
private fun StepCard(
    title: String,
    iconRes: Int,
    fallback: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.heightIn(min = 72.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PngOrFallbackIcon(iconRes, fallback, size = 22.dp)
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF163A4A)
            )
        }
    }
}

/* =====================================================================================
   ✅ S I S M O  (tu pantalla actual - se mantiene)
   ===================================================================================== */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SismoScreen(onBack: () -> Unit) {
    val bg = Color(0xFFEFF4F8)
    val card = Color.White

    Scaffold(
        containerColor = bg,
        topBar = {
            TopAppBar(
                title = { Text("Protocolos de Seguridad") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = card)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(18.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Sismo en campus\no sedes",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF163A4A)
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Guía de actuación inmediata",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF2C5A6B)
                        )
                    }

                    PngOrFallbackIcon(
                        resId = R.drawable.ic_quake_building,
                        fallback = Icons.Filled.Report,
                        size = 64.dp
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2B6EA6)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PngOrFallbackIcon(
                            resId = R.drawable.ic_rule_shield,
                            fallback = Icons.Filled.Shield,
                            size = 22.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "REGLA DE ORO",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "Actúa rápido para reducir lesiones: agáchate, cúbrete y agárrate. " +
                                "Cuando termine el movimiento, evacúa con orden y llega al punto de reunión seguro.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PngOrFallbackIcon(
                            resId = R.drawable.ic_info_orange,
                            fallback = Icons.Filled.Report,
                            size = 22.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "¿Cuándo aplicar este protocolo?",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF163A4A)
                        )
                    }

                    BulletRow(
                        iconRes = R.drawable.ic_info_orange,
                        fallback = Icons.Filled.Report,
                        text = "Durante un sismo en campus o en sedes externas."
                    )
                    BulletRow(
                        iconRes = R.drawable.ic_info_orange,
                        fallback = Icons.Filled.Report,
                        text = "Cuando se active la evacuación hacia punto de reunión seguro."
                    )
                }
            }

            Text(
                text = "DURANTE EL MOVIMIENTO",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFB56A1E),
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ActionMiniCard(
                    title = "AGÁCHATE",
                    desc = "colócate en manos y rodillas para evitar caídas.",
                    iconRes = R.drawable.ic_duck,
                    fallback = Icons.Filled.Report,
                    modifier = Modifier.weight(1f)
                )
                ActionMiniCard(
                    title = "CÚBRETE",
                    desc = "protege cabeza y cuello bajo una mesa resistente.",
                    iconRes = R.drawable.ic_cover,
                    fallback = Icons.Filled.Report,
                    modifier = Modifier.weight(1f)
                )
                ActionMiniCard(
                    title = "AGÁRRATE",
                    desc = "sostén el refugio hasta que pare el temblor.",
                    iconRes = R.drawable.ic_hold,
                    fallback = Icons.Filled.Report,
                    modifier = Modifier.weight(1f)
                )
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "DESPUÉS DEL MOVIMIENTO (Evacuación)",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF163A4A)
                    )

                    NumberedStep(1, "Verifica si estás bien y ayuda a quien lo necesite sin exponerte.")
                    NumberedStep(2, "Evacúa con calma siguiendo rutas establecidas (sin correr).")
                    NumberedStep(3, "Llega al punto de reunión y pasa lista con tu equipo/coordinación.")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DoDontCard(
                    title = "LO QUE SÍ HACER",
                    iconRes = R.drawable.ic_check,
                    fallback = Icons.Filled.Shield,
                    bgColor = Color(0xFFDFF3E6),
                    items = listOf(
                        "Mantén la calma y sigue instrucciones.",
                        "Aléjate de vidrios/estanterías.",
                        "Ayuda a personas con movilidad reducida."
                    ),
                    modifier = Modifier.weight(1f)
                )

                DoDontCard(
                    title = "LO QUE EVITAR",
                    iconRes = R.drawable.ic_close,
                    fallback = Icons.Filled.Report,
                    bgColor = Color(0xFFF7DADA),
                    items = listOf(
                        "Correr o empujar durante la evacuación.",
                        "Usar ascensores.",
                        "Regresar sin autorización."
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = card),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Información del Campus UCA",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF163A4A)
                    )

                    CampusNote("Identifica el punto de reunión más cercano antes de iniciar actividades.")
                    CampusNote("Sigue indicaciones de coordinación CSS y brigadas internas.")
                    CampusNote("Reporta daños/lesiones a tu coordinador inmediatamente.")
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFC62828)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PngOrFallbackIcon(
                        resId = R.drawable.ic_phone_white,
                        fallback = Icons.Filled.Report,
                        size = 28.dp
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "¿EMERGENCIA EN CAMPUS?",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Text(
                            text = "Emergencias dentro del campus UCA:\n2210-6600, extensión 555.",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun BulletRow(
    iconRes: Int,
    fallback: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        PngOrFallbackIcon(iconRes, fallback, size = 18.dp)
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF2C5A6B))
    }
}

@Composable
private fun ActionMiniCard(
    title: String,
    desc: String,
    iconRes: Int,
    fallback: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.heightIn(min = 120.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PngOrFallbackIcon(iconRes, fallback, size = 34.dp)
            Text(title, fontWeight = FontWeight.ExtraBold, color = Color(0xFF163A4A))
            Text(desc, style = MaterialTheme.typography.bodySmall, color = Color(0xFF2C5A6B))
        }
    }
}

@Composable
private fun NumberedStep(number: Int, text: String) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(Color(0xFF2B6EA6)),
            contentAlignment = Alignment.Center
        ) {
            Text(number.toString(), color = Color.White, fontWeight = FontWeight.ExtraBold)
        }
        Spacer(Modifier.width(10.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF2C5A6B))
    }
}

@Composable
private fun DoDontCard(
    title: String,
    iconRes: Int,
    fallback: androidx.compose.ui.graphics.vector.ImageVector,
    bgColor: Color,
    items: List<String>,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = bgColor),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PngOrFallbackIcon(iconRes, fallback, size = 18.dp)
                Spacer(Modifier.width(8.dp))
                Text(title, fontWeight = FontWeight.ExtraBold, color = Color(0xFF163A4A))
            }
            items.forEach { t ->
                Text("• $t", style = MaterialTheme.typography.bodySmall, color = Color(0xFF2C5A6B))
            }
        }
    }
}

@Composable
private fun CampusNote(text: String) {
    Row(verticalAlignment = Alignment.Top) {
        PngOrFallbackIcon(
            resId = R.drawable.ic_pin,
            fallback = Icons.Filled.Report,
            size = 18.dp
        )
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF2C5A6B))
    }
}

/* =====================================================================================
   ✅ L A B / B I O S E G U R I D A D  (se mantiene igual)
   ===================================================================================== */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LabBioseguridadScreen(onBack: () -> Unit) {
    val bg = Color(0xFFEFF4F8)
    var selectedInfo by remember { mutableStateOf<InfoPopup?>(null) }

    Scaffold(
        containerColor = bg,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = bg)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bg)
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Laboratorios y bioseguridad",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF163A4A)
                )

                Text(
                    text = "Tu seguridad es nuestra prioridad",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF2C5A6B)
                )

                Spacer(Modifier.height(6.dp))

                BigPpeCard()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconOnlyCard(
                        iconRes = R.drawable.ic_shower,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedInfo = InfoPopup(
                                title = "EMERGENCIA",
                                desc = "Ubicación de ducha y lavaojos. Antes de iniciar, identifica dónde están para actuar rápido ante salpicaduras químicas o biológicas.",
                                iconRes = R.drawable.ic_shower
                            )
                        }
                    )

                    IconOnlyCard(
                        iconRes = R.drawable.ic_doc,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedInfo = InfoPopup(
                                title = "DOCUMENTACIÓN",
                                desc = "Revisar Fichas de Datos de Seguridad (FDS). Ahí encontrarás riesgos, EPP recomendado y pasos ante exposición o derrames.",
                                iconRes = R.drawable.ic_doc
                            )
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconOnlyCard(
                        iconRes = R.drawable.ic_no_pipette,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedInfo = InfoPopup(
                                title = "PROHIBIDO",
                                desc = "No pipetear con la boca. Usa pipeteadores o dispositivos adecuados para evitar ingestión o inhalación accidental.",
                                iconRes = R.drawable.ic_no_pipette
                            )
                        }
                    )

                    IconOnlyCard(
                        iconRes = R.drawable.ic_biohazard,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            selectedInfo = InfoPopup(
                                title = "RESIDUOS",
                                desc = "Desechar residuos según normativa. Separa residuos biológicos, químicos y comunes. No mezcles contenedores.",
                                iconRes = R.drawable.ic_biohazard
                            )
                        }
                    )
                }

                selectedInfo?.let { info ->
                    InfoPopupDialog(info = info, onDismiss = { selectedInfo = null })
                }

                SpillProtocolCard()

                Text(
                    text = "La bioseguridad es responsabilidad de todos.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF2C5A6B),
                    modifier = Modifier.padding(top = 6.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onBack,
                    shape = RoundedCornerShape(24.dp),
                    contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp)
                ) {
                    Text("Volver")
                }
            }
        }
    }
}

@Composable
private fun BigPpeCard() {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "EQUIPO DE PROTECCIÓN PERSONAL (PPE)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1C1C1C)
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PngOrFallbackIcon(R.drawable.ic_lab_coat, Icons.Filled.Shield, size = 44.dp)
                PngOrFallbackIcon(R.drawable.ic_gloves, Icons.Filled.Shield, size = 44.dp)
                PngOrFallbackIcon(R.drawable.ic_goggles, Icons.Filled.Shield, size = 44.dp)
            }

            Text(
                text = "Usar bata, guantes y gafas de protección.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF1C1C1C)
            )
        }
    }
}

private data class InfoPopup(
    val title: String,
    val desc: String,
    val iconRes: Int
)

@Composable
private fun IconOnlyCard(
    iconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(46.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun InfoPopupDialog(
    info: InfoPopup,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 10.dp)
            ) {
                Text("Entendido")
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = info.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = Color.Unspecified
                )
                Text(info.title, fontWeight = FontWeight.ExtraBold)
            }
        },
        text = { Text(info.desc, style = MaterialTheme.typography.bodyMedium) },
        shape = RoundedCornerShape(22.dp)
    )
}

@Composable
private fun SpillProtocolCard() {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF0D9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "PROTOCOLO DE DERRAME",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1C1C1C),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(12.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpillStep(R.drawable.ic_alert, Icons.Filled.Report, "Aislar área")
                ArrowDivider()
                SpillStep(R.drawable.ic_wind, Icons.Filled.Report, "Ventilar")
                ArrowDivider()
                SpillStep(R.drawable.ic_megaphone, Icons.Filled.Report, "Notificar\nresponsable")
            }
        }
    }
}

@Composable
private fun SpillStep(
    iconRes: Int,
    fallbackIcon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            PngOrFallbackIcon(iconRes, fallbackIcon, size = 26.dp)
        }
        Text(label, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF1C1C1C))
    }
}

@Composable
private fun ArrowDivider() {
    Box(
        modifier = Modifier
            .width(34.dp)
            .height(2.dp)
            .background(Color(0xFFD48B2A))
    )
}

@Composable
private fun PngOrFallbackIcon(
    resId: Int,
    fallback: androidx.compose.ui.graphics.vector.ImageVector,
    size: androidx.compose.ui.unit.Dp = 34.dp
) {
    val painter = runCatching { painterResource(id = resId) }.getOrNull()
    if (painter != null) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(size),
            tint = Color.Unspecified
        )
    } else {
        Icon(
            imageVector = fallback,
            contentDescription = null,
            modifier = Modifier.size(size),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}