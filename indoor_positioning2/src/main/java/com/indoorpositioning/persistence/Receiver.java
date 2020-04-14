package com.indoorpositioning.persistence;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="receiver")
public class Receiver {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_rcv;
	@NotNull
	private Integer x_pos;
	@NotNull
    private Integer y_pos;
	@OneToMany(mappedBy = "receiver")
	@OrderBy("date DESC")
    private List<History> histories;
	
    public Receiver() {}
	public Receiver(Integer id_rcv, Integer x_pos, Integer y_pos) {
		this.id_rcv = id_rcv;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
	@Override
    public String toString() {
        return String.format(
                "Receiver [id_rcv='%d',x_pos='%d',y_pos='%d']",
                this.id_rcv,this.x_pos,this.y_pos);
    }
	public Integer getId_rcv() {
		return id_rcv;
	}
	public void setId_rcv(Integer id_rcv) {
		this.id_rcv = id_rcv;
	}
	public Integer getX_pos() {
		return x_pos;
	}
	public void setX_pos(Integer x_pos) {
		this.x_pos = x_pos;
	}
	public Integer getY_pos() {
		return y_pos;
	}
	public void setY_pos(Integer y_pos) {
		this.y_pos = y_pos;
	}
	public List<History> getHistories() {
		return histories;
	}
	public void setHistories(List<History> histories) {
		this.histories = histories;
	}
//	public List<History> getLastNHistories(Integer n) {
//		Integer numOfHistories=histories.size();
//		//To avoid an out of range index
//		if (n>numOfHistories) {
//			n=numOfHistories;
//		}
//		List<History> historyList = null;
//		for (int i = 0; i <numOfHistories; i++) {
//			History h=histories.get(i);
//			historyList.add(h);
//		}
//		return historyList;
//	}
	
}
