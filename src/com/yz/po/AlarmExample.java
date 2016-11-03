package com.yz.po;

import java.util.ArrayList;
import java.util.List;

public class AlarmExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlarmExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCameraidIsNull() {
            addCriterion("cameraid is null");
            return (Criteria) this;
        }

        public Criteria andCameraidIsNotNull() {
            addCriterion("cameraid is not null");
            return (Criteria) this;
        }

        public Criteria andCameraidEqualTo(Integer value) {
            addCriterion("cameraid =", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotEqualTo(Integer value) {
            addCriterion("cameraid <>", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidGreaterThan(Integer value) {
            addCriterion("cameraid >", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cameraid >=", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidLessThan(Integer value) {
            addCriterion("cameraid <", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidLessThanOrEqualTo(Integer value) {
            addCriterion("cameraid <=", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidIn(List<Integer> values) {
            addCriterion("cameraid in", values, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotIn(List<Integer> values) {
            addCriterion("cameraid not in", values, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidBetween(Integer value1, Integer value2) {
            addCriterion("cameraid between", value1, value2, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotBetween(Integer value1, Integer value2) {
            addCriterion("cameraid not between", value1, value2, "cameraid");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeIsNull() {
            addCriterion("alarmtime is null");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeIsNotNull() {
            addCriterion("alarmtime is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeEqualTo(String value) {
            addCriterion("alarmtime =", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeNotEqualTo(String value) {
            addCriterion("alarmtime <>", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeGreaterThan(String value) {
            addCriterion("alarmtime >", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeGreaterThanOrEqualTo(String value) {
            addCriterion("alarmtime >=", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeLessThan(String value) {
            addCriterion("alarmtime <", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeLessThanOrEqualTo(String value) {
            addCriterion("alarmtime <=", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeLike(String value) {
            addCriterion("alarmtime like", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeNotLike(String value) {
            addCriterion("alarmtime not like", value, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeIn(List<String> values) {
            addCriterion("alarmtime in", values, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeNotIn(List<String> values) {
            addCriterion("alarmtime not in", values, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeBetween(String value1, String value2) {
            addCriterion("alarmtime between", value1, value2, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andAlarmtimeNotBetween(String value1, String value2) {
            addCriterion("alarmtime not between", value1, value2, "alarmtime");
            return (Criteria) this;
        }

        public Criteria andPertypeIsNull() {
            addCriterion("pertype is null");
            return (Criteria) this;
        }

        public Criteria andPertypeIsNotNull() {
            addCriterion("pertype is not null");
            return (Criteria) this;
        }

        public Criteria andPertypeEqualTo(Integer value) {
            addCriterion("pertype =", value, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeNotEqualTo(Integer value) {
            addCriterion("pertype <>", value, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeGreaterThan(Integer value) {
            addCriterion("pertype >", value, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pertype >=", value, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeLessThan(Integer value) {
            addCriterion("pertype <", value, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeLessThanOrEqualTo(Integer value) {
            addCriterion("pertype <=", value, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeIn(List<Integer> values) {
            addCriterion("pertype in", values, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeNotIn(List<Integer> values) {
            addCriterion("pertype not in", values, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeBetween(Integer value1, Integer value2) {
            addCriterion("pertype between", value1, value2, "pertype");
            return (Criteria) this;
        }

        public Criteria andPertypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pertype not between", value1, value2, "pertype");
            return (Criteria) this;
        }

        public Criteria andPernameIsNull() {
            addCriterion("pername is null");
            return (Criteria) this;
        }

        public Criteria andPernameIsNotNull() {
            addCriterion("pername is not null");
            return (Criteria) this;
        }

        public Criteria andPernameEqualTo(String value) {
            addCriterion("pername =", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameNotEqualTo(String value) {
            addCriterion("pername <>", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameGreaterThan(String value) {
            addCriterion("pername >", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameGreaterThanOrEqualTo(String value) {
            addCriterion("pername >=", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameLessThan(String value) {
            addCriterion("pername <", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameLessThanOrEqualTo(String value) {
            addCriterion("pername <=", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameLike(String value) {
            addCriterion("pername like", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameNotLike(String value) {
            addCriterion("pername not like", value, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameIn(List<String> values) {
            addCriterion("pername in", values, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameNotIn(List<String> values) {
            addCriterion("pername not in", values, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameBetween(String value1, String value2) {
            addCriterion("pername between", value1, value2, "pername");
            return (Criteria) this;
        }

        public Criteria andPernameNotBetween(String value1, String value2) {
            addCriterion("pername not between", value1, value2, "pername");
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