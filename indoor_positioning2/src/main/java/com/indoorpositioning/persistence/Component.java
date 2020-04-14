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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="component")
public class Component {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_cmp;
	@NotEmpty
	@Column(unique = true)
	@Size(max = 100)
	private String name;
	@Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date s_date;
	@Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
	private Date e_date;
    @ManyToOne(
	          fetch = FetchType.LAZY,
	          optional = true
	          //cascade = {CascadeType.PERSIST}
	  )
	  @JoinColumn(
	          name = "id_bk",
	          nullable = true
	  )
	@JsonIgnore
    private Beacon beacon;
    public Component() {}
	public Component(Integer id_cmp,String name, Date s_date, Date e_date) {
		this.id_cmp = id_cmp;
		this.name=name;
		this.s_date = s_date;
		this.e_date = e_date;
	}
	@Override
    public String toString() {
        return String.format(
                "Component [id_cmp='%d',name='%s',s_date='%s',e_date='%s']",
                this.id_cmp,this.name,this.s_date.toString(),this.e_date.toString());
    }
	public Integer getId_cmp() {
		return id_cmp;
	}
	public void setId_cmp(Integer id_cmp) {
		this.id_cmp = id_cmp;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getS_date() {
		return s_date;
	}
	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}
	public Date getE_date() {
		return e_date;
	}
	public void setE_date(Date e_date) {
		this.e_date = e_date;
	}
	public Beacon getBeacon() {
		return beacon;
	}
	public void setBeacon(Beacon beacon) {
		this.beacon = beacon;
	}	
}