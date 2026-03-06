// DeskUI for SwiftUI
// A professional UI component library for business/enterprise apps

import SwiftUI

// MARK: - Theme

public struct DeskColors {
    public var primary: Color
    public var onPrimary: Color
    public var background: Color
    public var surface: Color
    public var text: Color
    public var textSecondary: Color
    public var border: Color
    public var error: Color
    public var success: Color
    public var warning: Color
    public var iconBg: Color
    public var searchBg: Color

    public init(
        primary: Color = Color(red: 0.10, green: 0.10, blue: 0.49),
        onPrimary: Color = .white,
        background: Color = Color(red: 0.96, green: 0.96, blue: 0.97),
        surface: Color = .white,
        text: Color = Color(red: 0.12, green: 0.15, blue: 0.18),
        textSecondary: Color = Color(red: 0.42, green: 0.46, blue: 0.50),
        border: Color = Color(red: 0.88, green: 0.90, blue: 0.92),
        error: Color = Color(red: 0.89, green: 0.30, blue: 0.30),
        success: Color = Color(red: 0.28, green: 0.73, blue: 0.47),
        warning: Color = Color(red: 0.93, green: 0.54, blue: 0.21),
        iconBg: Color = Color(red: 0.91, green: 0.93, blue: 0.96),
        searchBg: Color = Color(red: 0.95, green: 0.96, blue: 0.97)
    ) {
        self.primary = primary; self.onPrimary = onPrimary; self.background = background
        self.surface = surface; self.text = text; self.textSecondary = textSecondary
        self.border = border; self.error = error; self.success = success
        self.warning = warning; self.iconBg = iconBg; self.searchBg = searchBg
    }
}

public struct DeskSizes {
    public var navBarHeight: CGFloat = 56
    public var iconBox: CGFloat = 48
    public var avatar: CGFloat = 32
    public var inputHeight: CGFloat = 44
    public var cardHeight: CGFloat = 110
    public var cornerRadius: CGFloat = 12
    public var inputRadius: CGFloat = 6
    public var badgeRadius: CGFloat = 4
    public init() {}
}

public class DeskThemeManager: ObservableObject {
    @Published public var colors = DeskColors()
    @Published public var sizes = DeskSizes()
    public static let shared = DeskThemeManager()
}

// MARK: - Status Badge

public struct DeskStatusBadge: View {
    let label: String
    let color: Color

    public init(status: String, docstatus: Int = -1) {
        let resolved = Self.resolve(status: status, docstatus: docstatus)
        self.label = resolved.0; self.color = resolved.1
    }

    public init(label: String, color: Color) {
        self.label = label; self.color = color
    }

    public var body: some View {
        if !label.isEmpty {
            Text(label).font(.system(size: 9, weight: .semibold))
                .foregroundColor(color).padding(.horizontal, 6).padding(.vertical, 2)
                .background(color.opacity(0.12)).cornerRadius(4)
        }
    }

    static func resolve(status: String, docstatus: Int) -> (String, Color) {
        if !status.isEmpty {
            switch status.lowercased() {
            case "paid", "completed", "active": return (status, .green)
            case "unpaid", "overdue", "expired": return (status, .red)
            case "cancelled", "closed": return (status, .gray)
            case "draft": return (status, .orange)
            case "submitted", "open": return (status, .blue)
            default: return (status, .blue)
            }
        }
        switch docstatus {
        case 0: return ("Brouillon", .orange)
        case 1: return ("Soumis", .blue)
        case 2: return ("Annule", .gray)
        default: return ("", .clear)
        }
    }
}

// MARK: - Filter Chip

public struct DeskFilterChip: View {
    let label: String
    let value: String
    let onRemove: () -> Void

    public init(label: String, value: String, onRemove: @escaping () -> Void) {
        self.label = label; self.value = value; self.onRemove = onRemove
    }

    @EnvironmentObject var theme: DeskThemeManager

    public var body: some View {
        HStack(spacing: 4) {
            Text("\(label): \(value)").font(.system(size: 11))
            Button(action: onRemove) { Image(systemName: "xmark").font(.system(size: 9, weight: .bold)) }
        }
        .foregroundColor(theme.colors.primary)
        .padding(.horizontal, 8).padding(.vertical, 4)
        .background(theme.colors.primary.opacity(0.1)).cornerRadius(12)
    }
}

// MARK: - Card

public struct DeskCard: View {
    let icon: String
    let label: String
    let onTap: () -> Void

    public init(icon: String, label: String, onTap: @escaping () -> Void) {
        self.icon = icon; self.label = label; self.onTap = onTap
    }

    @EnvironmentObject var theme: DeskThemeManager

    public var body: some View {
        Button(action: onTap) {
            VStack(spacing: 6) {
                RoundedRectangle(cornerRadius: 12).fill(theme.colors.iconBg)
                    .frame(width: 48, height: 48)
                    .overlay(Image(systemName: icon).font(.system(size: 20)).foregroundColor(theme.colors.primary))
                Text(label).font(.system(size: 11, weight: .medium)).foregroundColor(theme.colors.text)
                    .multilineTextAlignment(.center).lineLimit(2).frame(height: 30)
            }
            .frame(maxWidth: .infinity).frame(height: 110)
            .background(theme.colors.surface).cornerRadius(12)
            .overlay(RoundedRectangle(cornerRadius: 12).stroke(theme.colors.border, lineWidth: 1))
        }.buttonStyle(.plain)
    }
}

// MARK: - List Item

public struct DeskListItem: View {
    let title: String
    let subtitle: String
    let status: String
    let docstatus: Int
    let onTap: () -> Void

    public init(title: String, subtitle: String = "", status: String = "", docstatus: Int = -1, onTap: @escaping () -> Void) {
        self.title = title; self.subtitle = subtitle; self.status = status; self.docstatus = docstatus; self.onTap = onTap
    }

    public var body: some View {
        Button(action: onTap) {
            HStack {
                VStack(alignment: .leading, spacing: 3) {
                    HStack(spacing: 6) {
                        Text(title).font(.system(size: 14, weight: .medium)).foregroundColor(Color(red: 0.12, green: 0.15, blue: 0.18)).lineLimit(1)
                        DeskStatusBadge(status: status, docstatus: docstatus)
                    }
                    if !subtitle.isEmpty {
                        Text(subtitle).font(.system(size: 12)).foregroundColor(.gray).lineLimit(1)
                    }
                }
                Spacer()
                Image(systemName: "chevron.right").font(.system(size: 11)).foregroundColor(.gray)
            }.padding(.vertical, 6)
        }
    }
}

// MARK: - Section Header

public struct DeskSectionHeader: View {
    let label: String
    public init(_ label: String) { self.label = label }

    public var body: some View {
        if !label.isEmpty {
            VStack(alignment: .leading, spacing: 6) {
                Text(label.uppercased())
                    .font(.system(size: 11, weight: .semibold)).foregroundColor(.gray)
                    .tracking(0.5).padding(.top, 20)
                Divider()
            }
        }
    }
}

// MARK: - Avatar

public struct DeskAvatar: View {
    let initials: String
    @EnvironmentObject var theme: DeskThemeManager

    public init(initials: String) { self.initials = initials }

    public var body: some View {
        Circle().fill(theme.colors.primary.opacity(0.15)).frame(width: 32, height: 32)
            .overlay(Text(initials.isEmpty ? "?" : initials).font(.system(size: 13, weight: .semibold)).foregroundColor(theme.colors.primary))
    }
}

// MARK: - Search Bar

public struct DeskSearchBar: View {
    @Binding var text: String
    let placeholder: String

    public init(text: Binding<String>, placeholder: String = "Rechercher...") {
        self._text = text; self.placeholder = placeholder
    }

    @EnvironmentObject var theme: DeskThemeManager

    public var body: some View {
        HStack(spacing: 8) {
            Image(systemName: "magnifyingglass").foregroundColor(.gray).font(.system(size: 14))
            TextField(placeholder, text: $text).font(.system(size: 14))
            if !text.isEmpty {
                Button(action: { text = "" }) {
                    Image(systemName: "xmark.circle.fill").foregroundColor(.gray).font(.system(size: 14))
                }
            }
        }
        .padding(.horizontal, 12).frame(height: 34)
        .background(theme.colors.searchBg).cornerRadius(8)
    }
}

// MARK: - Button

public struct DeskButton: View {
    let title: String
    let action: () -> Void
    let enabled: Bool

    public init(_ title: String, enabled: Bool = true, action: @escaping () -> Void) {
        self.title = title; self.enabled = enabled; self.action = action
    }

    @EnvironmentObject var theme: DeskThemeManager

    public var body: some View {
        Button(action: action) {
            Text(title).frame(maxWidth: .infinity).padding(.vertical, 10)
                .background(enabled ? theme.colors.primary : theme.colors.primary.opacity(0.5))
                .foregroundColor(.white).cornerRadius(6).font(.system(size: 14, weight: .medium))
        }.disabled(!enabled)
    }
}
