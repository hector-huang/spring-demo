package me.hector.xml.demo;

import java.util.Enumeration;
import java.util.Hashtable;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXMyHandler extends DefaultHandler {
	// Hash table that stores all tags
	private Hashtable<String, Integer> tags;
	
	@Override
	public void startDocument() throws SAXException {
		tags = new Hashtable<String, Integer>();
	}
	
	@Override
	public void startElement (String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
		String key = localName;
		Object value = tags.get(key);
		
		if (value == null) {
			tags.put(key, new Integer(1));
		}
		else {
			int count = ((Integer)value).intValue();
			count++;
			tags.put(key, new Integer(count));
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		Enumeration e = tags.keys();
		while (e.hasMoreElements()) {
			String tag = (String)e.nextElement();
			int count = ((Integer)tags.get(tag)).intValue();
			System.out.println("Local Name \"" + tag + "\" occurs " + count + " times");
		}
	}
}
