package xhj.zime.com.criminallntent.CrimeList;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import xhj.zime.com.criminallntent.CrimeDetail.Crime;

public class CrimeLab {
    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 5;i++){
            Crime crime = new Crime();
            crime.setTitle("Crime # "+i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime: mCrimes){
            if (crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }

    public int getCrimeIndex(Crime crime){
        return mCrimes.indexOf(crime);
    }
    public void addCrime(Crime crime){
        mCrimes.add(crime);
    }
}
