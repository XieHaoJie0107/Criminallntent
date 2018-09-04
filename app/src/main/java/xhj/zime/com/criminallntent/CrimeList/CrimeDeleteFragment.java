package xhj.zime.com.criminallntent.CrimeList;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.UUID;

import xhj.zime.com.criminallntent.CrimeDetail.Crime;
import xhj.zime.com.criminallntent.R;

public class CrimeDeleteFragment extends DialogFragment {
    private static final String ARG_CRIME_DELETE_ID = "crime_delete_id";
    public static final String EXTRA_CRIME_DELETE_STATUS = "delete_status";
    public static final int CRIME_DELETE_STATUS_SUCCESS = 1;
    private UUID mCrimeId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mCrimeId = (UUID) getArguments().getSerializable(ARG_CRIME_DELETE_ID);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.dialog_make_sure_delete)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteCrime();
                        sendResult(Activity.RESULT_OK,CRIME_DELETE_STATUS_SUCCESS);
                    }
                })
                .create();
        return dialog;
    }
    public static CrimeDeleteFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_DELETE_ID,crimeId);
        CrimeDeleteFragment fragment = new CrimeDeleteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sendResult(int resultCode,int status){
        Intent data = new Intent();
        data.putExtra(EXTRA_CRIME_DELETE_STATUS,status);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,data);
    }
    private void deleteCrime() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        Crime crime = crimeLab.getCrime(mCrimeId);
        crimeLab.deleteCrime(crime);
    }
}
