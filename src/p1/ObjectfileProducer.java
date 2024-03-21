package p1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectfileProducer implements MessageProducer {

    private int delay;
    private int times;
    private Message[] messages;
    private int index;

    public ObjectfileProducer(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {

            this.times = ois.readInt();
            this.delay = ois.readInt();
            int size = ois.readInt();
            messages = new Message[size];

            for (int i = 0; i < size; i++) {
                messages[i] = ((Message) ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int delay() {
        return delay;
    }

    @Override
    public int times() {
        return times;
    }

    @Override
    public int size() {
        return messages.length;
    }

    @Override
    public Message nextMessage() {
        if (messages.length == 0) {
            return null;
        } else {
            index++;
            if (index == messages.length) {
                index = 0;
            }
            return messages[index];
        }
    }
}

