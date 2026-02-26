package com.coderplus.css.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.coderplus.css.data.repository.ContentRepository

@Composable
fun ImportanciaScreen(modifier: Modifier = Modifier) {
    val bg = MaterialTheme.colorScheme.background

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(bg)
            .padding(vertical = 80.dp, horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¿Por qué es importante?",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        ContentRepository.importanciaVisualItems.forEachIndexed { index, item ->
            val accent = when (index) {
                0 -> Color(0xFF2B6EA6) // azul
                1 -> Color(0xFFF39C32) // naranja
                else -> Color(0xFF1E7A43) // verde
            }

            ImportanciaItemBlock(
                title = item.title,
                description = item.description,
                iconRes = item.iconRes,
                accentColor = accent
            )

            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}

@Composable
private fun ImportanciaItemBlock(
    title: String,
    description: String,
    iconRes: Int,
    accentColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(accentColor.copy(alpha = 0.18f)),
            contentAlignment = Alignment.Center
        ) {
            SafePngIcon(
                resId = iconRes,
                size = 48.dp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF2B6EA6),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
private fun SafePngIcon(
    resId: Int,
    size: androidx.compose.ui.unit.Dp
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
        // Si el drawable no existe, no crashea: simplemente no muestra nada.
        Spacer(modifier = Modifier.size(size))
    }
}