package com.orionletizi.util.logging;

public class NullLogger implements Logger {
  @Override
  public void fine(Object msg) {
    return;
  }

  @Override
  public void logln(String level, Object msg) {
    return;
  }

  @Override
  public void warn(Object s) {
    return;
  }

  @Override
  public void info(Object s) {
    return;
  }
}
