import java.util.ArrayList;
import java.util.List;

public class Ogrenci {
    private String ogrenciNo;
    private String ogrenciAd;
    private String ogrenciSoyad;
    private String ogrenciBolum;
    private List<Ders> ogrenciDersler;

    public Ogrenci(String ogrenciNo, String ogrenciAd, String ogrenciSoyad, String ogrenciBolum, Ders seciliDers) {
        this.ogrenciNo = ogrenciNo;
        this.ogrenciAd = ogrenciAd;
        this.ogrenciSoyad = ogrenciSoyad;
        this.ogrenciBolum = ogrenciBolum;
        this.ogrenciDersler = new ArrayList<>();
        this.ogrenciDersler.add(seciliDers);
    }

    public String getOgrenciNo() {
        return ogrenciNo;
    }

    public String getOgrenciAd() {
        return ogrenciAd;
    }

    public String getOgrenciSoyad() {
        return ogrenciSoyad;
    }

    public String getOgrenciBolum() {
        return ogrenciBolum;
    }

    public List<Ders> getOgrenciDersler() {
        return ogrenciDersler;
    }

    public void dersEkle(Ders ders) {
        ogrenciDersler.add(ders);
    }

    @Override
    public String toString() {
        return "Ogrenci{" +
                "ogrenciNo='" + ogrenciNo + '\'' +
                ", ogrenciAd='" + ogrenciAd + '\'' +
                ", ogrenciSoyad='" + ogrenciSoyad + '\'' +
                ", ogrenciBolum='" + ogrenciBolum + '\'' +
                ", ogrenciDersler=" + ogrenciDersler +
                '}';
    }
}
