---
sidebar_position: 6
---

# DeskSidebar

Slide-out navigation panel with hierarchical items. Supports expand/collapse for items with children, active state highlighting, and indented sub-items.

## Import

```kotlin
import com.deskui.layout.DeskSidebar
import com.deskui.layout.DeskSidebarItem
```

## Usage

```kotlin
DeskSidebar(
    items = listOf(
        DeskSidebarItem("Home", Icons.Default.Home),
        DeskSidebarItem("Accounting", Icons.Default.AccountBalance, hasChildren = true, isExpanded = true),
        DeskSidebarItem("Chart of Accounts", Icons.Default.ListAlt, indent = true),
        DeskSidebarItem("Journal Entry", Icons.Default.Edit, indent = true),
        DeskSidebarItem("Buying", Icons.Default.ShoppingCart, hasChildren = true),
        DeskSidebarItem("Selling", Icons.Default.Store),
    ),
    activeLabel = "Accounting",
    onItemClick = { item -> navigate(item) },
    onClose = { showSidebar = false }
)
```

## DeskSidebarItem

```kotlin
data class DeskSidebarItem(
    val label: String,
    val icon: ImageVector,
    val type: String = "",
    val linkTo: String? = null,
    val hasChildren: Boolean = false,
    val isExpanded: Boolean = false,
    val indent: Boolean = false
)
```

| Field | Type | Description |
|---|---|---|
| `label` | `String` | Display name |
| `icon` | `ImageVector` | Material icon |
| `type` | `String` | Item type (for routing logic) |
| `linkTo` | `String?` | Navigation target |
| `hasChildren` | `Boolean` | Shows expand/collapse chevron |
| `isExpanded` | `Boolean` | Current expand state |
| `indent` | `Boolean` | Indents item (for sub-items) |

## Visual Spec

- Active item: `DeskColors.primary` background with `DeskShapes.sidebar` radius
- Active text/icon: `DeskColors.onPrimary`
- Inactive text: `DeskColors.text`
- Inactive icon: `DeskColors.textSecondary`
- Expand indicator: `KeyboardArrowUp` / `KeyboardArrowDown`

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `items` | `List<DeskSidebarItem>` | — | Navigation items |
| `activeLabel` | `String?` | — | Currently active item label |
| `onItemClick` | `(DeskSidebarItem) -> Unit` | — | Item click handler |
| `onClose` | `() -> Unit` | — | Close sidebar handler |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
