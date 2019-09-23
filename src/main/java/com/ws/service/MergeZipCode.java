package com.ws.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Stack;

import com.ws.exception.ZipCodeMergerException;
import com.ws.model.ZipCodeRange;

public interface MergeZipCode {

	public Stack<ZipCodeRange> merge(String fileName) throws ZipCodeMergerException, IOException, URISyntaxException;
}
