package com.sapan.sampleapp.ui.contact;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ContactInfo implements Parcelable {

    public String id="0";
    public String addressId = "0";
    public int contactType = 0;
    public boolean isLastItem;
    public boolean isFirstItemOnSubTitle;
    public boolean checked;
    public boolean addAnimation;
    public int recipientTypeToogleCount;
    public String name;
    public String phone;
    public String lookupkey;
    public String subtitle;
    public long directoryId = -1;
    public int limit = -1;

    public boolean isPendingToAdd;

    public String toString() {
        return "id : " + id + ", phone : " + phone + ", name : " + name + ", directoryId : " + directoryId + ", limit : " + limit + ", isLastItem : " + isLastItem
                + ", isFirstItemOnSubTitle : " + isFirstItemOnSubTitle + ", subtitle : " + subtitle + ", lookupkey : " + lookupkey;
    }

    public ContactInfo() {}

    public ContactInfo(String contactId, String phone, String name) {
        this.id = contactId;
        this.phone = phone;
        this.name = name;
    }

    public static final Creator<ContactInfo> CREATOR = new Creator<ContactInfo>() {
        @Override
        public ContactInfo[] newArray(int size) {
            return new ContactInfo[size];
        }

        @Override
        public ContactInfo createFromParcel(Parcel parcel) {
            return new ContactInfo(parcel);
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
}
