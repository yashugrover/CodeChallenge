package com.ws.validation;

import com.ws.constants.ErrorConstants;
import com.ws.exception.ZipCodeMergerException;

public class ZipCodeValidator {
	
	/** Validates Zip Codes 
	 * 
	 * @param lowerRange
	 * @param higherRange
	 * @throws ZipCodeMergerException when higherRange < lowerRange for any zip code
	 *                                range OR if zip code is not a positive value
	 */
	public static void validateZipCode(Integer lowerRange, Integer higherRange) throws ZipCodeMergerException {
		if (lowerRange > higherRange) {
			throw new ZipCodeMergerException("Higher range : " + higherRange
					+ " should not be lower than lower Range : " + lowerRange,
					ErrorConstants.INVALID_RANGE);
		} else if(lowerRange <=0 || higherRange <=0) {
			throw new ZipCodeMergerException("ZipCode must be a postitive number", ErrorConstants.INVALID_ZIPCODE);
		}		
	}

}
