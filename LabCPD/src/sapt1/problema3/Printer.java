package sapt1.problema3;

import sapt1.problema3.Document;

public class Printer extends Thread {

    private final long delay;
    private final Document document;

    public Printer(long delay, Document document) {
        this.delay = delay;
        this.document = document;
    }

    @Override
    public void run() {
        while (!document.hasDoc()) {
            try {
                System.out.println("Nothing to print :(");
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (document.hasDoc()) {
            try {
                String docName = document.print();
                System.out.println("Printing: " + docName);
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
