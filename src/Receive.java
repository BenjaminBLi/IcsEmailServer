public class Receive {
    public static void main(String args[]){
        String message = "";
        do {
            System.out.println("Waiting...");
            message = NetIO.receiveRequest();
            System.out.println(message);
        } while(true);
    }
}
