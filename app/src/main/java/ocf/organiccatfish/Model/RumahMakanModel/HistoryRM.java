package ocf.organiccatfish.Model.RumahMakanModel;

public class HistoryRM {

    private int idPemesanan, idRumahMakan, beratPesan;
    private String waktuPesan, jenisUkuran, status;

    public HistoryRM(int idPemesanan, int idRumahMakan, int beratPesan, String waktuPesan, String jenisUkuran, String status){
        this.idPemesanan = idPemesanan;
        this.idRumahMakan = idRumahMakan;
        this.beratPesan = beratPesan;
        this.waktuPesan = waktuPesan;
        this.jenisUkuran = jenisUkuran;
        this.status = status;
    }

    public int getIdPemesanan() {
        return idPemesanan;
    }

    public int getIdRumahMakan() {
        return idRumahMakan;
    }

    public int getBeratPesan() {
        return beratPesan;
    }

    public String getWaktuPesan() {
        return waktuPesan;
    }

    public String getJenisUkuran() {
        return jenisUkuran;
    }

    public String getStatus() {
        return status;
    }
}
