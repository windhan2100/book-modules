package com.book.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andRealPayIsNull() {
            addCriterion("real_pay is null");
            return (Criteria) this;
        }

        public Criteria andRealPayIsNotNull() {
            addCriterion("real_pay is not null");
            return (Criteria) this;
        }

        public Criteria andRealPayEqualTo(Double value) {
            addCriterion("real_pay =", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayNotEqualTo(Double value) {
            addCriterion("real_pay <>", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayGreaterThan(Double value) {
            addCriterion("real_pay >", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayGreaterThanOrEqualTo(Double value) {
            addCriterion("real_pay >=", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayLessThan(Double value) {
            addCriterion("real_pay <", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayLessThanOrEqualTo(Double value) {
            addCriterion("real_pay <=", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayIn(List<Double> values) {
            addCriterion("real_pay in", values, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayNotIn(List<Double> values) {
            addCriterion("real_pay not in", values, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayBetween(Double value1, Double value2) {
            addCriterion("real_pay between", value1, value2, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayNotBetween(Double value1, Double value2) {
            addCriterion("real_pay not between", value1, value2, "realPay");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeIsNull() {
            addCriterion("purchase_type is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeIsNotNull() {
            addCriterion("purchase_type is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeEqualTo(Integer value) {
            addCriterion("purchase_type =", value, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeNotEqualTo(Integer value) {
            addCriterion("purchase_type <>", value, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeGreaterThan(Integer value) {
            addCriterion("purchase_type >", value, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_type >=", value, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeLessThan(Integer value) {
            addCriterion("purchase_type <", value, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_type <=", value, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeIn(List<Integer> values) {
            addCriterion("purchase_type in", values, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeNotIn(List<Integer> values) {
            addCriterion("purchase_type not in", values, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeBetween(Integer value1, Integer value2) {
            addCriterion("purchase_type between", value1, value2, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPurchaseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_type not between", value1, value2, "purchaseType");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNull() {
            addCriterion("pay_way is null");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNotNull() {
            addCriterion("pay_way is not null");
            return (Criteria) this;
        }

        public Criteria andPayWayEqualTo(Integer value) {
            addCriterion("pay_way =", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotEqualTo(Integer value) {
            addCriterion("pay_way <>", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThan(Integer value) {
            addCriterion("pay_way >", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_way >=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThan(Integer value) {
            addCriterion("pay_way <", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThanOrEqualTo(Integer value) {
            addCriterion("pay_way <=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayIn(List<Integer> values) {
            addCriterion("pay_way in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotIn(List<Integer> values) {
            addCriterion("pay_way not in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayBetween(Integer value1, Integer value2) {
            addCriterion("pay_way between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_way not between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPeakIdIsNull() {
            addCriterion("peak_id is null");
            return (Criteria) this;
        }

        public Criteria andPeakIdIsNotNull() {
            addCriterion("peak_id is not null");
            return (Criteria) this;
        }

        public Criteria andPeakIdEqualTo(String value) {
            addCriterion("peak_id =", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdNotEqualTo(String value) {
            addCriterion("peak_id <>", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdGreaterThan(String value) {
            addCriterion("peak_id >", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdGreaterThanOrEqualTo(String value) {
            addCriterion("peak_id >=", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdLessThan(String value) {
            addCriterion("peak_id <", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdLessThanOrEqualTo(String value) {
            addCriterion("peak_id <=", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdLike(String value) {
            addCriterion("peak_id like", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdNotLike(String value) {
            addCriterion("peak_id not like", value, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdIn(List<String> values) {
            addCriterion("peak_id in", values, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdNotIn(List<String> values) {
            addCriterion("peak_id not in", values, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdBetween(String value1, String value2) {
            addCriterion("peak_id between", value1, value2, "peakId");
            return (Criteria) this;
        }

        public Criteria andPeakIdNotBetween(String value1, String value2) {
            addCriterion("peak_id not between", value1, value2, "peakId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andPlatIsNull() {
            addCriterion("plat is null");
            return (Criteria) this;
        }

        public Criteria andPlatIsNotNull() {
            addCriterion("plat is not null");
            return (Criteria) this;
        }

        public Criteria andPlatEqualTo(Integer value) {
            addCriterion("plat =", value, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatNotEqualTo(Integer value) {
            addCriterion("plat <>", value, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatGreaterThan(Integer value) {
            addCriterion("plat >", value, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatGreaterThanOrEqualTo(Integer value) {
            addCriterion("plat >=", value, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatLessThan(Integer value) {
            addCriterion("plat <", value, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatLessThanOrEqualTo(Integer value) {
            addCriterion("plat <=", value, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatIn(List<Integer> values) {
            addCriterion("plat in", values, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatNotIn(List<Integer> values) {
            addCriterion("plat not in", values, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatBetween(Integer value1, Integer value2) {
            addCriterion("plat between", value1, value2, "plat");
            return (Criteria) this;
        }

        public Criteria andPlatNotBetween(Integer value1, Integer value2) {
            addCriterion("plat not between", value1, value2, "plat");
            return (Criteria) this;
        }

        public Criteria andDramaIdIsNull() {
            addCriterion("drama_id is null");
            return (Criteria) this;
        }

        public Criteria andDramaIdIsNotNull() {
            addCriterion("drama_id is not null");
            return (Criteria) this;
        }

        public Criteria andDramaIdEqualTo(Long value) {
            addCriterion("drama_id =", value, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdNotEqualTo(Long value) {
            addCriterion("drama_id <>", value, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdGreaterThan(Long value) {
            addCriterion("drama_id >", value, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("drama_id >=", value, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdLessThan(Long value) {
            addCriterion("drama_id <", value, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdLessThanOrEqualTo(Long value) {
            addCriterion("drama_id <=", value, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdIn(List<Long> values) {
            addCriterion("drama_id in", values, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdNotIn(List<Long> values) {
            addCriterion("drama_id not in", values, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdBetween(Long value1, Long value2) {
            addCriterion("drama_id between", value1, value2, "dramaId");
            return (Criteria) this;
        }

        public Criteria andDramaIdNotBetween(Long value1, Long value2) {
            addCriterion("drama_id not between", value1, value2, "dramaId");
            return (Criteria) this;
        }

        public Criteria andPlayIdIsNull() {
            addCriterion("play_id is null");
            return (Criteria) this;
        }

        public Criteria andPlayIdIsNotNull() {
            addCriterion("play_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlayIdEqualTo(Long value) {
            addCriterion("play_id =", value, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdNotEqualTo(Long value) {
            addCriterion("play_id <>", value, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdGreaterThan(Long value) {
            addCriterion("play_id >", value, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdGreaterThanOrEqualTo(Long value) {
            addCriterion("play_id >=", value, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdLessThan(Long value) {
            addCriterion("play_id <", value, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdLessThanOrEqualTo(Long value) {
            addCriterion("play_id <=", value, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdIn(List<Long> values) {
            addCriterion("play_id in", values, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdNotIn(List<Long> values) {
            addCriterion("play_id not in", values, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdBetween(Long value1, Long value2) {
            addCriterion("play_id between", value1, value2, "playId");
            return (Criteria) this;
        }

        public Criteria andPlayIdNotBetween(Long value1, Long value2) {
            addCriterion("play_id not between", value1, value2, "playId");
            return (Criteria) this;
        }

        public Criteria andLiveDateIsNull() {
            addCriterion("live_date is null");
            return (Criteria) this;
        }

        public Criteria andLiveDateIsNotNull() {
            addCriterion("live_date is not null");
            return (Criteria) this;
        }

        public Criteria andLiveDateEqualTo(String value) {
            addCriterion("live_date =", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateNotEqualTo(String value) {
            addCriterion("live_date <>", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateGreaterThan(String value) {
            addCriterion("live_date >", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateGreaterThanOrEqualTo(String value) {
            addCriterion("live_date >=", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateLessThan(String value) {
            addCriterion("live_date <", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateLessThanOrEqualTo(String value) {
            addCriterion("live_date <=", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateLike(String value) {
            addCriterion("live_date like", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateNotLike(String value) {
            addCriterion("live_date not like", value, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateIn(List<String> values) {
            addCriterion("live_date in", values, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateNotIn(List<String> values) {
            addCriterion("live_date not in", values, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateBetween(String value1, String value2) {
            addCriterion("live_date between", value1, value2, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveDateNotBetween(String value1, String value2) {
            addCriterion("live_date not between", value1, value2, "liveDate");
            return (Criteria) this;
        }

        public Criteria andLiveTimeIsNull() {
            addCriterion("live_time is null");
            return (Criteria) this;
        }

        public Criteria andLiveTimeIsNotNull() {
            addCriterion("live_time is not null");
            return (Criteria) this;
        }

        public Criteria andLiveTimeEqualTo(String value) {
            addCriterion("live_time =", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeNotEqualTo(String value) {
            addCriterion("live_time <>", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeGreaterThan(String value) {
            addCriterion("live_time >", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("live_time >=", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeLessThan(String value) {
            addCriterion("live_time <", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeLessThanOrEqualTo(String value) {
            addCriterion("live_time <=", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeLike(String value) {
            addCriterion("live_time like", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeNotLike(String value) {
            addCriterion("live_time not like", value, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeIn(List<String> values) {
            addCriterion("live_time in", values, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeNotIn(List<String> values) {
            addCriterion("live_time not in", values, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeBetween(String value1, String value2) {
            addCriterion("live_time between", value1, value2, "liveTime");
            return (Criteria) this;
        }

        public Criteria andLiveTimeNotBetween(String value1, String value2) {
            addCriterion("live_time not between", value1, value2, "liveTime");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(String value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(String value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(String value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(String value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(String value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLike(String value) {
            addCriterion("period like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotLike(String value) {
            addCriterion("period not like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<String> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<String> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(String value1, String value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(String value1, String value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andCoverIsNull() {
            addCriterion("cover is null");
            return (Criteria) this;
        }

        public Criteria andCoverIsNotNull() {
            addCriterion("cover is not null");
            return (Criteria) this;
        }

        public Criteria andCoverEqualTo(String value) {
            addCriterion("cover =", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotEqualTo(String value) {
            addCriterion("cover <>", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverGreaterThan(String value) {
            addCriterion("cover >", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverGreaterThanOrEqualTo(String value) {
            addCriterion("cover >=", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLessThan(String value) {
            addCriterion("cover <", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLessThanOrEqualTo(String value) {
            addCriterion("cover <=", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverLike(String value) {
            addCriterion("cover like", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotLike(String value) {
            addCriterion("cover not like", value, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverIn(List<String> values) {
            addCriterion("cover in", values, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotIn(List<String> values) {
            addCriterion("cover not in", values, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverBetween(String value1, String value2) {
            addCriterion("cover between", value1, value2, "cover");
            return (Criteria) this;
        }

        public Criteria andCoverNotBetween(String value1, String value2) {
            addCriterion("cover not between", value1, value2, "cover");
            return (Criteria) this;
        }

        public Criteria andUpgradeIsNull() {
            addCriterion("upgrade is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeIsNotNull() {
            addCriterion("upgrade is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeEqualTo(Integer value) {
            addCriterion("upgrade =", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeNotEqualTo(Integer value) {
            addCriterion("upgrade <>", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeGreaterThan(Integer value) {
            addCriterion("upgrade >", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("upgrade >=", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeLessThan(Integer value) {
            addCriterion("upgrade <", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeLessThanOrEqualTo(Integer value) {
            addCriterion("upgrade <=", value, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeIn(List<Integer> values) {
            addCriterion("upgrade in", values, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeNotIn(List<Integer> values) {
            addCriterion("upgrade not in", values, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeBetween(Integer value1, Integer value2) {
            addCriterion("upgrade between", value1, value2, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeNotBetween(Integer value1, Integer value2) {
            addCriterion("upgrade not between", value1, value2, "upgrade");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdIsNull() {
            addCriterion("upgrade_order_id is null");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdIsNotNull() {
            addCriterion("upgrade_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdEqualTo(Long value) {
            addCriterion("upgrade_order_id =", value, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdNotEqualTo(Long value) {
            addCriterion("upgrade_order_id <>", value, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdGreaterThan(Long value) {
            addCriterion("upgrade_order_id >", value, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("upgrade_order_id >=", value, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdLessThan(Long value) {
            addCriterion("upgrade_order_id <", value, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("upgrade_order_id <=", value, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdIn(List<Long> values) {
            addCriterion("upgrade_order_id in", values, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdNotIn(List<Long> values) {
            addCriterion("upgrade_order_id not in", values, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdBetween(Long value1, Long value2) {
            addCriterion("upgrade_order_id between", value1, value2, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andUpgradeOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("upgrade_order_id not between", value1, value2, "upgradeOrderId");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}