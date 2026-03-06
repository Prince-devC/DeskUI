---
sidebar_position: 10
---

# DeskSectionHeader

Uppercase section label with divider. Used to separate groups in forms and link lists.

## Import

```kotlin
import com.deskui.components.DeskSectionHeader
```

## Usage

```kotlin
DeskSectionHeader("Account Details")

// Form fields here...

DeskSectionHeader("Payment Information")

// More fields...
```

## Visual Spec

- Text: 11sp, semibold, uppercase, `textSecondary` color
- Letter spacing: 0.5sp
- Divider below label
- Top padding: 16dp, bottom padding: 8dp

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `label` | `String` | — | Section label (auto-uppercased) |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |

:::note
Empty or blank labels render nothing. Safe to pass dynamic values.
:::
