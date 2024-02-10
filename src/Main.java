import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        try {
            linkedList.insertFromFile(linkedList,"src/Source.txt");
            linkedList.display();
        }
        catch (IOException e){
            System.out.println("error:"+e.getMessage());
        }
        long startTime = System.nanoTime();
        linkedList.searchFromFile("src/Search.txt");
        long stopTime = System.nanoTime();
        System.out.println("Total Time Pass: "+(stopTime-startTime));

        long startTime2 = System.nanoTime();
        linkedList.searchAndMoveFromFile("src/Search.txt");
        long stopTime2 = System.nanoTime();
        System.out.println("Total Time Pass:" + (stopTime2-startTime2));
        linkedList.display();
    }
}