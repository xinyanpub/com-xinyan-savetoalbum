#import "SaveToAlbum.h"

@implementation SaveToAlbum

- (void)saveToAlbum:(CDVInvokedUrlCommand*)command{
    NSString* source = [command.arguments objectAtIndex:0];
    if (source != nil && [source length] > 0) {
	    [self.commandDelegate runInBackground:^{
			UIImage *img = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:source]]];
			UIImageWriteToSavedPhotosAlbum(img, nil, nil, nil);
			
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }];
	}
}
@end