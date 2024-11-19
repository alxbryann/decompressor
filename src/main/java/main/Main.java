package main;

import model.BinaryTree;
import model.Node;
import model.ReadFile;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to decompressor by alxbryann");
        ReadFile readFile = new ReadFile("src/main/resources/compressed/DeskTest.xlsm.pkz");
        ArrayList<Integer> file = readFile.readFileByteByByte();
        ArrayList<Integer> tree = readFile.prepareTree(file);
        int significantBits = readFile.getSignificantBytes();
        BinaryTree bt = new BinaryTree();
        Node root = bt.createTree(tree);
        ArrayList<String> instructions = readFile.getInstructions();
        readFile.createNewFile(bt.searchInTree(instructions, root, significantBits));
    }
}
