---
sidebar_position: 5
---

# DeskPageHeader

Page-level header with back navigation, title, and action slot. Used at the top of list views, form views, and report views.

## Import

```kotlin
import com.deskui.layout.DeskPageHeader
```

## Usage

### Basic

```kotlin
DeskPageHeader(
    title = "Sales Invoice",
    onBack = { navigateBack() }
)
```

### With Actions

```kotlin
DeskPageHeader(title = "Sales Invoice", onBack = { goBack() }) {
    Text(
        "Save", fontSize = 14.sp, color = Color.White,
        modifier = Modifier
            .background(DeskTheme.colors.primary, RoundedCornerShape(6.dp))
            .clickable { save() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
```

## Structure

```
┌────┬──────────────────────┬─────────┐
│ ←  │  Sales Invoice       │  [Save] │
└────┴──────────────────────┴─────────┘
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `title` | `String` | — | Page title |
| `onBack` | `() -> Unit` | — | Back navigation handler |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
| `actions` | `@Composable RowScope.() -> Unit` | `{}` | Action buttons slot |
