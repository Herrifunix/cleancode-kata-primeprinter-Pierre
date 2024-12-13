function buildUrl(url, options) {
    var queryString = [];
    var key;
    var builtUrl;

    if (!url){
    url = ''}

    if (options.path) {
            builtUrl = `${builtUrl}/${options.path}`;
        }
      if (options.queryParams) {
        for (key in options.queryParams) {
          if (options.queryParams.hasOwnProperty(key)) {
            queryString.push(key + '=' + options.queryParams[key]);
          }
        }
        builtUrl += '?' + queryString.join('&');
      }

      if (options.hash) {
              builtUrl = `${builtUrl}#${options.hash}`;
          }
    }

    return builtUrl;
};