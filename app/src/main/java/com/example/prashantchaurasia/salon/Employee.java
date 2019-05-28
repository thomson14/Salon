package com.example.prashantchaurasia.salon;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {
    private String name ,designation ,intime,outtime ,weekoff ,mobile ;
    private int eid,status,uid;
    public Employee(String name, String designation, String intime, String outtime, String weekoff, String mobile, int eid, int status, int uid) {
        this.name = name;
        this.designation = designation;
        this.intime = intime;
        this.outtime = outtime;
        this.weekoff = weekoff;
        this.mobile = mobile;
        this.eid = eid;
        this.status = status;
        this.uid = uid;
    }

    protected Employee(Parcel in) {
        name = in.readString();
        designation = in.readString();
        intime = in.readString();
        outtime = in.readString();
        weekoff = in.readString();
        mobile = in.readString();
        eid = in.readInt();
        status = in.readInt();
        uid = in.readInt();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getWeekoff() {
        return weekoff;
    }

    public void setWeekoff(String weekoff) {
        this.weekoff = weekoff;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(designation);
        parcel.writeString(intime);
        parcel.writeString(outtime);
        parcel.writeString(weekoff);
        parcel.writeString(mobile);
        parcel.writeInt(eid);
        parcel.writeInt(status);
        parcel.writeInt(uid);
    }
}
