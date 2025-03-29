public abstract interface AbstractSystem {
    void addFragment(int min, int max);
    void run();
    public int getMinFragments();
    public int getMinOverlap();
}