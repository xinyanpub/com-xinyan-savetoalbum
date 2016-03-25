var exec = require('cordova/exec');

isAndroid = cordova.platformId == "android";

function SaveToAlbum() {}

SaveToAlbum.prototype.download = function (url, successCallback, errorCallback, filePath) {
    if(isAndroid) {
        var fileTransfer = new FileTransfer();

        fileTransfer.download(
            encodeURI(url),
            filePath,
            function() {
                exec(successCallback, errorCallback, "SaveToAlbum", "saveToAlbum", [url, filePath]);
            },
            function(error) {
                alert('Something went wrong. Try again later.');
            },
            false,
            {}
        );

    } else {
      exec(successCallback, errorCallback, "SaveToAlbum", "saveToAlbum", [url]);
    }
}

module.exports = new SaveToAlbum();