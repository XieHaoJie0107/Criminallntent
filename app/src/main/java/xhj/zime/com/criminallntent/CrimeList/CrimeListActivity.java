package xhj.zime.com.criminallntent.CrimeList;

import android.support.v4.app.Fragment;

import xhj.zime.com.criminallntent.SingleFragmentActivity;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
