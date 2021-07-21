package ocf.organiccatfish.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import ocf.organiccatfish.Model.DriverModel.UserDv;
import ocf.organiccatfish.Model.PeternakLeleModel.HargaBeliPL;
import ocf.organiccatfish.Model.PeternakLeleModel.UserPL;
import ocf.organiccatfish.Model.RumahMakanModel.UserRM;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if(mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUserPL(UserPL userPL) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id_peternaklele", userPL.getId_peternaklele());
        editor.putString("no_ktp", userPL.getNo_ktp());
        editor.putString("nama_lengkap", userPL.getNama_lengkap());
        editor.putString("no_hp", userPL.getNo_hp());
        editor.putString("nama_usaha", userPL.getNama_usaha());
        editor.putString("username", userPL.getUsername());

        editor.apply();
    }

    public void saveUserRM(UserRM userRM){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id_rumahmakan", userRM.getId_rumahmakan());
        editor.putString("no_ktp", userRM.getNo_ktp());
        editor.putString("nama_lengkap", userRM.getNama_lengkap());
        editor.putString("no_hp", userRM.getNo_hp());
        editor.putString("nama_rumahmakan", userRM.getNama_rumahmakan());
        editor.putString("username", userRM.getUsername());
    }

    public void saveUserDv(UserDv userDv){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id_driver", userDv.getId_driver());
        editor.putString("no_ktp", userDv.getNo_ktp());
        editor.putString("nama_lengkap", userDv.getNama_lengkap());
        editor.putString("no_hp", userDv.getNo_hp());
        editor.putString("alamat", userDv.getAlamat());
        editor.putString("username", userDv.getUsername());

        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id_peternaklele", -1) != -1;
    }

    public boolean isLoggedInRm(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id_rumahmakan", -1) != -1;
    }

    public boolean isLoggedInDv(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id_driver", -1) != -1;
    }

    public UserPL getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserPL(
                sharedPreferences.getInt("id_peternaklele", -1),
                sharedPreferences.getString("no_ktp", null),
                sharedPreferences.getString("nama_lengkap", null),
                sharedPreferences.getString("no_hp", null),
                sharedPreferences.getString("nama_usaha", null),
                sharedPreferences.getInt("jumlah_kolam", 0),
                sharedPreferences.getInt("jumlah_produksi", 0),
                sharedPreferences.getString("username", null)
        );
    }

    public UserDv getUserDv(){
        SharedPreferences sharedPreferencesdv = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserDv(
                sharedPreferencesdv.getInt("id_driver", -1),
                sharedPreferencesdv.getString("no_ktp", null),
                sharedPreferencesdv.getString("nama_lengkap", null),
                sharedPreferencesdv.getString("no_hp", null),
                sharedPreferencesdv.getString("alamat", null),
                sharedPreferencesdv.getString("username", null)
        );
    }

    public HargaBeliPL hargaBeliPL() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new HargaBeliPL(
                sharedPreferences.getInt("harga_beli", 0)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
