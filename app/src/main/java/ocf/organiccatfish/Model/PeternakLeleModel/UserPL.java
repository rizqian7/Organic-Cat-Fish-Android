package ocf.organiccatfish.Model.PeternakLeleModel;

public class UserPL {
    private Integer id_peternaklele, jumlah_kolam, jumlah_produksi;
    private String no_ktp, nama_lengkap, no_hp, nama_usaha, username;

    public UserPL(Integer id_peternaklele, String no_ktp, String nama_lengkap, String no_hp, String nama_usaha, Integer jumlah_kolam, Integer jumlah_produksi, String username) {
        this.id_peternaklele = id_peternaklele;
        this.no_ktp = no_ktp;
        this.nama_lengkap = nama_lengkap;
        this.no_hp = no_hp;
        this.nama_usaha = nama_usaha;
        this.jumlah_kolam = jumlah_kolam;
        this.jumlah_produksi = jumlah_produksi;
        this.username = username;
    }

    public Integer getId_peternaklele() {
        return id_peternaklele;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getNama_usaha() {
        return nama_usaha;
    }

    public Integer getJumlah_kolam() {
        return jumlah_kolam;
    }

    public Integer getJumlah_produksi() {
        return jumlah_produksi;
    }

    public String getUsername() {
        return username;
    }
}
