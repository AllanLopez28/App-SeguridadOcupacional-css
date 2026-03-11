package com.coderplus.css.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coderplus.css.data.repository.ContentRepository

// ─── Colores locales ──────────────────────────────────────────────────────────
private val ReportesBg          = Color(0xFFF4F6FB)
private val ReportesCardBg      = Color.White
private val ReportesTitleColor  = Color(0xFF0F2D40)
private val ReportesTextColor   = Color(0xFF6B7B93)
private val ReportesAccent      = Color(0xFF0F6AB4)
private val SideCardBg          = Color(0xFFEEF2F7)
private val SideCardTitle       = Color(0xFF1E3A5F)
private val SideCardText        = Color(0xFF6B7B93)
private val BulletColor         = Color(0xFF0F6AB4)
private val NumberBg            = Color(0xFF1E3A5F)

@Composable
fun ReportesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ReportesBg)
            .padding(vertical = 60.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // ── Contenedor principal con fondo blanco ─────────────────────────────
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = ReportesCardBg,
            shadowElevation = 1.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Título
                Text(
                    text = "Reportes e incidentes",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ReportesTitleColor
                )

                // Descripción
                Text(
                    text = ContentRepository.reportesDescription,
                    fontSize = 14.sp,
                    color = ReportesTextColor,
                    lineHeight = 21.sp
                )

                // Lista de tipos de reporte con bullet
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    ContentRepository.reportesTipos.forEach { reporte ->
                        ReporteBulletItem(
                            tipo = reporte.tipo,
                            descripcion = reporte.descripcion
                        )
                    }
                }
            }
        }

        // ── Tarjeta: Contacto de reporte ──────────────────────────────────────
        SideInfoCard(
            title = "Contacto de reporte",
            content = {
                Text(
                    text = "Envía el reporte a tu coordinador del CSS con estos datos mínimos:",
                    fontSize = 13.sp,
                    color = SideCardText,
                    lineHeight = 19.sp
                )
                Spacer(Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf(
                        "Correo/teléfono de tu encargado en la actividad",
                        "Fecha, hora y lugar del evento",
                        "Actividad que se realizaba y quiénes estaban"
                    ).forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(BulletColor)
                            )
                            Text(
                                text = item,
                                fontSize = 13.sp,
                                color = SideCardText,
                                lineHeight = 19.sp
                            )
                        }
                    }
                }
            }
        )

        // ── Tarjeta: Checklist rápido ─────────────────────────────────────────
        SideInfoCard(
            title = "Checklist rápido",
            content = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf(
                        "Detén la actividad si hay riesgo.",
                        "Atiende y documenta (foto, lugar, hora).",
                        "Notifica a la coordinación del CSS."
                    ).forEachIndexed { index, step ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            // Número circular
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .clip(CircleShape)
                                    .background(NumberBg),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Text(
                                text = step,
                                fontSize = 13.sp,
                                color = SideCardText,
                                lineHeight = 19.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

// ─── Ítem de bullet (tipo: descripción) ──────────────────────────────────────
@Composable
private fun ReporteBulletItem(tipo: String, descripcion: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Bullet azul
        Box(
            modifier = Modifier
                .padding(top = 6.dp)
                .size(7.dp)
                .clip(CircleShape)
                .background(BulletColor)
        )
        Text(
            text = buildAnnotatedString {
                append(tipo)
                append(": ")
                pushStyle(
                    androidx.compose.ui.text.SpanStyle(
                        color = ReportesTextColor,
                        fontWeight = FontWeight.Normal
                    )
                )
                append(descripcion)
                pop()
            },
            fontSize = 14.sp,
            color = ReportesTitleColor,
            lineHeight = 21.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// ─── Tarjeta lateral con fondo gris azulado ───────────────────────────────────
@Composable
private fun SideInfoCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = SideCardBg,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = SideCardTitle
            )
            content()
        }
    }
}