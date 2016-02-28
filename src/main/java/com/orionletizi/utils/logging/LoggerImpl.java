package com.orionletizi.utils.logging;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoggerImpl implements Logger {

  private static final DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MMM-dd.kk:mm:ss");
  private static final Map<String, Set<LoggerImpl>> loggers = new HashMap<>();
  private static final Set<String> suppressedLoggers = new HashSet<>();
  private boolean on = true;

  public static Logger forClass(final Class clazz) {
    return forClass(clazz, new PrintWriter(System.out));
  }

  public static Logger forClass(final Class clazz, PrintWriter out) {
    final LoggerImpl logger = new LoggerImpl(clazz, out);
    synchronized (loggers) {
      final String key = clazz.getSimpleName();
      Set<LoggerImpl> loggerSet = loggers.get(key);
      if (loggerSet == null) {
        loggerSet = new HashSet<>();
        loggers.put(key, loggerSet);
      }
      loggerSet.add(logger);
      if (suppressedLoggers.contains(key)) {
        logger.off();
      }
    }
    return logger;
  }

  public static Logger forObject(final Object o) {
    return forClass(o.getClass());
  }

  public static Logger forObject(final Object o, PrintWriter out) {
    return forClass(o.getClass(), out);
  }

  public static void turnOff(final Class clazz) {
    synchronized (loggers) {
      final String key = clazz.getSimpleName();
      suppressedLoggers.add(key);
      final Set<LoggerImpl> loggerSet = loggers.get(key);
      if (loggerSet != null) {
        for (LoggerImpl logger : loggerSet) {
          logger.off();
        }
      }
    }
  }

  public static void turnOn(final Class clazz) {
    synchronized (loggers) {
      final String key = clazz.getSimpleName();
      suppressedLoggers.remove(key);
      final Set<LoggerImpl> loggerSet = LoggerImpl.loggers.get(key);
      if (loggerSet != null) {
        for (LoggerImpl logger : loggerSet) {
          logger.on();
        }
      }
    }
  }

  private void off() {
    this.on = false;
  }

  private void on() {
    this.on = true;
  }

  private final String name;
  private final PrintWriter out;

  private LoggerImpl(final String name) {
    this(name, new PrintWriter(new OutputStreamWriter(System.out)));
  }

  private LoggerImpl(final String name, PrintWriter out) {

    this.name = name;
    this.out = out;
  }

  private LoggerImpl(Object o) {

    this(o.getClass());
  }

  private LoggerImpl(Class clazz) {

    this(clazz.getSimpleName());
  }

  private LoggerImpl(Class clazz, PrintWriter out) {
    this(clazz.getSimpleName(), out);
  }

  @Override
  public void fine(final Object msg) {
    logln("FINE", msg);
  }

  @Override
  public void logln(final String level, final Object msg) {
    if (on) {
      out.println(df.print(System.currentTimeMillis()) + " " + level + " -- " + name + ": " + msg);
      out.flush();
    }
  }


  @Override
  public void warn(Object s) {
    logln("WARN", s);
  }

  @Override
  public void info(Object s) {
    logln("INFO", s);
  }
}
