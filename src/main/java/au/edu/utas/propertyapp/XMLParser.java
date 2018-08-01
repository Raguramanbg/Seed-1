package au.edu.utas.propertyapp;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.Context;
import android.util.Log;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	XMLParser.java
 * AUTHOR:     Rainer Wasinger
 * 
 * NOTE:    	***UNDERSTANDING THIS CLASS IS NOT REQUIRED FOR THE KIT305 
 * 				ANDROID LABS.***
 *
 * CLASS DESCRIPTION:
 * XMLParser. This class uses the XMLUnmarshaller class to parse an XML 
 * file (the result is stored in the XMLPropertyCatalog searchResults object).
 *
 *****************************************************************************/
public class XMLParser {
	private static final String TAG = "XMLParser";

	public XMLPropertyCatalog createXMLPropertyCatalog(Context context, boolean toRent) {
		XMLPropertyCatalog searchResults = null;
		Log.d(TAG, "createXMLPropertyCatalog");

		try {
			// Get a SAXParser from the SAXPArserFactory.
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			
			// Get the XMLReader of the SAXParser we created.
			XMLReader xr = sp.getXMLReader();
			
			// Create a new ContentHandler and apply it to the XML-Reader.
			XMLUnmarshaller xmlUnmarshaller = new XMLUnmarshaller();
			xr.setContentHandler(xmlUnmarshaller);

			// Get the static XML resource file for reading.
			// InputStream is = context.getResources().openRawResource(R.raw.simpletest);
			InputStream is = null;
			if (toRent) {
				is = context.getResources().openRawResource(R.raw.searchresults_camperdown_rent);
			} else { //toBuy
				is = context.getResources().openRawResource(R.raw.searchresults_camperdown_buy);
			}
			 xr.parse(new InputSource(is));

			// The XMLUmarshaller now provides the parsed data to us.
			searchResults = xmlUnmarshaller.getSearchResults();

		} catch (ParserConfigurationException e) {
			Log.e(TAG, "Caught ParserConfigurationException: " + e.getMessage());
		} catch (SAXException e) {
			Log.e(TAG, "Caught SAXException e: " + e.getMessage());
		} catch (IOException e) {
			Log.e(TAG, "Caught IOException in createSearchResultsCatalog(Context, String): " + e.getMessage());
		}
		return (searchResults);
	} // createXMLPropertyCatalog

} // XMLParser class

