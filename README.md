# DeskUI

A modern, professional UI component library for business and enterprise mobile applications.

Built for **Kotlin Multiplatform** (Jetpack Compose) and **SwiftUI**.

> DeskUI is not another Material Design wrapper. It is a purpose-built design system for ERP, CRM, accounting, and business applications — where clarity, density, and professionalism matter more than playfulness.

---

## Why DeskUI

| Material Design | DeskUI |
|---|---|
| Consumer-oriented, playful | Business-oriented, professional |
| Generic components | Domain-specific: status badges, filter chips, data tables, form renderers |
| One-size-fits-all | Optimized for data-dense screens |
| Looks like "Android" on iOS | Native feel on both platforms |

---

## Installation

### Android (Jetpack Compose)

```kotlin
// build.gradle.kts
dependencies {
    implementation("com.deskui:deskui-compose:0.1.0")
}
```

### iOS (SwiftUI)

```swift
// Package.swift
dependencies: [
    .package(url: "https://github.com/Prince-devC/DeskUI", from: "0.1.0")
]
```

---

## Quick Start

```kotlin
import com.deskui.theme.DeskTheme
import com.deskui.layout.*
import com.deskui.components.*

@Composable
fun App() {
    DeskTheme {
        DeskScaffold(
            navBar = {
                DeskNavBar(
                    logo = { Image(painterResource(R.drawable.logo), null, Modifier.height(28.dp)) },
                    searchQuery = query,
                    onSearchChange = { query = it },
                    unreadCount = 3,
                    userInitials = "AB"
                )
            },
            bottomBar = {
                DeskBottomBar(
                    tabs = listOf(DeskTab("Home", Icons.Default.Home), DeskTab("Pending", Icons.Default.Schedule)),
                    selected = 0,
                    onSelect = { selectedTab = it }
                )
            }
        ) {
            // Your content here
        }
    }
}
```

---

## Theme

DeskUI uses a composable theme system. Every color, size, shape, and spacing value is configurable.

### Colors

```kotlin
DeskTheme(
    colors = DeskColors(
        primary = Color(0xFF1A1A7D),      // Navy blue — professional, not playful
        background = Color(0xFFF5F5F7),    // Light grey canvas
        surface = Color.White,              // Card/panel background
        text = Color(0xFF1F272E),           // Primary text
        textSecondary = Color(0xFF6B7580),  // Labels, captions
        border = Color(0xFFE0E5E8),         // Subtle borders
        error = Color(0xFFE34D4D),          // Validation errors
        success = Color(0xFF48BB78),        // Positive states
        warning = Color(0xFFED8936),        // Draft, pending
        iconBg = Color(0xFFE8EDFA),         // Icon container fill
        searchBg = Color(0xFFF2F4F5),       // Search bar background
    )
)
```

### Typography

```kotlin
DeskTypography(
    h1 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    h2 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
    h3 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    body = TextStyle(fontSize = 14.sp),
    bodySmall = TextStyle(fontSize = 13.sp),
    caption = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
    label = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.SemiBold),
    badge = TextStyle(fontSize = 9.sp, fontWeight = FontWeight.SemiBold),
)
```

### Shapes

```kotlin
DeskShapes(
    card = 12.dp,     // Card corner radius
    button = 6.dp,    // Button corner radius
    input = 6.dp,     // Input field corner radius
    badge = 4.dp,     // Status badge corner radius
    chip = 12.dp,     // Filter chip corner radius (pill)
    sidebar = 8.dp,   // Active sidebar item radius
    icon = 12.dp,     // Icon container radius
)
```

---

## Components

### DeskStatusBadge

Color-coded status indicator for document states.

```kotlin
// Automatic color resolution
DeskStatusBadge(status = "Paid")        // green
DeskStatusBadge(status = "Draft")       // orange
DeskStatusBadge(status = "Cancelled")   // grey
DeskStatusBadge(docstatus = 1)          // "Submitted" in primary

// Custom
DeskStatusBadge(label = "Urgent", color = Color.Red)
```

**Resolved colors:**

| Status | Color |
|---|---|
| Paid, Completed, Active | `success` (green) |
| Unpaid, Overdue, Expired | `error` (red) |
| Draft | `warning` (orange) |
| Submitted, Open | `primary` (blue) |
| Cancelled, Closed | `textSecondary` (grey) |

---

### DeskCard

Grid card with icon and label. Fixed height for uniform layouts.

```kotlin
LazyVerticalGrid(columns = GridCells.Fixed(3)) {
    items(modules) { module ->
        DeskCard(
            icon = Icons.Default.Inventory,
            label = module.name,
            onClick = { navigate(module) }
        )
    }
}
```

---

### DeskListItem

Standard list row with title, subtitle, status badge, and navigation chevron.

```kotlin
LazyColumn {
    items(invoices) { inv ->
        DeskListItem(
            title = inv.name,
            subtitle = "${inv.customer} - ${inv.total}",
            status = inv.status,
            onClick = { openInvoice(inv) }
        )
        HorizontalDivider(color = DeskTheme.colors.border)
    }
}
```

---

### DeskTextField

Outlined text input with label, placeholder, validation indicator, and keyboard type support.

```kotlin
DeskTextField(
    value = name,
    onValueChange = { name = it },
    label = "Customer Name",
    required = true
)

DeskTextField(
    value = amount,
    onValueChange = { amount = it },
    label = "Grand Total",
    keyboardType = KeyboardType.Decimal
)

DeskTextField(
    value = notes,
    onValueChange = { notes = it },
    label = "Notes",
    singleLine = false,
    minLines = 3
)
```

---

### DeskSelect

Dropdown picker with label.

```kotlin
DeskSelect(
    value = status,
    onValueChange = { status = it },
    options = listOf("Draft", "Submitted", "Cancelled"),
    label = "Status"
)
```

---

### DeskFilterChip

Removable filter tag showing active filter key-value pairs.

```kotlin
Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
    activeFilters.forEach { (key, value) ->
        DeskFilterChip(label = key, value = value, onRemove = { removeFilter(key) })
    }
}
```

---

### DeskSearchBar

Inline search input with icon and clear button.

```kotlin
DeskSearchBar(
    query = searchText,
    onQueryChange = { searchText = it },
    onClear = { searchText = "" }
)
```

---

## Layout

### DeskNavBar

Top navigation bar combining logo, search, notifications, and user avatar.

```kotlin
DeskNavBar(
    logo = { Image(painterResource(R.drawable.logo), null, Modifier.height(28.dp)) },
    searchQuery = query,
    onSearchChange = { query = it },
    onSearchClear = { query = "" },
    unreadCount = notifications.count { !it.isRead },
    onNotificationClick = { showNotifications = true },
    userInitials = "FY",
    onAvatarClick = { showProfile = true }
)
```

### DeskBottomBar

Bottom tab bar with icons and optional badge counts.

```kotlin
DeskBottomBar(
    tabs = listOf(
        DeskTab("Home", Icons.Default.Home),
        DeskTab("Pending", Icons.Default.Schedule)
    ),
    selected = currentTab,
    badges = mapOf(1 to pendingCount),
    onSelect = { currentTab = it }
)
```

### DeskTabBar

Horizontal scrollable tabs for form sections.

```kotlin
DeskTabBar(
    tabs = listOf("Details", "Accounting", "More Info"),
    selected = selectedSection,
    onSelect = { selectedSection = it }
)
```

### DeskSidebar

Slide-out navigation with hierarchical items.

```kotlin
DeskSidebar(
    items = listOf(
        DeskSidebarItem("Home", Icons.Default.Home),
        DeskSidebarItem("Accounting", Icons.Default.AccountBalance, hasChildren = true),
        DeskSidebarItem("Buying", Icons.Default.ShoppingCart),
    ),
    activeLabel = "Accounting",
    onItemClick = { navigate(it) },
    onClose = { showSidebar = false }
)
```

### DeskPageHeader

Page-level header with back navigation and action slot.

```kotlin
DeskPageHeader(title = "Sales Invoice", onBack = { goBack() }) {
    DeskButton("Save", onClick = { save() })
}
```

### DeskScaffold

Full app shell combining all layout components.

```kotlin
DeskScaffold(
    navBar = { DeskNavBar(...) },
    bottomBar = { DeskBottomBar(...) }
) {
    // Page content
}
```

---

## Roadmap

- [ ] DeskDataTable — Horizontal scrollable data table with typed columns
- [ ] DeskLinkField — Search-as-you-type with API-driven suggestions
- [ ] DeskDatePicker — Native date picker integration
- [ ] DeskFileUpload — Attach field with preview
- [ ] DeskNumberCard — KPI display card
- [ ] DeskNotificationSheet — Full notification panel
- [ ] DeskFormRenderer — Dynamic form builder from metadata
- [ ] Compose Multiplatform support (Desktop, Web)
- [ ] Figma design tokens export

---

## License

Apache 2.0

---

Built with precision. No emojis. No gimmicks. Just clean, professional UI.
