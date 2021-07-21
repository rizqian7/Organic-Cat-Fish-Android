package ocf.organiccatfish.Model.DriverModel;

public class PanenDv {

    private int id_peternaklele, id_panen, berat_panen, jumlah_kolam;
    private String waktu_panen, nama, no_hp, jenis_pakan;

    public PanenDv (int id_peternaklele, int id_panen, int berat_panen, int jumlah_kolam, String waktu_panen, String nama, String no_hp, String jenis_pakan){
        this.id_peternaklele = id_peternaklele;
        this.id_panen = id_panen;
        this.berat_panen = berat_panen;
        this.jumlah_kolam = jumlah_kolam;
        this.waktu_panen = waktu_panen;
        this.nama = nama;
        this.no_hp = no_hp;
        this.jenis_pakan = jenis_pakan;
    }

    public int getId_peternaklele() {
        return id_peternaklele;
    }

    public int getId_panen() {
        return id_panen;
    }

    public int getBerat_panen() {
        return berat_panen;
    }

    public int getJumlah_kolam() {
        return jumlah_kolam;
    }

    public String getWaktu_panen() {
        return waktu_panen;
    }

    public String getNama() {
        return nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getJenis_pakan() {
        return jenis_pakan;
    }
}
