package au.edu.utas.propertyapp;

import java.util.Vector;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	XMLPropertyCatalog.java
 * AUTHOR:     Rainer Wasinger
 * 
 * NOTE:    	***UNDERSTANDING THIS CLASS IS NOT REQUIRED FOR THE KIT305 
 * 				ANDROID LABS.***
 *
 * CLASS DESCRIPTION:
 * XMLPropertyCatalog. This class contains the internal representation of 
 * the property listings, stored as a tree structure and using Vectors as the
 * containers.
 *
 *****************************************************************************/
class XMLPropertyCatalog {
	public static final String TAG = "SearchResultsCatalog";
	private String results, totalResults;
	private Vector<Listing> listings;

	public XMLPropertyCatalog() {
		listings = new Vector<Listing>();
	} // Constructor

	public void setResults(String rhs) {
		results = rhs;
	} // setResults

	public void setTotalResults(String rhs) {
		totalResults = rhs;
	} // setTotalResults

	public void addListing(Listing rhs) {
		listings.addElement(rhs);
	} // addListing

	public Listing getListing(int index) {
		return (listings.elementAt(index));
	} // getListing

	public int getListingsSize() {
		return (listings.size());
	} // getListingsSize

	public String toString() {
		// String newline = System.getProperty("line.separator");
		StringBuffer buf = new StringBuffer();

		buf.append("--- Search Results ---\n");
		buf.append("SearchResults: Results='" + results + "' " + 
				"TotalResults='" + totalResults + "'.\n");
		for (int i = 0; i < listings.size(); i++) {
			buf.append(listings.elementAt(i));
		}
		return buf.toString();
	} // toString
} // XMLPropertyCatalog class

// --------------------------------------------------------------
class Listing {
	private String id, instructionType, type, isNew, activationDate, placementDate, newspaperId;
	private String headline, virtualTourUrl, displayableAddress, dateCreated, dateModified, priority, feed;
	private Vector<Organisation> organisation;
	private Vector<Creator> creator;
	private Vector<Contacts> contacts;
	private Vector<Property2> property;
	private Vector<Instruction> instruction;

	public Listing() {
		organisation = new Vector<Organisation>();
		creator = new Vector<Creator>();
		contacts = new Vector<Contacts>();
		property = new Vector<Property2>();
		instruction = new Vector<Instruction>();
	} // Constructor

	public void setId(String rhs) {
		id = rhs;
	} // setId

	public String getId() {
		return (this.id);
	} // getId

	public void setInstructionType(String rhs) {
		instructionType = rhs;
	} // setInstructionType

	public String getInstructionType() {
		return (this.instructionType);
	} // getInstructionType

	public void setType(String rhs) {
		type = rhs;
	} // setType

	public String getType() {
		return (this.type);
	} // getType

	public void setIsNew(String rhs) {
		isNew = rhs;
	} // setIsNew

	public String getIsNew() {
		return (this.isNew);
	} // getIsNew

	public void setActivationDate(String rhs) {
		activationDate = rhs;
	} // setActivationDate

	public String getActivationDate() {
		return (this.activationDate);
	} // getActivationDate

	public void setPlacementDate(String rhs) {
		placementDate = rhs;
	} // setPlacementDate

	public String getPlacementDate() {
		return (this.placementDate);
	} // getPlacementDate

	public void setNewspaperID(String rhs) {
		newspaperId = rhs;
	} // setNewspaperID

	public String getNewspaperID() {
		return (this.newspaperId);
	} // getNewspaperID

	public void setHeadline(String rhs) {
		headline = rhs;
	} // setHeadline

	public String getHeadline() {
		return (this.headline);
	} // getHeadline

	public void setVirtualTourUrl(String rhs) {
		virtualTourUrl = rhs;
	} // setVirtualTourUrl

	public String getVirtualTourUrl() {
		return (this.virtualTourUrl);
	} // getVirtualTourUrl

	public void setDisplayableAddress(String rhs) {
		displayableAddress = rhs;
	} // setDisplayableAddress

	public String getDisplayableAddress() {
		return (this.displayableAddress);
	} // getDisplayableAddress

	public void setDateCreated(String rhs) {
		dateCreated = rhs;
	} // setDateCreated

	public String getDateCreated() {
		return (this.dateCreated);
	} // getDateCreated

	public void setDateModified(String rhs) {
		dateModified = rhs;
	} // setDateModified

	public String getDateModified() {
		return (this.dateModified);
	} // getDateModified

	public void setPriority(String rhs) {
		priority = rhs;
	} // setPriority

	public String getPriority() {
		return (this.priority);
	} // getPriority

	public void setFeed(String rhs) {
		feed = rhs;
	} // setFeed

	public String getFeed() {
		return (this.feed);
	} // getFeed

	public void addOrganisation(Organisation o) {
		organisation.addElement(o);
	} // addOrganisation

	public Organisation getOrganisation(int index) {
		return (organisation.elementAt(index));
	} // getOrganisation

	public int getOrganisationSize() {
		return (organisation.size());
	} // getOrganisationSize

	public void addCreator(Creator c) {
		creator.addElement(c);
	} // addCreator

	public Creator getCreator(int index) {
		return (creator.elementAt(index));
	} // getCreator

	public int getCreatorSize() {
		return (creator.size());
	} // getCreatorSize

	public void addContacts(Contacts c) {
		contacts.addElement(c);
	} // addContacts

	public Contacts getContacts(int index) {
		return (contacts.elementAt(index));
	} // getContacts

	public int getContactsSize() {
		return (contacts.size());
	} // getContactsSize

	public void addProperty(Property2 p) {
		property.addElement(p);
	} // addProperty

	public Property2 getProperty(int index) {
		return (property.elementAt(index));
	} // getProperty

	public int getProperty2Size() {
		return (property.size());
	} // getProperty2Size

	public void addInstruction(Instruction i) {
		instruction.addElement(i);
	} // addInstruction

	public Instruction getInstruction(int index) {
		return (instruction.elementAt(index));
	} // getInstruction

	public int getInstructionSize() {
		return (instruction.size());
	} // getInstructionSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Listing: ID='" + id + "' InstructionType='" + instructionType + "' Type='" + type
				+ "' IsNew='" + isNew + "' ActivationDate='" + activationDate + "' PlacementDate='" + placementDate + "' NewspaperId='"
				+ newspaperId + "'.\n");
		buf.append("\t\tHeadline='" + headline + "'\n\t\tVirtualTourUrl='" + virtualTourUrl + "'\n\t\tDisplayableAddress='"
				+ displayableAddress + "'\n\t\tDateCreated='" + dateCreated + "'\n\t\tDateModified='" + dateModified + "'\n\t\tPriority='"
				+ priority + "'\n\t\tFeed='" + feed + "'.\n");

		int i;
		for (i = 0; i < organisation.size(); i++) {
			buf.append(organisation.elementAt(i).toString());
		}
		for (i = 0; i < creator.size(); i++) {
			buf.append(creator.elementAt(i).toString());
		}
		for (i = 0; i < contacts.size(); i++) {
			buf.append(contacts.elementAt(i).toString());
		}
		for (i = 0; i < property.size(); i++) {
			buf.append(property.elementAt(i).toString());
		}
		for (i = 0; i < instruction.size(); i++) {
			buf.append(instruction.elementAt(i).toString());
		}
		return ("\t" + buf.toString() + "\n");
	} // toString
} // Listing class

// --------------------------------------------------------------
class Organisation {
	private String id;

	public Organisation() {
	} // Constructor

	public void setId(String rhs) {
		id = rhs;
	} // setId

	public String getId() {
		return (this.id);
	} // getId

	public String toString() {
		return ("\t\tOrganisation: ID='" + id + "'.\n");
	} // toString
} // Organisation class

// --------------------------------------------------------------
class Creator {
	private Vector<Participant> participants;

	public Creator() {
		participants = new Vector<Participant>();
	} // Constructor

	public void addParticipant(Participant p) {
		participants.addElement(p);
	} // addParticipant

	public Participant getParticipant(int index) {
		return (participants.elementAt(index));
	} // getParticipant

	public int getParticipantSize() {
		return (participants.size());
	} // getParticipantSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Creator:\n");
		for (int i = 0; i < participants.size(); i++) {
			buf.append(participants.elementAt(i).toString());
		}
		return ("\t\t" + buf.toString());
	} // toString
} // Creator class

// --------------------------------------------------------------
class Participant {
	private String name, firstName, lastName, emailAddress;
	private Vector<PhoneNumbers> phoneNumbers;

	public Participant() {
		phoneNumbers = new Vector<PhoneNumbers>();
	} // Constructor

	public void setName(String rhs) {
		name = rhs;
	} // setName

	public String getName() {
		return (this.name);
	} // getName

	public void setFirstName(String rhs) {
		firstName = rhs;
	} // setFirstName

	public String getFirstName() {
		return (this.firstName);
	} // getFirstName

	public void setLastName(String rhs) {
		lastName = rhs;
	} // setLastName

	public String getLastName() {
		return (this.lastName);
	} // getLastName

	public void setEmailAddress(String rhs) {
		emailAddress = rhs;
	} // setEmailAddress

	public String getEmailAddress() {
		return (this.emailAddress);
	} // getEmailAddress

	public void addPhoneNumbers(PhoneNumbers p) {
		phoneNumbers.addElement(p);
	} // addPhoneNumbers

	public PhoneNumbers getPhoneNumbers(int index) {
		return (phoneNumbers.elementAt(index));
	} // getPhoneNumbers

	public int getPhoneNumbersSize() {
		return (phoneNumbers.size());
	} // getPhoneNumbersSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Participant: Name='" + name + "' FirstName='" + firstName + "'" + "' LastName='" + lastName
				+ "'" + "' EmailAddress='" + emailAddress + "'.\n");
		for (int i = 0; i < phoneNumbers.size(); i++) {
			buf.append(phoneNumbers.elementAt(i).toString());
		}
		return ("\t\t\t\t" + buf.toString());
	} // toString
} // Participant class

// --------------------------------------------------------------
class PhoneNumbers {
	private Vector<Number> numbers;

	public PhoneNumbers() {
		numbers = new Vector<Number>();
	} // Constructor

	public void addNumber(Number n) {
		numbers.addElement(n);
	} // addNumber

	public Number getNumber(int index) {
		return (numbers.elementAt(index));
	} // getNumber

	public int getNumberSize() {
		return (numbers.size());
	} // getNumberSize

	public String toString() {
		StringBuffer buf = new StringBuffer("PhoneNumbers:\n");
		for (int i = 0; i < numbers.size(); i++) {
			buf.append(numbers.elementAt(i).toString());
		}
		return ("\t\t\t\t\t" + buf.toString());
	} // toString
} // PhoneNumbers class

// --------------------------------------------------------------
class Number {
	private String type, number;

	public Number() {
	} // Constructor

	public void setType(String rhs) {
		type = rhs;
	} // setType

	public String getType() {
		return (this.type);
	} // getType

	public void setNumber(String rhs) {
		number = rhs;
	} // setNumber

	public String getNumber() {
		return (this.number);
	} // getNumber

	public String toString() {
		return ("\t\t\t\t\t\t" + "Number: Type='" + type + "' Number='" + number + "'.\n");
	} // toString
} // Number class

// --------------------------------------------------------------
class Contacts {
	private Vector<Contact> contacts;

	public Contacts() {
		contacts = new Vector<Contact>();
	} // Constructor

	public void addContact(Contact c) {
		contacts.addElement(c);
	} // addContact

	public Contact getContact(int index) {
		return (contacts.elementAt(index));
	} // getContact

	public int getContactSize() {
		return (contacts.size());
	} // getContactSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Contacts:\n");
		for (int i = 0; i < contacts.size(); i++) {
			buf.append(contacts.elementAt(i).toString());
		}
		return ("\t\t" + buf.toString());
	} // toString
} // Contacts class

// --------------------------------------------------------------
class Contact {
	private Vector<Participant> participants;

	public Contact() {
		participants = new Vector<Participant>();
	} // Constructor

	public void addParticipant(Participant p) {
		participants.addElement(p);
	} // addParticipant

	public Participant getParticipant(int index) {
		return (participants.elementAt(index));
	} // getParticipant

	public int getParticipantSize() {
		return (participants.size());
	} // getParticipantSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Contact:\n");
		for (int i = 0; i < participants.size(); i++) {
			buf.append(participants.elementAt(i).toString());
		}
		return ("\t\t\t" + buf.toString());
	} // toString
} // Contact class

// --------------------------------------------------------------
// A Property2 object represents an entire property that is contained within
// the given XML property search results listing.
// --------------------------------------------------------------
class Property2 {
	private String type, typeCode;
	private Vector<Address> address;
	private Vector<Features> features;
	private Vector<Images> images;

	public Property2() {
		address = new Vector<Address>();
		features = new Vector<Features>();
		images = new Vector<Images>();
	} // Constructor

	public void setType(String rhs) {
		type = rhs;
	} // setType

	public String getType() {
		return (this.type);
	} // getType

	public void setTypeCode(String rhs) {
		typeCode = rhs;
	} // setTypeCode

	public String getTypeCode() {
		return (this.typeCode);
	} // getTypeCode

	public void addAddress(Address a) {
		address.addElement(a);
	} // addAddress

	public Address getAddress(int index) {
		return (address.elementAt(index));
	} // getAddress

	public int getAddressSize() {
		return (address.size());
	} // getAddressSize

	public void addFeatures(Features f) {
		features.addElement(f);
	} // addFeatures

	public Features getFeatures(int index) {
		return (features.elementAt(index));
	} // getFeatures

	public int getFeaturesSize() {
		return (features.size());
	} // getFeaturesSize

	public void addImages(Images i) {
		images.addElement(i);
	} // addImages

	public Images getImages(int index) {
		return (images.elementAt(index));
	} // getImages

	public int getImagesSize() {
		return (images.size());
	} // getImagesSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Property: Type='" + type + "' TypeCode='" + typeCode + "'.\n");

		int i;
		for (i = 0; i < address.size(); i++) {
			buf.append(address.elementAt(i).toString());
		}
		for (i = 0; i < features.size(); i++) {
			buf.append(features.elementAt(i).toString());
		}
		for (i = 0; i < images.size(); i++) {
			buf.append(images.elementAt(i).toString());
		}
		return ("\t\t" + buf.toString() + "\n");
	} // toString
} // Property2 class

// --------------------------------------------------------------
class Address {
	private String displayOption, displayableAddress;
	private String unitNumber, streetNumber, street, suburb, postcode, state;

	public Address() {
	} // Constructor

	public void setDisplayOption(String rhs) {
		displayOption = rhs;
	} // setDisplayOption

	public String getDisplayOption() {
		return (this.displayOption);
	} // getDisplayOption

	public void setDisplayableAddress(String rhs) {
		displayableAddress = rhs;
	} // setDisplayableAddress

	public String getDisplayableAddress() {
		return (this.displayableAddress);
	} // getDisplayableAddress

	public void setUnitNumber(String rhs) {
		unitNumber = rhs;
	} // setUnitNumber

	public String getUnitNumber() {
		return (this.unitNumber);
	} // getUnitNumber

	public void setStreetNumber(String rhs) {
		streetNumber = rhs;
	} // setStreetNumber

	public String getStreetNumber() {
		return (this.streetNumber);
	} // getStreetNumber

	public void setStreet(String rhs) {
		street = rhs;
	} // setStreet

	public String getStreet() {
		return (this.street);
	} // getStreet

	public void setSuburb(String rhs) {
		suburb = rhs;
	} // setSuburb

	public String getSuburb() {
		return (this.suburb);
	} // getSuburb

	public void setPostcode(String rhs) {
		postcode = rhs;
	} // setPostcode

	public String getPostcode() {
		return (this.postcode);
	} // getPostcode

	public void setState(String rhs) {
		state = rhs;
	} // setState

	public String getState() {
		return (this.state);
	} // getState

	public String toString() {
		StringBuffer buf = new StringBuffer("Address: DisplayOption='" + displayOption + "' DisplayableAddress='" + displayableAddress
				+ "'.\n");
		buf.append("\t\t\t\tUnitNumber='" + unitNumber + "'\n\t\t\t\tStreetNumber='" + streetNumber + "'\n\t\t\t\tStreet='" + street
				+ "'\n\t\t\t\tSuburb='" + suburb + "'\n\t\t\t\tpostcode='" + postcode + "'\n\t\t\t\tState='" + state + "'.\n");
		return ("\t\t\t" + buf.toString());
	} // toString
} // Address class

// --------------------------------------------------------------
class Features {
	private Vector<Feature> features;

	public Features() {
		features = new Vector<Feature>();
	} // Constructor

	public void addFeature(Feature f) {
		features.addElement(f);
	} // addFeature

	public Feature getFeature(int index) {
		return (features.elementAt(index));
	} // getFeature

	public int getFeatureSize() {
		return (features.size());
	} // getFeatureSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Features:\n");
		for (int i = 0; i < features.size(); i++) {
			buf.append(features.elementAt(i).toString());
		}
		return ("\t\t\t" + buf.toString());
	} // toString
} // Features class

// --------------------------------------------------------------
class Feature {
	private String name, quantity;

	public Feature() {
	} // Constructor

	public void setName(String rhs) {
		name = rhs;
	} // setName

	public String getName() {
		return (this.name);
	} // getName

	public void setQuantity(String rhs) {
		quantity = rhs;
	} // setQuantity

	public String getQuantity() {
		return (this.quantity);
	} // getQuantity

	public String toString() {
		return ("\t\t\t\t" + "Feature: Name='" + name + "' Quantity='" + quantity + "'.\n");
	} // toString
} // Feature class

// --------------------------------------------------------------
class Images {
	private Vector<Image> images;

	public Images() {
		images = new Vector<Image>();
	} // Constructor

	public void addImage(Image i) {
		images.addElement(i);
	} // addImage

	public Image getImage(int index) {
		return (images.elementAt(index));
	} // getImage

	public int getImageSize() {
		return (images.size());
	} // getImageSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Images:\n");
		for (int i = 0; i < images.size(); i++) {
			buf.append(images.elementAt(i).toString());
		}
		return ("\t\t\t" + buf.toString());
	} // toString
} // Images class

// --------------------------------------------------------------
class Image {
	private String type, linkUrl, thumbUrl;
	private Vector<File> files;

	public Image() {
		files = new Vector<File>();
	} // Constructor

	public void setType(String rhs) {
		type = rhs;
	} // setType

	public String getType() {
		return (this.type);
	} // getType

	public void setLinkUrl(String rhs) {
		linkUrl = rhs;
	} // setLinkUrl

	public String getLinkUrl() {
		return (this.linkUrl);
	} // getLinkUrl

	public void setThumbUrl(String rhs) {
		thumbUrl = rhs;
	} // setThumbUrl

	public String getThumbUrl() {
		return (this.thumbUrl);
	} // getThumbUrl

	public void addFile(File f) {
		files.addElement(f);
	} // addFile

	public File getFile(int index) {
		return (files.elementAt(index));
	} // getFile

	public int getFileSize() {
		return (files.size());
	} // getFileSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Image: Type='" + type + "' LinkUrl='" + linkUrl + "' ThumbUrl='" + thumbUrl + "'.\n");
		for (int i = 0; i < files.size(); i++) {
			buf.append(files.elementAt(i).toString());
		}
		return ("\t\t\t\t" + buf.toString());
	} // toString
} // Image class

// --------------------------------------------------------------
class File {
	private String format;

	public File() {
	} // Constructor

	public void setFormat(String rhs) {
		format = rhs;
	} // setFormat

	public String getFormat() {
		return (this.format);
	} // getFormat

	public String toString() {
		return ("\t\t\t\t\t" + "File: Format='" + format + "'.\n");
	} // toString
} // File class

// --------------------------------------------------------------
class Instruction {
	private Vector<Price> price;
	private Vector<Auction> auction;

	public Instruction() {
		price = new Vector<Price>();
		auction = new Vector<Auction>();
	} // Constructor

	public void addPrice(Price p) {
		price.addElement(p);
	} // addPrice

	public Price getPrice(int index) {
		return (price.elementAt(index));
	} // getPrice

	public int getPriceSize() {
		return (price.size());
	} // getPriceSize

	public void addAuction(Auction a) {
		auction.addElement(a);
	} // addAuction

	public Auction getAuction(int index) {
		return (auction.elementAt(index));
	} // getAuction

	public int getAuctionSize() {
		return (auction.size());
	} // getAuctionSize

	public String toString() {
		StringBuffer buf = new StringBuffer("Instruction:\n");
		int i;
		for (i = 0; i < price.size(); i++) {
			buf.append(price.elementAt(i).toString());
		}
		for (i = 0; i < auction.size(); i++) {
			buf.append(auction.elementAt(i).toString());
		}
		return ("\t\t" + buf.toString() + "\n");
	} // toString
} // Instruction class

// --------------------------------------------------------------
class Price {
	private String displayPrice, showPrice, price, priceFrom, priceTo, pricePrefix;

	public Price() {
	} // Constructor

	public void setDisplayPrice(String rhs) {
		displayPrice = rhs;
	} // setDisplayPrice

	public String getDisplayPrice() {
		return (this.displayPrice);
	} // getDisplayPrice

	public void setShowPrice(String rhs) {
		showPrice = rhs;
	} // setShowPrice

	public String getShowPrice() {
		return (this.showPrice);
	} // getShowPrice

	public void setPrice(String rhs) {
		price = rhs;
	} // setPrice

	public String getPrice() {
		return (this.price);
	} // getPrice

	public void setPriceFrom(String rhs) {
		priceFrom = rhs;
	} // setPriceFrom

	public String getPriceFrom() {
		return (this.priceFrom);
	} // getPriceFrom

	public void setPriceTo(String rhs) {
		priceTo = rhs;
	} // setPriceTo

	public String getPriceTo() {
		return (this.priceTo);
	} // getPriceTo

	public void setPricePrefix(String rhs) {
		pricePrefix = rhs;
	} // setPriceprefix

	public String getPricePrefix() {
		return (this.pricePrefix);
	} // getPricePrefix

	public String toString() {
		return ("\t\t\t" + "Price: DisplayPrice='" + displayPrice + "' ShowPrice='" + showPrice + "' Price='" + price + "' PriceFrom='"
				+ priceFrom + "' PriceTo='" + priceTo + "' PricePrefix='" + pricePrefix + "'.\n");
	} // toString
} // Price class.

// --------------------------------------------------------------
class Auction {
	private String dateAuction;
	private String location;

	public Auction() {
	} // Constructor

	public void setDateAuction(String rhs) {
		dateAuction = rhs;
	} // setDateAuction

	public String getDateAuction() {
		return (this.dateAuction);
	} // getDateAuction

	public void setLocation(String rhs) {
		location = rhs;
	} // setLocation

	public String getLocation() {
		return (this.location);
	} // getLocation

	public String toString() {
		StringBuffer buf = new StringBuffer("Auction: DateAuction='" + dateAuction + "'.\n");
		buf.append("\t\t\t\tLocation='" + location + "'.\n");
		return ("\t\t\t" + buf.toString() + "\n");
	} // toString
} // Auction class.

