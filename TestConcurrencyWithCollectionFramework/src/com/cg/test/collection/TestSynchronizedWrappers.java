package com.cg.test.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TestSynchronizedWrappers {
	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		list.add("Amy");
		list.add("Carl");
		list.add("Erica");
		
		List<String> synchList = Collections.synchronizedList(list);
		
		Collection<String> synchCollection= Collections.synchronizedCollection(list);		
		
	}
}
