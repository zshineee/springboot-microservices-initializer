const PROXY_CONFIG = [

  {
    "context": "/api",
    "target": "http://localhost/",
    "secure": false,
    "changeOrigin": true,
    "pathRewrite": {
      "^/api": ""
    }
  },
  {
    "context": "/proxy/api",
    "target": "http://localhost/",
    "secure": false,
    "changeOrigin": true,
    "pathRewrite": {
      "^/proxy/api": ""
    }
  }
]

module.exports = PROXY_CONFIG;
