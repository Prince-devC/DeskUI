---
sidebar_position: 4
---

# DeskTabBar

Horizontal scrollable tab bar for form sections. Highlights the active tab with primary color.

## Import

```kotlin
import com.deskui.layout.DeskTabBar
```

## Usage

```kotlin
var selectedSection by remember { mutableStateOf(0) }

DeskTabBar(
    tabs = listOf("Details", "Accounting", "More Info"),
    selected = selectedSection,
    onSelect = { selectedSection = it }
)
```

:::note
`DeskTabBar` renders nothing when there is only one tab. This is intentional — single-section forms don't need a tab bar.
:::

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `tabs` | `List<String>` | — | Tab labels |
| `selected` | `Int` | — | Currently selected index |
| `onSelect` | `(Int) -> Unit` | — | Tab selection callback |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
