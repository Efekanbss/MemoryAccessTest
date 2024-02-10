import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class LinkedList<T extends Comparable> {
    private Node<T> head;

    public Node<T> createNode(T val) {
        return new Node<T>(val);
    }

    public void insertToFront(T val) {
        Node<T> newNode = createNode(val);
        newNode.next = head;
        head = newNode;
    }

    public void insertToEnd(T val) {
        Node<T> newNode = createNode(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> iterator = head;
        while (iterator.next != null)
            iterator = iterator.next;
        iterator.next = newNode;

    }

    public T findMin() {
        if (head == null)
            return null;
        T min = head.value;
        Node<T> iterator = head.next;
        while (iterator != null) {
            if (min.compareTo(iterator.value) == 1)
                min = iterator.value;
            iterator = iterator.next;
        }
        return min;
    }

    public void sortedInsert(T val) {
        Node<T> newNode = createNode(val);
        if (head == null)
            head = newNode;
        else if (val.compareTo(head.value) <= 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> iterator = head;
            while (iterator.next != null && iterator.next.value.compareTo(val) == -1) {
                iterator = iterator.next;
            }
            newNode.next = iterator.next;
            iterator.next = newNode;
        }
    }

    public void deleteTheFirst() {
        if (head != null)
            head = head.next;
    }

    public void delete(T val) {
        if (head == null)
            return;
        if (head.value.compareTo(val) == 0)
            head = head.next;
        else {
            Node<T> previous = head, iterator = head;
            while (iterator != null && iterator.value.compareTo(val) != 0) {
                previous = iterator;
                iterator = iterator.next;
            }
            if (iterator != null)
                previous.next = iterator.next;

        }
    }


    public void recursiveAddToEnd(T val) {
        head = recursiveAddToEnd(head, val);
    }

    public Node<T> recursiveAddToEnd(Node<T> tempHead, T val) {
        if (tempHead == null)
            return createNode(val);
        else {
            tempHead.next = recursiveAddToEnd(tempHead.next, val);
        }
        return tempHead;
    }

    public void swapFirstAndLastNodes() {
        if (head == null || head.next == null)
            return;
        Node<T> iterator = head, previous = head;
        while (iterator.next != null) {
            previous = iterator;
            iterator = iterator.next;
        }
        if (head.next == iterator) {
            iterator.next = head;
            head.next = null;
            head = iterator;
        } else {
            iterator.next = head.next;
            previous.next = head;
            head.next = null;
            head = iterator;
        }


    }

    public void display() {
        Node<T> iterator = head;
        while (iterator != null) {
            System.out.print(iterator + " ");
            iterator = iterator.next;
        }
        System.out.println();

    }

    int numTotal = 0;
    int numUnique = 0;

    public boolean isContain(T val) {
        Node<T> iterator = head;
        while (iterator != null) {
            if (iterator.value.compareTo(val)== 0) {
                return true;
            }
            iterator = iterator.next;
        }
        return false;
    }
    public void insertFromFile(LinkedList<T> lst, String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line ;
            while ((line = bufferedReader.readLine())!= null) {
                String[] data = line.split(",");
                for (String val : data) {
                    try {
                        T intValue = (T) Integer.valueOf(val);
                        if (!lst.isContain(intValue)) {
                            insertToEnd(intValue);
                            numUnique++;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid value: " + val);
                    }
                }
            }
        }
    }


    public int search(int val) {
        int accesses = 0;
        int valueAccess = 0;
        Node<T> temp = head;
        while (temp != null) {
            accesses++;
            if (temp.value.compareTo(val) == 0) {
                valueAccess++;
                return accesses + valueAccess ;
            }
            else {
                temp = temp.next;
            }
        }
        return -1;
    }

    public void searchFromFile(String fileName) {
        int totalAccess = 0;
        int totalVal = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                for (String data : values) {
                    int intValue = Integer.parseInt(data);
                    int access = search(intValue);
                    if (access != -1) {
                        totalAccess = totalAccess + access;
                        totalVal++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("exception");
        }


        double averageAccess = (double) totalAccess / numUnique;


        System.out.println("Total Memory Accesses: " + totalAccess);
        System.out.println("Average Memory Accesses: " + averageAccess);





    }
    public int searchAndMoveToBeginning(int val) {
        int access = 0;
        int valueAccess = 0;
        Node<T> prev = null;
        Node<T> current = head;

        while (current != null) {
            access++;
            if (current.value.compareTo(val) == 0) {
                if (prev != null) {
                    prev.next = current.next;
                    current.next = head;
                    head = current;
                    valueAccess++;
                }
                return access + valueAccess;
            }
            prev = current;
            current = current.next;
        }

        return -1;
    }

    public void searchAndMoveFromFile(String fileName) {
        int totalAccess = 0;
        int totalVal = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    int intValue = Integer.parseInt(value);
                    int access = searchAndMoveToBeginning(intValue);
                    if (access != -1) {
                        totalAccess += access;
                        totalVal++;

                    }
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("exception");
        }

        double averageAccess = (double) totalAccess / numUnique;

        System.out.println("Total Memory Accesses: " + totalAccess);
        System.out.println("Average Memory Accesses: " + averageAccess);


    }
}



