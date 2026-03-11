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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.coderplus.css.data.repository.ContentRepository

// ─── Colores locales ──────────────────────────────────────────────────────────
private val RiesgosBg         = Color(0xFFEEF2F7)
private val RiesgosTitleColor = Color(0xFF0F2D40)
private val RiesgosCardBg     = Color.White
private val RiesgosIconCircle = Color(0xFFFDE8E0)   // círculo salmón/rosado
private val RiesgosItemTitle  = Color(0xFF1E3A5F)
private val RiesgosItemText   = Color(0xFF6B7B93)

@Composable
fun RiesgosScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(RiesgosBg)
            .padding(vertical = 60.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // ── Título ────────────────────────────────────────────────────────────
        Text(
            text = "Riesgos comunes",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = RiesgosTitleColor,
            textAlign = TextAlign.Center
        )

        // ── Grid de tarjetas ──────────────────────────────────────────────────
        // En móvil: 2 columnas
        val items = ContentRepository.riesgosItems
        val rows = items.chunked(2)

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEach { item ->
                        RiesgoCard(
                            iconUrl = item.iconUrl,
                            title = item.title,
                            description = item.description,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    // Si la fila tiene solo 1 ítem, rellenar con spacer
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

// ─── Tarjeta individual de riesgo ─────────────────────────────────────────────
@Composable
private fun RiesgoCard(
    iconUrl: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = RiesgosCardBg,
        shadowElevation = 2.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Ícono en círculo salmón
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(RiesgosIconCircle),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = iconUrl,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF1C1C1C))
                )
            }

            // Título
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = RiesgosItemTitle,
                lineHeight = 20.sp
            )

            // Descripción
            Text(
                text = description,
                fontSize = 13.sp,
                color = RiesgosItemText,
                lineHeight = 19.sp
            )
        }
    }
}