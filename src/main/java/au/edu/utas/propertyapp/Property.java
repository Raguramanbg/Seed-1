package au.edu.utas.propertyapp;

import android.content.Context;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	Property.java
 * AUTHORS:     Rainer Wasinger
 * 
 * NOTE:    	***THIS CLASS NEEDS UNDERSTANDING, BUT SHOULD NOT NEED
 * 				MODIFYING.***
 *
 * CLASS DESCRIPTION:
 * Property. This class represents a property object. It is used to store
 * the property data while in memory, and to handle calls to display the 
 * property.
 * 
 *****************************************************************************/
public class Property {
	//private static final String TAG = "Property";

	private String mPropertyID, mAddress, mDescription, mImage, mPrice, mType, 
		mSuburb, mRegion, mBedrooms, mBathrooms, mCarSpaces;

	public Property() {
	} // Constructor

	/**
	 * makeProperty. This method creates a Property object, using the data that 
	 * is passed to it.
	 */
	public static Property makeProperty(Context ctx, 
			String address, String description, String price,
			String type, String suburb, String region, String bedrooms, 
			String bathrooms, String carspaces, String propertyID, String image) {

		Property p = new Property();

		p.setAddress(address);
		p.setDescription(description);
		p.setImage(image);
		p.setPrice(price);
		p.setType(type);
		p.setSuburb(suburb);
		p.setRegion(region);
		p.setBedrooms(bedrooms);
		p.setBathrooms(bathrooms);
		p.setCarSpaces(carspaces);
			
		p.setPropertyID(propertyID);
		return (p);
	} // makeProperty
	
	// Getter and setter functions for the individual property attributes.
	public String getPropertyID() { return mPropertyID; }
	public void setPropertyID(String s) { this.mPropertyID = s; }

	public String getAddress() { return mAddress; }
	public void setAddress(String s) { mAddress = s; }

	public String getDescription() { return mDescription; }
	public void setDescription(String s) { mDescription = s; }

	public String getImage() { return mImage; }
	public void setImage(String s) { mImage = s; }

	public String getPrice() { return mPrice; }
	public void setPrice(String s) { mPrice = s; }

	public String getType() { return mType; }
	public void setType(String s) { mType = s; }
	
	public String getSuburb() { return mSuburb; }
	public void setSuburb(String s) { mSuburb = s; }
	
	public String getRegion() { return mRegion; }
	public void setRegion(String s) { mRegion = s; }
	
	public String getBedrooms() { return mBedrooms; }
	public void setBedrooms(String s) { mBedrooms = s; }

	public String getBathrooms() { return mBathrooms; }
	public void setBathrooms(String s) { mBathrooms = s; }
	
	public String getCarSpaces() { return mCarSpaces; }
	public void setCarSpaces(String s) { mCarSpaces = s; }
	
} // Property class
