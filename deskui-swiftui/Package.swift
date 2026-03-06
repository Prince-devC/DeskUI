// DeskUI - Professional UI Component Library
// Swift Package Manager manifest

// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "DeskUI",
    platforms: [.iOS(.v16)],
    products: [
        .library(name: "DeskUI", targets: ["DeskUI"]),
    ],
    targets: [
        .target(name: "DeskUI", path: "Sources/DeskUI"),
    ]
)
