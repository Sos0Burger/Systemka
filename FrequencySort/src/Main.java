import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{10,6,2,6,2,10,10,7,7,7,8,9,8,8,9,9,9,9};
        float counter;
        boolean isDuplicate;
        Iterator<Float> iter;
        HashMap<Float, Integer> map = new HashMap<>();
        ArrayList<Integer> uniqueValues = new ArrayList<>();
        ArrayList<Float> sequence = new ArrayList<>();
        uniqueValues.add(array[0]);
        //поиск уникальных значений
        for(int i = 0;i<array.length;i++){
            isDuplicate = false;
            for(int j = 0;j<uniqueValues.size();j++){
                if(array[i]==uniqueValues.get(j)){
                    isDuplicate = true;
                }

            }
            if(!isDuplicate) {
                uniqueValues.add(array[i]);
            }
        }

        //подсчет количества

        for (int j = 0;j<uniqueValues.size();j++){
            counter = 0;
            for(int i = 0;i<array.length;i++){
                if(array[i]==uniqueValues.get(j)){
                    counter++;
                }
            }
            iter = map.keySet().iterator();
            while (iter.hasNext()){
                if(iter.next() == counter){
                    counter-=0.000001;
                }
            }
            map.put(counter, uniqueValues.get(j));
        }
        iter = map.keySet().iterator();

        while (iter.hasNext()){
            sequence.add(iter.next());
        }
        Collections.sort(sequence);

        for (int j = sequence.size()-1; j>=0;j--) {
            for(int i = 0;i<Math.round(sequence.get(j));i++){
                System.out.print(map.get(sequence.get(j))+" ");
            }
        }
    }
}
