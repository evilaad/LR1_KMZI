import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SecondPart_Decrypt {
    public static void main(String[] args) throws IOException {
        String encryptedFileName = "crypto.txt";
        int blockSize = 11;
        String encryptedMessage = FirstPart_Encryptor.getStringsFromFile(encryptedFileName, blockSize).getFirst();

        decryptMessage(encryptedMessage, blockSize);
    }

    private static void decryptMessage(String encryptedMessage, int blockSize) {

        List<String> stringsAfterFormCharArray = new ArrayList<>();

        if(encryptedMessage.length() % blockSize != 0){
            int neededCharacters = blockSize - encryptedMessage.length() % blockSize;

            for(int i = 0; i < neededCharacters; i++){
                encryptedMessage += ".";
            }
        }

        int rowNum = encryptedMessage.length() / blockSize;
        int colNum = blockSize;

        char[][] symbols = new char[rowNum][colNum];
        int idx = 0;

        for(int j = 0; j < colNum; j++) {
            if( j % 2 == 0) {
                for (int i = 0; i < rowNum; i++) {
                    symbols[i][j] = encryptedMessage.charAt(idx++);
                }
            } else {
                for (int i = rowNum - 1; i >= 0; i--) {
                    symbols[i][j] = encryptedMessage.charAt(idx++);
                }
            }
        }

        for(char[] chars : symbols) {
            StringBuilder sb = new StringBuilder();
            for(char character : chars) {
                sb.append(character);
            }
            stringsAfterFormCharArray.add(sb.toString());
        }


        try {
            Files.write(Paths.get("decrypt.txt"), stringsAfterFormCharArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
