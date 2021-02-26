package sapt1.problema3;

public class Employee extends Thread {

    private final long delay;
    private final Document document;
    private final Integer number;

    public Employee(long delay, Document document, Integer number) {
        super();
        this.delay = delay;
        this.document = document;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            try {
                sleep(delay);
                System.out.println("Elaborating doc: " + i + " by employee: " + number);
                document.elaborate("doc: " + i + " by employee: " + number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
