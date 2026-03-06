---
sidebar_position: 4
---

# DeskTextField

Outlined text input with optional label, placeholder, validation indicator, and keyboard type support.

## Import

```kotlin
import com.deskui.components.DeskTextField
```

## Usage

### Basic

```kotlin
var name by remember { mutableStateOf("") }

DeskTextField(
    value = name,
    onValueChange = { name = it },
    label = "Customer Name"
)
```

### Required Field

```kotlin
DeskTextField(
    value = email,
    onValueChange = { email = it },
    label = "Email Address",
    required = true,
    placeholder = "user@example.com"
)
```

### Numeric Input

```kotlin
DeskTextField(
    value = amount,
    onValueChange = { amount = it },
    label = "Grand Total",
    keyboardType = KeyboardType.Decimal
)
```

### Password

```kotlin
DeskTextField(
    value = password,
    onValueChange = { password = it },
    label = "Password",
    isPassword = true
)
```

### Multi-line

```kotlin
DeskTextField(
    value = notes,
    onValueChange = { notes = it },
    label = "Notes",
    singleLine = false,
    minLines = 3
)
```

### Read-only

```kotlin
DeskTextField(
    value = "Auto-generated",
    onValueChange = { },
    label = "Document ID",
    readOnly = true
)
```

## Props

| Prop | Type | Default | Description |
|---|---|---|---|
| `value` | `String` | — | Current text value |
| `onValueChange` | `(String) -> Unit` | — | Value change callback |
| `label` | `String?` | `null` | Field label above input |
| `placeholder` | `String` | `""` | Placeholder text |
| `required` | `Boolean` | `false` | Shows asterisk after label |
| `keyboardType` | `KeyboardType` | `Text` | Keyboard type |
| `isPassword` | `Boolean` | `false` | Masks input |
| `singleLine` | `Boolean` | `true` | Single or multi-line |
| `minLines` | `Int` | `1` | Minimum lines (multi-line) |
| `readOnly` | `Boolean` | `false` | Disables editing |
| `modifier` | `Modifier` | `Modifier` | Compose modifier |
