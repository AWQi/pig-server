package com.pojo;

public class ChangePigstyBreed {
    private Integer id;

    private Integer earlabel;

    private Integer outPigsty;

    private Integer inPigsty;

    private String turngroupDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEarlabel() {
        return earlabel;
    }

    public void setEarlabel(Integer earlabel) {
        this.earlabel = earlabel;
    }

    public Integer getOutPigsty() {
        return outPigsty;
    }

    public void setOutPigsty(Integer outPigsty) {
        this.outPigsty = outPigsty;
    }

    public Integer getInPigsty() {
        return inPigsty;
    }

    public void setInPigsty(Integer inPigsty) {
        this.inPigsty = inPigsty;
    }

    public String getTurngroupDate() {
        return turngroupDate;
    }

    public void setTurngroupDate(String turngroupDate) {
        this.turngroupDate = turngroupDate == null ? null : turngroupDate.trim();
    }
}