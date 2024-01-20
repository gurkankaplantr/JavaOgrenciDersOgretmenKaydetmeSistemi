import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class OgrenciKayitForm extends JFrame {
    private JPanel JPanel;
    private JButton btn_Kaydet;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox<Ders> comboBox1;
    private JTextField textField5;
    private JTable ogrtablo;
    private JTextField txtarama;
    private JButton geriGelButton;

    private DefaultTableModel tableModel;

    public OgrenciKayitForm() {
        setTitle("Öğrenci Kayıt Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);
        JPanel.setBackground(new Color(173, 216, 230));

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Öğrenci Adı");
        tableModel.addColumn("Öğrenci Soyadı");
        tableModel.addColumn("Telefon");
        tableModel.addColumn("Adres");
        tableModel.addColumn("Sınıf");
        tableModel.addColumn("Ders");

        ogrtablo.setModel(tableModel);

        ogrenciBilgileriniTabloyaCek();

        List<Ders> dersListesi = dersVerileriniOku("ders.csv");
        comboBox1.setModel(new DefaultComboBoxModel<>(dersListesi.toArray(new Ders[0])));

        btn_Kaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bilgi1 = textField1.getText();
                String bilgi2 = textField2.getText();
                String bilgi3 = textField3.getText();
                String bilgi4 = textField4.getText();
                String ogrenciSinif = textField5.getText();

                Ders seciliDers = (Ders) comboBox1.getSelectedItem();

                kaydetOgrenciCSV(bilgi1, bilgi2, bilgi3, bilgi4, ogrenciSinif, seciliDers);

                Object[] newRow = {bilgi1, bilgi2, bilgi3, bilgi4, ogrenciSinif, seciliDers.getDersAd()};
                tableModel.addRow(newRow);

                JOptionPane.showMessageDialog(null, "Öğrenci bilgileri başarıyla kaydedildi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);

                Anasayfa anasayfa = new Anasayfa();
                anasayfa.setVisible(true);

                dispose();
            }
        });

        geriGelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Anasayfa anasayfa = new Anasayfa();
                anasayfa.setVisible(true);

                dispose();
            }
        });

        txtarama.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTableByOgrnciAdi(txtarama.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTableByOgrnciAdi(txtarama.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTableByOgrnciAdi(txtarama.getText());
            }
        });
    }

    private void filterTableByOgrnciAdi(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        ogrtablo.setRowSorter(sorter);

        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 2));
        }
    }

    private List<Ders> dersVerileriniOku(String dosyaYolu) {
        List<Ders> dersListesi = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dersBilgileri = line.split(",");
                if (dersBilgileri.length == 3) {
                    String dersKodu = dersBilgileri[0].trim();
                    String dersAd = dersBilgileri[1].trim();
                    String dersDonem = dersBilgileri[2].trim();

                    Ders ders = new Ders(dersKodu, dersAd, dersDonem);
                    dersListesi.add(ders);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Dosya okuma hatası: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }

        return dersListesi;
    }

    private void kaydetOgrenciCSV(String bilgi1, String bilgi2, String bilgi3, String bilgi4, String ogrenciSinif, Ders seciliDers) {
        String dosyaYolu = "ogrenci.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(dosyaYolu, true))) {
            writer.println(String.format("%s,%s,%s,%s,%s,%s", bilgi1, bilgi2, bilgi3, bilgi4, ogrenciSinif, seciliDers.getDersAd()));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Dosya yazma hatası: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ogrenciBilgileriniTabloyaCek() {
        String dosyaYolu = "ogrenci.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ogrenciBilgileri = line.split(",");
                if (ogrenciBilgileri.length == 6) {
                    tableModel.addRow(ogrenciBilgileri);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Dosya okuma hatası: " + e.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new OgrenciKayitForm();
    }
}
