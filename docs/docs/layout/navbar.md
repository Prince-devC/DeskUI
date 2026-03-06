---
sidebar_position: 2
---

# DeskNavBar

Top navigation bar combining logo, search bar, notification bell with badge, and user avatar. The primary navigation element.

## Import

```kotlin
import com.deskui.layout.DeskNavBar
```

## Usage

```kotlin
DeskNavBar(
    logo = { Image(painterResource(R.drawable.logo), null, Modifier.height(28.dp)) },
    searchQuery = query,
    onSearchChange = { query = it },
    onSearchClear = { query = "" },
    unreadCount = notifications.count { !it.isRead },
    onNotificationClick = { showNotifications = true },
    userInitials = "FY",
    onAvatarClick = { showProfile = true }
)
```

### Minimal

```kotlin
DeskNavBar(
    logo = { Text("MyApp", fontWeight = FontWeight.Bold) }
)
```

## Structure

```
┌──────┬─────────────────────┬──┬────┐
│ Logo │  🔍 Search...       │🔔│ AB │
└──────┴─────────────────────┴──┴────┘
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `logo` | `@Composable () -> Unit` | — | Logo slot (Image, Text, etc.) |
| `searchQuery` | `String` | `""` | Current search text |
| `onSearchChange` | `(String) -> Unit` | `{}` | Search text change |
| `onSearchClear` | `() -> Unit` | `{}` | Clear search |
| `unreadCount` | `Int` | `0` | Notification badge count |
| `onNotificationClick` | `() -> Unit` | `{}` | Bell click handler |
| `userInitials` | `String` | `""` | Avatar initials |
| `onAvatarClick` | `() -> Unit` | `{}` | Avatar click handler |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
