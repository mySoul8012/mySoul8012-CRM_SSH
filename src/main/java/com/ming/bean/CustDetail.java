package com.ming.bean;

public class CustDetail implements java.io.Serializable {
    private Integer custDetailId;
    private String custRegion;
    private String custZip;
    private String custAddress;
    private String custFax;
    private String custWebsite;
    private String custlicense;
    private String custCorporation;
    private Integer custCapital;
    private String custBank;
    private String custPic;
    private String custComment;

    private Customer customer;

    public Integer getCustDetailId() {
        return custDetailId;
    }

    public void setCustDetailId(Integer custDetailId) {
        this.custDetailId = custDetailId;
    }

    public String getCustRegion() {
        return custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion;
    }

    public String getCustZip() {
        return custZip;
    }

    public void setCustZip(String custZip) {
        this.custZip = custZip;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }

    public String getCustWebsite() {
        return custWebsite;
    }

    public void setCustWebsite(String custWebsite) {
        this.custWebsite = custWebsite;
    }

    public String getCustlicense() {
        return custlicense;
    }

    public void setCustlicense(String custlicense) {
        this.custlicense = custlicense;
    }

    public String getCustCorporation() {
        return custCorporation;
    }

    public void setCustCorporation(String custCorporation) {
        this.custCorporation = custCorporation;
    }

    public Integer getCustCapital() {
        return custCapital;
    }

    public void setCustCapital(Integer custCapital) {
        this.custCapital = custCapital;
    }

    public String getCustBank() {
        return custBank;
    }

    public void setCustBank(String custBank) {
        this.custBank = custBank;
    }

    public String getCustPic() {
        return custPic;
    }

    public void setCustPic(String custPic) {
        this.custPic = custPic;
    }

    public String getCustComment() {
        return custComment;
    }

    public void setCustComment(String custComment) {
        this.custComment = custComment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
