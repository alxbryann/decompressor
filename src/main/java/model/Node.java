package model;


public class Node {
    private boolean isFrequency;
    private int Ascii;
    private Node left;
    private Node right;

    public Node() {
        this.isFrequency = true;
    }

    public Node(int valorAscii) {
        this.isFrequency = false;
        this.Ascii = valorAscii;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isFrequency() {
        return isFrequency;
    }

    public void setFrequency(boolean frequency) {
        isFrequency = frequency;
    }

    public int getAscii() {
        return Ascii;
    }
}