package ru.isaevisa05.rpt;

import java.io.*;
import java.net.SocketAddress;
import java.util.Map;

public class IPForwarding {
    private SocketAddress address;
    private String to = null;
    private Object msg;

    public IPForwarding(SocketAddress address, Object msg) {
        this.address = address;
        this.msg = msg;
    }
    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(byteArrayOutputStream);

        try {
            objectOutputStream.writeObject(address);
            objectOutputStream.writeObject(msg);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static IPForwarding get(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream =
                new ObjectInputStream(byteArrayInputStream);

        try {
            SocketAddress address = (SocketAddress) objectInputStream.readObject();
            Object object = objectInputStream.readObject();
            return new IPForwarding(address, object);
        } catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    public SocketAddress getAddress() {
        return address;
    }

    public String getTo() {
        return to;
    }

    public Object getMsg() {
        return msg;
    }
}
