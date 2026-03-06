---
sidebar_position: 2
---

# DeskCard

Grid card with icon and label. Fixed height for uniform grid layouts. Used for module navigation, shortcuts, and workspace items.

## Import

```kotlin
import com.deskui.components.DeskCard
```

## Usage

```kotlin
DeskCard(
    icon = Icons.Default.Inventory,
    label = "Item",
    onClick = { navigateTo("Item") }
)
```

### In a Grid

```kotlin
LazyVerticalGrid(
    columns = GridCells.Fixed(3),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
) {
    items(modules) { module ->
        DeskCard(
            icon = module.icon,
            label = module.name,
            onClick = { navigate(module) }
        )
    }
}
```

## Visual Spec

- Height: `DeskSizes.cardHeight` (110dp)
- Icon container: `DeskSizes.iconBox` (48dp) with `DeskColors.iconBg` fill
- Corner radius: `DeskShapes.card` (12dp)
- Border: 1dp `DeskColors.border`
- Label: 11sp, medium weight, 2-line max, centered

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `icon` | `ImageVector` | — | Material icon displayed in the icon box |
| `label` | `String` | — | Card label text |
| `onClick` | `() -> Unit` | — | Click handler |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |

## SwiftUI

```swift
DeskCard(icon: "shippingbox", label: "Item") {
    navigateTo("Item")
}
```
