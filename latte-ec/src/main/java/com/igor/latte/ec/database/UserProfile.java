package com.igor.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Igor on 2017/9/14.
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private long mUserId = 0;

    private String mName = null;
    private String mAvatar = null;
    private String mGender = null;
    private String mAddress = null;

    @Generated(hash = 999954825)
    public UserProfile(long mUserId, String mName, String mAvatar, String mGender,
            String mAddress) {
        this.mUserId = mUserId;
        this.mName = mName;
        this.mAvatar = mAvatar;
        this.mGender = mGender;
        this.mAddress = mAddress;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getMUserId() {
        return this.mUserId;
    }
    public void setMUserId(long mUserId) {
        this.mUserId = mUserId;
    }
    public String getMName() {
        return this.mName;
    }
    public void setMName(String mName) {
        this.mName = mName;
    }
    public String getMAvatar() {
        return this.mAvatar;
    }
    public void setMAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }
    public String getMGender() {
        return this.mGender;
    }
    public void setMGender(String mGender) {
        this.mGender = mGender;
    }
    public String getMAddress() {
        return this.mAddress;
    }
    public void setMAddress(String mAddress) {
        this.mAddress = mAddress;
    }
}
