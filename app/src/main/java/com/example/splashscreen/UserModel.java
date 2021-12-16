package com.example.splashscreen;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    String name;
    String username;
    String email;
    boolean jenkel;
    String pass;
    String alamat;
    String telpon;
    String hobi;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isJenkel() {
        return jenkel;
    }

    public void setJenkel(boolean jenkel) {
        this.jenkel = jenkel;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeByte((byte) (jenkel ? 1 : 0));
        dest.writeString(pass);
        dest.writeString(alamat);
        dest.writeString(telpon);
        dest.writeString(hobi);
    }

    public UserModel(){

    }

    protected UserModel(Parcel in) {
        name = in.readString();
        username = in.readString();
        email = in.readString();
        jenkel = in.readByte() != 0;
        pass = in.readString();
        alamat = in.readString();
        telpon = in.readString();
        hobi = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }
        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
