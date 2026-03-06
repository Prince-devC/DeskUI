---
sidebar_position: 7
---

# DeskButton

Primary action button. Full-width by default with theme primary color.

## Import

```kotlin
import com.deskui.components.DeskButton
```

## Usage

```kotlin
DeskButton(text = "Save", onClick = { save() })
```

### Disabled State

```kotlin
DeskButton(
    text = "Saving...",
    onClick = { },
    enabled = false
)
```

### Custom Width

```kotlin
DeskButton(
    text = "Submit",
    onClick = { submit() },
    modifier = Modifier.width(200.dp)
)
```

## Visual Spec

- Height: padding-based (10dp vertical)
- Corner radius: `DeskShapes.button` (6dp)
- Background: `DeskColors.primary` (50% opacity when disabled)
- Text: 14sp, medium weight, white

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `text` | `String` | — | Button label |
| `onClick` | `() -> Unit` | — | Click handler |
| `enabled` | `Boolean` | `true` | Enabled state |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |

## SwiftUI

```swift
DeskButton("Save") { save() }
DeskButton("Saving...", enabled: false) { }
```
