package com.pojo;

public class Eliminate {
    private Integer id;

    private Integer earlabel;

    private String pigstyMessage;

    private String pigType;

    private String eliminateStage;

    private String eliminateType;

    private String eliminateReason;

    private Integer number;

    private Float totalWeight;

    private String eliminateDate;

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

    public String getPigstyMessage() {
        return pigstyMessage;
    }

    public void setPigstyMessage(String pigstyMessage) {
        this.pigstyMessage = pigstyMessage == null ? null : pigstyMessage.trim();
    }

    public String getPigType() {
        return pigType;
    }

    public void setPigType(String pigType) {
        this.pigType = pigType == null ? null : pigType.trim();
    }

    public String getEliminateStage() {
        return eliminateStage;
    }

    public void setEliminateStage(String eliminateStage) {
        this.eliminateStage = eliminateStage == null ? null : eliminateStage.trim();
    }

    public String getEliminateType() {
        return eliminateType;
    }

    public void setEliminateType(String eliminateType) {
        this.eliminateType = eliminateType == null ? null : eliminateType.trim();
    }

    public String getEliminateReason() {
        return eliminateReason;
    }

    public void setEliminateReason(String eliminateReason) {
        this.eliminateReason = eliminateReason == null ? null : eliminateReason.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getEliminateDate() {
        return eliminateDate;
    }

    public void setEliminateDate(String eliminateDate) {
        this.eliminateDate = eliminateDate == null ? null : eliminateDate.trim();
    }
}