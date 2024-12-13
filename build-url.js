function buildUrl(url, options) {
    if (!url) {
        url = '';
    }

    let builtUrl = url;
    if (options) {
        builtUrl = appendPath(builtUrl, options.path);
        builtUrl = appendQueryParams(builtUrl, options.queryParams);
        builtUrl = appendHash(builtUrl, options.hash);
    }
    return builtUrl;
}

function appendPath(url, path) {
    if (path) {
        return `${url}/${path}`;
    }
    return url;
}

function appendQueryParams(url, queryParams) {
    if (!queryParams) {
        return url;
    }
    const queryString = [];
    for (const key in queryParams) {
        if (queryParams.hasOwnProperty(key)) {
            queryString.push(`${key}=${queryParams[key]}`);
        }
    }

    if (queryString.length > 0) {
        return `${url}?${queryString.join('&')}`;
    }
    return url;
}

function appendHash(url, hash) {
    if (hash) {
        return `${url}#${hash}`;
    }
    return url;
}
