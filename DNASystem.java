import java.util.*;

public class DNASystem {

    private final List<Fragment> fragments;
    private int[][] result;
    private final int [][] fragmentMatrix;
    private final int size;

    public DNASystem(int number) {
        fragments = new ArrayList<>(number);
        size = number;
        result = new int[2][size];
        fragmentMatrix = new int[size][size];
    }

    public void createFragment(int start, int end) {
        fragments.add(new Fragment(start, end));
    }

    public int[] start() {
        Collections.sort(fragments);
        populateMatrix();
        populateResult();
        calculateAnswer();
        return new int[]{result[0][size-1], result[1][size-1]};
    }

    private void populateResult() {
        result[0][0] = 1;
        result[1][0] = 0;
        for(int i = 1; i < size; i++) {
            result[0][i] = size + 1;
            result[1][i] = size*10000;
        }
    }

    private void calculateAnswer() {
        for(int i = 1; i < size; i++) {
            for(int k = 0; k < i; k++) {
                if(fragmentMatrix[k][i] != 0) {
                    int overlap = 0;
                    int value = fragmentMatrix[k][i]+result[0][k] -1;
                    if(fragUnion(fragments.get(k), fragments.get(i)) == 1){
                        overlap += result[1][k];
                    }
                    else{ overlap = calculateOverlap(k,i)+result[1][k];}
                    if(value < result[0][i] || (value == result[0][i] &&  overlap < result[1][i])) {
                        result[0][i] = value;
                        result[1][i] = overlap;
                    }
                }
            }
        }
    }

    private int calculateOverlap(int k, int i) {
        Fragment temp1 = fragments.get(k);
        Fragment temp2 = fragments.get(i);
        return Math.min(temp1.end(),temp2.end()) - Math.max(temp1.start(),temp2.start()) + 1;
    }

    private void populateMatrix() {
        for(int i = 0; i < size; i++) {
            for(int j = i; j < size; j++) {
                fragmentMatrix[i][j] = fragUnion(fragments.get(i),fragments.get(j));
                if(fragmentMatrix[i][j] == 0) {
                    break;
                }
            }
        }
    }

    private int fragUnion(Fragment fragment, Fragment fragment1) {
        if(fragment.equals(fragment1)) {
            return 1;
        }
        return intersect(fragment, fragment1);
    }

    private int intersect(Fragment fragment, Fragment fragment1) {
        if( fragment.start() == fragment1.start()|| (fragment.end() >= fragment1.end())) {
            return 1;
        }
        else if(fragment.end() >= fragment1.start()  || fragment.end() == fragment1.start()-1) {
            return 2;
        }
        return 0;
    }
}
