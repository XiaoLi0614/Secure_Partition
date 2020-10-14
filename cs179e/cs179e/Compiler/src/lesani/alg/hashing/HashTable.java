package lesani.alg.hashing;

import java.util.*;
import java.lang.reflect.Array;

// A linear-probing hash table implementation

public class HashTable<K, V> {

   private Element[] elements;
   private int size;
   private int capacity;

   private double maxLoadRatio; // determines how full the array can get before resizing occurs; default 1/2
   private double minLoadRatio; // determines how empty the array can get before resizing occurs; default 3/4
   private double defaultLoadRatio; // determines how full the array should be made when resizing; default 1/4
   private Element removed = new Element(null, null);
   // We store removed at the place of removed elements.
   // A better way is to rehash the elements after the removed one.

   public HashTable(double maxLoadRatio, double minLoadRatio, double defaultLoadRatio, int capacity){

      assert defaultLoadRatio < maxLoadRatio && maxLoadRatio < 1;
      assert 0 < minLoadRatio && minLoadRatio < defaultLoadRatio;

      size = 0;
      this.maxLoadRatio = maxLoadRatio;
      this.minLoadRatio = minLoadRatio;
      this.capacity = capacity;
      this.defaultLoadRatio = defaultLoadRatio;

      elements = (Element[]) Array.newInstance(Element.class, capacity); // Make the new array;
   }

   // Default the set-size ratio to 1/2
   public HashTable(double maximum, double minimum){
      this(maximum, minimum, 0.5, 10);
   }

   // Default the max-size ratio to 3/4 and the min-size ratio to 1/4.
   public HashTable(){
      this(0.75, 0.25);
   }

   /**
    * Returns the value that is mapped to the given key.
    *
    * @param key the key to locate
    * @return the value mapped to {@code key} or {@code null} if not found
    */
   public V get(K key){
      assert key != null;

      int i = hash(key) % capacity;
      int end = (i + (capacity - 1)) % capacity;
      while (true) {
         Element currentElement = elements[i];
         if (currentElement != null &&
             currentElement != removed &&
             currentElement.key.equals(key))
            return elements[i].value;
         if (currentElement == null || i == end)
            return null;
         i = (i+1) % capacity;
      }
   }

   public void put(K key, V val) {
      assert key != null;

      int i = hash(key) % capacity;
      Element currentElement = elements[i];
      while (currentElement == removed ||
            (currentElement != null && !key.equals(currentElement.key))) {
         i = (i+1) % capacity;
         currentElement = elements[i];
      }
      if (currentElement == null || currentElement == removed)
         size++;
      elements[i] = new Element(key, val);

      resizeIfNeeded();
   }

   private int hash(K key){
      return Math.abs(key.hashCode());
   }

   // Resize the array if necessary.
   private void resizeIfNeeded(){
      if (!(size < capacity * minLoadRatio || size > capacity * maxLoadRatio))
         return;

      int newCapacity = (int) (size / defaultLoadRatio);

      @SuppressWarnings("unchecked")
      Element[] newElements = (Element[]) Array.newInstance(Element.class, newCapacity);

      for(int j = 0; j < capacity; j++){
         Element q = elements[j];
         if (q == null || q == removed)
            continue;

         int i = hash(q.key) % newCapacity;
         while (newElements[i] != null)
            i = (i+1) % newCapacity;
         newElements[i] = q;
      }
      this.elements = newElements;
      this.capacity = newCapacity;
   }

   public int size() {
      return size;
   }

   public Set<K> keySet() {
      Set<K> set = new HashSet<K>(size);
      for(Element p : elements)
         if (p != null && p != removed)
            set.add(p.key);
      return set;
   }

   public boolean remove(K key) {
      assert key != null;

      int i = hash(key) % capacity;
      int end = (i + (capacity - 1)) % capacity;
      while (true) {
         Element currentElement = elements[i];
         if (currentElement != null &&
             currentElement != removed &&
             currentElement.key.equals(key)) {
            elements[i] = removed;
            size--;
            return true;
         }
         if (currentElement == null || i == end)
            return false;
         i = (i+1) % capacity;
      }
   }

   public String toString() {
      String s = "";
      for (int i = 0; i < elements.length; i++) {
         Element element = elements[i];
         if (i != elements.length-1)
            s += element + ",";
         else
            s += element;
      }
      return s;
//      return String.format("Hashtable(%.2f, %.2f, %.2f)",
//            maxLoadRatio, minLoadRatio, defaultLoadRatio);
   }

   private class Element {
      K key;
      V value;

      public Element(K key, V val){
         this.key = key;
         value = val;
      }

      @Override
      public String toString() {
         return "[" + key + "->" + value + "]";
      }
   }

   public static void main(String[] args) {
      HashTable<Integer, Integer> map = new HashTable<Integer, Integer>();
      map.put(1, 1);
      map.put(2, 2);
      map.put(3, 3);
      map.put(4, 4);
      map.put(5, 5);
      map.put(6, 6);
      map.put(7, 7);
      map.put(8, 8);
      map.put(9, 9);
      map.put(18, 18);
      System.out.println(map);

      int val = map.get(5);
      System.out.println(val);

      map.remove(5);
      System.out.println(map);

      Integer val2 = map.get(5);
      System.out.println(val2);

      map.remove(9);
      System.out.println(map);
      val = map.get(18);
      System.out.println(val);

   }

}


