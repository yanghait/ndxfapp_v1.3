package com.ynzhxf.nd.firecontrolapp.login.bean;

import com.google.gson.annotations.SerializedName;

public class LoginUserBean {
    @SerializedName("UserName")
    String userName = "";
    @SerializedName("Password")
    String password = "";
    @SerializedName("ConfirmPassword")
    String confirmPassword = "";
    @SerializedName("ProjectRoleTypeID")
    String projectRoleTypeID = "";
    @SerializedName("UserOrgainzationType")
    String userOrgainzationType = "";
    @SerializedName("Name")
    String name = "";
    @SerializedName("OrganizationID")
    String organizationID = "";
    @SerializedName("RoleID")
    String roleID = "";
    @SerializedName("Role")
    String role = "";
    @SerializedName("Phone")
    String Phone = "";
    @SerializedName("ContactsPhone")
    String ContactsPhone = "";
    @SerializedName("Addr")
    String Addr = "";
    @SerializedName("Occupation")
    String Occupation = "";
    @SerializedName("IsReceivePushMsgAlert")
    boolean IsReceivePushMsgAlert = false;
    @SerializedName("IdCard")
    String IdCard = "";
    @SerializedName("Sex")
    String Sex = "";
    @SerializedName("IsLock")
    boolean IsLock = false;
    @SerializedName("Remark")
    String Remark = "";
    @SerializedName("InPutTime")
    String InPutTime = "";
    @SerializedName("QueryKey")
    String QueryKey = "";
    @SerializedName("ID")
    String ID = "";
    @SerializedName("IsNew")
    boolean IsNew = false;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getProjectRoleTypeID() {
        return projectRoleTypeID;
    }

    public String getUserOrgainzationType() {
        return userOrgainzationType;
    }

    public String getName() {
        return name;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public String getRoleID() {
        return roleID;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return Phone;
    }

    public String getContactsPhone() {
        return ContactsPhone;
    }

    public String getAddr() {
        return Addr;
    }

    public String getOccupation() {
        return Occupation;
    }

    public boolean isReceivePushMsgAlert() {
        return IsReceivePushMsgAlert;
    }

    public String getIdCard() {
        return IdCard;
    }

    public String getSex() {
        return Sex;
    }

    public boolean isLock() {
        return IsLock;
    }

    public String getRemark() {
        return Remark;
    }

    public String getInPutTime() {
        return InPutTime;
    }

    public String getQueryKey() {
        return QueryKey;
    }

    public String getID() {
        return ID;
    }

    public boolean isNew() {
        return IsNew;
    }
}
