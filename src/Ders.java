public class Ders {
    private String dersKodu;
    private String dersAd;
    private String dersDonem;

    public Ders(String dersKodu, String dersAd, String dersDonem) {
        this.dersKodu = dersKodu;
        this.dersAd = dersAd;
        this.dersDonem = dersDonem;
    }

    public String getDersKodu() {
        return dersKodu;
    }

    public String getDersAd() {
        return dersAd;
    }

    public String getDersDonem() {
        return dersDonem;
    }

    @Override
    public String toString() {
        return dersAd;  // ComboBox'ta görüntülenecek metin
    }
}
