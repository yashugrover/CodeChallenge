package com.ws.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;

import com.ws.constants.ErrorConstants;
import com.ws.exception.ZipCodeMergerException;
import com.ws.model.ZipCodeRange;
import com.ws.validation.ZipCodeValidator;

public class FileUtil {

	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

	/**
	 * Read Zip Code ranges from a file and populate them into a list of
	 * ZipCodeRanges
	 * 
	 * @param inputFileName
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ZipCodeMergerException when higherRange < lowerRange for any zip code
	 *                                range OR if Input file is empty OR if file not found
	 */
	public List<ZipCodeRange> readAndValidateZipCodeRanges(String inputFileName)
			throws IOException, URISyntaxException, ZipCodeMergerException {

		List<ZipCodeRange> zipCodeRanges = new ArrayList<ZipCodeRange>();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputFileName);

		if (inputStream == null)
			throw new ZipCodeMergerException("File Not Found Exception", ErrorConstants.FILE_NOT_FOUND);
		try {
			if (inputStream != null) {
				LineIterator it = IOUtils.lineIterator(inputStream, "UTF-8");
				while (it.hasNext()) {
					String line = it.nextLine();
					Integer lowerRange = Integer.parseInt(line.substring(0, line.indexOf(",")));
					Integer higherRange = Integer.parseInt(line.substring(line.indexOf(",") + 1, line.length()));
					
					// Validate zip code before adding to List
					ZipCodeValidator.validateZipCode(lowerRange, higherRange);
					
					LOGGER.info(new MessageFormat("Reading zipcodes from input file :: [{0},{1}]")
							.format(new Object[] { lowerRange.toString(), higherRange.toString() }));

					zipCodeRanges.add(new ZipCodeRange(lowerRange, higherRange));
				}

				if (zipCodeRanges.isEmpty()) {
					throw new ZipCodeMergerException("Input file is empty, no ZipCode Ranges found",
							ErrorConstants.FILE_EMPTY);
				}
			}
		} finally {
			// Closing stream
			IOUtils.closeQuietly(inputStream);
		}

		return zipCodeRanges;
	}
}
