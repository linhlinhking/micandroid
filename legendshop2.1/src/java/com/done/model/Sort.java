package com.done.model;

import java.util.List;

/**
 * Sort generated by MyEclipse Persistence Tools
 */

public class Sort implements java.io.Serializable {

    // Fields

    private Integer sortId;

    private String sortName;

    private String picture;

    private String userId;

    private String userName;

    private Integer seq;

    private List<Nsort> nsort;

    // Constructors

    /** default constructor */
    public Sort() {
    }

    /** minimal constructor */
    public Sort(Integer sortId) {
        this.sortId = sortId;
    }

    /** full constructor */
    public Sort(Integer sortId, String sortName, String picture, String userId, String userName) {
        this.sortId = sortId;
        this.sortName = sortName;
        this.picture = picture;
        this.userId = userId;
        this.userName = userName;
    }

    // Property accessors

    public Integer getSortId() {
        return this.sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Nsort> getNsort() {
        return nsort;
    }

    public void setNsort(List<Nsort> nsort) {
        this.nsort = nsort;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}