package com.orionletizi.util.logging;

import org.junit.Test;

public class LoggerTest {

  @Test
  public void test() {
    Logger logger = LoggerImpl.forObject(this);
    logger.warn("Warn!");
    logger.info("Info!");
  }

}