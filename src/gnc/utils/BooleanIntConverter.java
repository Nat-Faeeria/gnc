package gnc.utils;

/**
 * Created by nathanael on 23/05/17.
 */
public class BooleanIntConverter {

    public static Boolean int2Boolean(Integer value) {
        return !(value == 0);
    }

    public static Integer boolean2Int(Boolean value) {
        return value ? 1: 0 ;
    }

}
