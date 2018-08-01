package au.edu.utas.propertyapp;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	XMLUnmarshaller.java
 * AUTHOR:     Rainer Wasinger
 * 
 * NOTE:    	***UNDERSTANDING THIS CLASS IS NOT REQUIRED FOR THE KIT305 
 * 				ANDROID LABS.***
 *
 * CLASS DESCRIPTION:
 * XMLUnmarshaller. This class parses the XML property data file and stores
 * it in the XMLPropertyCatalog.
 *
 *****************************************************************************/
 
public class XMLUnmarshaller extends DefaultHandler {
	private static final String TAG = "XMLUnmarshaller";

	private XMLPropertyCatalog searchResults;
	private Stack<Object> stack;
	private boolean isStackReadyForText;
	@SuppressWarnings("unused") private Locator locator; //Suppressing warnings about unused field values.
 
	@SuppressWarnings("unused") private boolean inSearchResults, inListing, inOrganisation, inCreator, 
		inContacts, inContact, inParticipant, inPhoneNumbers, inNumber,
		inProperty, inAddress, inFeatures, inFeature, inImages, inImage, 
		inFile, inInstruction, inPrice, inAuction;

	public XMLUnmarshaller() {
		stack = new Stack<Object>();
		isStackReadyForText = false;
	} // Constructor

	public XMLPropertyCatalog getSearchResults() {
		return searchResults;
	}

	// Callbacks
	public void setDocumentLocator(Locator rhs) {
		locator = rhs;
	}

	public void startElement(String uri, String localName, String qName, 
			Attributes attributes) {
		String currentAttr;

		isStackReadyForText = false;
		// if next element is complex, push a new instance on the stack.
		// if element has attributes, set them in the new instance.
		if (localName.equals("SearchResults")) { // Tree node.
			inSearchResults = true;
			stack.push(new XMLPropertyCatalog());
			currentAttr = resolveAttribute(uri, "Results", attributes, "unknown");
			((XMLPropertyCatalog) stack.peek()).setResults(currentAttr);
			currentAttr = resolveAttribute(uri, "TotalResults", attributes, "unknown");
			((XMLPropertyCatalog) stack.peek()).setTotalResults(currentAttr);
		}
		else if (localName.equals("Listing")) { // Tree node.
			inListing = true;
			stack.push(new Listing());
			currentAttr = resolveAttribute(uri, "ID", attributes, "unknown");
			((Listing) stack.peek()).setId(currentAttr);
			currentAttr = resolveAttribute(uri, "InstructionType", attributes, "unknown");
			((Listing) stack.peek()).setInstructionType(currentAttr);
			currentAttr = resolveAttribute(uri, "Type", attributes, "unknown");
			((Listing) stack.peek()).setType(currentAttr);
			currentAttr = resolveAttribute(uri, "IsNew", attributes, "unknown");
			((Listing) stack.peek()).setIsNew(currentAttr);
			currentAttr = resolveAttribute(uri, "ActivationDate", attributes, "unknown");
			((Listing) stack.peek()).setActivationDate(currentAttr);
			currentAttr = resolveAttribute(uri, "PlacementDate", attributes, "unknown");
			((Listing) stack.peek()).setPlacementDate(currentAttr);
			currentAttr = resolveAttribute(uri, "NewspaperID", attributes, "unknown");
			((Listing) stack.peek()).setNewspaperID(currentAttr);
		}
		else if (localName.equals("Organisation")) { // Leaf node.
			inOrganisation = true;
			stack.push(new Organisation());
			currentAttr = resolveAttribute(uri, "ID", attributes, "unknown");
			((Organisation) stack.peek()).setId(currentAttr);
		}
		else if (localName.equals("Creator")) {
			inCreator = true;
			stack.push(new Creator());
		} // Tree node
		else if (localName.equals("Participant")) { // Sometimes tree node and sometimes leaf node.
			inParticipant = true;
			stack.push(new Participant());
			currentAttr = resolveAttribute(uri, "Name", attributes, "unknown");
			((Participant) stack.peek()).setName(currentAttr);
			currentAttr = resolveAttribute(uri, "FirstName", attributes, "unknown");
			((Participant) stack.peek()).setFirstName(currentAttr);
			currentAttr = resolveAttribute(uri, "LastName", attributes, "unknown");
			((Participant) stack.peek()).setLastName(currentAttr);
			currentAttr = resolveAttribute(uri, "EmailAddress", attributes, "unknown");
			((Participant) stack.peek()).setEmailAddress(currentAttr);
		}
		else if (localName.equals("Contacts")) {
			inContacts = true;
			stack.push(new Contacts());
		} // Tree node.
		else if (localName.equals("Contact")) {
			inContact = true;
			stack.push(new Contact());
		} // Tree node.
		else if (localName.equals("PhoneNumbers")) {
			inPhoneNumbers = true;
			stack.push(new PhoneNumbers());
		} // Tree node.
		else if (localName.equals("Number")) { // Leaf node.
			inNumber = true;
			stack.push(new Number());
			currentAttr = resolveAttribute(uri, "Type", attributes, "unknown");
			((Number) stack.peek()).setType(currentAttr);
			currentAttr = resolveAttribute(uri, "Number", attributes, "unknown");
			((Number) stack.peek()).setNumber(currentAttr);
		}
		else if (localName.equals("Property")) { // Tree node.
			inProperty = true;
			stack.push(new Property2());
			currentAttr = resolveAttribute(uri, "Type", attributes, "unknown");
			((Property2) stack.peek()).setType(currentAttr);
			currentAttr = resolveAttribute(uri, "TypeCode", attributes, "unknown");
			((Property2) stack.peek()).setTypeCode(currentAttr);
		}
		else if (localName.equals("Address")) { // Tree node.
			inAddress = true;
			stack.push(new Address());
			currentAttr = resolveAttribute(uri, "DisplayOption", attributes, "unknown");
			((Address) stack.peek()).setDisplayOption(currentAttr);
			currentAttr = resolveAttribute(uri, "DisplayableAddress", attributes, "unknown");
			((Address) stack.peek()).setDisplayableAddress(currentAttr);
		}
		else if (localName.equals("Features")) {
			inFeatures = true;
			stack.push(new Features());
		} // Tree node.
		else if (localName.equals("Feature")) { // Leaf node.
			inFeature = true;
			stack.push(new Feature());
			currentAttr = resolveAttribute(uri, "Name", attributes, "unknown");
			((Feature) stack.peek()).setName(currentAttr);
			currentAttr = resolveAttribute(uri, "Quantity", attributes, "unknown");
			((Feature) stack.peek()).setQuantity(currentAttr);
		}
		else if (localName.equals("Images")) {
			inImages = true;
			stack.push(new Images());
		} // Tree node.
		else if (localName.equals("Image")) { // Tree node.
			inImage = true;
			stack.push(new Image());
			currentAttr = resolveAttribute(uri, "Type", attributes, "unknown");
			((Image) stack.peek()).setType(currentAttr);
			currentAttr = resolveAttribute(uri, "LinkUrl", attributes, "unknown");
			((Image) stack.peek()).setLinkUrl(currentAttr);
			currentAttr = resolveAttribute(uri, "ThumbUrl", attributes, "unknown");
			((Image) stack.peek()).setThumbUrl(currentAttr);
		}
		else if (localName.equals("File")) { // Leaf node.
			inFile = true;
			stack.push(new File());
			currentAttr = resolveAttribute(uri, "Format", attributes, "unknown");
			((File) stack.peek()).setFormat(currentAttr);
		}
		else if (localName.equals("Instruction")) {
			inInstruction = true;
			stack.push(new Instruction());
		} // Tree node.
		else if (localName.equals("Price")) { // Leaf node.
			inPrice = true;
			stack.push(new Price());
			currentAttr = resolveAttribute(uri, "DisplayPrice", attributes, "unknown");
			((Price) stack.peek()).setDisplayPrice(currentAttr);
			currentAttr = resolveAttribute(uri, "ShowPrice", attributes, "unknown");
			((Price) stack.peek()).setShowPrice(currentAttr);
			currentAttr = resolveAttribute(uri, "Price", attributes, "unknown");
			((Price) stack.peek()).setPrice(currentAttr);
			currentAttr = resolveAttribute(uri, "PriceFrom", attributes, "unknown");
			((Price) stack.peek()).setPriceFrom(currentAttr);
			currentAttr = resolveAttribute(uri, "PriceTo", attributes, "unknown");
			((Price) stack.peek()).setPriceTo(currentAttr);
			currentAttr = resolveAttribute(uri, "PricePrefix", attributes, "unknown");
			((Price) stack.peek()).setPricePrefix(currentAttr);
		}
		else if (localName.equals("Auction")) {// Leaf node.
			inAuction = true;
			stack.push(new Auction());
			currentAttr = resolveAttribute(uri, "DateAuction", attributes, "unknown");
			((Auction) stack.peek()).setDateAuction(currentAttr);
		}
		// if next element is simple (i.e. contains only a value and no attributes), push StringBuffer. This makes the stack ready to accept
		// character text.
		else if (localName.equals("UnitNumber") || localName.equals("StreetNumber") || localName.equals("Street")
				|| localName.equals("Suburb") || localName.equals("Postcode") || localName.equals("State") || localName.equals("Headline")
				|| localName.equals("VirtualTourUrl") || localName.equals("DisplayableAddress") || localName.equals("DateCreated")
				|| localName.equals("DateModified") || localName.equals("Priority") || localName.equals("Feed")
				|| localName.equals("Location")) {
			stack.push(new StringBuffer());
			isStackReadyForText = true;
		}

		// if none of the above, it is an unexpected element.
		else { // do nothing
			Log.d(TAG, "startElement - ignoring: " + localName);
		}
	} // startElement

	public void endElement(String uri, String localName, String qName) {
		// recognized text is always content of an element.
		// When the element closes, no more text should be expected.
		isStackReadyForText = false;

		// pop the stack and add to 'parent' element, which is next on the stack
		// important to pop stack first, then peek at top element!
		Object currentObj = stack.pop();

		// RWNB1: Indentation here corresponds to XML Structure not to if/else statements.
		// RWNB2: Because the Participant tag occurs within both the Creator and Contact tags,
		// we use inCreator and inContact to differentiate between the two.
		if (localName.equals("SearchResults")) {
			searchResults = (XMLPropertyCatalog) currentObj;
			inSearchResults = false;
		}
		else if (localName.equals("Listing")) {
			((XMLPropertyCatalog) stack.peek()).addListing((Listing) currentObj);
			inListing = false;
		}
		else if (localName.equals("Organisation")) {
			((Listing) stack.peek()).addOrganisation((Organisation) currentObj);
			inOrganisation = false;
		}
		else if (localName.equals("Creator")) {
			((Listing) stack.peek()).addCreator((Creator) currentObj);
			inCreator = false;
		}
		else if (inCreator == true && localName.equals("Participant")) {
			((Creator) stack.peek()).addParticipant((Participant) currentObj);
			inParticipant = false;
		}
		else if (localName.equals("Contacts")) {
			((Listing) stack.peek()).addContacts((Contacts) currentObj);
			inContacts = false;
		}
		else if (localName.equals("Contact")) {
			((Contacts) stack.peek()).addContact((Contact) currentObj);
			inContact = false;
		}
		else if (inContact == true && localName.equals("Participant")) {
			((Contact) stack.peek()).addParticipant((Participant) currentObj);
			inParticipant = false;
		}
		else if (localName.equals("PhoneNumbers")) {
			((Participant) stack.peek()).addPhoneNumbers((PhoneNumbers) currentObj);
			inPhoneNumbers = false;
		}
		else if (localName.equals("Number")) {
			((PhoneNumbers) stack.peek()).addNumber((Number) currentObj);
			inNumber = false;
		}
		else if (localName.equals("Property")) {
			((Listing) stack.peek()).addProperty((Property2) currentObj);
			inProperty = false;
		}
		else if (localName.equals("Address")) {
			((Property2) stack.peek()).addAddress((Address) currentObj);
			inAddress = false;
		}
		else if (localName.equals("Features")) {
			((Property2) stack.peek()).addFeatures((Features) currentObj);
			inFeatures = false;
		}
		else if (localName.equals("Feature")) {
			((Features) stack.peek()).addFeature((Feature) currentObj);
			inFeature = false;
		}
		else if (localName.equals("Images")) {
			((Property2) stack.peek()).addImages((Images) currentObj);
			inImages = false;
		}
		else if (localName.equals("Image")) {
			((Images) stack.peek()).addImage((Image) currentObj);
			inImage = false;
		}
		else if (localName.equals("File")) {
			((Image) stack.peek()).addFile((File) currentObj);
			inFile = false;
		}
		else if (localName.equals("Instruction")) {
			((Listing) stack.peek()).addInstruction((Instruction) currentObj);
			inInstruction = false;
		}
		else if (localName.equals("Price")) {
			((Instruction) stack.peek()).addPrice((Price) currentObj);
			inPrice = false;
		}
		else if (localName.equals("Auction")) {
			((Instruction) stack.peek()).addAuction((Auction) currentObj);
			inAuction = false;
		}

		// for simple elements, pop StringBuffer and convert to String
		else if (localName.equals("UnitNumber")) {
			((Address) stack.peek()).setUnitNumber(currentObj.toString());
		}
		else if (localName.equals("StreetNumber")) {
			((Address) stack.peek()).setStreetNumber(currentObj.toString());
		}
		else if (localName.equals("Street")) {
			((Address) stack.peek()).setStreet(currentObj.toString());
		}
		else if (localName.equals("Suburb")) {
			((Address) stack.peek()).setSuburb(currentObj.toString());
		}
		else if (localName.equals("Postcode")) {
			((Address) stack.peek()).setPostcode(currentObj.toString());
		}
		else if (localName.equals("State")) {
			((Address) stack.peek()).setState(currentObj.toString());
		}
		else if (localName.equals("Headline")) {
			((Listing) stack.peek()).setHeadline(currentObj.toString());
		}
		else if (localName.equals("VirtualTourUrl")) {
			((Listing) stack.peek()).setVirtualTourUrl(currentObj.toString());
		}
		else if (localName.equals("DisplayableAddress")) {
			((Listing) stack.peek()).setDisplayableAddress(currentObj.toString());
		}
		else if (localName.equals("DateCreated")) {
			((Listing) stack.peek()).setDateCreated(currentObj.toString());
		}
		else if (localName.equals("DateModified")) {
			((Listing) stack.peek()).setDateModified(currentObj.toString());
		}
		else if (localName.equals("Priority")) {
			((Listing) stack.peek()).setPriority(currentObj.toString());
		}
		else if (localName.equals("Feed")) {
			((Listing) stack.peek()).setFeed(currentObj.toString());
		}
		else if (localName.equals("Location")) {
			((Auction) stack.peek()).setLocation(currentObj.toString());
		}

		// if none of the above, it is an unexpected element. Push the popped element back!
		else {
			// Log.d(TAG, "endElement - pushing back popped: " + localName);
			stack.push(currentObj);
		}
	} // endElement

	public void characters(char[] data, int start, int length) {
		// if stack is not ready, data is not content of recognized element.
		if (isStackReadyForText == true) {
			((StringBuffer) stack.peek()).append(data, start, length);
		}
		else { // read data which is not part of recognized element.
		}
	} // characters

	private String resolveAttribute(String uri, String localName, 
			Attributes attributes, String defaultValue) {
		String tmp = attributes.getValue(uri, localName);
		return (tmp != null) ? (tmp) : (defaultValue);
	} // resolveAttrib

} // XMLUnmarshaller class.
