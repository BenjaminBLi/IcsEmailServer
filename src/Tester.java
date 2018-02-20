public class Tester{
	public static void main(String[] args){
	    Globals.availableList = new AvailableList();
	    Message c = new Message();
	    int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
        if(error == Globals.PROCESS_OK){
            c.deleteFromMessagesFile(5);
            System.out.println(Globals.availableList);
        }
        FileIO.closeMessagesFile();
    }
}