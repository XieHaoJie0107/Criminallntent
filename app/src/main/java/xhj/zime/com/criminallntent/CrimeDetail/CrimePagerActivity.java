package xhj.zime.com.criminallntent.CrimeDetail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

import xhj.zime.com.criminallntent.CrimeList.CrimeLab;
import xhj.zime.com.criminallntent.R;

public class CrimePagerActivity extends AppCompatActivity implements View.OnClickListener,CrimeFragment.CallBacks {
    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private static final String EXTRA_CRIME_ID = "xhj.zime.com.criminallntent.CrimeDetail.crime_id";
    private Button mJumpToFitst, mJumpToLast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);
        mJumpToFitst = (Button) findViewById(R.id.btn_jump_to_first);
        mJumpToLast = (Button) findViewById(R.id.btn_jump_to_last);
        mJumpToFitst.setOnClickListener(this);
        mJumpToLast.setOnClickListener(this);
        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = mCrimes.get(i);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
            }
        }
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0){
                    mJumpToFitst.setEnabled(false);
                    mJumpToLast.setEnabled(true);
                }else if (i == mCrimes.size() - 1){
                    mJumpToLast.setEnabled(false);
                    mJumpToFitst.setEnabled(true);
                }else {
                    mJumpToLast.setEnabled(true);
                    mJumpToFitst.setEnabled(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jump_to_first:
                mViewPager.setCurrentItem(0);
                mJumpToFitst.setEnabled(false);
                mJumpToLast.setEnabled(true);
                break;
            case R.id.btn_jump_to_last:
                mViewPager.setCurrentItem(mCrimes.size() - 1);
                mJumpToLast.setEnabled(false);
                mJumpToFitst.setEnabled(true);
                break;
        }
    }

    @Override
    public void onCrimeUpdate(Crime crime) {

    }
}
