package com.ws.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.ws.comparator.RangeComparator;
import com.ws.exception.ZipCodeMergerException;
import com.ws.model.ZipCodeRange;
import com.ws.util.FileUtil;

public class MergeZipCodeDefaultImpl implements MergeZipCode {

	private static final Logger LOGGER = Logger.getLogger(MergeZipCodeDefaultImpl.class);

	/** This method reads ZipCodeRanges from a given file, merges them and returns result in a Stack
	 *
	 */
	public Stack<ZipCodeRange> merge(String inputFileName) throws ZipCodeMergerException, IOException, URISyntaxException {
		
		LOGGER.debug("Default Implmentation selected");
		
		List<ZipCodeRange> zipCodeRanges = new FileUtil().readAndValidateZipCodeRanges(inputFileName);

		// Sort the zip code ranges based on lower range.
		Collections.sort(zipCodeRanges, new RangeComparator());

		// Stack to store final result
		Stack<ZipCodeRange> stack = new Stack<ZipCodeRange>();
		
		// Initiatlize the stack with first range
		stack.push(zipCodeRanges.get(0));
		
		// If the zip code overlaps merge them, else push the current zipcoderange to top of stack 
		for (int i = 1; i < zipCodeRanges.size(); i++) {
			if(doesZipCodesOverlap(stack.peek(), zipCodeRanges.get(i))){
				merge(stack, zipCodeRanges.get(i));
			} else {
				stack.push(zipCodeRanges.get(i));
			}
		}
		
		return stack;
	}

	/** Determines if the ZipCodeRanges overlap or not
	 * 
	 * @param zipCodeRange1
	 * @param zipCodeRange2
	 * @return
	 */
	private boolean doesZipCodesOverlap(ZipCodeRange zipCodeRange1, ZipCodeRange zipCodeRange2) {

		if (zipCodeRange1.getHigherRange() < zipCodeRange2.getLowerRange()) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/** Merge top of stack and current zipcoderange and return back updated stack
	 * @param stack
	 * @param zipCodeRange
	 * @return
	 */
	private Stack<ZipCodeRange> merge(Stack<ZipCodeRange>stack, ZipCodeRange zipCodeRange) {
		ZipCodeRange topOfStackZipCodeRange = stack.pop();
		topOfStackZipCodeRange.setHigherRange(Math.max(topOfStackZipCodeRange.getHigherRange(), zipCodeRange.getHigherRange()));
		stack.push(topOfStackZipCodeRange);
	
		return stack;
	}
}
