package model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class BinaryTree {

    private int index = 0;

    @SuppressWarnings(value = "ReassignedVariable")
    public Node createTree(ArrayList<Integer> data) {
        if (index >= data.size())
            return null;

        Node newNode = null;
        if (data.get(index) == 0) {
            newNode = new Node();
            index += 2;
            newNode.setLeft(createTree(data));
            newNode.setRight(createTree(data));
        } else if (data.get(index) == 1) {
            newNode = new Node(data.get(index + 1));
            index += 2;
        }

        return newNode;
    }


    @SuppressWarnings({"MismatchedQueryAndUpdateOfStringBuilder", "ForLoopReplaceableByForEach", "ReassignedVariable"})
    public byte[] searchInTree(ArrayList<String> instructions, Node root, int significantBits) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Node currentNode = root;
        StringBuilder newLastByte = new StringBuilder();
        for (int i = 0; i < significantBits; i++) {
            newLastByte.append(instructions.getLast().charAt(i));
        }
        instructions.removeLast();
        instructions.add(newLastByte.toString());
        for (int i = 0; i < instructions.size(); i++) {
            for (int j = 0; j < instructions.get(i).length(); j++) {
                if ("0".equalsIgnoreCase(String.valueOf(instructions.get(i).charAt(j)))) {
                    currentNode = currentNode.getLeft();
                    if (!currentNode.isFrequency()) {
                        output.write(currentNode.getAscii());
                        currentNode = root;
                    }
                } else if ("1".equalsIgnoreCase(String.valueOf(instructions.get(i).charAt(j)))) {
                    currentNode = currentNode.getRight();
                    if (!currentNode.isFrequency()) {
                        output.write(currentNode.getAscii());
                        currentNode = root;
                    }
                }
            }
        }
        return output.toByteArray();
    }

    public void printPreorder(Node node) {
        if (node == null) {
            return;
        }

        if (node.isFrequency()) {
            System.out.print("F ");
        } else {
            System.out.print((char) node.getAscii() + " ");
        }
        printPreorder(node.getLeft());
        printPreorder(node.getRight());
    }

}
