public class TestingFeb8 {
    public static void main(String args[]){
	Globals.availableList = new AvailableList();
	
	int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if(error == Globals.PROCESS_OK){
	    Message message = new Message();
	    message.readFromMessagesFile(0);
	    System.out.println(message);
	}
    }
}
