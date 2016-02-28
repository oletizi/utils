package com.orionletizi.util;

public class Assertions {

  public static void assertFalse(boolean b) {
    assertTrue("", !b);
  }

  public static void assertFalse(final String message, boolean b) {
    assertTrue(message, !b);
  }

  public static void assertTrue(boolean b) {
    assertTrue("", b);
  }

  public static void assertTrue(String msg, boolean b) {
    if (!b) {
      throw new AssertionError(msg);
    }
  }

  public static void assertNotNull(Object o) {
    assertNotNull("", o);
  }

  public static void assertNotNull(String msg, Object o) {
    if (o == null) {
      throw new AssertionError(msg);
    }
  }
}
