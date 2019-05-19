package com.project.pettycash.withdraw.vo;

import java.util.List;

public class PettyResVo {
	private int countWait;
	private int countAll;
	private int countApprove;
	private int countNotApprove;
	private int countCancel;
	private int countSuccess;
	private List<PettyCashVo> datas;

	public int getCountWait() {
		return countWait;
	}

	public void setCountWait(int countWait) {
		this.countWait = countWait;
	}

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}

	public int getCountApprove() {
		return countApprove;
	}

	public void setCountApprove(int countApprove) {
		this.countApprove = countApprove;
	}

	public int getCountNotApprove() {
		return countNotApprove;
	}

	public void setCountNotApprove(int countNotApprove) {
		this.countNotApprove = countNotApprove;
	}

	public int getCountCancel() {
		return countCancel;
	}

	public void setCountCancel(int countCancel) {
		this.countCancel = countCancel;
	}

	public int getCountSuccess() {
		return countSuccess;
	}

	public void setCountSuccess(int countSuccess) {
		this.countSuccess = countSuccess;
	}

	public List<PettyCashVo> getDatas() {
		return datas;
	}

	public void setDatas(List<PettyCashVo> datas) {
		this.datas = datas;
	}

}
