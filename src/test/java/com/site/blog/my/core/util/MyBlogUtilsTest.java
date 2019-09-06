package com.site.blog.my.core.util;

import com.site.blog.my.core.util.MyBlogUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class MyBlogUtilsTest {

  @Rule public final ExpectedException thrown = ExpectedException.none();

  @Rule public final Timeout globalTimeout = new Timeout(10000);

  // Test written by Diffblue Cover.
  @Test
  public void getHostInputNullOutputNull() {

    // Act and Assert result
    Assert.assertNull(MyBlogUtils.getHost(null));
  }
}
