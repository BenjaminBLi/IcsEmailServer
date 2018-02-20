import java.io.*;
public class FileIO {

    public static int openMessagesFile(String fileName){
        try{
            Globals.msg = new RandomAccessFile(fileName, "rw");
            Globals.totalRecordsInMessageFile = (int)(Globals.msg.length() / Globals.RECORD_LEN);
            return Globals.PROCESS_OK;
        }catch(IOException e){
            return Globals.PROCESS_ERROR;
        }
    }
    public static int closeMessagesFile(){
        try{
            Globals.msg.close();
            return Globals.PROCESS_OK;
        }catch(IOException e){
            return Globals.PROCESS_ERROR;
        }
    }
    public static int saveAvailableList(String fileName){
        try{
            RandomAccessFile f = new RandomAccessFile(fileName, "rw");
            f.setLength(0);

            Available p = Globals.availableList.getHead();
            while(p != null){
                f.writeInt(p.getRecordNumber());
                p=p.getNext();
            }
            f.close();
            return Globals.PROCESS_OK;
        }catch(IOException e){
            return Globals.PROCESS_ERROR;
        }
    }
    public static int retrieveAvailableList(String fileName){
        try {
            RandomAccessFile f = new RandomAccessFile(fileName, "rw");
            long nodes = f.length()/Globals.INT_LEN;
            Globals.availableList = new AvailableList();
            for(int i = 0; i < nodes; i++){
                Globals.availableList.addRecord(f.readInt());
            }
            f.close();
            return Globals.PROCESS_OK;
        }catch(IOException e){
            return Globals.PROCESS_ERROR;
        }
    }
}
