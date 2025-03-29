import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class ConcreteSystem implements AbstractSystem {
    List<AbstractFragment> fragments;

    ConcreteSystem(int fragmentNumber) {
        fragments = new ArrayList<AbstractFragment>(fragmentNumber);
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

}
