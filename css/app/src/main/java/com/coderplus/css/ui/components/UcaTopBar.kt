package com.coderplus.css.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.coderplus.css.R

// ─── Colores del navbar ───────────────────────────────────────────────────────
private val NavBg          = Color(0xFF1E3A5F)
private val NavText        = Color.White
private val NavDivider     = Color.White.copy(alpha = 0.15f)
private val NavMenuBg      = Color(0xFF1E3A5F)
private val NavMenuBorder  = Color(0xFF2563EB)   // borde azul del botón de menú
private val NavItemHover   = Color.White.copy(alpha = 0.08f)

@Composable
fun UcaTopBar(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuOpen by remember { mutableStateOf(false) }

    val menuItems = listOf(
        "Importancia"  to "importancia",
        "¿Qué es?"     to "que-es",
        "Protocolos"   to "protocolos",
        "EPP"          to "epp",
        "Riesgos"      to "riesgos",
        "Reportes"     to "reportes",
        "Registro"     to "registro"
    )

    // ── Barra superior ────────────────────────────────────────────────────────
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = NavBg,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Logo CSS UCA
            NavLogo()

            // Botón hamburguesa con borde azul
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(NavMenuBorder.copy(alpha = 0.25f))
                    .clickable { menuOpen = true },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Abrir menú",
                    tint = NavText,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }

    // ── Menú lateral (drawer) como Dialog full-screen ─────────────────────────
    if (menuOpen) {
        Dialog(
            onDismissRequest = { menuOpen = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Fondo semitransparente (tap fuera = cerrar)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.45f))
                        .clickable { menuOpen = false }
                )

                // Panel del menú (ancho fijo, pegado a la derecha)
                AnimatedVisibility(
                    visible = menuOpen,
                    enter = slideInHorizontally(initialOffsetX = { it }),
                    exit = slideOutHorizontally(targetOffsetX = { it }),
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(280.dp)
                            .background(NavMenuBg)
                            .statusBarsPadding()
                    ) {
                        // Cabecera del menú
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            NavLogo()

                            IconButton(onClick = { menuOpen = false }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Cerrar menú",
                                    tint = NavText,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }

                        HorizontalDivider(color = NavDivider, thickness = 1.dp)

                        // Ítems del menú
                        Spacer(Modifier.height(8.dp))
                        menuItems.forEach { (label, route) ->
                            NavMenuItem(
                                label = label,
                                onClick = {
                                    onNavigate(route)
                                    menuOpen = false
                                }
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = NavDivider,
                                thickness = 0.5.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

// ─── Logo CSS UCA ─────────────────────────────────────────────────────────────
@Composable
private fun NavLogo() {
    val context = androidx.compose.ui.platform.LocalContext.current

    // Verificamos si el drawable existe usando el Context (no requiere @Composable)
    val logoResId = remember {
        context.resources.getIdentifier("logo_uca", "drawable", context.packageName)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (logoResId != 0) {
            Image(
                painter = painterResource(id = logoResId),
                contentDescription = "Logo CSS UCA",
                modifier = Modifier.height(44.dp)
            )
        } else {
            Column {
                Text(
                    text = "CSS UCA",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = NavText,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "Centro de Servicio Social",
                    fontSize = 9.sp,
                    color = NavText.copy(alpha = 0.75f),
                    letterSpacing = 0.2.sp
                )
            }
        }
    }
}

// ─── Ítem individual del menú ─────────────────────────────────────────────────
@Composable
private fun NavMenuItem(
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = NavText,
            letterSpacing = 0.1.sp
        )
    }
}