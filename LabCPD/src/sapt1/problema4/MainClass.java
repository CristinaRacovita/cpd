package sapt1.problema4;

public class MainClass {
    public static void main(String[] args) {
        final String myString = "abcdefghijklmnoprstuvwxyz";

        MyFile myFile = new MyFile("sharedMemory.txt");
        myFile.write(myString);

        StartThread startThread = new StartThread(myFile);
        EndThread endThread = new EndThread(myFile);

        startThread.start();
        endThread.start();
    }
}
