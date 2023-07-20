package com.example.vus;

public class Stud
{
    String Reg_No,Bank_Name,Branch,IFSC,Name,Scholar_ship_sem,acc_name,acc_no,ph_no,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Stud() {

    }
    public Stud(String reg_No, String bank_Name, String branch, String IFSC, String name, String scholar_ship_sem, String acc_name, String acc_no, String ph_no,String status) {
        Reg_No = reg_No;
        Bank_Name = bank_Name;
        Branch = branch;
        this.IFSC = IFSC;
        Name = name;
        Scholar_ship_sem = scholar_ship_sem;
        this.acc_name = acc_name;
        this.acc_no = acc_no;
        this.ph_no = ph_no;
        this.status=status;
    }

    public String getReg_No() {
        return Reg_No;
    }

    public void setReg_No(String reg_No) {
        Reg_No = reg_No;
    }

    public String getBank_Name() {
        return Bank_Name;
    }

    public void setBank_Name(String bank_Name) {
        Bank_Name = bank_Name;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getIFSC() {
        return IFSC;
    }

    public void setIFSC(String IFSC) {
        this.IFSC = IFSC;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getScholar_ship_sem() {
        return Scholar_ship_sem;
    }

    public void setScholar_ship_sem(String scholar_ship_sem) {
        Scholar_ship_sem = scholar_ship_sem;
    }

    public String getAcc_name() {
        return acc_name;
    }

    public void setAcc_name(String acc_name) {
        this.acc_name = acc_name;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }


}
