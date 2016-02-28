package com.orionletizi.utils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

  public static boolean isFile(final URL url) {
    return url != null && "file".equalsIgnoreCase(url.getProtocol());
  }

  public static boolean isReachable(final URL... urls) {
    boolean rv = false;
    for (URL url : urls) {
      if (isFile(url)) {
        return new File(url.getFile()).exists();
      } else if (isHttp(url)) {
        try {
          final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("HEAD");
          return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      }
    }
    return rv;
  }

  private static boolean isHttp(final URL url) {
    return ("http".equalsIgnoreCase(url.getProtocol()) || "https".equalsIgnoreCase(url.getProtocol()));
  }

  public static boolean isDirectory(final URL url) {
    return isFile(url) && new File(url.getFile()).isDirectory();
  }

  public static URL fileToUrl(final File file) {
    try {
      return file.toURI().toURL();
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public static File urlToFile(final URL songsRoot) {
    return new File(songsRoot.getFile());
  }

  public static URL getResource(final String s) {
    return ClassLoader.getSystemResource(s);
  }

}
