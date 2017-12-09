package org.bespin.enet;

public class MutableInteger extends Number {

  private int value;

  public MutableInteger(final int value) {
    this.value = value;
  }

  public void setValue(final int newValue) {
    this.value = newValue;
  }

  @Override
  public double doubleValue() {
    return (double) this.value;
  }

  @Override
  public float floatValue() {
    return (float) this.value;
  }

  @Override
  public int intValue() {
    return this.value;
  }

  @Override
  public long longValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @Override
  public int hashCode() {
    return value;
  }

  @Override
  public boolean equals(final Object obj) {
    try {
      return ((MutableInteger) obj).value == this.value;
    } catch (final NullPointerException | ClassCastException npe) {
      return false;
    }
  }
}
