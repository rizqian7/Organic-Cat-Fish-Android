package ocf.organiccatfish.Fragment.RumahMakan;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.NotNull;

import ocf.organiccatfish.Fragment.DateMask;
import ocf.organiccatfish.Fragment.DatePickerFragment;
import ocf.organiccatfish.Fragment.PeternakLele.HomePLFragment;
import ocf.organiccatfish.R;

public class HomeRMFragment extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    public static final int REQUEST_CODE = 11; //Used to identify the result

    private EditText etTanggalPesan, etBeratPesan,etJenisUkuran;
    private TextView tv_hargaIkan;
    String selectedDate;

//    public HomeRMFragment.OnFragmentInteractionListener mListener;

    public HomeRMFragment(){

    }

    public static HomeRMFragment newInstance(){
        HomeRMFragment homeRMFragment = new HomeRMFragment();
        return homeRMFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rm_home, container, false);

        //get referenced to EditText
        etTanggalPesan = view.findViewById(R.id.etDatePicker);
        etBeratPesan    = view.findViewById(R.id.etBeratIkan);
        etJenisUkuran   = view.findViewById(R.id.etUkuranPesan);

        //get harga beli ikan
        tv_hargaIkan   = view.findViewById(R.id.tv_hargabeli);
        tv_hargaIkan.setText("75000");
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
        etTanggalPesan.addTextChangedListener(new DateMask());

        //get fragment manager
        final FragmentManager fm = (getActivity()).getSupportFragmentManager();

        //using an onclick listener on the button to show the date picker
        etTanggalPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create the datePickerFragment
                DialogFragment newFragment = new DatePickerFragment();
                //set the target fragment to receive the result, specifying the code
                newFragment.setTargetFragment(HomeRMFragment.this, REQUEST_CODE);
                //show the date picker
                newFragment.show(fm, "date picker");
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //get date from string
            selectedDate = data.getStringExtra("selectedDate");
            //set the value of edittext
            etTanggalPesan.setText(selectedDate);
        }
    }

//    @Override
//    public void onAttach(@NonNull @NotNull Context context) {
//        super.onAttach(context);
//        if (context instanceof HomeRMFragment.OnFragmentInteractionListener){
//            mListener = (HomePLFragment.OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + "must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onClick(View v) {

    }
}
