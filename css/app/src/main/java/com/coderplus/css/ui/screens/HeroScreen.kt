package com.coderplus.css.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.coderplus.css.R
import kotlin.math.roundToInt

// ─── Colores del Hero ────────────────────────────────────────────────────────
private val HeroOverlayStart = Color(0xFF0D1B2A).copy(alpha = 0.88f)
private val HeroOverlayEnd   = Color(0xFF1A3A5C).copy(alpha = 0.75f)
private val BadgeGreen       = Color(0xFF2ECC71)
private val BadgeGreenBg     = Color(0xFF1A4731).copy(alpha = 0.90f)
private val CardBg           = Color(0xFFFFFFFF).copy(alpha = 0.12f)
private val CardIconBg       = Color(0xFF2563EB)
private val StatsBg          = Color(0xFFFFFFFF).copy(alpha = 0.10f)
private val StatsDivider     = Color(0xFFFFFFFF).copy(alpha = 0.20f)
private val TextHero         = Color.White
private val TextHeroMuted    = Color(0xFFCBD5E1)

@Composable
fun HeroScreen(
    onVerProtocolos: () -> Unit = {},
    onRealizarExamen: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // ── Imagen de fondo (drawable local) ─────────────────────────────────
        Image(
            painter = painterResource(id = R.drawable.hero_uca_2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 500.dp)
        )

        // ── Overlay degradado oscuro sobre la imagen ──────────────────────────
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(HeroOverlayStart, HeroOverlayEnd)
                    )
                )
        )

        // ── Contenido ─────────────────────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            HeroBadge()
            HeroTitle()
            HeroSubtitle()
            HeroFloatingCard()
            HeroButtons(
                onVerProtocolos = onVerProtocolos,
                onRealizarExamen = onRealizarExamen
            )
            HeroStatsBar()
        }
    }
}

// ─── Badge "● CAMPUS SEGURO" ──────────────────────────────────────────────────
@Composable
private fun HeroBadge() {
    Surface(
        shape = RoundedCornerShape(50.dp),
        color = BadgeGreenBg
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            PulsingDot()
            Text(
                text = "CAMPUS SEGURO",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = BadgeGreen,
                letterSpacing = 0.8.sp
            )
        }
    }
}

@Composable
private fun PulsingDot() {
    val inf = rememberInfiniteTransition(label = "pulse")
    val scale by inf.animateFloat(
        initialValue = 1f,
        targetValue = 1.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dotScale"
    )
    Box(
        modifier = Modifier
            .size((7 * scale).dp)
            .clip(CircleShape)
            .background(BadgeGreen)
    )
}

// ─── Título y subtítulo ───────────────────────────────────────────────────────
@Composable
private fun HeroTitle() {
    Text(
        text = "Seguridad Ocupacional\nen CSS UCA",
        fontSize = 34.sp,
        fontWeight = FontWeight.ExtraBold,
        color = TextHero,
        lineHeight = 40.sp
    )
}

@Composable
private fun HeroSubtitle() {
    Text(
        text = "Infórmate acerca de la importancia de la seguridad ocupacional y protocolos a seguir en los proyectos del Centro de Servicio Social: identificar peligros, usar el EPP correcto y actuar a tiempo.",
        fontSize = 14.sp,
        color = TextHeroMuted,
        lineHeight = 21.sp
    )
}

// ─── Tarjeta flotante "Protocolo Activo" ─────────────────────────────────────
@Composable
private fun HeroFloatingCard() {
    val inf = rememberInfiniteTransition(label = "float")
    val offsetY by inf.animateFloat(
        initialValue = 0f,
        targetValue = -12f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floatY"
    )

    Surface(
        shape = RoundedCornerShape(18.dp),
        color = CardBg,
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(0, offsetY.roundToInt()) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Ícono en caja azul
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CardIconBg),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = "https://api.iconify.design/heroicons:clipboard-document-check.svg?color=white",
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "Protocolo Activo",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextHero
                )
                Text(
                    text = "\"Completa tu capacitación obligatoria en protocolos de seguridad. Al llegar al 100% estarás listo para realizar el examen de certificación y registrar tu participación en el programa de Campus Seguro\"",
                    fontSize = 12.sp,
                    color = TextHeroMuted,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

// ─── Botones de acción ────────────────────────────────────────────────────────
@Composable
private fun HeroButtons(
    onVerProtocolos: () -> Unit,
    onRealizarExamen: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onVerProtocolos,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TextHero,
                contentColor = Color(0xFF0D1B2A)
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp)
        ) {
            Text(
                text = "Ver protocolos",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }

        OutlinedButton(
            onClick = onRealizarExamen,
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = TextHero
            ),
            border = androidx.compose.foundation.BorderStroke(
                width = 1.5.dp,
                color = TextHero.copy(alpha = 0.65f)
            )
        ) {
            Text(
                text = "Realizar examen",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ─── Barra de estadísticas ────────────────────────────────────────────────────
@Composable
private fun HeroStatsBar() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = StatsBg,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeroStatItem(
                iconUrl = "https://api.iconify.design/heroicons:clipboard-document-check.svg?color=white",
                label = "Índice de cumplimiento"
            )

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(36.dp)
                    .background(StatsDivider)
            )

            HeroStatItem(
                iconUrl = "https://api.iconify.design/heroicons:user-group.svg?color=white",
                label = "Estudiantes capacitados"
            )
        }
    }
}

@Composable
private fun HeroStatItem(iconUrl: String, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(CardIconBg),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = iconUrl,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            color = TextHeroMuted,
            fontWeight = FontWeight.Medium,
            lineHeight = 17.sp
        )
    }
}