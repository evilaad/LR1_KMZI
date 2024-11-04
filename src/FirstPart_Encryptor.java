import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstPart_Encryptor {
    public static void main(String[] args) throws IOException {
        String exampleMessage = "message.txt";

        List<String> strings = getStringsFromFile(exampleMessage, 7);

        String encryptedMessage = encryptMessage(strings);
        writeEncryptedText(encryptedMessage);

    }

    private static void writeEncryptedText(String encryptedMessage) {
        StringBuilder sb = new StringBuilder(encryptedMessage);
        String filePath = "encryptedText.txt";

        try(FileWriter writer = new FileWriter(filePath)) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static List<String> getStringsFromFile(String filePath, int blockSize) throws IOException {
        List<String> fileStrings = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while((line = br.readLine()) != null){
                if(line.length() != blockSize){ // Если длина строки из текстового файла меньше длины блока,
                    // то добавляем точки в конец строки, чтобы достичь длину блока
                    int diff = blockSize - line.length();
                    for(int i = 0; i < diff; i++){
                        line += '.';
                    }
                }
                fileStrings.add(line);
            }
        }

        return fileStrings;
    }

    private static String encryptMessage(List<String> strings) {
        int rowNum = strings.size();
        int colNum = strings.getFirst().length();

        StringBuilder sb = new StringBuilder();

        char[][] symbols = new char[rowNum][colNum];

        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                symbols[i][j] = strings.get(i).charAt(j);
            }
        }

        for(int j = 0; j < colNum; j++){
            if(j % 2 == 0) {
                for (int i = 0; i < rowNum; i++) {
                    sb.append(symbols[i][j]);
                }
            } else {
                for (int i = rowNum - 1; i >= 0; i--) {
                    sb.append(symbols[i][j]);
                }
            }
        }

        return sb.toString();
    }
}
