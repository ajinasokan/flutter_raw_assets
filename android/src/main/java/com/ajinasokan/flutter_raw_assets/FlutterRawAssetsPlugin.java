package com.ajinasokan.flutter_raw_assets;

import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import java.io.InputStream;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.loader.FlutterLoader;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import io.flutter.view.FlutterMain;

/** FlutterRawAssetsPlugin */
public class FlutterRawAssetsPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private AssetManager assets;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    assets = flutterPluginBinding.getApplicationContext().getAssets();
    channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "com.ajinasokan.flutter_raw_assets");
    channel.setMethodCallHandler(this);
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.ajinasokan.flutter_raw_assets");
    final FlutterRawAssetsPlugin plugin = new FlutterRawAssetsPlugin();
    plugin.assets = registrar.activity().getAssets();
    channel.setMethodCallHandler(plugin);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getBytes")) {
      String asset = (String) call.argument("asset");
      int offset = (int) call.argument("offset");
      int len = (int) call.argument("length");

      String assetKey = FlutterLoader.getInstance().getLookupKeyForAsset(asset);
      try {
          InputStream inputStream = assets.open(assetKey);
          inputStream.skip(offset);
          byte[] buff = new byte[len];
          inputStream.read(buff, 0, len);
          result.success(buff);
      } catch (Exception e) {
        e.printStackTrace();
        result.error(e.getMessage(), null, null);
      }
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
