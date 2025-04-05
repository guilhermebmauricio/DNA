import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Guilherme Mauricio 62746
 * @author Andre Santiago 65102
 */
public class DNASystem {
    private final List<Fragment> fragments;
    private int[][] matrix;
    private int overlap;
    private final int size;

    public DNASystem(int number) {
        fragments = new ArrayList<>(number);
        size = number;
        overlap = 0;
        matrix = new int[size][size];
    }

    public void createFragment(int start, int end) {
        fragments.add(new Fragment(start, end));
    }

    public int[] start() {
        Collections.sort(fragments);
        calculateAnswer();
        return new int[] { matrix[0][0] , overlap };
    }

    private void calculateAnswer() {
        int lastElement;
        int[] lineOverlap = new int[size];
        Fragment f1, f2;
        lastElement = size - 1;
        matrix[lastElement][lastElement] = 1;
        for (int i = lastElement - 1; i >= 0; i--) {
            f1 = fragments.get(i);
            matrix[i][i] = 0;
            lineOverlap[i] = 0;
            for (int j = lastElement; j > i; j--) {
                matrix[i][j] = matrix[i + 1][j];
                // n(j) != 0
                if (matrix[i][j] != 0) {
                    f2 = fragments.get(j);
                    if (hasIntersection(f1, f2) || f1.end() == f2.start() - 1) {
                        if (f1.start() == f2.start() && i == 0) {
                            // n(i) = n(j), o(i) = o(j)
                            if (matrix[i][i] == 0 || matrix[i][i] > matrix[i][j]
                                    || (matrix[i][i] == matrix[i][j] && lineOverlap[i] > lineOverlap[j])) {
                                matrix[i][i] = matrix[i][j];
                                lineOverlap[i] = lineOverlap[j];
                            }
                        } else if (f1.end() > f2.end() && matrix[i][j] == 1) {
                            // n(i) = n(j), o(i) = o(j)
                                matrix[i][i] = matrix[i][j];
                                lineOverlap[i] = lineOverlap[j];
                                matrix[i][j]++;
                                lineOverlap[j] += (f1.end() - f2.end());
                        } else if (f1.end() == f2.end()) {
                            // n(i) = n(j), o(i) = o(j)
                            if (matrix[i][i] == 0 || matrix[i][i] > matrix[i][j] + 1
                                    || (matrix[i][i] == matrix[i][j] && lineOverlap[i] > lineOverlap[j])) {
                                matrix[i][i] = matrix[i][j];
                                lineOverlap[i] = lineOverlap[j];
                            }
                        } else if (f1.end() < f2.end()) {
                            // n(i) = n(j) + 1, o(i) = o(j) + calculateOverlap(i, j)
                            if (matrix[i][i] == 0 || matrix[i][i] > matrix[i][j] + 1
                                    || (matrix[i][i] == matrix[i][j] + 1 && lineOverlap[i] > (lineOverlap[j] + calculateOverlap(fragments.get(i), fragments.get(j))))) {
                                matrix[i][i] = matrix[i][j] + 1;
                                lineOverlap[i] = lineOverlap[j] + calculateOverlap(f1, f2);
                            }
                        }
                    }
                }
            }
        }
        overlap = lineOverlap[0];
    }

    private boolean hasIntersection(Fragment fragment1, Fragment fragment2) {
        return !(fragment1.end() < fragment2.start());
    }

    private int calculateOverlap(Fragment fragment1, Fragment fragment2) {
        return 1 + Math.min(fragment1.end(), fragment2.end()) - Math.max(fragment1.start(), fragment2.start());
    }

}
