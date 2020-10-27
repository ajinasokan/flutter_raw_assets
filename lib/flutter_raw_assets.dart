import 'dart:async';
import 'dart:typed_data';
import 'package:flutter/services.dart';

class FlutterRawAssets {
  static const MethodChannel _channel =
      const MethodChannel('com.ajinasokan.flutter_raw_assets');

  static Future<Uint8List> getBytes(
      String asset, int offset, int length) async {
    return Uint8List.fromList(await _channel.invokeMethod('getBytes', {
      "asset": asset,
      "offset": offset,
      "length": length,
    }));
  }
}
