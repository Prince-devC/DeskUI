---
sidebar_position: 3
---

# DeskListItem

Standard list row with title, subtitle, status badge, and navigation chevron. Designed for document lists.

## Import

```kotlin
import com.deskui.components.DeskListItem
```

## Usage

```kotlin
DeskListItem(
    title = "INV-00042",
    subtitle = "Customer Corp - 150,000",
    status = "Paid",
    onClick = { openInvoice("INV-00042") }
)
```

### In a List

```kotlin
LazyColumn {
    items(invoices) { inv ->
        DeskListItem(
            title = inv.name,
            subtitle = "${inv.customer} - ${inv.grandTotal}",
            status = inv.status,
            onClick = { openDoc(inv.doctype, inv.name) }
        )
        HorizontalDivider(color = DeskTheme.colors.border)
    }
}
```

### With Docstatus

```kotlin
DeskListItem(
    title = "PO-00015",
    docstatus = 1,  // Shows "Submitted" badge
    onClick = { }
)
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `title` | `String` | — | Primary text (document name) |
| `subtitle` | `String` | `""` | Secondary text (customer, amount, etc.) |
| `status` | `String` | `""` | Status string for badge |
| `docstatus` | `Int` | `-1` | Docstatus integer for badge |
| `onClick` | `() -> Unit` | — | Click handler |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |

## SwiftUI

```swift
DeskListItem(title: "INV-00042", subtitle: "Customer Corp", status: "Paid") {
    openInvoice("INV-00042")
}
```
