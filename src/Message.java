import jdk.nashorn.internal.objects.Global;

import java.util.Date;
public class Message {
    private String text = null, sender, receiver, dateTime, subject;
    private char command, marker, eosMarker;
    public Message(){
	text = null;
    }

    public Message(String s){
        setMessage(s);
    }

    public void setMessage(String s){
        command = s.charAt(Globals.COMMAND_POS);
        sender = s.substring(Globals.SENDER_POS, Globals.SENDER_POS+Globals.SENDER_LEN);
        receiver = s.substring(Globals.RECEIVER_POS, Globals.RECEIVER_POS + Globals.RECEIVER_LEN);
        dateTime = s.substring(Globals.DATE_TIME_POS, Globals.FIRST_RECORD_MARKER_POS);
        marker = s.charAt(Globals.FIRST_RECORD_MARKER_POS);
        subject = s.substring(Globals.FIRST_RECORD_MARKER_POS + 1, s.indexOf(Globals.END_OF_SUBJECT_MARKER));
        eosMarker = s.charAt(s.indexOf(Globals.END_OF_SUBJECT_MARKER));
        text = s.substring(s.indexOf(Globals.END_OF_SUBJECT_MARKER)+1);
    }

    public String getMessage(){
        return command + sender + receiver + dateTime + marker + subject + eosMarker + text;
    }

    public char getCommand() {
        return command;
    }

    public char getMarker() {
        return marker;
    }

    public String getDateTime() {
        return dateTime;
    }

    public char getEosMarker() {
        return eosMarker;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public void setCommand(char command) {
        this.command = command;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setEosMarker(char eosMarker) {
        this.eosMarker = eosMarker;
    }

    public void setMarker(char marker) {
        this.marker = marker;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
            if(nextPos == Globals.EMPTY_AVAILABLE_LIST){
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

    @Override
    public String toString() {
        return "Command      : " + command + "\n" +
                "Sender:      : " + sender + "\n" +
                "Receiver     : " + receiver + "\n" +
                "Date/Time    : " + dateTime + "\n" +
                "Marker       : " + marker + "\n" +
                "Subject      : " + subject + "\n" +
                "EOS Marker   : " + eosMarker + "\n" +
                "Message Text : " + text;
    }
}

