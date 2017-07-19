package com.bennytran.currencyapi.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rate {

    @Id
    private String code;
    private Float rate;
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    private Date date;

    public Rate(String eur, float v, Date date) {
        this.code = eur;
        this.rate = v;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
