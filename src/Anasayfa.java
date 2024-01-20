import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Anasayfa extends JFrame {
    private JButton btn_DersKayit;
    private JButton btn_Ogrenci;
    private JPanel JPanel;
    private JButton ogretmenKayitSayfasiButton;

    public Anasayfa(){

        setTitle("ANASAYFA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,  700);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);
        JPanel.setBackground(new Color(173, 216, 230));

        btn_DersKayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayitForm dersKayitForm = new DersKayitForm();
                dersKayitForm.setVisible(true); // DersKayitForm'u görünür hale getiriyoruz.
            }
        });

        btn_Ogrenci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciKayitForm ogrenciKayitForm = new OgrenciKayitForm();
                ogrenciKayitForm.setVisible(true);
            }
        });
        ogretmenKayitSayfasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgretmenKayitForm ogretmenKayitForm = new OgretmenKayitForm();
                ogretmenKayitForm.setVisible(true); //
            }
        });

    }

    public static void main(String[] args) {
        new Anasayfa();
    }
}
