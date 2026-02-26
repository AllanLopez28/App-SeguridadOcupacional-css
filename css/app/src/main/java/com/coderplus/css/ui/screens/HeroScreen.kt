package com.coderplus.css.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.coderplus.css.data.repository.ContentRepository
import com.coderplus.css.ui.components.UcaButton
import com.coderplus.css.ui.components.UcaCarousel

@Composable
fun HeroScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        // Carousel de fondo
        UcaCarousel(
            slides = ContentRepository.heroSlides,
            modifier = Modifier.fillMaxWidth()
        )

        // Contenido superpuesto
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 80.dp, bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = ContentRepository.heroTitle,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = ContentRepository.heroSubtitle,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.95f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                UcaButton(
                    text = "Ver protocolos",
                    onClick = { onNavigate("protocolos") },
                    modifier = Modifier.weight(1f),
                    isPrimary = true
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            UcaButton(
                text = "Hacer examen",
                onClick = { onNavigate("examen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                isPrimary = true
            )
        }
    }
}
