package p1;

import java.util.ArrayList;

public class MessageManager{

    private Buffer<Message> messageBuffer;
    private ArrayList<ICallBack> listeners;
    private Thread thread;

    public MessageManager(Buffer<Message> msb) {
        this.listeners = new ArrayList<>();
        this.messageBuffer = msb;
    }

    public void addListener (ICallBack listener) {
        listeners.add(listener);
    }

    private class InnerClass extends Thread {

        public void run() {
            Message m;
            while (true) {
                try {
                    m = messageBuffer.get();
                    for (ICallBack ic : listeners) {
                        ic.methodToCall(m);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new InnerClass();
            thread.start();
        }
    }
}
