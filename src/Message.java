public class Message {
    private String text = null;
    public Message(){
	text = null;
    }
    public void readFromMessagesFile(int recordNumber) {
		String data = "";
		Record record = new Record();
		do {
			record.readFromMessagesFile(recordNumber);
			data += record.getData();
			recordNumber = record.getNext();
		} while (recordNumber != Globals.END_OF_MESSAGE);
		text = data;
	}

	public int writeToMessagesFile(){
        String s = this.text;
        int start = Globals.totalRecordsInMessageFile;
        while(s != null && s.length() != 0){
            int nextPos = Globals.availableList.getNextRecord();
            if(nextPos == Globals.AVAILABLE_LIST_EMPTY){
                int pos = Globals.totalRecordsInMessageFile;
                if(s.length() <= Globals.RECORD_DATA_LEN){
                    Record c = new Record(s, Globals.END_OF_MESSAGE);
                    c.writeToMessagesFile(pos, Globals.APPEND);
                    s = "";
                }else{
                    Record c = new Record(s.substring(0, Globals.RECORD_DATA_LEN), pos+1);
                    c.writeToMessagesFile(pos, Globals.APPEND);
                    s = s.substring(Globals.RECORD_DATA_LEN);
                }
            }else{
                if(s.length() <= Globals.RECORD_DATA_LEN){
                    Record c = new Record(s, Globals.END_OF_MESSAGE);
                    c.writeToMessagesFile(nextPos, Globals.MODIFY);
                    s = "";
                }else{
                    Record c = new Record(s.substring(0, Globals.RECORD_DATA_LEN), nextPos);
                    c.writeToMessagesFile(nextPos, Globals.MODIFY);
                    s = s.substring(Globals.RECORD_DATA_LEN);
                }
            }
        }
        return start;
    }

    public void deleteFromMessagesFile(int recordNumber){
        Record record = new Record();
        int error = record.readFromMessagesFile(recordNumber);
        while(error == Globals.PROCESS_OK && recordNumber!= Globals.END_OF_MESSAGE) {
            error = record.deleteFromMessagesFile(recordNumber);
            recordNumber = record.getNext();
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        return "Message text: " + text;
    }
}

