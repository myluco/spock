package org.spockframework.runtime.extension.builtin;

import org.opentest4j.TestAbortedException;

/**
 * @author Leonard Brünings
 */
public class PendingFeatureBaseInterceptor {
  protected final Class<? extends Throwable>[] expectedExceptions;
  protected final String reason;

  public PendingFeatureBaseInterceptor(Class<? extends Throwable>[] expectedExceptions, String reason) {
    this.expectedExceptions = expectedExceptions;
    this.reason = reason;
  }

  protected boolean isExpected(Throwable e) {
    for (Class<? extends Throwable> exception : expectedExceptions) {
      if(exception.isInstance(e)) {
        return true;
      }
    }
    return false;
  }
  protected AssertionError featurePassedUnexpectedly() {
    return new AssertionError("Feature is marked with @PendingFeature but passes unexpectedly");
  }

  protected TestAbortedException assumptionViolation() {
    return new TestAbortedException("Feature not yet implemented correctly."
      + ("".equals(reason) ? "" : " Reason: " + reason));
  }
}
