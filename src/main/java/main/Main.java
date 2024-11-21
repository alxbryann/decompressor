package main;

import model.BinaryTree;
import model.Node;
import model.ReadFile;
import view.View;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        while (true) {
            if (view.isFileSelected() == true) {
                try {
                    ReadFile readFile = new ReadFile(view.getPath());
                    view.showMessage("reading file...");
                    ArrayList<Integer> file = readFile.readFileByteByByte();
                    ArrayList<Integer> tree = readFile.prepareTree(file);
                    view.showMessage("creating tree...");
                    int significantBits = readFile.getSignificantBytes();
                    view.showMessage("reading significantBytes...");
                    BinaryTree bt = new BinaryTree();
                    Node root = bt.createTree(tree);
                    ArrayList<String> instructions = readFile.getInstructions();
                    view.showMessage("reading instructions...");
                    view.showMessage(
                            "creating file..."
                                    + readFile.createNewFile(bt.searchInTree(instructions, root, significantBits)));
                    try {
                        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                        if (desktop.isSupported(java.awt.Desktop.Action.OPEN)) {
                            desktop.open(new java.io.File(readFile.getOutputPath()));
                        } else {
                            view.showMessage("Open action is not supported on your system.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        view.showMessage("An error occurred while trying to open the file.");
                    }

                } catch (Exception e) {
                    view.showMessage("There is no selected file.");
                }

            }
            view.setIsFileSelected(false);
            Thread.sleep(1000);
        }
    }
}
