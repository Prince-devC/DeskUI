package com.deskui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deskui.theme.DeskTheme

// === Status Badge ===
@Composable
fun DeskStatusBadge(label: String, color: Color, modifier: Modifier = Modifier) {
    if (label.isNotEmpty()) {
        Text(
            label, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = color,
            modifier = modifier
                .background(color.copy(alpha = 0.12f), RoundedCornerShape(DeskTheme.shapes.badge))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun DeskStatusBadge(status: String, docstatus: Int = 0) {
    val (label, color) = resolveStatus(status, docstatus)
    DeskStatusBadge(label, color)
}

private fun resolveStatus(status: String, docstatus: Int): Pair<String, Color> = when {
    status.isNotEmpty() -> when (status.lowercase()) {
        "paid", "completed", "active" -> status to Color(0xFF48BB78)
        "unpaid", "overdue", "expired" -> status to Color(0xFFE34D4D)
        "cancelled", "closed" -> status to Color(0xFF6B7580)
        "draft" -> status to Color(0xFFED8936)
        "submitted", "open" -> status to Color(0xFF1A1A7D)
        else -> status to Color(0xFF1A1A7D)
    }
    docstatus == 0 -> "Brouillon" to Color(0xFFED8936)
    docstatus == 1 -> "Soumis" to Color(0xFF1A1A7D)
    docstatus == 2 -> "Annule" to Color(0xFF6B7580)
    else -> "" to Color.Transparent
}

// === Filter Chip ===
@Composable
fun DeskFilterChip(label: String, value: String, onRemove: () -> Unit) {
    Row(
        Modifier.background(DeskTheme.colors.primary.copy(alpha = 0.1f), RoundedCornerShape(DeskTheme.shapes.chip))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$label: $value", fontSize = 11.sp, color = DeskTheme.colors.primary)
        Spacer(Modifier.width(4.dp))
        Text("×", Modifier.clickable(onClick = onRemove), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DeskTheme.colors.primary)
    }
}

// === Text Field ===
@Composable
fun DeskTextField(
    value: String, onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null, placeholder: String = "", required: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text, isPassword: Boolean = false,
    singleLine: Boolean = true, minLines: Int = 1
) {
    Column(modifier.padding(vertical = 6.dp)) {
        if (label != null) {
            Text(
                label + if (required) " *" else "",
                fontSize = 12.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.textSecondary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        OutlinedTextField(
            value = value, onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(), singleLine = singleLine, minLines = minLines,
            placeholder = if (placeholder.isNotEmpty()) {{ Text(placeholder, fontSize = 14.sp, color = DeskTheme.colors.textSecondary) }} else null,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            shape = RoundedCornerShape(DeskTheme.shapes.input),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DeskTheme.colors.border,
                unfocusedBorderColor = DeskTheme.colors.border,
                cursorColor = DeskTheme.colors.primary
            ),
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = DeskTheme.colors.text)
        )
    }
}

// === Select Field ===
@Composable
fun DeskSelect(
    value: String, onValueChange: (String) -> Unit, options: List<String>,
    modifier: Modifier = Modifier, label: String? = null, required: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier.padding(vertical = 6.dp)) {
        if (label != null) {
            Text(label + if (required) " *" else "", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.textSecondary,
                modifier = Modifier.padding(bottom = 4.dp))
        }
        Box {
            Row(
                Modifier.fillMaxWidth().height(DeskTheme.sizes.inputHeight)
                    .border(1.dp, DeskTheme.colors.border, RoundedCornerShape(DeskTheme.shapes.input))
                    .clickable { expanded = true }.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    value.ifEmpty { "Selectionner..." }, fontSize = 14.sp,
                    color = if (value.isEmpty()) DeskTheme.colors.textSecondary else DeskTheme.colors.text,
                    modifier = Modifier.weight(1f)
                )
                Text("▾", fontSize = 12.sp, color = DeskTheme.colors.textSecondary)
            }
            DropdownMenu(expanded, { expanded = false }) {
                DropdownMenuItem(text = { Text("-- Aucun --") }, onClick = { onValueChange(""); expanded = false })
                options.forEach { opt ->
                    DropdownMenuItem(text = { Text(opt) }, onClick = { onValueChange(opt); expanded = false })
                }
            }
        }
    }
}

// === Checkbox ===
@Composable
fun DeskCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.textSecondary, modifier = Modifier.weight(1f))
        Switch(checked, onCheckedChange, colors = SwitchDefaults.colors(checkedTrackColor = DeskTheme.colors.primary))
    }
}

// === Primary Button ===
@Composable
fun DeskButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    Text(
        text, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.onPrimary,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
            .background(
                if (enabled) DeskTheme.colors.primary else DeskTheme.colors.primary.copy(alpha = 0.5f),
                RoundedCornerShape(DeskTheme.shapes.button)
            )
            .clickable(enabled = enabled, onClick = onClick)
            .padding(vertical = 10.dp)
    )
}

// === Section Header ===
@Composable
fun DeskSectionHeader(label: String, modifier: Modifier = Modifier) {
    if (label.isNotBlank()) {
        Column(modifier.padding(top = 16.dp, bottom = 8.dp)) {
            Text(label.uppercase(), fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
                color = DeskTheme.colors.textSecondary, letterSpacing = 0.5.sp)
            Spacer(Modifier.height(6.dp))
            HorizontalDivider(color = DeskTheme.colors.border)
        }
    }
}

// === Avatar ===
@Composable
fun DeskAvatar(initials: String, modifier: Modifier = Modifier) {
    Box(
        modifier.size(DeskTheme.sizes.avatar).clip(CircleShape)
            .background(DeskTheme.colors.primary.copy(alpha = 0.15f)),
        contentAlignment = Alignment.Center
    ) {
        Text(initials.ifEmpty { "?" }, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = DeskTheme.colors.primary)
    }
}

// === Notification Dot ===
@Composable
fun DeskNotificationBadge(count: Int, modifier: Modifier = Modifier) {
    if (count > 0) {
        Text(
            "$count", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DeskTheme.colors.onPrimary,
            modifier = modifier.background(DeskTheme.colors.error, CircleShape).padding(3.dp)
        )
    }
}

// === Icon Box ===
@Composable
fun DeskIconBox(icon: String, modifier: Modifier = Modifier) {
    Box(
        modifier.size(DeskTheme.sizes.iconBox).background(DeskTheme.colors.iconBg, RoundedCornerShape(DeskTheme.shapes.icon)),
        contentAlignment = Alignment.Center
    ) {
        Text(icon, fontSize = 20.sp)
    }
}

// === Card ===
@Composable
fun DeskCard(icon: String, label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxWidth().height(DeskTheme.sizes.cardHeight)
            .background(DeskTheme.colors.surface, RoundedCornerShape(DeskTheme.shapes.card))
            .border(1.dp, DeskTheme.colors.border, RoundedCornerShape(DeskTheme.shapes.card))
            .clickable(onClick = onClick).padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DeskIconBox(icon)
        Spacer(Modifier.height(6.dp))
        Text(label, fontSize = 11.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center,
            maxLines = 2, color = DeskTheme.colors.text, modifier = Modifier.height(30.dp))
    }
}

// === List Item ===
@Composable
fun DeskListItem(
    title: String, subtitle: String = "", status: String = "", docstatus: Int = -1,
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth().background(DeskTheme.colors.surface).clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.text, maxLines = 1)
                if (status.isNotEmpty() || docstatus >= 0) DeskStatusBadge(status, docstatus)
            }
            if (subtitle.isNotBlank()) Text(subtitle, fontSize = 12.sp, color = DeskTheme.colors.textSecondary, maxLines = 1)
        }
        Text(">", fontSize = 11.sp, color = DeskTheme.colors.textSecondary)
    }
}

// === Link Item (for sidebar/links sections) ===
@Composable
fun DeskLinkItem(icon: String = "📄", label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, fontSize = 14.sp, modifier = Modifier.width(24.dp))
        Spacer(Modifier.width(10.dp))
        Text(label, fontSize = 14.sp, color = DeskTheme.colors.text, modifier = Modifier.weight(1f))
        Text(">", fontSize = 11.sp, color = DeskTheme.colors.textSecondary)
    }
}

// === Search Bar ===
@Composable
fun DeskSearchBar(query: String, onQueryChange: (String) -> Unit, onClear: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier.height(34.dp).background(DeskTheme.colors.searchBg, RoundedCornerShape(8.dp)).padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("🔍", fontSize = 14.sp)
        Spacer(Modifier.width(8.dp))
        BasicTextField(
            value = query, onValueChange = onQueryChange,
            modifier = Modifier.weight(1f), singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = DeskTheme.colors.text),
            decorationBox = { inner ->
                if (query.isEmpty()) Text("Rechercher...", fontSize = 14.sp, color = DeskTheme.colors.textSecondary)
                inner()
            }
        )
        if (query.isNotEmpty()) {
            Text("×", Modifier.clickable(onClick = onClear), fontSize = 14.sp, color = DeskTheme.colors.textSecondary)
        }
    }
}
