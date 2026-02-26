package com.coderplus.css.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.coderplus.css.data.model.HeroSlide
import com.coderplus.css.utils.Constants
import kotlinx.coroutines.delay

@Composable
fun UcaCarousel(
    slides: List<HeroSlide>,
    modifier: Modifier = Modifier
) {
    var currentSlide by remember { mutableStateOf(0) }
    val slidesCount = slides.size
    val context = LocalContext.current

    // Auto-scroll
    LaunchedEffect(slidesCount) {
        if (slidesCount == 0) return@LaunchedEffect
        while (true) {
            delay(Constants.CAROUSEL_AUTO_SCROLL_DELAY)
            currentSlide = (currentSlide + 1) % slidesCount
        }
    }

    if (slidesCount == 0) return

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        slides.forEachIndexed { index, slide ->
            val isActive = index == currentSlide
            val alpha by animateFloatAsState(
                targetValue = if (isActive) 1f else 0f,
                animationSpec = tween(durationMillis = 800),
                label = "slideAlpha"
            )

            // 🔥 Convertir "hero_uca_1.jpg" -> resId de drawable
            val drawableName = remember(slide.imageRes) {
                slide.imageRes.substringBeforeLast(".") // quita .jpg/.png
            }
            val resId = remember(drawableName) {
                context.resources.getIdentifier(drawableName, "drawable", context.packageName)
            }

            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = if (resId != 0) resId else slide.imageRes, // fallback por si no lo encuentra
                    contentDescription = slide.alt,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { this.alpha = alpha },
                    contentScale = ContentScale.Crop
                )

                // Overlay oscuro
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f * alpha))
                )
            }
        }

        // Indicadores
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            slides.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .size(if (index == currentSlide) 32.dp else 8.dp, 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == currentSlide) Color.White
                            else Color.White.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}