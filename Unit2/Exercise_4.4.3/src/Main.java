import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("src/ficheroBMP");

        byte[] data = new byte[1024];

        int byteRead = inputStream.read(data);

        int fileSize     = (data[2] & 0xFF) | ((data[3] & 0xFF) << 8) | ((data[4] & 0xFF) << 16) | ((data[5] & 0xFF) << 24);
        int width        = (data[18] & 0xFF) | ((data[19] & 0xFF) << 8) | ((data[20] & 0xFF) << 16) | ((data[21] & 0xFF) << 24);
        int height       = (data[22] & 0xFF) | ((data[23] & 0xFF) << 8) | ((data[24] & 0xFF) << 16) | ((data[25] & 0xFF) << 24);
        int bitsPerPixel = (data[28] & 0xFF) | ((data[29] & 0xFF) << 8);

        inputStream.close();
        System.out.println("File size: " + fileSize + " bytes");
        System.out.println("Width: " + width + " px");
        System.out.println("Height: " + height + " px");
        System.out.println("Bits por píxel: " + bitsPerPixel);

    }
}