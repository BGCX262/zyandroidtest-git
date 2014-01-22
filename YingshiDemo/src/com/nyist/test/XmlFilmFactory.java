package com.nyist.test;

import java.util.List;



import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class XmlFilmFactory extends DefaultHandler {
	private List<film_info> filmhotInfos;
	private film_info tInfo;
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
		else if(tagname.equals("price"))
		{
			tInfo.setprice(temp);
		}
		else if(tagname.equals("time"))
		{
			tInfo.settime(temp);
		}
		super.characters(ch, start, length);
	}

	public XmlFilmFactory(List<film_info> filmhotInfos) {
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
		if (localName.equals("FilmHot")) {
			    tInfo = new film_info();
		}
		  tagname = localName;
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String endName)
			throws SAXException {
		if (endName.equals("FilmHot")) {
			     filmhotInfos.add(tInfo);
		}
		tagname ="";
		super.endElement(uri, localName, endName);
	}
}