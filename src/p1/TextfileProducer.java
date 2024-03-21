package p1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;


public class TextfileProducer implements MessageProducer{

    private int times;
    private int delay;
    private Message[] messages;
    private int currentIndex = -1;



    /**
     * @param filename vagen till den sparade filen
     * @BufferedReader laser av text
     * @InputStreamReader skapar en InputStream som ska anvanda den givna texten
     */
    public TextfileProducer(String filename) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            this.times = Integer.parseInt(br.readLine());
            this.delay = Integer.parseInt(br.readLine());
            int size = Integer.parseInt(br.readLine());
            messages = new Message[size];
            for (int i = 0; i < size; i++) {
                String str = br.readLine();
                ImageIcon icon = new ImageIcon(br.readLine());
                Message m = new Message(str, icon);
                messages[i] = m;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int delay() {
        return 0;
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
            currentIndex++;
            if (currentIndex == messages.length) {
                currentIndex = 0;
            }
            return messages[currentIndex];
        }
    }
}

