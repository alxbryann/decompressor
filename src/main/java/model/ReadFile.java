package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    private final String path;
    private ArrayList<Integer> fileByteByByte;
    private final ArrayList<Integer> byteData;
    private int treeSize;
    private String outputName;

    public ReadFile(String path) {
        this.path = path;
        this.byteData = new ArrayList<>();
        this.fileByteByByte = new ArrayList<>();
        outputName = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
    }

    @SuppressWarnings({"unchecked", "resource", "CatchMayIgnoreException"})
    public ArrayList<Integer> readFileByteByByte() {
        try {
            FileInputStream file = new FileInputStream(path);
            int byteRead;
            while ((byteRead = file.read()) != -1) {
                byteData.add(byteRead);
            }
            fileByteByByte = (ArrayList<Integer>) byteData.clone();
        } catch (IOException e) {
        }
        return byteData;
    }

    public ArrayList<Integer> prepareTree(ArrayList<Integer> dataTemp) {
        treeSize = (dataTemp.getFirst() + dataTemp.getFirst() + 1) * 2;
        dataTemp.removeFirst();
        while (dataTemp.size() > treeSize) {
            dataTemp.removeLast();
        }
        return dataTemp;
    }

    public int getSignificantBytes() {
        return fileByteByByte.get(treeSize + 1);
    }

    public ArrayList<String> getInstructions() {
        ArrayList<String> instructions = new ArrayList<>();
        for (int i = treeSize + 2; i < fileByteByByte.size(); i++) {
            String prepareInstruction = Integer.toBinaryString(fileByteByByte.get(i));
            while (prepareInstruction.length() < 8) {
                prepareInstruction = 0 + prepareInstruction;
            }
            instructions.add(prepareInstruction);
        }

        return instructions;
    }

    public String getFileByteByByte() {
        return fileByteByByte.toString();
    }

    public void createNewFile(byte[] data) {
        try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/decompressed/" + outputName)) {
            outputStream.write(data);
            System.out.println("Created at: src/main/resources/decompressed/" + outputName);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
