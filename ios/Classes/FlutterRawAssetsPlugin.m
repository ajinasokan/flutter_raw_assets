#import "FlutterRawAssetsPlugin.h"
#if __has_include(<flutter_raw_assets/flutter_raw_assets-Swift.h>)
#import <flutter_raw_assets/flutter_raw_assets-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_raw_assets-Swift.h"
#endif

@implementation FlutterRawAssetsPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterRawAssetsPlugin registerWithRegistrar:registrar];
}
@end
