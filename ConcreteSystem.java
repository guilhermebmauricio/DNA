import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class ConcreteSystem implements AbstractSystem {
    List<AbstractFragment> fragments;
    int minFragments, minOverlap;

    ConcreteSystem(int fragmentNumber) {
        fragments = new ArrayList<AbstractFragment>(fragmentNumber);
        minFragments = 1;
        minOverlap = 0;
    }

    public void addFragment(int min, int max) {
        AbstractFragment fragment = new ConcreteFragment(min, max);
        int previous = fragments.size();
        for (int i = 0; i < fragments.size(); i++) {
            AbstractFragment f = fragments.get(i);
            if (fragment.getMin() < f.getMin()
                    || (fragment.getMin() == f.getMin() && !(fragment.getMax() > f.getMax()))) {
                fragments.add(i, fragment);
                break;
            }
            if (previous == fragments.size())
                fragments.add(fragment);
        }
    }

    //
    public void run() {
        int[][] result = new int[fragments.size()][fragments.size()];
        //cria meia tabela com valores
        for (int i = 0; i < fragments.size(); i++) {
            AbstractFragment f1 = fragments.get(i);
            for (int j = 0; j < fragments.size(); j++) {
                AbstractFragment f2 = fragments.get(j);
                if (i == j || f1.getMax() > f2.getMax())
                    result[i][j] = 1;
                else {
                    if (f1.getMax() >= f2.getMin())
                        result[i][j] = 2;
                    else {
                        result[i][j] = 0;
                        break;
                    }
                }
            }
        }
        //loop para calcular e definir o valor de minFragments e minOverlap
    }

    public int getMinFragments() {
        return minFragments;
    }

    public int getMinOverlap() {
        return minOverlap;
    }

}
