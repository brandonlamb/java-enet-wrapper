package org.bespin.enet;

import java.nio.ByteBuffer;
import java.util.EnumSet;

public class Packet {

  ByteBuffer nativeState;
  boolean owned;
  private ByteBuffer buffer;

  Packet(final ByteBuffer nativeState) {
    this.nativeState = nativeState;
    owned = false;
  }

  public Packet(final ByteBuffer buffer, final EnumSet<Flag> flags) throws EnetException {
    if (!buffer.isDirect()) {
      this.buffer = ByteBuffer.allocateDirect(buffer.remaining());
      this.buffer.put(buffer.duplicate());
    } else {
      this.buffer = buffer;
    }
    this.nativeState = create(this.buffer, Flag.toBits(flags));
    owned = true;
  }

  public Packet(final byte[] bytes, final int offset, final int length, final EnumSet<Flag> flags)
    throws EnetException {
    this(ByteBuffer.wrap(bytes, offset, length), flags);
  }

  public Packet(final byte[] bytes, final EnumSet<Flag> flags) throws EnetException {
    this(bytes, 0, bytes.length, flags);
  }

  private static native ByteBuffer create(final ByteBuffer data, final int flags) throws EnetException;

  private static native ByteBuffer get_bytes(final ByteBuffer ctx) throws EnetException;

  private static native int get_flags(final ByteBuffer ctx) throws EnetException;

  private static native void destroy(final ByteBuffer ctx) throws EnetException;

  public ByteBuffer getBytes() throws EnetException {
    if (buffer == null) {
      buffer = get_bytes(nativeState);
    }
    return buffer;
  }

  public EnumSet<Flag> getFlags() throws EnetException {
    return Flag.fromBits(get_flags(nativeState));
  }

  protected void finalize() throws Throwable {
    if (owned) {
      destroy(nativeState);
    }
    super.finalize();
  }

  public enum Flag {
    RELIABLE(1),
    UNSEQUENCED(1 << 1),
    UNRELIABLE_FRAGMENT(1 << 3);

    public final int bitValue;

    Flag(int bitValue) {
      this.bitValue = bitValue;
    }

    static int toBits(EnumSet<Flag> set) {
      int ret = 0;
      for (Flag f : set) {
        ret |= f.bitValue;
      }
      return ret;
    }

    static EnumSet<Flag> fromBits(int bits) {
      EnumSet<Flag> ret = EnumSet.noneOf(Flag.class);
      for (Flag f : EnumSet.allOf(Flag.class)) {
        if ((bits & f.bitValue) != 0) {
          ret.add(f);
        }
      }
      return ret;
    }
  }
}
