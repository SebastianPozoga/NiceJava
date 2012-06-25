package eu.pozoga.nice.code.helper;

/**
 *
 * @author Sebastian Pożoga
 */
public class StringHelper {

    public static String UpperFirstChar(String in){
        return in.substring(0, 1).toUpperCase()+in.substring(1);
    }

    public static String LowerFirstChar(String in){
        return in.substring(0, 1).toLowerCase()+in.substring(1);
    }

}
