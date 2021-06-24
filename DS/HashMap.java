package DS;

public class HashMap {
    int size= 20;
    LinkedList<Entry> [] hashMap;

    public HashMap() {
        hashMap =new LinkedList[size];
    }

    public void put(String key,String value){
        int index=key.hashCode()%size;
        if(hashMap[index]==null){
            hashMap[index]=new LinkedList<>();
            hashMap[index].add(new Entry(key,value));
        }
        else{
            for (int i = 0; i < hashMap[index].size; i++) {
                if(hashMap[index].get(i).key.equals(key)) {
                    hashMap[index].get(i).value = value;
                    return;
                }
            }
            hashMap[index].add(new Entry(key,value));
        }
    }

    public Entry get(String key){
        int index=key.hashCode()%size;
        if(hashMap[index]==null)
            return null;
        for (int i = 0; i < hashMap[index].size; i++) {
            if(hashMap[index].get(i).key.equals(key)) {
                return hashMap[index].get(i) ;
            }
        }
        return null;
    }

    static class Entry{
        String key;
        String  value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}