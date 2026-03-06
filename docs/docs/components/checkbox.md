---
sidebar_position: 6
---

# DeskCheckbox

Toggle switch with label. Uses Material Switch internally, styled with theme colors.

## Import

```kotlin
import com.deskui.components.DeskCheckbox
```

## Usage

```kotlin
var isActive by remember { mutableStateOf(false) }

DeskCheckbox(
    label = "Is Active",
    checked = isActive,
    onCheckedChange = { isActive = it }
)
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `label` | `String` | — | Label text |
| `checked` | `Boolean` | — | Current state |
| `onCheckedChange` | `(Boolean) -> Unit` | — | State change callback |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
