package com.site.blog.my.core.util;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyBlogUtilsTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  // Test written by Diffblue Cover.
  @Test
  public void getHostInputNullOutputNull() {

    // Act and Assert result
    Assert.assertNull(MyBlogUtils.getHost(null));
  }
}
