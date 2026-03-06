---
sidebar_position: 2
slug: /theme
---

# Theme

DeskUI uses a composable theme system built on `CompositionLocal`. Every visual property — colors, typography, shapes, spacing, and sizes — is configurable.

## Usage

Wrap your app in `DeskTheme`:

```kotlin
DeskTheme {
    // All DeskUI components inside here will use the theme
}
```

### Custom Theme

```kotlin
DeskTheme(
    colors = DeskColors(primary = Color(0xFF2563EB)),
    shapes = DeskShapes(card = 16.dp)
) {
    // Components now use blue primary and larger card radius
}
```

## Access Theme Values

Inside any composable:

```kotlin
val primary = DeskTheme.colors.primary
val bodyStyle = DeskTheme.typography.body
val cardRadius = DeskTheme.shapes.card
val spacing = DeskTheme.spacing.lg
```

---

## DeskColors

| Property | Default | Description |
|---|---|---|
| `primary` | `#1A1A7D` | Primary brand color |
| `onPrimary` | `#FFFFFF` | Text on primary |
| `background` | `#F5F5F7` | Page background |
| `surface` | `#FFFFFF` | Card/panel background |
| `text` | `#1F272E` | Primary text |
| `textSecondary` | `#6B7580` | Labels, captions, placeholders |
| `border` | `#E0E5E8` | Borders and dividers |
| `error` | `#E34D4D` | Error states, overdue |
| `success` | `#48BB78` | Positive states, paid |
| `warning` | `#ED8936` | Draft, pending |
| `iconBg` | `#E8EDFA` | Icon container fill |
| `searchBg` | `#F2F4F5` | Search bar background |

```kotlin
DeskColors(
    primary = Color(0xFF1A1A7D),
    onPrimary = Color.White,
    background = Color(0xFFF5F5F7),
    surface = Color.White,
    text = Color(0xFF1F272E),
    textSecondary = Color(0xFF6B7580),
    border = Color(0xFFE0E5E8),
    error = Color(0xFFE34D4D),
    success = Color(0xFF48BB78),
    warning = Color(0xFFED8936),
    iconBg = Color(0xFFE8EDFA),
    searchBg = Color(0xFFF2F4F5),
)
```

---

## DeskTypography

| Style | Size | Weight | Use Case |
|---|---|---|---|
| `h1` | 20sp | Bold | Page titles |
| `h2` | 18sp | SemiBold | Section titles |
| `h3` | 16sp | Medium | Subsection titles |
| `body` | 14sp | Normal | Body text, inputs |
| `bodySmall` | 13sp | Normal | Secondary content |
| `caption` | 12sp | Medium | Labels, field names |
| `label` | 11sp | SemiBold | Section headers, uppercase labels |
| `badge` | 9sp | SemiBold | Status badges |

---

## DeskShapes

| Property | Default | Description |
|---|---|---|
| `card` | `12dp` | Card corner radius |
| `button` | `6dp` | Button corner radius |
| `input` | `6dp` | Input field corner radius |
| `badge` | `4dp` | Status badge corner radius |
| `chip` | `12dp` | Filter chip corner radius (pill) |
| `sidebar` | `8dp` | Active sidebar item radius |
| `icon` | `12dp` | Icon container radius |

---

## DeskSpacing

| Property | Default | Description |
|---|---|---|
| `xs` | `4dp` | Minimal spacing |
| `sm` | `8dp` | Tight spacing |
| `md` | `12dp` | Default spacing |
| `lg` | `16dp` | Comfortable spacing |
| `xl` | `20dp` | Section spacing |
| `xxl` | `24dp` | Large section spacing |

---

## DeskSizes

| Property | Default | Description |
|---|---|---|
| `navBarHeight` | `56dp` | Navigation bar height |
| `iconBox` | `48dp` | Icon container size |
| `avatar` | `32dp` | Avatar circle diameter |
| `inputHeight` | `44dp` | Input field height |
| `cardHeight` | `110dp` | Grid card height |
| `badgeDot` | `8dp` | Unread indicator dot |
