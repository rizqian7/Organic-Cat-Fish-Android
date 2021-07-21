package ocf.organiccatfish.Model.PeternakLeleModel;

public class HistoryPL {

    private int idPenjemputan, idPeternakLele, beratPanen, jumlahKolam;
    private String waktuPanen, jenisPakan, status;

    public HistoryPL(int kodePenjemputan, int idPeternakLele, String tanggalPanenPL, int beratPanen, int jumlahKolam, String jenisPakan, String statusPL){
        this.idPenjemputan = kodePenjemputan;
        this.idPeternakLele = idPeternakLele;
        this.waktuPanen = tanggalPanenPL;
        this.beratPanen = beratPanen;
        this.jumlahKolam = jumlahKolam;
        this.jenisPakan = jenisPakan;
        this.status = statusPL;
    }

    public int getKodePenjemputan() {
        return idPenjemputan;
    }

    public int getIdPeternakLele() {
        return idPeternakLele;
    }

    public int getBeratPanen() {
        return beratPanen;
    }

    public int getJumlahKolam() {
        return jumlahKolam;
    }

    public String getTanggalPanenPL() {
        return waktuPanen;
    }

    public String getJenisPakan() {
        return jenisPakan;
    }

    public String getStatusPL() {
        return status;
    }

}
