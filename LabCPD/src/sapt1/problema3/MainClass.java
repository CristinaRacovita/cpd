package sapt1.problema3;

/*
 *  Intr-un birou sunt 8 functionari care din când în când tipăresc la imprimantă documente,
 *  nu toți elaborează documentele în același ritm.  Fiindcă au o singură imprimantă în birou, poate tipări
 *  doar o singura persoană la un moment dat. Să se simuleze functionarea biroului.
 */

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void execute() {
        Document document = new Document();
        System.out.println("Initialize threads synchronized by queue");

        int noOfEmployees = 3;

        Printer printer = new Printer(1000L, document);
        List<Employee> employees = new ArrayList<>(noOfEmployees);

        for (int i = 0; i < noOfEmployees; i++) {
            Employee employee = new Employee(500L * (i + 1), document, (i + 1));
            employees.add(employee);
        }

        System.out.println("Start threads synchronized by queue");

        for (Employee employee : employees) {
            employee.start();
        }

        printer.start();
    }

    public static void main(String[] args) {
        execute();
    }
}
