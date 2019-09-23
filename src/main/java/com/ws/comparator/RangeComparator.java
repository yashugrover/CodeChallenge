package com.ws.comparator;

import java.util.Comparator;

import com.ws.model.ZipCodeRange;

public class RangeComparator implements Comparator<ZipCodeRange>{

	/** This method compares two ZipCodeRange based on their lower range.
	 *  It returns a value > 1, if zipCodeRange1 > zipCodeRange2 lowerRange.
	 *  It returns a value < 1, if zipCodeRange1 < zipCodeRange2 lowerRange.
	 *  It returns 0, if both are equal.
	 */
	
	public int compare(ZipCodeRange zipCodeRange1, ZipCodeRange zipCodeRange2) {
		return zipCodeRange1.getLowerRange() - zipCodeRange2.getLowerRange();
	}
}
