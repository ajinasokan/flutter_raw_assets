# Flutter RAW Assets

A Flutter plugin to read large assets in chunks of bytes.

Add to pubspec.yaml:

```yaml
dependencies:
  flutter_raw_assets:
    git:
      url: https://github.com/ajinasokan/flutter_raw_assets

flutter:
  assets:
    - assets/file.bin
```

Read 100 bytes from beginning of `assets/file.bin`:

```dart
Uint8List bytes = await FlutterRawAssets.getBytes("assets/file.bin", 0, 100);
```