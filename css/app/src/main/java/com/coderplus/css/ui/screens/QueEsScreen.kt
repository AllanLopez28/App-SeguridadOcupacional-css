package com.coderplus.css.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.coderplus.css.data.repository.ContentRepository
import com.coderplus.css.utils.Constants

// ─── Colores locales ──────────────────────────────────────────────────────────
private val QueEsBg         = Color(0xFFF4F6FB)
private val QueEsIconBg     = Color(0xFF1E3A5F)
private val QueEsTitleColor = Color(0xFF0F2D40)
private val QueEsTextColor  = Color(0xFF6B7B93)
private val QueEsItemTitle  = Color(0xFF1E3A5F)
private val StatCardBg      = Color(0xFFEEF2F7)
private val StatIconBg      = Color(0xFF1E3A5F)
private val StatValueColor  = Color(0xFF0F2D40)
private val StatLabelColor  = Color(0xFF6B7B93)

// ─── Datos de las tarjetas de estadísticas ────────────────────────────────────
private data class StatCard(
    val iconUrl: String,
    val value: String,
    val label: String
)

private val statCards = listOf(
    StatCard(
        iconUrl = Constants.ICON_SHIELD_CHECK,
        value = "100%",
        label = "Proyectos con evaluación de riesgos"
    ),
    StatCard(
        iconUrl = Constants.ICON_USER_GROUP,
        value = "200+",
        label = "Estudiantes capacitados al año"
    ),
    StatCard(
        iconUrl = Constants.ICON_CLIPBOARD_CHECK,
        value = "0",
        label = "Incidentes graves en actividades"
    )
)

@Composable
fun QueEsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(QueEsBg)
            .padding(vertical = 60.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // ── Título ────────────────────────────────────────────────────────────
        Text(
            text = "¿Qué es la Seguridad  Ocupacional?",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = QueEsTitleColor,
            lineHeight = 34.sp
        )

        // ── Descripción ───────────────────────────────────────────────────────
        Text(
            text = "Es el conjunto de prácticas, protocolos y controles que aplicamos en los proyectos del Centro de Servicio Social para evitar incidentes, cuidar la salud de los estudiantes y de las comunidades donde intervenimos.",
            fontSize = 15.sp,
            color = QueEsTextColor,
            lineHeight = 23.sp
        )

        // ── Lista de ítems ────────────────────────────────────────────────────
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            ContentRepository.queEsItems.forEach { item ->
                QueEsListItem(
                    iconUrl = item.iconUrl,
                    title = item.title,
                    description = item.description
                )
            }
        }

        // ── Tarjetas de estadísticas ──────────────────────────────────────────
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            statCards.forEach { stat ->
                QueEsStatCard(stat = stat)
            }
        }
    }
}

// ─── Ítem de la lista (ícono + título + descripción) ─────────────────────────
@Composable
private fun QueEsListItem(
    iconUrl: String,
    title: String,
    description: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Ícono en caja oscura
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = QueEsIconBg,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = iconUrl,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = QueEsItemTitle
            )
            Text(
                text = description,
                fontSize = 13.sp,
                color = QueEsTextColor,
                lineHeight = 19.sp
            )
        }
    }
}

// ─── Tarjeta de estadística ───────────────────────────────────────────────────
@Composable
private fun QueEsStatCard(stat: StatCard) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = StatCardBg,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Ícono
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = StatIconBg,
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = stat.iconUrl,
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                )
            }

            // Valor
            Text(
                text = stat.value,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = StatValueColor
            )

            // Etiqueta
            Text(
                text = stat.label,
                fontSize = 13.sp,
                color = StatLabelColor,
                lineHeight = 18.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}