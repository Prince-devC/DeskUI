---
sidebar_position: 3
---

# DeskBottomBar

Bottom tab bar with icons and optional badge counts. Supports any number of tabs.

## Import

```kotlin
import com.deskui.layout.DeskBottomBar
import com.deskui.layout.DeskTab
```

## Usage

```kotlin
DeskBottomBar(
    tabs = listOf(
        DeskTab("Home", Icons.Default.Home),
        DeskTab("Pending", Icons.Default.Schedule),
        DeskTab("Settings", Icons.Default.Settings)
    ),
    selected = currentTab,
    onSelect = { currentTab = it }
)
```

### With Badges

```kotlin
DeskBottomBar(
    tabs = listOf(
        DeskTab("Home", Icons.Default.Home),
        DeskTab("Pending", Icons.Default.Schedule)
    ),
    selected = currentTab,
    badges = mapOf(1 to pendingCount),  // Badge on "Pending" tab
    onSelect = { currentTab = it }
)
```

## DeskTab

Data class for tab definition:

```kotlin
data class DeskTab(val label: String, val icon: ImageVector)
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `tabs` | `List<DeskTab>` | — | Tab definitions |
| `selected` | `Int` | — | Currently selected index |
| `badges` | `Map<Int, Int>` | `emptyMap()` | Badge counts by tab index |
| `onSelect` | `(Int) -> Unit` | — | Tab selection callback |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
