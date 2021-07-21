package ocf.organiccatfish.Model.RumahMakanModel;

public class UserRM {
    private Integer id_rumahmakan;
    private String no_ktp, nama_lengkap, no_hp, nama_rumahmakan, alamat, username;

    public UserRM(Integer id_rumahmakan, String no_ktp, String nama_lengkap, String no_hp, String nama_rumahmakan, String alamat, String username) {
        this.id_rumahmakan = id_rumahmakan;
        this.no_ktp = no_ktp;
        this.nama_lengkap = nama_lengkap;
        this.no_hp = no_hp;
        this.nama_rumahmakan = nama_rumahmakan;
        this.alamat = alamat;
        this.username = username;
    }

    public Integer getId_rumahmakan() {
        return id_rumahmakan;
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

    public String getNama_rumahmakan() {
        return nama_rumahmakan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getUsername() {
        return username;
    }
}
