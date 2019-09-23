package com.ws.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ws.exception.ZipCodeMergerException;
import com.ws.model.ZipCodeRange;

public class MainAppTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test 
	public void error_emptyFile() throws IOException, URISyntaxException {
		exceptionRule.expect(ZipCodeMergerException.class);
        exceptionRule.expectMessage("Input file is empty, no ZipCode Ranges found");
		MainApp.main(new String[] {"Error_EmptyFile.txt"});
	}
	
	@Test 
	public void error_InputFileNotSpecified() throws IOException, URISyntaxException {
		exceptionRule.expect(ZipCodeMergerException.class);
        exceptionRule.expectMessage("No input file passed in Program Arguments");
		MainApp.main(new String[] {});
	}
	
	@Test
    public void error_FileNotFound() throws IOException, URISyntaxException {
    	exceptionRule.expect(ZipCodeMergerException.class);
        exceptionRule.expectMessage("File Not Found Exception");
        MainApp.main(new String[]{"ErrorInputttt.txt"}); 
    }
	
	@Test
    public void error_higherZipCodeLowerThanLowerZipCode() throws IOException, URISyntaxException {
    	exceptionRule.expect(ZipCodeMergerException.class);
        exceptionRule.expectMessage("Higher range : 94132 should not be lower than lower Range : 94133");
        MainApp.main(new String[]{"Error_higherZipCodeLowerThanLowerZipCode.txt"}); 
    }	
	
	@Test
    public void error_zeroZipCode() throws IOException, URISyntaxException {
		exceptionRule.expect(ZipCodeMergerException.class);
        exceptionRule.expectMessage("ZipCode must be a postitive number");
        MainApp.main(new String[]{"Error_ZeroZipCode.txt"}); 
    }
	
	@Test
    public void error_negativeZipCode() throws IOException, URISyntaxException {
		exceptionRule.expect(ZipCodeMergerException.class);
		exceptionRule.expectMessage("ZipCode must be a postitive number");
        MainApp.main(new String[]{"Error_NegativeZipCode.txt"}); 
    }
	
	@Test
    public void success_SameBoundaryOverlap() throws IOException, URISyntaxException {
		MergeZipCode mergeZipCode = new MergeZipCodeDefaultImpl();
		Stack<ZipCodeRange> actualResult = mergeZipCode.merge("Success_SameBoundaryOverlap.txt");
		Stack<ZipCodeRange> expectedResult = new Stack<ZipCodeRange>();
		expectedResult.push(new ZipCodeRange(94086,94089));
		Assert.assertEquals(expectedResult, actualResult);
    }
	
	@Test
    public void success_NoOverlap() throws IOException, URISyntaxException {
		MergeZipCode mergeZipCode = new MergeZipCodeDefaultImpl();
		Stack<ZipCodeRange> actualResult = mergeZipCode.merge("Success_NoOverlap.txt");
		Stack<ZipCodeRange> expectedResult = new Stack<ZipCodeRange>();
		expectedResult.push(new ZipCodeRange(94086,94087));
		expectedResult.push(new ZipCodeRange(94088,94089));
		Assert.assertEquals(expectedResult, actualResult);
    }
	
	@Test
    public void success_PartialOverlap() throws IOException, URISyntaxException {
		MergeZipCode mergeZipCode = new MergeZipCodeDefaultImpl();
		Stack<ZipCodeRange> actualResult = mergeZipCode.merge("Success_PartialOverlap.txt");
		Stack<ZipCodeRange> expectedResult = new Stack<ZipCodeRange>();
		expectedResult.push(new ZipCodeRange(94000,94001));
		expectedResult.push(new ZipCodeRange(94080,94087));
		Assert.assertEquals(expectedResult, actualResult);
    }
	
	@Test
    public void success_TotalOverlap() throws IOException, URISyntaxException {
		MergeZipCode mergeZipCode = new MergeZipCodeDefaultImpl();
		Stack<ZipCodeRange> actualResult = mergeZipCode.merge("Success_TotalOverlap.txt");
		Stack<ZipCodeRange> expectedResult = new Stack<ZipCodeRange>();
		expectedResult.push(new ZipCodeRange(94080,94090));
		Assert.assertEquals(expectedResult, actualResult);
    }
	
	@Test
    public void success_SingleRange() throws IOException, URISyntaxException {
		MergeZipCode mergeZipCode = new MergeZipCodeDefaultImpl();
		Stack<ZipCodeRange> actualResult = mergeZipCode.merge("Success_SingleRange.txt");
		Stack<ZipCodeRange> expectedResult = new Stack<ZipCodeRange>();
		expectedResult.push(new ZipCodeRange(98989,98990));
		Assert.assertEquals(expectedResult, actualResult);
	}
}
