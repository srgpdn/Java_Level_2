package lesson3;

import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, String> map;

    public PhoneBook() {
        map = new HashMap<>();
    }

    public void add(String family, String phone) {
        String p = map.getOrDefault(family, "");
        if (p.equals(""))
            map.put(family, phone);
        else
            map.put(family, p + ", " + phone);

    }

    public String get(String family) {
        return map.get(family);
    }
}
