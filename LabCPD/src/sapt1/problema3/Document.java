package sapt1.problema3;

import java.util.LinkedList;
import java.util.Queue;

public class Document {
    Queue<String> documents = new LinkedList<>();
	

    public void elaborate(String docName) {
        System.out.println("Elaborate a document....." + docName);
        documents.add(docName);
    }

    public String print() {
        String docName = documents.poll();
        System.out.println("Print a document......" + docName);
        return docName;
    }

    public boolean hasDoc() {
        return !documents.isEmpty();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("Documents: ");
        documents.forEach(element -> builder.append(element).append(" "));
        return builder.toString();
    }
}
