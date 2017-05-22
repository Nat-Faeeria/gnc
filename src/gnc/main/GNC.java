package gnc.main;

import gnc.utils.Constants;

/**
 * Created by nathanael on 22/05/17.
 */
public class GNC {

    public static void main(String[] args) {
        try {
            Class.forName(Constants.JDBC_DRIVER_CLASS_NAME).newInstance();
        } catch (Exception e) {
            System.out.println("Error while instanciating " +
                    "the connection to the DB "+e.getMessage());
            e.printStackTrace();
        }
    }
}
