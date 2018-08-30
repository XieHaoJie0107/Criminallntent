package xhj.zime.com.criminallntent.CrimeDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import xhj.zime.com.criminallntent.R;
import xhj.zime.com.criminallntent.SingleFragmentActivity;

public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
