import java.util.Scanner;

//import hsa.*;
public class Send {
    public static void main(String args[]){
        Scanner Stdin = new Scanner(System.in);
        int error = -1;
        do {
            System.out.print("IP Address: ");
            String ipAddress = Stdin.nextLine();
            System.out.print("Message: ");
            String message = Stdin.nextLine();
            error = NetIO.sendRequest(message, ipAddress);
        } while(true);
    }
}
