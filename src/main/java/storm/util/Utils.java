package storm.util;

/**
 * Created by luohui on 15/11/18.
 */
public class Utils {
    public static void sleep(long n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
