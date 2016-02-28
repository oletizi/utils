package com.orionletizi.utils;

import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

  @Test
  public void testIsFile() throws Exception {
    assertTrue(Utils.isFile(new File("/").toURI().toURL()));
    assertFalse(Utils.isFile(new URL("http://google.com/")));
  }


  @Test
  public void testIsReachable() throws Exception {
    assertTrue(Utils.isReachable(new File("/").toURI().toURL()));
    assertTrue(Utils.isReachable(new URL("http://google.com/")));
    assertFalse(Utils.isReachable(new URL("ftp://lsdkjfsdlje")));
    assertFalse(Utils.isReachable(new URL("http://orionletizi.com/lsdfjoiejaljlsrijir")));
  }

  @Test
  public void testIsDirectory() throws Exception {
    assertTrue(Utils.isDirectory(Utils.fileToUrl(new File("/"))));
    assertFalse(Utils.isDirectory(new URL("http://google.com/")));

    File tmp = File.createTempFile("foo", "bar");
    assertTrue(tmp.exists());
    assertFalse(tmp.isDirectory());
    assertFalse(Utils.isDirectory(Utils.fileToUrl(tmp)));
  }

  @Test
  public void testFileToUrl() throws Exception {
    assertTrue(Utils.isFile(Utils.fileToUrl(new File("/"))));
    assertTrue(Utils.isDirectory(Utils.fileToUrl(new File("/"))));
  }
}