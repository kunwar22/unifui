package in.co.srdt.unif.model.rembctg;

public class CtgBasicPay {

	private long currentbasicpay;	
	private long previousbasicpay;
	
	public CtgBasicPay() {
		
	}

	public long getCurrentbasicpay() {
		return currentbasicpay;
	}

	public void setCurrentbasicpay(long currentbasicpay) {
		this.currentbasicpay = currentbasicpay;
	}

	public long getPreviousbasicpay() {
		return previousbasicpay;
	}

	public void setPreviousbasicpay(long previousbasicpay) {
		this.previousbasicpay = previousbasicpay;
	}

	@Override
	public String toString() {
		return "CtgBasicPay [currentbasicpay=" + currentbasicpay + ", previousbasicpay=" + previousbasicpay + "]";
	}
	
	
	
}
