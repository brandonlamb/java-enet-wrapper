package org.bespin.enet;

import java.nio.ByteBuffer;

public class Event {

  ByteBuffer nativeState;

  Event() {
    this(ByteBuffer.allocateDirect(sizeof()));
  }

  Event(final ByteBuffer nativeState) {
    this.nativeState = nativeState;
  }

  private static native int sizeof();

  private static native ByteBuffer peer(ByteBuffer ctx);

  private static native Type type(ByteBuffer ctx);

  private static native byte channelID(ByteBuffer ctx);

  private static native int data(ByteBuffer ctx);

  private static native ByteBuffer packet(ByteBuffer ctx);

  public Peer peer() {
    final ByteBuffer peer = peer(nativeState);
    return (peer == null) ? null : new Peer(peer);
  }

  public Type type() {
    return type(nativeState);
  }

  public int channelID() {
    return channelID(nativeState) & 0xFF;
  }

  public int data() {
    return data(nativeState);
  }

  public Packet packet() {
    final ByteBuffer packet = packet(nativeState);
    return (packet == null) ? null : new Packet(packet);
  }

  public enum Type {
    None,
    Connect,
    Disconnect,
    Receive
  }
}
