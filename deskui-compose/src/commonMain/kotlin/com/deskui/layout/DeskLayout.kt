package com.deskui.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deskui.components.DeskAvatar
import com.deskui.components.DeskNotificationBadge
import com.deskui.components.DeskSearchBar
import com.deskui.theme.DeskTheme

// ============================================================
// DeskNavBar
// ============================================================
// Top navigation bar with logo slot, search bar, notification bell,
// and user avatar. Matches the Frappe desk navbar pattern.
//
// Usage:
//   DeskNavBar(
//       logo = { Image(painterResource(R.drawable.logo), null, Modifier.height(28.dp)) },
//       searchQuery = query, onSearchChange = { query = it },
//       unreadCount = 3, onNotificationClick = { showNotifs = true },
//       userInitials = "AB"
//   )
// ============================================================

@Composable
fun DeskNavBar(
    logo: @Composable () -> Unit,
    searchQuery: String = "",
    onSearchChange: (String) -> Unit = {},
    onSearchClear: () -> Unit = {},
    unreadCount: Int = 0,
    onNotificationClick: () -> Unit = {},
    userInitials: String = "",
    onAvatarClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier.fillMaxWidth().background(DeskTheme.colors.surface)
                .padding(horizontal = DeskTheme.spacing.lg, vertical = DeskTheme.spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(DeskTheme.spacing.md)
        ) {
            logo()
            DeskSearchBar(searchQuery, onSearchChange, onSearchClear, Modifier.weight(1f))
            Box(Modifier.clickable(onClick = onNotificationClick)) {
                Icon(Icons.Default.Notifications, "Notifications", Modifier.size(20.dp), tint = DeskTheme.colors.text)
                DeskNotificationBadge(unreadCount, Modifier.align(Alignment.TopEnd).offset(x = 6.dp, y = (-6).dp))
            }
            DeskAvatar(userInitials, Modifier.clickable(onClick = onAvatarClick))
        }
        HorizontalDivider(color = DeskTheme.colors.border)
    }
}

// ============================================================
// DeskBottomBar
// ============================================================
// Bottom tab bar with icon + label tabs and optional badge counts.
//
// Usage:
//   DeskBottomBar(
//       tabs = listOf(DeskTab("Home", Icons.Default.Home), DeskTab("Pending", Icons.Default.Schedule)),
//       selected = 0, badges = mapOf(1 to 5), onSelect = { tab = it }
//   )
// ============================================================

data class DeskTab(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun DeskBottomBar(
    tabs: List<DeskTab>,
    selected: Int,
    badges: Map<Int, Int> = emptyMap(),
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        HorizontalDivider(color = DeskTheme.colors.border)
        Row(
            modifier.fillMaxWidth().background(DeskTheme.colors.surface).padding(horizontal = 40.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            tabs.forEachIndexed { i, tab ->
                Column(Modifier.clickable { onSelect(i) }, horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        Icon(tab.icon, tab.label, Modifier.size(22.dp),
                            tint = if (selected == i) DeskTheme.colors.primary else DeskTheme.colors.textSecondary)
                        badges[i]?.let { badge ->
                            if (badge > 0) DeskNotificationBadge(badge, Modifier.align(Alignment.TopEnd).offset(x = 8.dp, y = (-4).dp))
                        }
                    }
                    Text(tab.label, fontSize = 11.sp, color = if (selected == i) DeskTheme.colors.primary else DeskTheme.colors.textSecondary)
                }
            }
        }
    }
}

// ============================================================
// DeskTabBar
// ============================================================
// Horizontal scrollable tab bar for form sections.
//
// Usage:
//   DeskTabBar(tabs = listOf("Details", "Accounting", "More Info"), selected = 0, onSelect = { })
// ============================================================

@Composable
fun DeskTabBar(tabs: List<String>, selected: Int, onSelect: (Int) -> Unit, modifier: Modifier = Modifier) {
    if (tabs.size > 1) {
        ScrollableTabRow(selected, modifier, containerColor = DeskTheme.colors.surface, edgePadding = 0.dp) {
            tabs.forEachIndexed { i, label ->
                Tab(selected == i, onClick = { onSelect(i) }) {
                    Text(
                        label.ifEmpty { "Details" },
                        Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        fontSize = 13.sp,
                        fontWeight = if (selected == i) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (selected == i) DeskTheme.colors.primary else DeskTheme.colors.textSecondary
                    )
                }
            }
        }
        HorizontalDivider(color = DeskTheme.colors.border)
    }
}

// ============================================================
// DeskSidebar
// ============================================================
// Slide-out navigation panel with hierarchical items.
// Supports expand/collapse for items with children.
//
// Usage:
//   DeskSidebar(
//       items = listOf(DeskSidebarItem("Home", Icons.Default.Home), ...),
//       activeLabel = "Accounting", onItemClick = { }, onClose = { }
//   )
// ============================================================

data class DeskSidebarItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val type: String = "",
    val linkTo: String? = null,
    val hasChildren: Boolean = false,
    val isExpanded: Boolean = false,
    val indent: Boolean = false
)

@Composable
fun DeskSidebar(
    items: List<DeskSidebarItem>,
    activeLabel: String?,
    onItemClick: (DeskSidebarItem) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxHeight().background(DeskTheme.colors.surface).padding(vertical = DeskTheme.spacing.lg)) {
        Row(
            Modifier.fillMaxWidth().padding(horizontal = DeskTheme.spacing.lg, vertical = DeskTheme.spacing.sm),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            Text("PUBLIC", fontSize = 11.sp, fontWeight = FontWeight.SemiBold,
                color = DeskTheme.colors.textSecondary, letterSpacing = 0.5.sp)
            Icon(Icons.Default.Close, "Close", Modifier.size(16.dp).clickable(onClick = onClose), tint = DeskTheme.colors.textSecondary)
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            items.forEach { item ->
                val isActive = item.label == activeLabel
                Row(
                    Modifier.fillMaxWidth()
                        .padding(horizontal = if (isActive) 8.dp else 0.dp)
                        .background(
                            if (isActive) DeskTheme.colors.primary else Color.Transparent,
                            RoundedCornerShape(if (isActive) DeskTheme.shapes.sidebar else 0.dp)
                        )
                        .clickable { onItemClick(item) }
                        .padding(
                            start = if (item.indent) 36.dp else DeskTheme.spacing.lg,
                            end = DeskTheme.spacing.lg,
                            top = 10.dp, bottom = 10.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(item.icon, null, Modifier.size(18.dp),
                        tint = if (isActive) DeskTheme.colors.onPrimary else DeskTheme.colors.textSecondary)
                    Spacer(Modifier.width(10.dp))
                    Text(item.label, fontSize = 15.sp,
                        color = if (isActive) DeskTheme.colors.onPrimary else DeskTheme.colors.text,
                        modifier = Modifier.weight(1f))
                    if (item.hasChildren) {
                        Icon(
                            if (item.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            null, Modifier.size(16.dp),
                            tint = if (isActive) DeskTheme.colors.onPrimary else DeskTheme.colors.textSecondary
                        )
                    }
                }
            }
        }
    }
}

// ============================================================
// DeskScaffold
// ============================================================
// Full app shell combining navbar, content area, and bottom bar.
//
// Usage:
//   DeskScaffold(
//       navBar = { DeskNavBar(...) },
//       bottomBar = { DeskBottomBar(...) }
//   ) { content() }
// ============================================================

@Composable
fun DeskScaffold(
    navBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier.fillMaxSize().background(DeskTheme.colors.background)) {
        navBar()
        Box(Modifier.weight(1f)) { content() }
        bottomBar()
    }
}

// ============================================================
// DeskPageHeader
// ============================================================
// Page-level header with back button, title, and action slot.
//
// Usage:
//   DeskPageHeader(title = "Sales Invoice", onBack = { goBack() }) {
//       DeskButton("Save", onClick = { save() })
//   }
// ============================================================

@Composable
fun DeskPageHeader(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column {
        Row(
            modifier.fillMaxWidth().background(DeskTheme.colors.surface)
                .padding(horizontal = DeskTheme.spacing.lg, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, "Back",
                Modifier.size(20.dp).clickable(onClick = onBack),
                tint = DeskTheme.colors.primary
            )
            Spacer(Modifier.width(DeskTheme.spacing.sm))
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DeskTheme.colors.text, modifier = Modifier.weight(1f))
            actions()
        }
        HorizontalDivider(color = DeskTheme.colors.border)
    }
}
