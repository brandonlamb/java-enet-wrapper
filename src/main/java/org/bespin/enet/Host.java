package org.bespin.enet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;

public class Host {

  static {
//    try {
//      final ClassLoader loader = Host.class.getClassLoader();
//      final InputStream in = loader.getResourceAsStream("libjava-enet-wrapper-native.so");
//
//      String tmpname = System.getProperty("org.bespin.enet.tmpdir");
//      if (tmpname == null) {
//        tmpname = System.getProperty("java.io.tmpdir");
//      }
//
//      final File tmpdir = new File(tmpname);
//      final File libout = new File(tmpdir, "libjava-enet-wrapper-native.so");
//      final FileOutputStream out = new FileOutputStream(libout);
//      final byte[] b = new byte[1024];
//
//      int len;
//      while ((len = in.read(b)) != -1) {
//        out.write(b, 0, len);
//      }
//
//      in.close();
//      out.close();
//      System.load(libout.getAbsolutePath());
//    } catch (final UnsatisfiedLinkError | IOException ule) {
//      System.err.println("failed to load embedded native library!");
//      ule.printStackTrace();
//    }
        /*try
        {
            System.out.println("load from " + System.getProperty("java.library.path"));
            System.loadLibrary(System.mapLibraryName("java-enet-wrapper-native"));
        }
        catch (UnsatisfiedLinkError ule)
        {
            ule.printStackTrace();
            String lib = System.getProperty("enet.jnilib");
            System.load(lib);
        }*/
  }

  private ByteBuffer nativeState;

  public Host(
    final InetSocketAddress address,
    final int peerCount,
    final int channelLimit,
    final int incomingBandwidth,
    final int outgoingBandwidth
  ) throws EnetException {
    nativeState = create(
      addressToInt(address.getAddress()),
      address.getPort(),
      peerCount,
      channelLimit,
      incomingBandwidth,
      outgoingBandwidth
    );
  }

  static int addressToInt(final InetAddress address) throws EnetException {
    if (!(address instanceof Inet4Address)) {
      throw new EnetException("enet only supports IPv4");
    }
    final ByteBuffer buf = ByteBuffer.wrap(address.getAddress());
    buf.order(ByteOrder.nativeOrder());
    return buf.getInt(0);
  }

  private static native ByteBuffer create(
    int address,
    int port,
    int peerCount,
    int channelLimit,
    int incomingBandwidth,
    int outgoingBandwidth
  ) throws EnetException;

  private static native ByteBuffer connect(
    ByteBuffer ctx,
    int address,
    int port,
    int channelCount,
    int data
  ) throws EnetException;

  private static native void broadcast(ByteBuffer ctx, int channelID, ByteBuffer packet);

  private static native void channel_limit(ByteBuffer ctx, int channelLimit);

  private static native void bandwidth_limit(ByteBuffer ctx, int in, int out);

  private static native void flush(ByteBuffer ctx);

  private static native int checkEvents(ByteBuffer ctx, ByteBuffer event) throws EnetException;

  private static native int service(ByteBuffer ctx, int timeout, ByteBuffer event) throws EnetException;

  private static native void destroy(ByteBuffer ctx) throws EnetException;

  public Peer connect(final InetSocketAddress address, final int channelCount, final int data) throws EnetException {
    return new Peer(connect(nativeState, addressToInt(address.getAddress()), address.getPort(), channelCount, data));
  }

  public void broadcast(final int channelID, final Packet packet) {
    broadcast(nativeState, channelID, packet.nativeState);
  }

  public void channelLimit(final int channelLimit) {
    channel_limit(nativeState, channelLimit);
  }

  public void bandwidthLimit(final int incomingBandwidth, final int outgoingBandwidth) {
    bandwidth_limit(nativeState, incomingBandwidth, outgoingBandwidth);
  }

  public void flush() {
    flush(nativeState);
  }

  public Event checkEvents() throws EnetException {
    final Event event = new Event();
    final int ret = checkEvents(nativeState, event.nativeState);
    return (ret <= 0) ? null : event;
  }

  public Event service(int timeout) throws EnetException {
    final Event event = new Event();
    final int ret = service(nativeState, timeout, event.nativeState);
    return (ret <= 0) ? null : event;
  }

  public Event service(final long timeout, final TimeUnit unit) throws EnetException {
    return service((int) TimeUnit.MILLISECONDS.convert(timeout, unit));
  }

  @Override
  protected void finalize() throws Throwable {
    destroy(nativeState);
    super.finalize();
  }
}
