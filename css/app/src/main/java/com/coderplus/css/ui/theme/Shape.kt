package com.coderplus.css.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Formas basadas en tokens.css
val Shapes = Shapes(
    // radius-sm: 0.5rem = 8dp
    small = RoundedCornerShape(8.dp),
    // radius-md: 0.75rem = 12dp
    medium = RoundedCornerShape(12.dp),
    // radius-lg: 1.25rem = 20dp
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp)
)
