package com.ws.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.ws.constants.ApplicationConstants;
import com.ws.constants.ErrorConstants;
import com.ws.exception.ZipCodeMergerException;
import com.ws.model.ZipCodeRange;

public class MainApp {

	private static final Logger LOGGER = Logger.getLogger(MainApp.class);

	public static void main(String args[]) throws IOException, URISyntaxException {

		String inputFileName = getInputFileName(args);

		try {
			// Choose Implementation
			MergeZipCode mergeZipCode = MergeZipCodeImplFactory.getImpl(ApplicationConstants.DEFAULT_IMPLEMENTATION);

			// Merge the zip codes
			Stack<ZipCodeRange> mergedZipCodes = mergeZipCode.merge(inputFileName);

			displayResult(mergedZipCodes);

		} catch (ZipCodeMergerException zce) {
			LOGGER.error("Bad Request : " + zce.getErrorCode());
			throw zce;
		} catch (IOException ioe) {
			LOGGER.error("Error while reading file : " + inputFileName);
			throw ioe;
		} catch (URISyntaxException use) {
			LOGGER.error("File doesn't exist : " + inputFileName);
			throw use;
		} catch (NumberFormatException nfe) {
			LOGGER.error("Bad Request : " + nfe.getMessage());
			throw nfe;
		}
	}

	/**
	 * Reads Input file name from first index of args
	 * 
	 * @param args
	 * @return fileName if valid arguments exists
	 * @throws ZipCodeMergerException when no input file is specified in program
	 *                                arguments
	 */
	private static String getInputFileName(String[] args) {

		if (args.length > 0) {
			LOGGER.info("Input file name passed : " + args[0]);
			return args[0];
		} else {
			throw new ZipCodeMergerException("No input file passed in Program Arguments",
					ErrorConstants.INPUT_FILE_MISSING_IN_PARAM);
		}
	}

	/** Prints the final output */

	private static void displayResult(Stack<ZipCodeRange> mergedZipCodes) {
		LOGGER.info("Merged ZipCodes :: ");
		for (ZipCodeRange zipCodeRange : mergedZipCodes) {
			LOGGER.info("[" + zipCodeRange.getLowerRange() + "," + zipCodeRange.getHigherRange() + "]");
		}
	}
}
