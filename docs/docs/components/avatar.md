---
sidebar_position: 11
---

# DeskAvatar

Circular avatar showing user initials. Uses primary color at 15% opacity as background.

## Import

```kotlin
import com.deskui.components.DeskAvatar
```

## Usage

```kotlin
DeskAvatar(initials = "AB")
DeskAvatar(initials = "")  // Shows "?"
```

## Visual Spec

- Size: `DeskSizes.avatar` (32dp)
- Shape: Circle
- Background: `DeskColors.primary` at 15% opacity
- Text: 13sp, semibold, `DeskColors.primary`

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `initials` | `String` | — | 1-2 character initials (shows "?" if empty) |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
