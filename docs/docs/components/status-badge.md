---
sidebar_position: 1
---

# DeskStatusBadge

Color-coded status indicator for document states. Automatically resolves colors from status strings or docstatus integers.

## Import

```kotlin
import com.deskui.components.DeskStatusBadge
```

## Usage

### Automatic Color Resolution

```kotlin
DeskStatusBadge(status = "Paid")         // green
DeskStatusBadge(status = "Draft")        // orange
DeskStatusBadge(status = "Submitted")    // primary
DeskStatusBadge(status = "Cancelled")    // grey
DeskStatusBadge(status = "Overdue")      // red
```

### From Docstatus Integer

```kotlin
DeskStatusBadge(docstatus = 0)   // "Draft" — orange
DeskStatusBadge(docstatus = 1)   // "Submitted" — primary
DeskStatusBadge(docstatus = 2)   // "Cancelled" — grey
```

### Custom Label and Color

```kotlin
DeskStatusBadge(label = "Urgent", color = Color.Red)
DeskStatusBadge(label = "In Progress", color = Color(0xFF2563EB))
```

## Color Resolution Table

| Status | Color Token |
|---|---|
| Paid, Completed, Active | `success` (green) |
| Unpaid, Overdue, Expired | `error` (red) |
| Draft | `warning` (orange) |
| Submitted, Open | `primary` (blue) |
| Cancelled, Closed | `textSecondary` (grey) |

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `label` | `String` | — | Badge text |
| `color` | `Color` | — | Badge color (text + background at 12% opacity) |
| `status` | `String` | `""` | Auto-resolved status string |
| `docstatus` | `Int` | `-1` | Frappe docstatus integer (0/1/2) |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |

## SwiftUI

```swift
DeskStatusBadge(status: "Paid")
DeskStatusBadge(status: "Draft", docstatus: 0)
DeskStatusBadge(label: "Custom", color: .blue)
```
