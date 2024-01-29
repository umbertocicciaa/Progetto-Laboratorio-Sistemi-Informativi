package ui;

/**
 * @author umbertodomenicociccia
 */
public final class Util {
    private final static String select = "select";
    private final static String from = "from";
    private final static String where = "where";

    private final static String alter = "alter";
    private final static String table = "table";

    private final static String create = "create";

    private final static String index = "index";

    public static boolean stringheVerificate(String... strings) {
        boolean verificato = true;
        for (String stringa : strings) {
            if (stringa.isEmpty() ||
                    stringa.toLowerCase().contains(select) ||
                    stringa.toLowerCase().contains(from) ||
                    stringa.toLowerCase().contains(where) ||
                    stringa.toLowerCase().contains(alter) ||
                    stringa.toLowerCase().contains(table) ||
                    stringa.toLowerCase().contains(create) ||
                    stringa.toLowerCase().contains(index)
            ) {
                verificato = false;
                break;
            }
        }
        return verificato;
    }

    public static boolean stringheVerificatePossibileEmpty(String... strings) {
        boolean verificato = true;
        for (String stringa : strings) {
            if (stringa.toLowerCase().contains(select) ||
                    stringa.toLowerCase().contains(from) ||
                    stringa.toLowerCase().contains(where) ||
                    stringa.toLowerCase().contains(alter) ||
                    stringa.toLowerCase().contains(table) ||
                    stringa.toLowerCase().contains(create) ||
                    stringa.toLowerCase().contains(index)) {
                verificato = false;
                break;
            }
        }
        return verificato;
    }

}
