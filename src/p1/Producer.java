package p1;

public class Producer{

    private Buffer<MessageProducer> producerBuffer;
    private Buffer<Message> messageBuffer;
    private Thread thread;


    /**
     * @constructor Producer
     * @param producerBuffer
     * @param messageBuffer
     */
    public Producer(Buffer<MessageProducer> producerBuffer, Buffer<Message> messageBuffer) {
        this.producerBuffer = producerBuffer;
        this.messageBuffer = messageBuffer;
    }

    private class InClass extends Thread{

        @Override
        public void run(){
            MessageProducer in;
            while(true){
                try {
                    in = producerBuffer.get();
                    int times = in.times();
                    int size = in.size();

                    for (int i = 0; i<times; i++){
                        for (int j = 0; j<size; j++){
                            messageBuffer.put(in.nextMessage());
                            sleep(in.delay());
                        }
                    }

                } catch (InterruptedException e){
                    e.printStackTrace();
                }

            }

        }

    }

    public void start()  {
        if (thread == null) {
            thread = new InClass();
            thread.start();
        }
    }




}
