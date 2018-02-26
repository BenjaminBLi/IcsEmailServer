public class TestingStuff {
    public static void main(String args[]){
	//test 1
	/*
	Record record = new Record();
	byte[] z = record.getDataArray();
	boolean passed = true;
	for(int i = 0; i < z.length; i++)
	    passed &= (z[i] == (byte)Globals.BLANK);
	System.out.println("All array elements are blank: " + passed);
       
	System.out.println("Next variable: " + (record.getNext() == Globals.END_OF_MESSAGE));
	
	String test = record.getData();
	passed = true;
	for(int i = 0; i < test.length(); i++)
	    passed &= (test.charAt(i) == ' ');
	System.out.println("Data string: " + passed);
	
	//test 2
	System.out.println("-----------------------------");
	record = new Record("Hello Sun!", 6);
	z = record.getDataArray();
	for(int i = 0; i < z.length; i++)
	    System.out.println((char)z[i] + " " + (byte)z[i]);
	System.out.println("next: " + record.getNext());
	System.out.println(record.getData());
	System.out.println("length: " + (record.getData().length() - "Hello Sun!".length()));
	*/
	//opening/closing files
	
	//Test 1a
	/*
	int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if(error == Globals.PROCESS_OK){
	    String x = "";
	    for(int i = 0; i < Globals.RECORD_DATA_LEN; i++)
		x += "a";
	    Record r = new Record(x, 4);
	    r.writeToMessagesFile(0, Globals.APPEND);
	    FileIO.closeMessagesFile();
	} else {
	    System.out.println("Error opening messages file");
	}*/
	
	//Test 1b
	/*
	error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if(error == Globals.PROCESS_OK){
	    Record r = new Record();
	    r.readFromMessagesFile(0);
	    System.out.println(r);
	    FileIO.closeMessagesFile();
	} else {
	    System.out.println("Error opening messages file");
	}*/
	
	//test 2a
	
	//int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	/*
	if(error == Globals.PROCESS_OK){
	    Record r = new Record("Dear Susan, how are you today? I am going to try to write you more frequently ab", 2);
	    r.writeToMessagesFile(0, Globals.APPEND);
	    r = new Record("Hi Johnny, do you want to go to the movies tonight? I have free passes. Suzie", Globals.END_OF_MESSAGE);
	    r.writeToMessagesFile(1, Globals.APPEND);
	    r = new Record("out the material that we had left open last time. It seems that the entries that", 3);
	    r.writeToMessagesFile(2, Globals.APPEND);
	    r = new Record("you had included in the invoices contain some calculation errors. For example, t", 4);
	    r.writeToMessagesFile(3, Globals.APPEND);
	    r = new Record("he first item has a price of $39.75 and there were 5 items ordered. The resultin", 6);
	    r.writeToMessagesFile(4, Globals.APPEND);
	    r = new Record("To everyone at the sales department: This is a reminder that our monthly luncheo", 8);
	    r.writeToMessagesFile(5, Globals.APPEND);
	    r = new Record("6 g price should be $39.75 x 5 = $198.75 but you have it showing as $201.25, and t", 7);
	    r.writeToMessagesFile(6, Globals.APPEND);
	    r = new Record("he tax is calculated based on this figure of $201.25. Could you please review th", 9);
	    r.writeToMessagesFile(7, Globals.APPEND);
	    r = new Record("n will take place at Favourite Pizza, 275 Pepperoni Road. See you all there.", Globals.END_OF_MESSAGE);
	    r.writeToMessagesFile(8, Globals.APPEND);
	    r = new Record("e entire worksheet and email me a new copy. I truly appreciate it. Sincerely Pet", 10);
	    r.writeToMessagesFile(9, Globals.APPEND);
	    r = new Record("er, Inventory Manager.", Globals.END_OF_MESSAGE);
	    r.writeToMessagesFile(10, Globals.APPEND);
	} else {
	    System.out.println("Error opening messages file");
	}*/
	
	//test 2b
	/*error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if(error == Globals.PROCESS_OK){
	    for(int i = 0; i < Globals.totalRecordsInMessageFile; i++){
		Record r = new Record();
		r.readFromMessagesFile(i);
		System.out.println(r);
	    }
	    FileIO.closeMessagesFile();
	} else {
	    System.out.println("Error opening messages file");
	}*/
	
	/*Message message = new Message();
	message.readFromMessagesFile(1);
	System.out.println(message);
	
	message = new Message();
	message.readFromMessagesFile(0);
	System.out.println(message);
	
	message = new Message();
	message.readFromMessagesFile(5);
	System.out.println(message);
	
	FileIO.closeMessagesFile();
	//System.out.println(Globals.totalRecordsInMessageFile);*/
	Globals.availableList = new AvailableList();
	/*list.addRecord(5);
	list.addRecord(7);
	list.addRecord(0);
	System.out.println(list);
	System.out.println("NEXT RECORD: " + list.getNextRecord());
	System.out.println(list);
	System.out.println("NEXT RECORD: " + list.getNextRecord());
	System.out.println(list);
	System.out.println("NEXT RECORD: " + list.getNextRecord());
	System.out.println(list);*/
	Record record = new Record();
	Message message = new Message();
	int error = FileIO.openMessagesFile(Globals.MESSAGES_FILE);
	if(error == Globals.PROCESS_OK){
	    //record.deleteFromMessagesFile(1);
	    //record.deleteFromMessagesFile(5);
	    //record.deleteFromMessagesFile(8);
	    //Test 1
	    //message.setText("Hey my name is Alex");
	    //Test 2
	    //message.setText("Hey my name is Alex");
	    //message.writeToMessagesFile();
	    //message.setText("Hey my name is Mahir");
	    //Test 3
	    /*String test = "";
	    for(int i = 0; i < 80*3;i++)
		test += 1;
	    message.setText(test);*/
	    //Test 4
	    /*String test = "";
	    for(int i = 0; i < 80*5;i++)
		test += 1;
	    message.setText(test);*/
	    //message.writeToMessagesFile();
	    message.deleteFromMessagesFile(0);
	    message.deleteFromMessagesFile(5);
	    System.out.println(Globals.availableList);
	    FileIO.closeMessagesFile();
	}
    }
}
