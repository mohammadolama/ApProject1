package Server;

import java.util.Scanner;

public class ServerMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 55; i++) {
            String st = scanner.nextLine();
            String res = st.replace("Model", "Server.Model");
            System.out.println(res);
        }
    }
}
