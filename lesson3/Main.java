package lesson3;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private HashMap<String, Integer>  hmap = new HashMap<>();
    private String[] arwords = {"A", "B", "C", "D", "E",
                                "F", "G", "H", "I", "J",
                                "B", "D", "I", "X", "X", "Z"};

    public static void main(String[] args) {
	// write your code here
        Main cm = new Main();
        cm.fullHashMap();
        cm.uniqueList();
        cm.countWords();

    }

    private void fullHashMap() {
        for (int i = 0; i < arwords.length; i++) {
            hmap.put(arwords[i], hmap.getOrDefault(arwords[i], 0) + 1);
        }
    }

    private void uniqueList() {
        ArrayList<String> list = new ArrayList<>();
        for ( HashMap.Entry<String, Integer> map : hmap.entrySet() ) {
            String key = map.getKey();
            int value = map.getValue();
            if (value == 1)
                list.add(key);
        }
        System.out.println(list.toString());

    }

    private void countWords() {
        for (HashMap.Entry<String, Integer> map : hmap.entrySet()) {
            String key = map.getKey();
            int value = map.getValue();
            System.out.println(key + " - " + value);
        }
    }


}


