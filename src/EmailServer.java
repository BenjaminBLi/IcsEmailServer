public class EmailServer{
    public static String previousClientIPAddress = Globals.STR_NULL;
    public static long previousArrivalTime = 0;
    public static long currentArrivalTime = 0;

    public static void printMessage(Tree t){
        if(t.getRoot() == null)return;
        printMessage(new Tree(t.getRoot().getLeft()));
        Message m = new Message();
        int rN = t.getRoot().getRecordNumber();
        m.readFromMessagesFile(rN);
        System.out.println(m);
        System.out.println("--------------");
        printMessage(new Tree(t.getRoot().getRight()));
    }
    public static void main(String args[]){
        int command = -1;
        int error = -1;

        error = Init.initializeSystem();
        if(error == Globals.PROCESS_OK){
            Message message = new Message();
            int recordNumber = 0;
            TNode p = null;
            TNode q = null;

            String identification = Globals.STR_NULL;
            char serverCommand = 0;
            do {
                System.out.println("Waiting...");
                String request = NetIO.receiveRequest();
                serverCommand = request.charAt(0);
                switch(serverCommand){
                    case Globals.SEND_MESSAGE :
                        request = Utils.setReceivingTime(request);
                        message = new Message(request);
                        recordNumber = message.writeToMessagesFile();

                        identification = message.getIdSenderFirst();
                        p = new TNode(identification, recordNumber, null, null, null);
                        Globals.senderIndex.insertNode(p);

                        identification = message.getIdReceiverFirst();
                        p = new TNode(identification, recordNumber, null, null, null);
                        Globals.receiverIndex.insertNode(p);
                        break;
                    default:
                        System.out.println("Unknown request: " + request);
                        break;
                }
            } while(serverCommand != Globals.SERVER_SHUTDOWN);
        }
      /*  int error = Init.initializeSystem();
	if(error == Globals.PROCESS_OK){
	    Message message = new Message();
	    //Test 1
	    Globals.senderIndex.printTree();
	    int recordNumber = Globals.senderIndex.findNode("JB", 1).getRecordNumber();
	    message.readFromMessagesFile(recordNumber);
	    System.out.println(message);
	    System.out.println("--------------");
	    System.out.println("--------------");

	    //Test 2
	    printMessage(Globals.senderIndex);

	    //Test 3
	    TNode n = Globals.senderIndex.findNode("bJ", 1);
	    if(n == null)System.out.println("Not found");
	    else {
		recordNumber = n.getRecordNumber();
		message.readFromMessagesFile(recordNumber);
		System.out.println(message);
	    }

	    FileIO.closeMessagesFile();
	} else {
	    Error.report(0);
	}*/
    }
}
