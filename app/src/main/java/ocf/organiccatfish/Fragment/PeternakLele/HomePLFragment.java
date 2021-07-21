package ocf.organiccatfish.Fragment.PeternakLele;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Fragment.DateMask;
import ocf.organiccatfish.Fragment.DatePickerFragment;
import ocf.organiccatfish.Model.PeternakLeleModel.UpdatePanenResponsePL;
import ocf.organiccatfish.Model.PeternakLeleModel.UserPL;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePLFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener{

    public static final int REQUEST_CODE = 11; //Used to identify the result

    private EditText etTanggalPanen, etBeratIkan, etJumlahKolam, etJenisPakan;
    private TextView tv_hargaIkan;
    String selectedDate;

    public OnFragmentInteractionListener mListener;

    public HomePLFragment(){

    }

    public static HomePLFragment newInstance(){
        HomePLFragment homePLFragment = new HomePLFragment();
        return homePLFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pl_home, container, false);

        etTanggalPanen = view.findViewById(R.id.etDatePicker);
        etBeratIkan    = view.findViewById(R.id.etBeratIkan);
        etJumlahKolam  = view.findViewById(R.id.etJumlahKolam);
        etJenisPakan   = view.findViewById(R.id.etJenisPakan);

        //get harga beli ikan
        tv_hargaIkan   = view.findViewById(R.id.tv_hargajual);
        tv_hargaIkan.setText("55000");
//        getHargaBeli();

//        Call<HargaBeliPL> call = RetrofitClient.getInstance().getApi().getHargaBeliPL();
//        call.enqueue(new Callback<HargaIkanResponse>() {
//            @Override
//            public void onResponse(Call<HargaIkanResponse> call, Response<HargaIkanResponse> response) {
//
//                List<HargaBeliPL> list = response.body();
////                HargaBeliPL hargaBeliPL = SharedPrefManager.getInstance(getActivity()).hargaBeliPL();
////                id_harga = hargaBeliPL.getId_harga();
////                hargaBeli = hargaBeliPL.getHargaBeli();
//                tv_hargaIkan.setText(String.valueOf(list));
//            }
//
//            @Override
//            public void onFailure(Call<HargaIkanResponse> call, Throwable t) {
//
//            }
//        });

        view.findViewById(R.id.btnSimpan).setOnClickListener(this);

        //apply the input mask
        etTanggalPanen.addTextChangedListener(new DateMask());

        //get fragment manager
        final FragmentManager fm = (getActivity()).getSupportFragmentManager();

        //using an onclick listener on the button to show the date picker
        etTanggalPanen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the datePickerFragment
                DialogFragment newFragment = new DatePickerFragment();
                //set the target fragment to receive the result, specifying the code
                newFragment.setTargetFragment(HomePLFragment.this, REQUEST_CODE);
                //show the date picker
                newFragment.show(fm, "date picker");
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check for the result
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //get date from string
            selectedDate = data.getStringExtra("selectedDate");
            //set the value of edittext
            etTanggalPanen.setText(selectedDate);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_hargaIkan = view.findViewById(R.id.tv_hargabeli);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView;
        textView = getView().findViewById(R.id.etDatePicker);
        textView.setText(currentDateString);
    }

    @Override
    public void onClick(View v) {
        updatePanen();
    }

    private void updatePanen(){
        String waktu_panen   = etTanggalPanen.getText().toString().trim();
        Integer berat_panen  = Integer.valueOf(etBeratIkan.getText().toString().trim());
        Integer jumlah_kolam = Integer.valueOf(etJumlahKolam.getText().toString().trim());
        String jenis_pakan   = etJenisPakan.getText().toString().trim();

        if (waktu_panen.isEmpty()){
            etTanggalPanen.setError("Please Fill Out This Field");
            etTanggalPanen.requestFocus();
            return;
        } else if (berat_panen < 0){
            etBeratIkan.setError("Please Fill Out This Field");
            etBeratIkan.requestFocus();
            return;
        } else if (jumlah_kolam < 0){
            etJumlahKolam.setError("Please Fill Out This Field");
            etJumlahKolam.requestFocus();
            return;
        } else if (jenis_pakan.isEmpty()){
            etJenisPakan.setError("Please Fill Out This Field");
            etJenisPakan.requestFocus();
            return;
        }

        UserPL user = SharedPrefManager.getInstance(getActivity()).getUser();

        Call<UpdatePanenResponsePL> call = RetrofitClient
                .getInstance().getApi().UpdatePanenPL(user.getId_peternaklele(), waktu_panen, berat_panen, jumlah_kolam, jenis_pakan);

        call.enqueue(new Callback<UpdatePanenResponsePL>() {
            @Override
            public void onResponse(Call<UpdatePanenResponsePL> call, Response<UpdatePanenResponsePL> response) {
                if (response.code() == 200) {
                    UpdatePanenResponsePL updatePanenResponsePL = response.body();
                    Toast.makeText(getActivity(), updatePanenResponsePL.getMsg(), Toast.LENGTH_LONG).show();
                } else if (response.code() == 422) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePanenResponsePL> call, Throwable t) {
                Toast.makeText(getActivity(), "Koneksi Bermasalah", Toast.LENGTH_LONG).show();
            }
        });
    }

//    private void getHargaBeli(){
//        Call<HargaBeliPL> call = RetrofitClient.getInstance().getApi().getHargaBeliPL();
//        call.enqueue(new Callback<HargaBeliPL>() {
//            @Override
//            public void onResponse(Call<HargaBeliPL> call, Response<HargaBeliPL> response) {
//                HargaBeliPL hargaBeliPL = response.body();
//                tv_hargaIkan.setText(String.valueOf(SharedPrefManager.getInstance(getActivity()).hargaBeliPL().getHargaBeli()));
//            }
//
//            @Override
//            public void onFailure(Call<HargaBeliPL> call, Throwable t) {
//
//            }
//        });
////        Call<HargaBeliPL> call = RetrofitClient.getInstance().getApi().getHargaBeliPL();
////        call.enqueue(new Callback<HargaBeliPL>() {
////            @Override
////            public void onResponse(Call<HargaBeliPL> call, Response<HargaBeliPL> response) {
////                Log.i("ResponseString", response.body().toString());
////                if(response.isSuccessful()){
////                    if(response.body() != null) {
////                        Log.i("onSuccess", response.body().toString());
////
////                        String jsonresponse = response.body().toString();
////                        writeHargaBeli(jsonresponse);
////                    } else {
////                        Log.i("onEmptyResponse", "Returned Empty Response");
////                    }
////                }
////            }
////
////            @Override
////            public void onFailure(Call<HargaBeliPL> call, Throwable t) {
////                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
////            }
////        });
//    }

//    private void writeHargaBeli(String response){
//        try {
//            //getting JSON Object from the Response
//            JSONObject obj = new JSONObject(response);
//            if(obj.optString("status").equals("true")){
//
//                ArrayList<HargaBeliPL> hargaBeliArrayList = new ArrayList<>();
//                JSONArray dataArray = obj.getJSONArray("data");
//
//                for(int i = 0; i<dataArray.length(); i++){
//                    HargaBeliPL hargaBeliPL = new HargaBeliPL();
//                    JSONObject dataObj = dataArray.getJSONObject(i);
//
//                    hargaBeliPL.setHargaBeli(dataObj.getInt("harga_beli"));
//
//                    hargaBeliArrayList.add(hargaBeliPL);
//                }
//
//                for(int j = 0; j<hargaBeliArrayList.size(); j++){
//                    tv_hargaIkan.setText(tv_hargaIkan.getText().toString() + hargaBeliArrayList.get(j).getHargaBeli().toString());
//                }
//            }
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//    }

    public interface OnFragmentInteractionListener{
        void onFragmentListener(Uri uri);
    }

    public void ConfirmationPagePL(){

    }
}
