package ocf.organiccatfish.Model.DriverModel;

public class UserDv {

    private int id_driver;
    private String no_ktp, nama_lengkap, no_hp, alamat, username;

    public UserDv(int id_driver, String no_ktp, String nama_lengkap, String no_hp, String alamat, String username) {
        this.id_driver = id_driver;
        this.no_ktp = no_ktp;
        this.nama_lengkap = nama_lengkap;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.username = username;
    }

    public int getId_driver() {
        return id_driver;
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

    public String getAlamat() {
        return alamat;
    }

    public String getUsername() {
        return username;
    }

}
