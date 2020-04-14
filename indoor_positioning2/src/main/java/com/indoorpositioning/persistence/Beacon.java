package com.indoorpositioning.persistence;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.data.relational.core.mapping.Embedded.Nullable;


@Entity
@Table(name="beacon",uniqueConstraints = @UniqueConstraint(columnNames = {"major", "minor"}))
public class Beacon {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_bk;
	@NotNull
	private Integer major;
	@NotNull
    private Integer minor;
	@OneToMany(mappedBy = "beacon")
    private List<History> histories;
	@OneToMany(mappedBy = "beacon")
    private List<Component> components;
	@Nullable
	private String name;
	
    public Beacon() {}
	public Beacon(Integer id_bk, Integer major, Integer minor) {
		this.id_bk = id_bk;
		this.major = major;
		this.minor = minor;
		
	}
	@Override
    public String toString() {
        return String.format(
                "Beacon [id_bk='%d',major='%d',minor='%d',name='%s']",
                this.id_bk,this.major,this.minor,this.name);
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId_bk() {
		return id_bk;
	}
	public void setId_bk(Integer id_bk) {
		this.id_bk = id_bk;
	}
	public Integer getMajor() {
		return major;
	}
	public void setMajor(Integer major) {
		this.major = major;
	}
	public Integer getMinor() {
		return minor;
	}
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
}
