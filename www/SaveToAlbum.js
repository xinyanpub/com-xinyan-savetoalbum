var exec = require('cordova/exec');

isAndroid = cordova.platformId == "android";

function SaveToAlbum() {}

SaveToAlbum.prototype.download = function (url, successCallback, errorCallback, filePath) {
    if(isAndroid) {
        var fileTransfer = new FileTransfer();

        fileTransfer.download(
          encodeURI(url),
          filePath,
          function(entry) {
              exec(successCallback, errorCallback, "SaveToAlbum", "saveToAlbum", [entry.toURL(), filePath]);
          },
          function(error) {
              alert('文件保存出错');
          },
          false,
          {}
        );

    } else {
        exec(successCallback, errorCallback, "SaveToAlbum", "saveToAlbum", [url]);
    }
}

module.exports = new SaveToAlbum();