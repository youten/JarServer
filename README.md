# JarServer

Single Jar HTTP Server with KVS like API for Android.

## How re-archive jetty-x.x.x.jar
- download binary tarball from http://download.eclipse.org/jetty/
- extract

```bash
wget http://download.eclipse.org/jetty/8.1.17.v20150415/dist/jetty-distribution-8.1.17.v20150415.tar.gz
tar xvf jetty-distribution-8.1.17.v20150415.tar.gz
```

- re-archive

```bash
cd jetty-distribution-8.1.17.v20150415/lib/ext/
jar xf ../jetty-continuation-*.jar
jar xf ../jetty-http-*.jar
jar xf ../jetty-io-*.jar
jar xf ../jetty-security-*.jar
jar xf ../jetty-server-*.jar
jar xf ../jetty-servlet-*.jar
jar xf ../jetty-util-*.jar
jar xf ../jetty-webapp-*.jar
jar xf ../jetty-websocket-*.jar
jar xf ../jetty-xml-*.jar
jar xf ../servlet-api-*.jar
rm -rf about*
jar cf ../jetty-8.1.17.jar *
cd ..
```
- copy jetty-x.x.x.jar to Android's Project libs/ folder.

# License
Apache License Version 2.0


