package com.ynzhxf.nd.firecontrolapp.login.bean;

import com.google.gson.annotations.SerializedName;

public class LoginUserOrgBean {
    @SerializedName("MinistriesTypeID")
    String MinistriesTypeID = "";
    @SerializedName("MinistriesType")
    String MinistriesType = "";
    @SerializedName("AreaName")
    String AreaName = "";
    @SerializedName("Name")
    String Name = "";
    @SerializedName("ParentID")
    String ParentID = "";
    @SerializedName("Area")
    String Area = "";
    @SerializedName("Category")
    String Category = "";
    @SerializedName("Code")
    String Code = "";
    @SerializedName("Contacts")
    String Contacts = "";
    @SerializedName("ContactsPhone")
    String ContactsPhone = "";
    @SerializedName("Addr")
    String Addr = "";
    @SerializedName("OrganizationTypeID")
    String OrganizationTypeID = "";
    @SerializedName("OrganizationType")
    String OrganizationType = "";
    @SerializedName("ID")
    String ID = "";
    @SerializedName("IsNew")
    boolean IsNew = false;

    public String getMinistriesTypeID() {
        return MinistriesTypeID;
    }

    public String getMinistriesType() {
        return MinistriesType;
    }

    public String getAreaName() {
        return AreaName;
    }

    public String getName() {
        return Name;
    }

    public String getParentID() {
        return ParentID;
    }

    public String getArea() {
        return Area;
    }

    public String getCategory() {
        return Category;
    }

    public String getCode() {
        return Code;
    }

    public String getContacts() {
        return Contacts;
    }

    public String getContactsPhone() {
        return ContactsPhone;
    }

    public String getAddr() {
        return Addr;
    }

    public String getOrganizationTypeID() {
        return OrganizationTypeID;
    }

    public String getOrganizationType() {
        return OrganizationType;
    }

    public String getID() {
        return ID;
    }

    public boolean isNew() {
        return IsNew;
    }
}
