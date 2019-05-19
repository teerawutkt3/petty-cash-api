package com.project.pettycash.withdraw.persistence.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PETTY_CASH")
public class PettyCash {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "code")
	private String code;
	@Column(name = "emp_id")
	private String emp_id;
	@Column(name = "description")
	private String description;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "status")
	private String status;
	@Column(name = "craete_datetime")
	private Date craeteDatetime;
	@Column(name = "update_datetime")
	private Date updateDatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCraeteDatetime() {
		return craeteDatetime;
	}

	public void setCraeteDatetime(Date craeteDatetime) {
		this.craeteDatetime = craeteDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

}
