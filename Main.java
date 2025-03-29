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
        int fragments;
        fragments =  Integer.parseInt(br.readLine());
        
    }
}