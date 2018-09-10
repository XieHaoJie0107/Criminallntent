package xhj.zime.com.criminallntent.CrimeList;

import android.content.Intent;
import android.support.v4.app.Fragment;

import xhj.zime.com.criminallntent.CrimeDetail.Crime;
import xhj.zime.com.criminallntent.CrimeDetail.CrimeFragment;
import xhj.zime.com.criminallntent.CrimeDetail.CrimePagerActivity;
import xhj.zime.com.criminallntent.R;
import xhj.zime.com.criminallntent.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.CallBacks,CrimeFragment.CallBacks{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null){
            Intent intent = CrimePagerActivity.newIntent(this,crime.getId());
            startActivity(intent);
        }else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container,newDetail)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdate(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}
