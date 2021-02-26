package sapt1.problema4;

public class EndThread extends Thread {
    private final MyFile myFile;

    public EndThread(MyFile myFile) {
        this.myFile = myFile;
    }

    @Override
    public void run() {
        int i = myFile.lengthOfFile()-1;

        while (i != myFile.lengthOfFile() / 2) {
            try {
                System.out.println("End Thread read: " + myFile.readFromEnd(i));
                i--;
                sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("End Thread Done");

    }
}
