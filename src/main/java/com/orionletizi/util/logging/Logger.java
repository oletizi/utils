package com.orionletizi.util.logging;

public interface Logger {
  static Logger NULL_LOGGER = new NullLogger();

  void fine(Object msg);

  void logln(String level, Object msg);

  void warn(Object s);

  void info(Object s);
}
