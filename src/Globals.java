import java.io.*;
public class Globals {
    public static final String MESSAGES_FILE = "_messages.txt";
    public static RandomAccessFile msg = null;
    public static AvailableList availableList;

    public static final int PROCESS_ERROR = -1;
    public static final int PROCESS_OK = 0;
    public static int totalRecordsInMessageFile = 0;
    
    public static final int RECORD_DATA_LEN = 80;
    public static final int NEXT_RECORD_LEN = 4;
    public static final int RECORD_LEN = RECORD_DATA_LEN + NEXT_RECORD_LEN;
    
    public static final int END_OF_MESSAGE = -1;
    public static final char BLANK = (char)32;
    public static final int APPEND = 1;
    public static final int MODIFY = 2;

    public static final int AVAILABLE_LIST_EMPTY = -1;
    public static final int DELETED = '*';

}
