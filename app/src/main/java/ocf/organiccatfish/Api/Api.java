package ocf.organiccatfish.Api;

import ocf.organiccatfish.Model.DriverModel.LoginResponseDv;
import ocf.organiccatfish.Model.DriverModel.PanenResponse;
import ocf.organiccatfish.Model.HargaIkanResponse;
import ocf.organiccatfish.Model.PeternakLeleModel.HistoryResponsePL;
import ocf.organiccatfish.Model.PeternakLeleModel.SaveMapsAdaptorPL;
import ocf.organiccatfish.Model.DefaultResponse;
import ocf.organiccatfish.Model.PeternakLeleModel.LoginResponsePL;
import ocf.organiccatfish.Model.PeternakLeleModel.RegisterResponsePL;
import ocf.organiccatfish.Model.PeternakLeleModel.UpdatePanenResponsePL;
import ocf.organiccatfish.Model.RumahMakanModel.LoginResponseRM;
import ocf.organiccatfish.Model.RumahMakanModel.RegisterResponseRM;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @FormUrlEncoded
    @POST("createuser")
    Call<RegisterResponsePL> DaftarPL(
             @Field("no_ktp") String no_ktp,
             @Field("nama_lengkap") String nama_lengkap,
             @Field("no_hp") String no_hp,
             @Field("nama_usaha") String nama_usaha,
             @Field("jumlah_kolam") Integer jumlah_kolam,
             @Field("jumlah_produksi") Integer jumlah_produksi,
             @Field("alamat") String alamat,
             @Field("username") String username,
             @Field("password") String password
    );

    @FormUrlEncoded
    @POST("createuserrm")
    Call<RegisterResponseRM> DaftarRM(
            @Field("no_ktp") String no_ktp,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("no_hp") String no_hp,
            @Field("nama_rumahmakan") String nama_usaha,
            @Field("alamat") String alamat,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponsePL> updateUserSetting(
        @Path("id") int id,
        @Field("no_ktp") String no_ktp,
        @Field("nama_lengkap") String nama_lengkap,
        @Field("no_hp") String no_hp,
        @Field("nama_usaha") String nama_usaha,
        @Field("username") String username
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePasswordSetting(
        @Field("currentPassword") String currentpassword,
        @Field("newPassword") String newpassword,
        @Field("username") String username
    );

    // User Login PL
    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponsePL> LoginPL(
            @Field("username") String username,
            @Field("password") String password
    );

    // User Login RM
    @FormUrlEncoded
    @POST("userloginrm")
    Call<LoginResponseRM> LoginRM(
            @Field("username") String username,
            @Field("password") String password
    );

    // User Login Dv
    @FormUrlEncoded
    @POST("userlogindv")
    Call<LoginResponseDv> LoginDv(
            @Field("username") String username,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("updatepanen")
    Call<UpdatePanenResponsePL> UpdatePanenPL(
            @Field("id_peternak") Integer id_peternak,
            @Field("waktu_panen") String waktu_panen,
            @Field("berat_panen") Integer berat_panen,
            @Field("jumlah_kolam") Integer jumlah_kolam,
            @Field("jenis_pakan") String jenis_pakan
    );

    @GET ("getHargaBeli")
    Call<HargaIkanResponse> hargaBeliPL();

//    @GET("getHargaBeli")
//    Call<HargaBeliPL> getHargaBeliPL();

    @FormUrlEncoded
    @PUT("getmapslatlng/{id}")
    Call<SaveMapsAdaptorPL> saveLocation(
            @Path("id") int id,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude
    );

    @GET("allhistory")
    Call<HistoryResponsePL> getHistory();

    @GET("getallpanen/{id}")
    Call<PanenResponse> getPanen(
            @Path("id") int id
    );
}
