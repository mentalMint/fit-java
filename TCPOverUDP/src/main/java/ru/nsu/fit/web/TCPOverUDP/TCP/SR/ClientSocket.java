package ru.nsu.fit.web.TCPOverUDP.TCP.SR;

import ru.nsu.fit.web.TCPOverUDP.TCP.packet.TCPPacket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientSocket extends TCPSocket {
    public ClientSocket(int port) throws SocketException {
        super(port);
    }

    @Override
    public void connect(InetAddress address, int port) {
        makeBuffers();
        getSocket().connect(address, port);
        try {
            sendHandshake();
            receiveHandshakeAck();
            sendHandshakeAck();
//            setAck(0);
            setSeq(0);
            getReceiver().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendHandshake() throws IOException {
        TCPPacket packet = new TCPPacket();
        packet.ackNumber = getAck();
        packet.seqNumber = getSeq();
        packet.isSyn = true;
        getSocket().send(packet.makePacket());
        setSeq(getSeq() + 1);
    }

    private void receiveHandshakeAck() throws IOException {
        int receivedLength = 2 * Integer.BYTES + 1;
        DatagramPacket receivedPacket = new DatagramPacket(new byte[receivedLength], receivedLength);
        getSocket().receive(receivedPacket);
        TCPPacket packet = new TCPPacket();
        packet.extractPacket(receivedPacket);
//        setAck(getAck() + 1);
    }

    private void sendHandshakeAck() throws IOException {
        TCPPacket packet = new TCPPacket();
        packet.ackNumber = getAck();
        packet.seqNumber = getSeq();
        packet.isAck = true;
        getSocket().send(packet.makePacket());
        setSeq(getSeq() + 1);

    }
}

