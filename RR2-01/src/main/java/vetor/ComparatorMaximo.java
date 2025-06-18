package vetor;

import java.util.Comparator;

public class ComparatorMaximo <T> implements Comparable<T> {
    
    @Override
    public int compareTo(T o1, T o2){
        return o1.compareTo(o2);
    }
}
