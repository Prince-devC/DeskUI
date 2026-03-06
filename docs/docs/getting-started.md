---
sidebar_position: 1
slug: /getting-started
---

# Getting Started

DeskUI is a professional UI component library for business and enterprise mobile applications. It provides a complete design system with components optimized for ERP, CRM, accounting, and data-heavy apps.

## Installation

### Android (Jetpack Compose)

Add the dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("com.deskui:deskui-compose:0.1.0")
}
```

### iOS (SwiftUI)

Add the Swift Package dependency:

```swift
// Package.swift
dependencies: [
    .package(url: "https://github.com/Prince-devC/DeskUI", from: "0.1.0")
]
```

## Quick Start

Wrap your app in `DeskTheme` and use `DeskScaffold` for the app shell:

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
                    tabs = listOf(
                        DeskTab("Home", Icons.Default.Home),
                        DeskTab("Pending", Icons.Default.Schedule)
                    ),
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

## Design Principles

- **Professional & Neutral** — Clean lines, subtle borders, muted palette. No playful colors.
- **Data-Dense** — Compact typography and spacing for screens that show lots of information.
- **Business-First** — Components designed for real business workflows: status tracking, filtering, form entry, data tables.
- **Cross-Platform** — Same design language on Android (Compose) and iOS (SwiftUI). Native performance.
- **Configurable** — Every color, size, shape, and spacing value is customizable through `DeskTheme`.

## Architecture

```
DeskUI
├── theme/          DeskTheme, DeskColors, DeskTypography, DeskShapes, DeskSpacing, DeskSizes
├── components/     DeskStatusBadge, DeskCard, DeskListItem, DeskTextField, DeskSelect, ...
└── layout/         DeskScaffold, DeskNavBar, DeskBottomBar, DeskTabBar, DeskSidebar, DeskPageHeader
```

All components read their styling from `DeskTheme` via `CompositionLocal`. Change the theme once, and every component adapts.
