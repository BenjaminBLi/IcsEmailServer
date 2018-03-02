public class Init {
    public static int initializeSystem(){
	int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if(error == Globals.PROCESS_OK){
	    error = FileIO.retrieveAvailableList(Globals.AVAILABLE_LIST_FILE);
	    if(error == Globals.PROCESS_OK){
	   		//Globals.accounts = FileIO.retrieveAccounts(Globals.ACCOUNTS_FILE);
	   		if(Globals.accounts != null){

			}
	    } else {
		Error.report(2);
	    }
	} else {
	    Error.report(1);
	}
	return error;
    }
}
