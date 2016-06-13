import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Created by Admin on 6/12/2016.
 * S:\Folder of Holding\Downloads\archlinux-2016.06.01-dual.iso
 */
public class NiChe {
    NiChe(String[] arr) throws IOException, NoSuchAlgorithmException {
        String file_path = new String();
        Scanner kbd = new Scanner(System.in);
        String mode = new String();
        String ref = new String();

        if(arr.length == 1) {
            System.out.println("This program can do MD5 and SHA1 digests for input files:");
            System.out.println("\tjava NiChe [foo]    //one argument shows this help message");
            System.out.println("\tjava NiChe [file path] [mode]");
            System.out.println("\tjava NiChe [file path] [hash reference] [mode]");
            System.exit(0);
        }
        else if(arr.length == 2) {
            file_path = arr[0];
            mode = arr[1];
        }
        else if(arr.length == 3) {

        }
        else {
            System.out.print("Path to file: ");
            file_path = kbd.nextLine();
            System.out.print("[1. MD5] or [2. SHA1]: ");
            mode = kbd.next();
            mode = mode.toLowerCase();

            while(!(mode.equals("1") || mode.equals("2") || mode.equals("md5") || mode.equals("sha1"))) {
                System.out.println("Option not available. Please select a mode ([1 = MD5] or [2 = SHA1]):");
                mode = kbd.next();
            }

            System.out.print("Please enter the hash reference (can be found on source website): ");
            ref = kbd.next();
        }

        if(file_path.equals(null) || file_path.equals("\0")) {
            System.out.println("ERROR: No file path defined.");
            System.exit(-1);
        }

        Checksum cs = new Checksum(ref);

        if(mode.equals("1") || mode.equals("md5")) {
            System.out.println("MD5 digest of file --> " + cs.calculateMD5(file_path));
            System.out.println("Reference provided --> " + cs.getReference());
            System.out.println("Digest and reference are equal? --> " + cs.compare());
        }
        else if(mode.equals("2") || mode.equals("sha1")){
            System.out.println("SHA1 digest of file --> " + cs.calculateSHA1(file_path));
            System.out.println("Reference provided  --> " + cs.getReference());
            System.out.println("Digest and reference are equal? --> " + cs.compare());
        }
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException{
        NiChe ni = new NiChe(args);
    }
}
