---
sidebar_position: 9
---

# DeskSearchBar

Inline search input with search icon and clear button. Designed for embedding in navbars and list headers.

## Import

```kotlin
import com.deskui.components.DeskSearchBar
```

## Usage

```kotlin
var query by remember { mutableStateOf("") }

DeskSearchBar(
    query = query,
    onQueryChange = { query = it },
    onClear = { query = "" }
)
```

## Visual Spec

- Height: 34dp
- Background: `DeskColors.searchBg`
- Corner radius: 8dp
- Search icon: Material `Search`, 14dp
- Clear icon: Material `Close`, 14dp (visible when query is not empty)
- Placeholder: "Search..." in `textSecondary`

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `query` | `String` | — | Current search text |
| `onQueryChange` | `(String) -> Unit` | — | Text change callback |
| `onClear` | `() -> Unit` | — | Clear button callback |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |

## SwiftUI

```swift
@State var text = ""

DeskSearchBar(text: $text)
```
