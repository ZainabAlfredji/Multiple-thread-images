package p1;

public class P1Viewer extends Viewer implements ICallBack {

    public P1Viewer(MessageManager mm, int width, int height) {
        super(width, height);
        mm.addListener(this);
    }

    @Override
    public void methodToCall(Message m) {
        setMessage(m);
    }
}
