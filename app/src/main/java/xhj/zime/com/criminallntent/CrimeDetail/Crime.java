package xhj.zime.com.criminallntent.CrimeDetail;

import android.widget.ListView;

import java.util.Date;
import java.util.UUID;

public class Crime {
    //产生唯一ID
    private UUID mId;
    //记录中的标题
    private String mTitle;
    //记录时间
    private Date mDate;
    //问题是否得到解决
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
