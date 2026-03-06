---
sidebar_position: 8
---

# DeskFilterChip

Removable filter tag showing active filter key-value pairs. Used in list views to display active filters.

## Import

```kotlin
import com.deskui.components.DeskFilterChip
```

## Usage

```kotlin
DeskFilterChip(
    label = "Status",
    value = "Paid",
    onRemove = { removeFilter("Status") }
)
```

### Multiple Filters

```kotlin
Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
    activeFilters.forEach { (key, value) ->
        DeskFilterChip(
            label = key,
            value = value,
            onRemove = { activeFilters = activeFilters - key; reload() }
        )
    }
}
```

## Visual Spec

- Background: `DeskColors.primary` at 10% opacity
- Corner radius: `DeskShapes.chip` (12dp, pill shape)
- Text: 11sp, primary color
- Close icon: Material `Close` icon, 12dp

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `label` | `String` | — | Filter field name |
| `value` | `String` | — | Filter value |
| `onRemove` | `() -> Unit` | — | Remove callback |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
