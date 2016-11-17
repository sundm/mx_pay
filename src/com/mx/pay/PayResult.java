package com.mx.pay;

import java.io.Serializable;

public class PayResult implements Serializable {

	private static final long serialVersionUID = 3635600053153092768L;
	private int resultCode;
	private String tranTime;
	private String resultDesc;
	private String tradeId;
	private String tradeAmount;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getTranTime() {
		return tranTime;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	@Override
	public String toString() {
		return "PayResult [resultCode=" + resultCode + ", tranTime=" + tranTime + ", resultDesc=" + resultDesc
				+ ", tradeId=" + tradeId + ", tradeAmount=" + tradeAmount + "]";
	}

}
