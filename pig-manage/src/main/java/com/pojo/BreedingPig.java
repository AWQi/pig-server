package com.pojo;

public class BreedingPig {
    private Integer earlabel;

    private Integer pigstyMessage;

    private String pigVariety;

    private String pigType;

    private Long birthdate;

    private Long entergroupDate;

    private String pigState;

    private Integer gender;

    public Integer getEarlabel() {
        return earlabel;
    }

    public void setEarlabel(Integer earlabel) {
        this.earlabel = earlabel;
    }

    public Integer getPigstyMessage() {
        return pigstyMessage;
    }

    public void setPigstyMessage(Integer pigstyMessage) {
        this.pigstyMessage = pigstyMessage;
    }

    public String getPigVariety() {
        return pigVariety;
    }

    public void setPigVariety(String pigVariety) {
        this.pigVariety = pigVariety == null ? null : pigVariety.trim();
    }

    public String getPigType() {
        return pigType;
    }

    public void setPigType(String pigType) {
        this.pigType = pigType == null ? null : pigType.trim();
    }

    public Long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Long birthdate) {
        this.birthdate = birthdate;
    }

    public Long getEntergroupDate() {
        return entergroupDate;
    }

    public void setEntergroupDate(Long entergroupDate) {
        this.entergroupDate = entergroupDate;
    }

    public String getPigState() {
        return pigState;
    }

    public void setPigState(String pigState) {
        this.pigState = pigState == null ? null : pigState.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}