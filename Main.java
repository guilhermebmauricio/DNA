import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        DNASystem dna = new DNASystem(number);
        for (int i = 0; i < number; i++) {
            String input = br.readLine();
            String[] tokens = input.split(" ");
            dna.createFragment(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }
        int[] ans = dna.start();
        System.out.println(ans[0]+" "+ans[1]);
    }
}