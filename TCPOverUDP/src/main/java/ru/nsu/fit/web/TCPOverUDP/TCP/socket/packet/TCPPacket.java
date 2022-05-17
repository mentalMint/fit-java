package ru.nsu.fit.web.TCPOverUDP.TCP.socket.packet;

import java.net.DatagramPacket;
import java.nio.ByteBuffer;

public class TCPPacket {
    public byte[] buf = {};
    public int length = 0;
    public int ack;
    public int seq;
    public boolean isSyn;
    public boolean isAck;

    public DatagramPacket makePacket() {
        byte[] byteSeq = ByteBuffer.allocate(Integer.BYTES).putInt(seq).array();
        byte[] byteAck = ByteBuffer.allocate(Integer.BYTES).putInt(ack).array();
        int newLength = length + 2 * Integer.BYTES;
        byte[] newBuf = new byte[newLength];
        System.arraycopy(byteSeq, 0, newBuf, 0, Integer.BYTES);
        System.arraycopy(byteAck, 0, newBuf, Integer.BYTES, Integer.BYTES);
        System.arraycopy(buf, 0, newBuf, 2 * Integer.BYTES, length);
        return new DatagramPacket(newBuf, newLength);
    }

    private int bytesToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(bytes);
        buffer.rewind();
        return buffer.getInt();
    }

    public void extractPacket(DatagramPacket receivedPacket) {
        int realLength = receivedPacket.getLength() - 2 * Integer.BYTES;
        byte[] receivedBuf = receivedPacket.getData();
        byte[] byteSeq = new byte[Integer.BYTES];
        byte[] byteAck = new byte[Integer.BYTES];
        buf = new byte[realLength];
        System.arraycopy(receivedBuf, 0, byteSeq, 0, Integer.BYTES);
        System.arraycopy(receivedBuf, Integer.BYTES, byteAck, 0, Integer.BYTES);
        System.arraycopy(receivedBuf, Integer.BYTES * 2, buf, 0, realLength);
        ack = bytesToInt(byteAck);
        seq = bytesToInt(byteSeq);
    }
}