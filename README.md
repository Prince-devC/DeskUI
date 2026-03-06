# DeskUI

A modern, professional UI component library for business/enterprise mobile apps. Built for Kotlin Multiplatform (Jetpack Compose) and SwiftUI.

**DeskUI replaces Material Design** with a clean, sober design system optimized for ERP, CRM, and business applications.

## Design Philosophy

- **Professional & Neutral** — No playful colors or rounded bubbles. Clean lines, subtle shadows, muted palette.
- **Business-First Components** — Status badges, filter chips, dynamic forms, data tables, sidebar navigation — all built-in.
- **Cross-Platform Native** — Compose for Android, SwiftUI for iOS. Same design language, native performance.
- **Configurable Theme** — Colors, typography, border radius, spacing — all customizable via `DeskTheme`.

## Components

### Layout
- `DeskNavBar` — Logo + search bar + notification bell + avatar menu
- `DeskSidebar` — Hierarchical navigation with expand/collapse, active highlight
- `DeskBottomBar` — Tab bar with badge support
- `DeskScaffold` — Full app shell combining navbar + content + bottom bar

### Data Display
- `DeskCard` — Icon card with label (grid-ready, 110pt height)
- `DeskListItem` — Row with title, subtitle, status badge, chevron
- `DeskStatusBadge` — Color-coded status (Draft/orange, Submitted/blue, Paid/green, etc.)
- `DeskDataTable` — Horizontal scrollable table with typed columns, alternating rows
- `DeskNumberCard` — KPI display with label + formatted value

### Forms
- `DeskTextField` — Outlined input with label, placeholder, validation
- `DeskTextArea` — Multi-line input
- `DeskSelect` — Dropdown picker
- `DeskLinkField` — Search-as-you-type with suggestions dropdown
- `DeskCheckbox` — Toggle with label
- `DeskDateField` — Date input
- `DeskFormSection` — Section header with divider
- `DeskTabBar` — Horizontal scrollable tabs for form sections

### Navigation
- `DeskFilterChip` — Removable filter tag
- `DeskSearchOverlay` — Global search with grouped results (types + documents)
- `DeskNotificationSheet` — Notification list with unread indicators

## Installation

### Android (Compose)
```kotlin
// build.gradle.kts
dependencies {
    implementation("com.deskui:deskui-compose:0.1.0")
}
```

### iOS (SwiftUI)
```swift
// Package.swift
.package(url: "https://github.com/deskui/deskui-swiftui", from: "0.1.0")
```

## Quick Start

```kotlin
DeskTheme(
    colors = DeskColors(
        primary = Color(0xFF1A1A7D),
        background = Color(0xFFF5F5F7),
        surface = Color.White,
        // ...
    )
) {
    DeskScaffold(
        navBar = { DeskNavBar(logo = painterResource(R.drawable.logo), onSearch = { }) },
        bottomBar = { DeskBottomBar(tabs = listOf("Accueil", "En attente"), selected = 0) }
    ) {
        DeskCard(icon = "📦", label = "Item", onClick = { })
    }
}
```

## License

Apache 2.0
