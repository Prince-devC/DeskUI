---
sidebar_position: 12
---

# DeskIconBox

Rounded square container for icons. Used inside `DeskCard` and anywhere an icon needs a background container.

## Import

```kotlin
import com.deskui.components.DeskIconBox
```

## Usage

```kotlin
DeskIconBox(icon = Icons.Default.ShoppingCart)
DeskIconBox(icon = Icons.Default.AccountBalance)
```

## Visual Spec

- Size: `DeskSizes.iconBox` (48dp)
- Background: `DeskColors.iconBg`
- Corner radius: `DeskShapes.icon` (12dp)
- Icon size: 22dp, tinted with `DeskColors.primary`

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `icon` | `ImageVector` | — | Material icon to display |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
