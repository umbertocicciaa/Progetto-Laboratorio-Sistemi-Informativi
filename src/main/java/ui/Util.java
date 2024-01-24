package ui;
/**
 * @author umbertodomenicociccia
 * */
public final class Util {
    private final static String select = "select";
    private final static String from = "from";
    private final static String where = "where";

    public static String getSelect() {
        return select;
    }

    public static String getFrom() {
        return from;
    }

    public static String getWhere() {
        return where;
    }

    public static boolean stringheVerificate(String ...strings){
        boolean verificato=true;
        for(String stringa:strings){
            if(     stringa.isEmpty() ||
                    stringa.toLowerCase().contains(select)||
                    stringa.toLowerCase().contains(from) ||
                    stringa.toLowerCase().contains(where)){
                verificato=false;
                break;
            }
        }
        return verificato;
    }


}
