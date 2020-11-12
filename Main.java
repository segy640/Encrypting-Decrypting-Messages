package encryptdecrypt;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    String mode = "enc";
    String algorithm = "shift";
    char[] data = "".toCharArray();
    int key = 0;
    String in;
    String out;
    boolean isDataProvided = false;
    boolean isInputFile = false;
    boolean isOutputFile = false;

    public static void main(String... args) {
        Main main = new Main();
        main.getCMDInput(args);
        main.run();
        main.printData(main.getData());
    }

    public void run() {
        if (getMode().equals("dec")) {
            setKey(getKey() * (-1));
        }
        if (isDataProvided & isInputFile) {
            setData(encrypt(getData(), getKey()));
        } else {
            if (isInputFile) {
                setData(encrypt(readFromFile(getIn()), getKey()));
            } else if (isDataProvided) {
                   setData(encrypt(getData(), getKey()));
                   System.out.println(getData());
            }
        }
    }

    public void getCMDInput(String... args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    setMode(args[++i]);
                    break;
                case "-key":
                    setKey(Integer.parseInt(args[++i]));
                    break;
                case "-data":
                    setData(args[++i].toCharArray());
                    setIsData(true);
                    break;
                case "-in":
                    setIn(args[++i]);
                    setIsIn(true);
                    break;
                case "-out":
                    setOut(args[++i]);
                    setIsOut(true);
                    break;
                case "-alg":
                    setAlgorithm(args[++i]);
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
    }

    private char[] encrypt(char[] data, int key) {
        if (algorithm.equals("unicode")) {
            CaesarCipher unicode = new UnicodeCipher();
            return unicode.cipher(data, key);
        }
        CaesarCipher shift = new ShiftCipher();
        return shift.cipher(data, key);
    }

    public void printData(char[] data) {
        if (isOutputFile) {
            writeToFile(getOut());
        } else {
            System.out.println(String.copyValueOf(data));
        }
    }

     public char[] readFromFile(String in) {
        File file = new File(in);
        try(Scanner scanner = new Scanner(file)) {
            setData(scanner.nextLine().toCharArray());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeToFile(String out) {
        File file = new File(out);
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(String.copyValueOf(getData()));
                    } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public char[] getData() {
        return data;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public boolean isData() {
        return isDataProvided;
    }

    public void setIsData(boolean data) {
        isDataProvided = data;
    }

    public boolean isIn() {
        return isInputFile;
    }

    public void setIsIn(boolean in) {
        isInputFile = in;
    }

    public boolean isOut() {
        return isOutputFile;
    }

    public void setIsOut(boolean out) {
        isOutputFile = out;
    }

}