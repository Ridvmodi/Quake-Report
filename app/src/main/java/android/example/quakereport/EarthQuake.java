package android.example.quakereport;

import java.util.Date;

public class EarthQuake {

    private double mMag;
    private String mLocation;
    private long mTimeInMillisec;
    private String mSiteUrl;

    EarthQuake(double mag, String location, long timeInMillisec, String siteUrl) {
        this.mMag = mag;
        this.mLocation = location;
        this.mTimeInMillisec = timeInMillisec;
        this.mSiteUrl = siteUrl;

    }
    public double getMag() { return mMag; }

    public String getLocation() {
        return mLocation;
    }

    public long getmTimeInMillisec() {
        return mTimeInMillisec;
    }

    public String getmSiteUrl() {
        return mSiteUrl;
    }
}
