public class Error {
    public static void report(int errorNumber){
        String errorMessage = "";
        switch(errorNumber){
            case 00: errorMessage = "Error initialising system";
                 break;
            case 01: errorMessage = "01 Error opening messages " + Globals.MESSAGES_FILE;
                 break;
            case 02: errorMessage = "02 Error opening available list file " + Globals.AVAILABLE_LIST_FILE;
                 break;
            default: errorMessage = "Unknown error";
                 break;
        }
        System.out.println(errorMessage);
    }
}
