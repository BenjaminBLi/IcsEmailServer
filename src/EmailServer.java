import java.io.*;

public class EmailServer {
    public static void main(String args[]) {

        int error = -1;

        error = Init.initializeSystem();

        if(error == Globals.PROCESS_OK){
            Message message = new Message();
            int recordNumber = 0;

            String id = Globals.STR_NULL;

            TNode p = null;
            TNode q = null;

            p = (Globals.senderIndex.findNode("JBOND0007SUZIECUTE00000000"));

            recordNumber = p.getRecordNumber();

            message.readFromMessagesFile(recordNumber);

            System.out.println(message);

        }else{
            Error.report(0);
        }

    }
}

