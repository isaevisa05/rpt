package ru.isaevisa05.rpt;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Data implements Serializable {
    private Map<String, Object> data = new HashMap<>();
    private byte[] msg;

    public Data(Map<String, Object> data, byte[] msg) {
        this.data = data;
        this.msg = msg;
    }

    public byte[] getMessage() {
        return msg;
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream(byteArrayOutputStream);

        try {
            objectOutputStream.writeObject(data);
            objectOutputStream.writeByte(msg.length);
            objectOutputStream.write(msg);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Data get(byte[] bytes) throws IOException {
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream =
                new ObjectInputStream(byteArrayInputStream);

        try {
            Map<String, Object> data = (Map<String, Object>) objectInputStream.readObject();
            int length = objectInputStream.readByte();
            byte[] msg = new byte[length];
            for (int i = 0; i < length; i++) {
                msg[i] = objectInputStream.readByte();
            }
            return new Data(data, msg);
        } catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    public <T> T getValue(String key) {
        if (data.containsKey(key)) {
            return (T) data.get(key);
        } else {
            throw new RuntimeException("Values in the schema do not exist");
        }
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }
}