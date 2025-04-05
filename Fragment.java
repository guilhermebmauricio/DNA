import static java.lang.Integer.*;

/**
 * @author Guilherme Mauricio 62746
 * @author Andre Santiago 65102
 */
public record Fragment(int start, int end) implements Comparable<Fragment> {

    @Override
    public int compareTo(Fragment o) {
        int cmp = compare(this.start, o.start);
        if (cmp != 0) {
            return cmp;
        }
        return compare(this.end, o.end);
    }
}
