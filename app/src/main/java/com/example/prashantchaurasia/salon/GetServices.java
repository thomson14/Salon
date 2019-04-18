package com.example.prashantchaurasia.salon;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class GetServices implements Parcelable {

    private String name ;
    private int sid,uid,mprice,mcprice,fcprice,fprice ;

    private String ServiceName,For,Staff;
    private int quantity,rate,Total;
    private Double Discount;

    public GetServices(String name, int sid, int uid, int mprice, int mcprice, int fcprice, int fprice) {
        this.name = name;
        this.sid = sid;
        this.uid = uid;
        this.mprice = mprice;
        this.mcprice = mcprice;
        this.fcprice = fcprice;
        this.fprice = fprice;

        Log.d("GetServices","GetServices" +mcprice);
    }

    public GetServices(String serviceName, String aFor, String staff, int quantity, int rate, int total, Double discount) {
        ServiceName = serviceName;
        For = aFor;
        Staff = staff;
        this.quantity = quantity;
        this.rate = rate;
        Total = total;
        Discount = discount;
    }

    //For Total Bill


    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getFor() {
        return For;
    }

    public void setFor(String aFor) {
        For = aFor;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String staff) {
        Staff = staff;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    //For Services
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMprice() {
        return mprice;
    }

    public void setMprice(int mprice) {
        this.mprice = mprice;
    }

    public int getMcprice() {
        return mcprice;
    }

    public void setMcprice(int mcprice) {
        this.mcprice = mcprice;
    }

    public int getFcprice() {
        return fcprice;
    }

    public void setFcprice(int fcprice) {
        this.fcprice = fcprice;
    }

    public int getFprice() {
        return fprice;
    }

    public void setFprice(int fprice) {
        this.fprice = fprice;
    }

    protected GetServices(Parcel in) {
    }

    public static final Creator<GetServices> CREATOR = new Creator<GetServices>() {
        @Override
        public GetServices createFromParcel(Parcel in) {
            return new GetServices(in);
        }

        @Override
        public GetServices[] newArray(int size) {
            return new GetServices[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

}
