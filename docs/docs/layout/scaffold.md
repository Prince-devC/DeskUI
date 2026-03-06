---
sidebar_position: 1
---

# DeskScaffold

Full app shell combining navbar, content area, and bottom bar. The top-level layout component for DeskUI apps.

## Import

```kotlin
import com.deskui.layout.DeskScaffold
```

## Usage

```kotlin
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
            selected = currentTab,
            onSelect = { currentTab = it }
        )
    }
) {
    // Page content fills remaining space
    when (currentTab) {
        0 -> HomeContent()
        1 -> PendingContent()
    }
}
```

## Structure

```
┌─────────────────────────┐
│       DeskNavBar         │  ← navBar slot
├─────────────────────────┤
│                         │
│       Content           │  ← content slot (weight 1f)
│                         │
├─────────────────────────┤
│     DeskBottomBar       │  ← bottomBar slot
└─────────────────────────┘
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `navBar` | `@Composable () -> Unit` | — | Top navigation bar |
| `bottomBar` | `@Composable () -> Unit` | `{}` | Bottom tab bar (optional) |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
| `content` | `@Composable () -> Unit` | — | Main content area |
