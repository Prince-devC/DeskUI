package com.deskui.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// === Colors ===
@Stable
data class DeskColors(
    val primary: Color = Color(0xFF1A1A7D),
    val onPrimary: Color = Color.White,
    val background: Color = Color(0xFFF5F5F7),
    val surface: Color = Color.White,
    val text: Color = Color(0xFF1F272E),
    val textSecondary: Color = Color(0xFF6B7580),
    val border: Color = Color(0xFFE0E5E8),
    val error: Color = Color(0xFFE34D4D),
    val success: Color = Color(0xFF48BB78),
    val warning: Color = Color(0xFFED8936),
    val iconBg: Color = Color(0xFFE8EDFA),
    val searchBg: Color = Color(0xFFF2F4F5),
)

// === Typography ===
@Stable
data class DeskTypography(
    val h1: TextStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    val h2: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
    val h3: TextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    val body: TextStyle = TextStyle(fontSize = 14.sp),
    val bodySmall: TextStyle = TextStyle(fontSize = 13.sp),
    val caption: TextStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
    val label: TextStyle = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.SemiBold),
    val badge: TextStyle = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.SemiBold),
)

// === Shapes ===
@Stable
data class DeskShapes(
    val card: Dp = 12.dp,
    val button: Dp = 6.dp,
    val input: Dp = 6.dp,
    val badge: Dp = 4.dp,
    val chip: Dp = 12.dp,
    val sidebar: Dp = 8.dp,
    val icon: Dp = 12.dp,
)

// === Spacing ===
@Stable
data class DeskSpacing(
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
)

// === Sizes ===
@Stable
data class DeskSizes(
    val navBarHeight: Dp = 56.dp,
    val iconBox: Dp = 48.dp,
    val avatar: Dp = 32.dp,
    val inputHeight: Dp = 44.dp,
    val cardHeight: Dp = 110.dp,
    val badgeDot: Dp = 8.dp,
)

// === Theme ===
val LocalDeskColors = staticCompositionLocalOf { DeskColors() }
val LocalDeskTypography = staticCompositionLocalOf { DeskTypography() }
val LocalDeskShapes = staticCompositionLocalOf { DeskShapes() }
val LocalDeskSpacing = staticCompositionLocalOf { DeskSpacing() }
val LocalDeskSizes = staticCompositionLocalOf { DeskSizes() }

object DeskTheme {
    val colors: DeskColors @Composable get() = LocalDeskColors.current
    val typography: DeskTypography @Composable get() = LocalDeskTypography.current
    val shapes: DeskShapes @Composable get() = LocalDeskShapes.current
    val spacing: DeskSpacing @Composable get() = LocalDeskSpacing.current
    val sizes: DeskSizes @Composable get() = LocalDeskSizes.current
}

@Composable
fun DeskTheme(
    colors: DeskColors = DeskColors(),
    typography: DeskTypography = DeskTypography(),
    shapes: DeskShapes = DeskShapes(),
    spacing: DeskSpacing = DeskSpacing(),
    sizes: DeskSizes = DeskSizes(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDeskColors provides colors,
        LocalDeskTypography provides typography,
        LocalDeskShapes provides shapes,
        LocalDeskSpacing provides spacing,
        LocalDeskSizes provides sizes,
        content = content
    )
}
