package com.indoorpositioning.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="history")
public class History {

	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_history;
	@ManyToOne(
	          fetch = FetchType.LAZY,
	          optional = false
	  )
	  @JoinColumn(
	          name = "id_bk",
	          nullable = false
	  )
	@JsonIgnore
    private Beacon beacon;
	@ManyToOne(
	          fetch = FetchType.LAZY,
	          optional = false
	  )
	  @JoinColumn(
	          name = "id_rcv",
	          nullable = false
	  )
	@JsonIgnore
	private Receiver receiver;
	@NotNull
    private Integer rssi;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date date;
    @Max(100)
    private Float battery;
    
    
    public History() {}
	public History(Integer id_history, Beacon beacon, Receiver receiver, Integer rssi, Date date, Float battery) {
		this.id_history = id_history;
		this.beacon = beacon;
		this.receiver = receiver;
		this.rssi = rssi;
		this.date = date;
		this.battery = battery;
	}
	@Override
    public String toString() {
        return String.format(
                "History [id_history='%d', '%s' , '%s' ,rssi='%d',date='%s',battery='%s' ]",
                this.id_history, this.beacon.toString(), this.receiver.toString(),this.rssi,this.date.toString(),this.battery.toString());
    }
	public Integer getId_history() {
		return id_history;
	}
	public void setId_history(Integer id_history) {
		this.id_history = id_history;
	}
	
	public Integer getRssi() {
		return rssi;
	}
	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getBattery() {
		return battery;
	}
	public void setBattery(Float battery) {
		this.battery = battery;
	}
	public Beacon getBeacon() {
		return beacon;
	}
	public void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}
	public Receiver getReceiver() {
		return receiver;
	}
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	


}
