import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ogretmen {
    private String adi;
    private String soyadi;
    private String telNo;
    private String adres;
    private String ilgiliOkulBolum;
    private Ders verdigiDers;

    public Ogretmen(String adi, String soyadi, String telNo, String adres, String ilgiliOkulBolum, Ders verdigiDers) {
        this.adi = adi;
        this.soyadi = soyadi;
        this.telNo = telNo;
        this.adres = adres;
        this.ilgiliOkulBolum = ilgiliOkulBolum;
        this.verdigiDers = verdigiDers;
    }

    public String getAdi() {
        return adi;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public String getTelNo() {
        return telNo;
    }

    public String getAdres() {
        return adres;
    }

    public String getIlgiliOkulBolum() {
        return ilgiliOkulBolum;
    }

    public Ders getVerdigiDers() {
        return verdigiDers;
    }

    public void ogretmenBilgileriniKaydet(String dosyaAdi) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi, true))) {
            writer.write(adi + "," +
                    soyadi + "," +
                    telNo + "," +
                    adres + "," +
                    ilgiliOkulBolum + "," +
                    verdigiDers.getDersKodu() + "," +
                    verdigiDers.getDersAd() + "," +
                    verdigiDers.getDersDonem());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return adi + " " + soyadi;
    }
}
