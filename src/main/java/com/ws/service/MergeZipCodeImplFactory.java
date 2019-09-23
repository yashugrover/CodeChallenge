package com.ws.service;

import java.util.HashMap;
import java.util.Map;

import com.ws.exception.ZipCodeMergerException;

/**
 * Factory to retrieve MergeZipCode Implementation
 *
 */
public class MergeZipCodeImplFactory {
	
    private static MergeZipCodeDefaultImpl mergeZipCodeDefaultImpl = new MergeZipCodeDefaultImpl();

	private static Map<String, MergeZipCode> mergeZipCodeImplMap = new HashMap<String, MergeZipCode>();

	static {
		mergeZipCodeImplMap.put("default", mergeZipCodeDefaultImpl);
	}
	
	public static MergeZipCode getImpl(String key) {
		MergeZipCode mergeZipCode = mergeZipCodeImplMap.get(key);
		if (mergeZipCode == null) {
            throw new ZipCodeMergerException("Invalid Implementation passed : " + key);
		}
		return mergeZipCode;
	}

}
