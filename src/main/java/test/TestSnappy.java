package test;
import org.xerial.snappy.Snappy;

/**
 * Created by wangyue on 15/8/8.
 */
public class TestSnappy {
    public static void main(String[] args) throws Exception{

        String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of "
                + "Snappy, a fast compresser/decompresser.";
        byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
        byte[] uncompressed = Snappy.uncompress(compressed);

        String result = new String(uncompressed, "UTF-8");
        System.out.println(result);
    }
}
