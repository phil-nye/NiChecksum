import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.file.Files.newInputStream;

/**
 * Created by Admin on 6/12/2016.
 */
public class Checksum {
    public String input;
    public String original;

    public Checksum(String orig) {
        input = new String();
        original = orig;
    }

    public String getFileHex() {
        return input;
    }

    public String getReference() {
        return original;
    }

    public boolean compare() {
        if(input.equals(original)) {
            return true;
        }

        return false;
    }

    public String calculateMD5(String file_directory) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(Paths.get(file_directory));
             DigestInputStream dis = new DigestInputStream(is, md))
        {
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1;) {
                md.update(buffer, 0, read);
            }
        }
        byte[] mdbytes = md.digest();

        input = toHexString(mdbytes);

        return input;
    }

    public String calculateSHA1(String file_directory) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        try (InputStream is = Files.newInputStream(Paths.get(file_directory));
             DigestInputStream dis = new DigestInputStream(is, md))
        {
            final byte[] buffer = new byte[1024];
            for (int read = 0; (read = is.read(buffer)) != -1;) {
                md.update(buffer, 0, read);
            }
        }
        byte[] mdbytes = md.digest();

        input = toHexString(mdbytes);

        return input;
    }

    public String toHexString(byte[] my_bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        char[] hexChars = new char[my_bytes.length * 2];
        int v;

        for(int i = 0; i < my_bytes.length; i++) {
            v = my_bytes[i] & 0xFF; //set as int
            hexChars[i * 2] = hexArray[v >>> 4];    // '>>>' shift right zero fill; sets first value in 0x.. format
            hexChars[i * 2 + 1] = hexArray[v & 0x0F];   //  sets second value in 0x.. format
        }
        return new String(hexChars);
    }
}
