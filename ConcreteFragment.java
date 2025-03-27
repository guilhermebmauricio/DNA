class ConcreteFragment implements AbstractFragment {
    int min, max;
    ConcreteFragment(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    
}
