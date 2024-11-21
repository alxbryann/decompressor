package view;

import javax.swing.JPanel;

public class View extends javax.swing.JFrame {

    private javax.swing.JLabel messageLabel;
    private boolean fileSelected = false;
    private String path;

    public View() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(java.awt.Color.DARK_GRAY);
        this.setContentPane(panel);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Huffman Decompressor");
        this.setVisible(true);

        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Huffman Decompressor",
                javax.swing.SwingConstants.CENTER);
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 36));
        titleLabel.setForeground(java.awt.Color.WHITE);
        titleLabel.setBounds(100, 50, 600, 50);
        panel.add(titleLabel);

        javax.swing.JLabel authorLabel = new javax.swing.JLabel("Created by alxbryann and sebastiangf",
                javax.swing.SwingConstants.CENTER);
        authorLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        authorLabel.setForeground(java.awt.Color.LIGHT_GRAY);
        authorLabel.setBounds(100, 120, 600, 30);
        javax.swing.JButton selectFileButton = new javax.swing.JButton("Select File");
        selectFileButton.setBounds(350, 200, 100, 30);
        selectFileButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                selectFile();
            }
        });
        ((javax.swing.JPanel) this.getContentPane()).add(selectFileButton);
        panel.add(selectFileButton);
        panel.add(authorLabel);
    }

    public void selectFile() {
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
        }
        try {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        } catch (Exception e) {}
        fileSelected = true;
    }

    public void showMessage(String message) {
        if (messageLabel != null) {
            ((javax.swing.JPanel) this.getContentPane()).remove(messageLabel);
        }

        messageLabel = new javax.swing.JLabel(message, javax.swing.SwingConstants.CENTER);
        messageLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        messageLabel.setForeground(java.awt.Color.WHITE);
        ((javax.swing.JPanel) this.getContentPane()).add(messageLabel);
        this.repaint();

        new javax.swing.Timer(20, new java.awt.event.ActionListener() {
            int y = 450;

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (y > 420) {
                    y -= 10;
                    messageLabel.setBounds(100, y, 600, 30);
                    repaint();
                } else {
                    ((javax.swing.Timer) e.getSource()).stop();
                }
            }
        }).start();
    }

    public void setIsFileSelected(boolean fileSelected) {
        this.fileSelected = fileSelected;
    }

    public boolean isFileSelected() {
        return fileSelected;
    }

    public String getPath() {
        return path;
    }
}
