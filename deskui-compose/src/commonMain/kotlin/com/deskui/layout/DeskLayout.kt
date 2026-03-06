package com.deskui.layout

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deskui.components.DeskAvatar
import com.deskui.components.DeskNotificationBadge
import com.deskui.components.DeskSearchBar
import com.deskui.theme.DeskTheme

// === NavBar ===
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
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            logo()
            DeskSearchBar(searchQuery, onSearchChange, onSearchClear, Modifier.weight(1f))
            // Bell
            Box(Modifier.clickable(onClick = onNotificationClick)) {
                Text("🔔", fontSize = 16.sp)
                DeskNotificationBadge(unreadCount, Modifier.align(Alignment.TopEnd).offset(x = 6.dp, y = (-6).dp))
            }
            DeskAvatar(userInitials, Modifier.clickable(onClick = onAvatarClick))
        }
        HorizontalDivider(color = DeskTheme.colors.border)
    }
}

// === Bottom Bar ===
@Composable
fun DeskBottomBar(
    tabs: List<String>,
    icons: List<String> = listOf("🏠", "🕐"),
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
            tabs.forEachIndexed { i, label ->
                Column(Modifier.clickable { onSelect(i) }, horizontalAlignment = Alignment.CenterHorizontally) {
                    Box {
                        Text(icons.getOrElse(i) { "📋" }, fontSize = 20.sp)
                        badges[i]?.let { badge ->
                            if (badge > 0) DeskNotificationBadge(badge, Modifier.align(Alignment.TopEnd).offset(x = 8.dp, y = (-4).dp))
                        }
                    }
                    Text(label, fontSize = 11.sp, color = if (selected == i) DeskTheme.colors.primary else DeskTheme.colors.textSecondary)
                }
            }
        }
    }
}

// === Tab Bar (for forms) ===
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

// === Sidebar ===
@Composable
fun DeskSidebar(
    items: List<DeskSidebarItem>,
    activeLabel: String?,
    onItemClick: (DeskSidebarItem) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxHeight().background(DeskTheme.colors.surface).padding(vertical = 16.dp)
    ) {
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("PUBLIC", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = DeskTheme.colors.textSecondary, letterSpacing = 0.5.sp)
            Text("×", Modifier.clickable(onClick = onClose), fontSize = 14.sp, color = DeskTheme.colors.textSecondary)
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
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(item.icon, fontSize = 16.sp, modifier = Modifier.width(24.dp))
                    Spacer(Modifier.width(10.dp))
                    Text(item.label, fontSize = 15.sp, color = if (isActive) DeskTheme.colors.onPrimary else DeskTheme.colors.text,
                        modifier = Modifier.weight(1f))
                    if (item.hasChildren) {
                        Text(if (item.isExpanded) "▴" else "▾", fontSize = 12.sp,
                            color = if (isActive) DeskTheme.colors.onPrimary else DeskTheme.colors.textSecondary)
                    }
                }
            }
        }
    }
}

data class DeskSidebarItem(
    val label: String,
    val icon: String = "📋",
    val type: String = "",
    val linkTo: String? = null,
    val hasChildren: Boolean = false,
    val isExpanded: Boolean = false,
    val indent: Boolean = false
)

// === Scaffold ===
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

// === Page Header (for list/form/report views) ===
@Composable
fun DeskPageHeader(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Column {
        Row(
            modifier.fillMaxWidth().background(DeskTheme.colors.surface).padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("< ", Modifier.clickable(onClick = onBack), fontSize = 16.sp, fontWeight = FontWeight.Medium, color = DeskTheme.colors.primary)
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DeskTheme.colors.text, modifier = Modifier.weight(1f))
            actions()
        }
        HorizontalDivider(color = DeskTheme.colors.border)
    }
}
