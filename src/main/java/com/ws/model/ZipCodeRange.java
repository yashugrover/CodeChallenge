package com.ws.model;

public class ZipCodeRange {

	int lowerRange;
	int higherRange;

	public ZipCodeRange(int lowerRange, int higherRange) {
		this.lowerRange = lowerRange;
		this.higherRange = higherRange;
	}
	public int getLowerRange() {
		return lowerRange;
	}
	public void setLowerRange(int lowerRange) {
		this.lowerRange = lowerRange;
	}
	public int getHigherRange() {
		return higherRange;
	}
	public void setHigherRange(int higherRange) {
		this.higherRange = higherRange;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + higherRange;
		result = prime * result + lowerRange;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZipCodeRange other = (ZipCodeRange) obj;
		if (higherRange != other.higherRange)
			return false;
		if (lowerRange != other.lowerRange)
			return false;
		return true;
	}
}
