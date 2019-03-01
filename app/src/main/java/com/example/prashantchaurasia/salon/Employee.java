package com.example.prashantchaurasia.salon;

import android.os.Parcel;
import android.os.Parcelable;

public class Employee implements Parcelable {
    private String name ;
    private String designation ;
    private String mobile ;
    private String inTime ;
    private String outTime ;
    private String offDay ;

    public Employee(String name, String designation, String mobile, String inTime, String outTime, String offDay) {
        this.name = name;
        this.designation = designation;
        this.mobile = mobile;
        this.inTime = inTime;
        this.outTime = outTime;
        this.offDay = offDay;
    }

    protected Employee(Parcel in) {
        name = in.readString();
        designation = in.readString();
        mobile = in.readString();
        inTime = in.readString();
        outTime = in.readString();
        offDay = in.readString();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getOffDay() {
        return offDay;
    }

    public void setOffDay(String offDay) {
        this.offDay = offDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(designation);
        dest.writeString(mobile);
        dest.writeString(inTime);
        dest.writeString(outTime);
        dest.writeString(offDay);
    }
}
