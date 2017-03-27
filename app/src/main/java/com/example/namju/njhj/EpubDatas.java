package com.example.namju.njhj;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by namju on 2017-03-24.
 */

// EPUB 하나에 대한 정보
// implements Parcelable을 한 이유 : 액티비티 간에 정보를 전달하기 위함
public class EpubDatas implements Parcelable {
    private String epub = "";

    // 생성자
    public EpubDatas(String epub) {
        this.epub = epub;
    }
    public EpubDatas(Parcel in) {
        readFromParcel(in);
    }
    public EpubDatas() {
        // ...
    }

    public String getEpub() {
        return epub;
    }
    public void setEpub(String epub) {
        this.epub = epub;
    }
    // ????????????????
    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel arg0, int arg1) {
        arg0.writeString(epub);
    }
    private void readFromParcel(Parcel in) {
        epub = in.readString();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public EpubDatas createFromParcel(Parcel source) {
            return new EpubDatas(source);
        }
        public EpubDatas[] newArray(int size) {
            return new EpubDatas[size];
        }
    };
}
