package com.book.core.model;

import java.math.BigDecimal;
import java.util.Date;

public class TestOrder {
    private Long id;

    private Date createTime;

    private Long productId;

    private Double realPay;

    private Integer purchaseType;

    private Integer payWay;

    private String nickName;

    private String openId;

    private String mobile;

    private Integer status;

    private String peakId;

    private Long userId;

    private Date updateTime;

    private String productName;

    private String orderNo;

    private Integer plat;

    private Long dramaId;

    private Long playId;

    private String liveDate;

    private String liveTime;

    private String period;

    private String cover;

    private Integer upgrade;

    private Long upgradeOrderId;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getRealPay() {
        return realPay;
    }

    public void setRealPay(Double realPay) {
        this.realPay = realPay;
    }

    public Integer getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(Integer purchaseType) {
        this.purchaseType = purchaseType;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPeakId() {
        return peakId;
    }

    public void setPeakId(String peakId) {
        this.peakId = peakId == null ? null : peakId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getPlat() {
        return plat;
    }

    public void setPlat(Integer plat) {
        this.plat = plat;
    }

    public Long getDramaId() {
        return dramaId;
    }

    public void setDramaId(Long dramaId) {
        this.dramaId = dramaId;
    }

    public Long getPlayId() {
        return playId;
    }

    public void setPlayId(Long playId) {
        this.playId = playId;
    }

    public String getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(String liveDate) {
        this.liveDate = liveDate == null ? null : liveDate.trim();
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime == null ? null : liveTime.trim();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Integer upgrade) {
        this.upgrade = upgrade;
    }

    public Long getUpgradeOrderId() {
        return upgradeOrderId;
    }

    public void setUpgradeOrderId(Long upgradeOrderId) {
        this.upgradeOrderId = upgradeOrderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}