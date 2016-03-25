# PhoneGap/Cordova SaveToAlbum Plugin
 * Original by Jianye Xu

## About this Plugin
This plugin allows you to save photo to album in IOS and Android

## Installation

This requires cordova 4.0+
    
    cordova plugin add https://github.com/xinyanpub/com-xinyan-savetoalbum.git

Example:

```
window.plugins.savetoalbum.download(url, success, fail);

var success = function() {
  // Do something after save success
}

var fail = function(statusCode) {
  // Why did it fail? - look in the plug in for source of error codes.
  console.log(statusCode);
}


## LICENSE ##

The MIT License