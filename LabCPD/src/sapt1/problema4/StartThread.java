package sapt1.problema4;

public class StartThread extends Thread {
    private final MyFile myFile;

    public StartThread(MyFile myFile) {
        this.myFile = myFile;
    }

    @Override
    public void run() {
        int i = 0;
        int limit;
        if (myFile.lengthOfFile() % 2 == 1) {
            limit = myFile.lengthOfFile() / 2 + 1;
        } else {
            limit = myFile.lengthOfFile() / 2;
        }

        while (i != limit) {
            try {
                System.out.println("Start Thread read: " + myFile.readFromStart(i));
                i++;
                sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Start Thread Done");

    }
}
