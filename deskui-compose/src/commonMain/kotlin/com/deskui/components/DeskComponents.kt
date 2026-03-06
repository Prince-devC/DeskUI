package com.deskui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deskui.theme.DeskTheme

// ============================================================
// DeskStatusBadge
// ============================================================
// Displays a color-coded status indicator for document states.
//
// Usage:
//   DeskStatusBadge(label = "Paid", color = DeskTheme.colors.success)
//   DeskStatusBadge(status = "Draft", docstatus = 0)
// ============================================================

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
fun DeskStatusBadge(status: String = "", docstatus: Int = -1) {
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
    docstatus == 0 -> "Draft" to Color(0xFFED8936)
    docstatus == 1 -> "Submitted" to Color(0xFF1A1A7D)
    docstatus == 2 -> "Cancelled" to Color(0xFF6B7580)
    else -> "" to Color.Transparent
}

// ============================================================
// DeskFilterChip
// ============================================================
// A removable filter tag showing active filter key-value pairs.
//
// Usage:
//   DeskFilterChip(label = "Status", value = "Paid", onRemove = { })
// ============================================================

@Composable
fun DeskFilterChip(label: String, value: String, onRemove: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier.background(DeskTheme.colors.primary.copy(alpha = 0.1f), RoundedCornerShape(DeskTheme.shapes.chip))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("$label: $value", fontSize = 11.sp, color = DeskTheme.colors.primary)
        Spacer(Modifier.width(4.dp))
        Icon(Icons.Default.Close, null, Modifier.size(12.dp).clickable(onClick = onRemove), tint = DeskTheme.colors.primary)
    }
}

// ============================================================
// DeskTextField
// ============================================================
// Outlined text input with optional label, placeholder, and validation.
// Supports all keyboard types, password mode, and multi-line.
//
// Usage:
//   DeskTextField(value = name, onValueChange = { name = it }, label = "Full Name", required = true)
//   DeskTextField(value = amount, onValueChange = { }, label = "Amount", keyboardType = KeyboardType.Decimal)
//   DeskTextField(value = notes, onValueChange = { }, label = "Notes", singleLine = false, minLines = 3)
// ============================================================

@Composable
fun DeskTextField(
    value: String, onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null, placeholder: String = "", required: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text, isPassword: Boolean = false,
    singleLine: Boolean = true, minLines: Int = 1, readOnly: Boolean = false
) {
    Column(modifier.padding(vertical = 6.dp)) {
        if (label != null) {
            DeskFieldLabel(label, required)
        }
        OutlinedTextField(
            value = value, onValueChange = onValueChange, readOnly = readOnly,
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

// ============================================================
// DeskSelect
// ============================================================
// Dropdown picker with label support.
//
// Usage:
//   DeskSelect(value = status, onValueChange = { status = it },
//       options = listOf("Draft", "Submitted", "Cancelled"), label = "Status")
// ============================================================

@Composable
fun DeskSelect(
    value: String, onValueChange: (String) -> Unit, options: List<String>,
    modifier: Modifier = Modifier, label: String? = null, required: Boolean = false,
    emptyLabel: String = "Select..."
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier.padding(vertical = 6.dp)) {
        if (label != null) DeskFieldLabel(label, required)
        Box {
            Row(
                Modifier.fillMaxWidth().height(DeskTheme.sizes.inputHeight)
                    .border(1.dp, DeskTheme.colors.border, RoundedCornerShape(DeskTheme.shapes.input))
                    .clickable { expanded = true }.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    value.ifEmpty { emptyLabel }, fontSize = 14.sp,
                    color = if (value.isEmpty()) DeskTheme.colors.textSecondary else DeskTheme.colors.text,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.KeyboardArrowDown, null, Modifier.size(16.dp), tint = DeskTheme.colors.textSecondary)
            }
            DropdownMenu(expanded, { expanded = false }) {
                DropdownMenuItem(text = { Text("-- None --", color = DeskTheme.colors.textSecondary) },
                    onClick = { onValueChange(""); expanded = false })
                options.forEach { opt ->
                    DropdownMenuItem(text = { Text(opt) }, onClick = { onValueChange(opt); expanded = false })
                }
            }
        }
    }
}

// ============================================================
// DeskCheckbox
// ============================================================
// Toggle switch with label.
//
// Usage:
//   DeskCheckbox(label = "Is Active", checked = isActive, onCheckedChange = { isActive = it })
// ============================================================

@Composable
fun DeskCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.textSecondary, modifier = Modifier.weight(1f))
        Switch(checked, onCheckedChange, colors = SwitchDefaults.colors(checkedTrackColor = DeskTheme.colors.primary))
    }
}

// ============================================================
// DeskButton
// ============================================================
// Primary action button. Full-width by default.
//
// Usage:
//   DeskButton(text = "Save", onClick = { save() })
//   DeskButton(text = "Saving...", onClick = { }, enabled = false)
// ============================================================

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

// ============================================================
// DeskSectionHeader
// ============================================================
// Uppercase section label with divider. Used in forms and lists.
//
// Usage:
//   DeskSectionHeader("Account Details")
// ============================================================

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

// ============================================================
// DeskAvatar
// ============================================================
// Circular avatar showing user initials.
//
// Usage:
//   DeskAvatar(initials = "AB")
// ============================================================

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

// ============================================================
// DeskNotificationBadge
// ============================================================
// Red circle badge with count. Typically overlaid on bell icon.
//
// Usage:
//   DeskNotificationBadge(count = 3)
// ============================================================

@Composable
fun DeskNotificationBadge(count: Int, modifier: Modifier = Modifier) {
    if (count > 0) {
        Text(
            "$count", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DeskTheme.colors.onPrimary,
            modifier = modifier.background(DeskTheme.colors.error, CircleShape).padding(3.dp)
        )
    }
}

// ============================================================
// DeskIconBox
// ============================================================
// Rounded square container for icons. Used inside DeskCard.
//
// Usage:
//   DeskIconBox(icon = Icons.Default.ShoppingCart)
// ============================================================

@Composable
fun DeskIconBox(icon: ImageVector, modifier: Modifier = Modifier) {
    Box(
        modifier.size(DeskTheme.sizes.iconBox).background(DeskTheme.colors.iconBg, RoundedCornerShape(DeskTheme.shapes.icon)),
        contentAlignment = Alignment.Center
    ) {
        Icon(icon, null, Modifier.size(22.dp), tint = DeskTheme.colors.primary)
    }
}

// ============================================================
// DeskCard
// ============================================================
// Grid card with icon and label. Fixed height for uniform grids.
//
// Usage:
//   DeskCard(icon = Icons.Default.Inventory, label = "Item", onClick = { })
// ============================================================

@Composable
fun DeskCard(icon: ImageVector, label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
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

// ============================================================
// DeskListItem
// ============================================================
// Standard list row with title, subtitle, status badge, and chevron.
//
// Usage:
//   DeskListItem(title = "INV-001", subtitle = "Customer A", status = "Paid", onClick = { })
// ============================================================

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
        Icon(Icons.Default.KeyboardArrowDown, null, Modifier.size(14.dp), tint = DeskTheme.colors.textSecondary) // chevron
    }
}

// ============================================================
// DeskLinkItem
// ============================================================
// Navigation row with icon, label, and chevron. Used in sidebar links.
//
// Usage:
//   DeskLinkItem(icon = Icons.Default.AccountBalance, label = "Chart of Accounts", onClick = { })
// ============================================================

@Composable
fun DeskLinkItem(icon: ImageVector, label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, Modifier.size(16.dp), tint = DeskTheme.colors.textSecondary)
        Spacer(Modifier.width(10.dp))
        Text(label, fontSize = 14.sp, color = DeskTheme.colors.text, modifier = Modifier.weight(1f))
        Icon(Icons.Default.KeyboardArrowDown, null, Modifier.size(12.dp), tint = DeskTheme.colors.textSecondary)
    }
}

// ============================================================
// DeskSearchBar
// ============================================================
// Inline search input with icon and clear button.
//
// Usage:
//   DeskSearchBar(query = searchText, onQueryChange = { searchText = it }, onClear = { searchText = "" })
// ============================================================

@Composable
fun DeskSearchBar(query: String, onQueryChange: (String) -> Unit, onClear: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier.height(34.dp).background(DeskTheme.colors.searchBg, RoundedCornerShape(8.dp)).padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, null, Modifier.size(14.dp), tint = DeskTheme.colors.textSecondary)
        Spacer(Modifier.width(8.dp))
        BasicTextField(
            value = query, onValueChange = onQueryChange,
            modifier = Modifier.weight(1f), singleLine = true,
            textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = DeskTheme.colors.text),
            decorationBox = { inner ->
                if (query.isEmpty()) Text("Search...", fontSize = 14.sp, color = DeskTheme.colors.textSecondary)
                inner()
            }
        )
        if (query.isNotEmpty()) {
            Icon(Icons.Default.Close, null, Modifier.size(14.dp).clickable(onClick = onClear), tint = DeskTheme.colors.textSecondary)
        }
    }
}

// ============================================================
// DeskFieldLabel (internal)
// ============================================================

@Composable
internal fun DeskFieldLabel(label: String, required: Boolean = false) {
    Text(
        label + if (required) " *" else "",
        fontSize = 12.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.textSecondary,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}
