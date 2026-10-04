import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        try {
            InputStream inputStream = new FileInputStream("src/ficheroPNG");
            byte[] header = new byte[6];
            int bytesRead = inputStream.read(header);

            if (bytesRead < 2) {
                System.out.println("Archivo demasiado pequeño o no existe");
            }

            else if(header[0] == 0x42 && header[1] == 0x4D){
                System.out.println("Es un archivo BMP");
            }

            else if(header[0] == (byte) 0x89 && header[1] == 0x50 && header[2] == 0x4E && header[3] == 0x47) {
                System.out.println("Es un archivo PNG");
            }

            else if (header[0] == (byte) 0xFF && header[1] == (byte) 0xD8 && header[2] == (byte) 0xFF){
                System.out.println("Es un archivo JPEG");
            }

            else if(header[0] == 0x47 && header[1] == 0x49 && header[2] == 0x46 && header[3] == 0x38 && header[4] == 0x39 && header[5] == 0x61){
                System.out.println("Es un archivo GIF");
            }

            else if (header[0] == 0x00 && header[1] == 0x00 && header[2] == 0x01 && header[3] == 0x00){
                System.out.println("Es un archivo ICO");
            }
            else{
                System.out.println("No existe");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}