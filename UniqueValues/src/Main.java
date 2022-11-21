import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(51);
        arrayList.add(51);
        arrayList.add(51);
        arrayList.add(670);
        arrayList.add(670);
        for (int item : arrayList
        ) {
            System.out.print(item + " ");
        }
        System.out.print("\n");
        HashSet<Integer> set = new HashSet<>(arrayList);
        for (int item : set
        ) {
            System.out.print(item + " ");
        }
    }
}