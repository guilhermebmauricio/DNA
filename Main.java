import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(System.in));
        runCommands(br);
    }

    private static void runCommands(BufferedReader br) throws IOException {
        int fragmentNumber, min, max;
        fragmentNumber = Integer.parseInt(br.readLine());
        AbstractSystem sys = new ConcreteSystem(fragmentNumber);
        for (int i = 0; i < fragmentNumber; i++) {
            min = Integer.parseInt(br.readLine());
            max = Integer.parseInt(br.readLine());
            sys.addFragment(min, max);
        }
        sys.run();
        System.out.println(sys.getMinFragments() + " " + sys.getMinOverlap());
    }
}