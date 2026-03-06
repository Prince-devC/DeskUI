---
sidebar_position: 5
---

# DeskSelect

Dropdown picker with label support. Opens a material dropdown menu on tap.

## Import

```kotlin
import com.deskui.components.DeskSelect
```

## Usage

```kotlin
var status by remember { mutableStateOf("") }

DeskSelect(
    value = status,
    onValueChange = { status = it },
    options = listOf("Draft", "Submitted", "Cancelled"),
    label = "Status"
)
```

### Required

```kotlin
DeskSelect(
    value = category,
    onValueChange = { category = it },
    options = categories,
    label = "Category",
    required = true
)
```

### Custom Empty Label

```kotlin
DeskSelect(
    value = selected,
    onValueChange = { selected = it },
    options = items,
    emptyLabel = "Choose an option..."
)
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `value` | `String` | — | Currently selected value |
| `onValueChange` | `(String) -> Unit` | — | Selection callback |
| `options` | `List<String>` | — | Available options |
| `label` | `String?` | `null` | Field label |
| `required` | `Boolean` | `false` | Shows asterisk |
| `emptyLabel` | `String` | `"Select..."` | Placeholder when empty |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
