import java.util.*;

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
        lastElement = size - 1;
        matrix[lastElement][lastElement] = 1;
        for (int i = lastElement - 1; i >= 0; i--) {
            matrix[i][i] = 0;
            lineOverlap[i] = 0;
            for (int j = lastElement; j > i; j--) {
                matrix[i][j] = matrix[i + 1][j];
                // n(j) != 0
                if (matrix[i][j] != 0) {
                    // há interseção
                    if (hasIntersection(fragments.get(i), fragments.get(j))) {
                        if (i == 0 && (fragments.get(i).start() == fragments.get(j).start())) {
                            if (matrix[i][i] == 0 || matrix[i][i] > matrix[i][j]
                                    || (matrix[i][i] == matrix[i][j] && lineOverlap[i] > lineOverlap[j])) {
                                matrix[i][i] = matrix[i][j];
                                lineOverlap[i] = lineOverlap[j];
                            }
                        } else if (fragments.get(i).end() >= fragments.get(j).end()) {
                            if (matrix[i][i] == 0 || matrix[i][i] > matrix[i][j]
                                    || (matrix[i][i] == matrix[i][j] && lineOverlap[i] > lineOverlap[j])) {
                                matrix[i][i] = matrix[i][j];
                                lineOverlap[i] = lineOverlap[j];
                            }
                            if (fragments.get(i).start() != fragments.get(j).start()) {
                                matrix[i][j]++;
                                lineOverlap[j] += calculateOverlap(fragments.get(i), fragments.get(j));
                            }
                        } else {
                            // caso geral: n(i) = n(j) + 1, o(i) = o(j) + calculateOverlap(i, j)
                            if (matrix[i][i] == 0 || matrix[i][i] > matrix[i][j]
                                    || (matrix[i][i] + 1 == matrix[i][j] && lineOverlap[i] > lineOverlap[j] + calculateOverlap(fragments.get(i), fragments.get(j)))) {
                                matrix[i][i] = matrix[i][j] + 1;
                                lineOverlap[i] = lineOverlap[j] + calculateOverlap(fragments.get(i), fragments.get(j));
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
        return Math.min(fragment1.end(), fragment2.end()) - Math.max(fragment1.start(), fragment2.start());
    }

}
