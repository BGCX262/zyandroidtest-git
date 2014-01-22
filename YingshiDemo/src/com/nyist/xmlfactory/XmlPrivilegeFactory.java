package com.nyist.xmlfactory;

import java.util.List;



import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import com.nyist.privilege_member.privilege_info;

public class XmlPrivilegeFactory extends DefaultHandler {
	private List<privilege_info> filmhotInfos;
	private privilege_info tInfo;
	private String tagname;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String temp = new String(ch, start, length);
		if (tagname.equals("name")) {
			tInfo.setname(temp);
			
		} else if (tagname.equals("brief")) {
		    tInfo.setbrief(temp);
		}
		super.characters(ch, start, length);
	}

	public XmlPrivilegeFactory(List<privilege_info> filmhotInfos) {
		super();
		this.filmhotInfos = filmhotInfos;
	}
	public void endDocument(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equals("Group")) {
			    tInfo = new privilege_info();
		}
		  tagname = localName;
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String endName)
			throws SAXException {
		if (endName.equals("Group")) {
			     filmhotInfos.add(tInfo);
		}
		tagname ="";
		super.endElement(uri, localName, endName);
	}
}