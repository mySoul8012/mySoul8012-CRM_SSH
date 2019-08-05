package com.ming.bean;

public class Customer implements java.io.Serializable {
    private Integer custId;
    private User custUser;
    private String custName;
    private Dict custSource;
    private Dict custIndustry;
    private Dict custLevel;

    private CustDetail custDetail;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public User getCustUser() {
        return custUser;
    }

    public void setCustUser(User custUser) {
        this.custUser = custUser;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Dict getCustSource() {
        return custSource;
    }

    public void setCustSource(Dict custSource) {
        this.custSource = custSource;
    }

    public Dict getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(Dict custIndustry) {
        this.custIndustry = custIndustry;
    }

    public Dict getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(Dict custLevel) {
        this.custLevel = custLevel;
    }

    public CustDetail getCustDetail() {
        return custDetail;
    }

    public void setCustDetail(CustDetail custDetail) {
        this.custDetail = custDetail;
    }
}
