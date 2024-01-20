import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;

public class DersKayitForm extends JFrame {

    private JPanel JPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton kaydetButton;
    private JTable derstablo;
    private JTextField txtarama;
    private JButton geri;

    private DefaultTableModel tableModel;

    public DersKayitForm() {

        setTitle("Ders Kayıt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);
        JPanel.setBackground(new Color(173, 216, 230));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Ders Kodu");
        tableModel.addColumn("Ders Adı");
        tableModel.addColumn("Ders Kredisi");
        derstablo.setModel(tableModel);

        dersleriTabloyaCek();

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bilgi1 = textField1.getText();
                String bilgi2 = textField2.getText();
                String bilgi3 = textField3.getText();

                kaydetDersCSV(bilgi1, bilgi2, bilgi3);

                Object[] newRow = {bilgi1, bilgi2, bilgi3};
                tableModel.addRow(newRow);

                JOptionPane.showMessageDialog(null, "Ders bilgileri başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        txtarama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTableByDersAdi(txtarama.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTableByDersAdi(txtarama.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTableByDersAdi(txtarama.getText());
            }
        });

        geri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Anasayfa'yı oluşturup görünür hale getir
                Anasayfa anasayfa = new Anasayfa();
                anasayfa.setVisible(true);

                // DersKayitForm penceresini kapat
                dispose();
            }
        });
    }

    private void kaydetDersCSV(String bilgi1, String bilgi2, String bilgi3) {
        String dosyaYolu = "ders.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaYolu, true))) {
            writer.println(bilgi1 + "," + bilgi2 + "," + bilgi3);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Dosya yazma hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dersleriTabloyaCek() {
        String dosyaYolu = "ders.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dersBilgileri = line.split(",");
                if (dersBilgileri.length == 3) {
                    tableModel.addRow(dersBilgileri);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Dosya okuma hatası: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTableByDersAdi(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        derstablo.setRowSorter(sorter);

        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 1));
        }
    }

    public static void main(String[] args) {
        new DersKayitForm();
    }
}
