package au.edu.utas.propertyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	PropertyDB.java
 * AUTHORS:     Rainer Wasinger
 * 
 * NOTE:    	***THIS CLASS NEEDS UNDERSTANDING, BUT SHOULD NOT NEED
 * 				MODIFYING.***
 * 				In particular, see the methods: open, addProperty, getProperty,
 * 				and close. 
 *
 * CLASS DESCRIPTION:
 * PropertyDB. This class is used to manage storage on the physical device.
 * It encapsulates data access to the SQLite database containing our property 
 * data (called "propertydata"). This data is held within the table: 
 * PROPERTY_TABLE.
 * 
 * DatabaseHelper. This is a helper class to manage connections to the database.
 * 
 *****************************************************************************/
public class PropertyDB {
	private static final String TAG = "PropertyDB";
	
	private static final String DATABASE_NAME 		= "propertydata";
	private static final int 	DATABASE_VERSION 	= 2;
	private static final String PROPERTY_TABLE 		= "property_data";
	
	/*
	 * Some constant definitions that will be used in the application to look 
	 * up data from the field names used in the database.
	 */
	public static final String KEY_ROWID 			= "_id";
	public static final String KEY_PROPERTY_ID 		= "property_id";
	public static final String KEY_ADDRESS 			= "address";
	public static final String KEY_DESCRIPTION 		= "description";
	public static final String KEY_PRICE 			= "price"; 
	public static final String KEY_TYPE 			= "type"; 
	public static final String KEY_SUBURB 			= "suburb";
	public static final String KEY_REGION 			= "region";
	public static final String KEY_BEDROOMS 		= "bedrooms";
	public static final String KEY_BATHROOMS 		= "bathrooms";
	public static final String KEY_CARSPACES 		= "carspaces";

	/*
	 * Database creation (SQL statement).
	 */
	private static final String PROPERTY_TABLE_CREATE = "create table " 
		+ PROPERTY_TABLE 
		+ " (" + KEY_ROWID + " integer primary key autoincrement, " 
		+ KEY_PROPERTY_ID + " long integer not null, " 
		+ KEY_ADDRESS + " string not null, "
		+ KEY_DESCRIPTION + " string not null, " 
		+ KEY_PRICE + " string not null, "
		+ KEY_TYPE + " string not null, "
		+ KEY_SUBURB + " string not null, "
		+ KEY_REGION + " string not null, "
		+ KEY_BEDROOMS + " string not null, "
		+ KEY_BATHROOMS + " string not null, "
		+ KEY_CARSPACES + " string not null);";

	XMLParser mPropertyXMLParser;
	XMLPropertyCatalog mXMLPropertyCatalog; // mXMLPropertyCatalog is where the 
											// XML property listings from the 
											// server are converted to a local 
											// internal tree structure.	 

	private SQLiteDatabase mDb;
	private DatabaseHelper mDbHelper;
	
	private final Context mCtx;
	
	public PropertyDB(Context ctx) {
		this.mCtx = ctx;	
	} // Constructor
	
	/**
	 * open. Opens the database and writes the property data to it.
	 * The 'toRent' variable indicates whether the 'buy' or 'rent' XML
	 * data should be parsed, and the 'refill' variable is used to reset the
	 * database when the user switches between the 'buy' and 'rent' modes.
	 */
	public PropertyDB open(boolean toRent, boolean refill) throws SQLException {
		Log.d(TAG, "open");
		//Check that the database object doesn't already exist.
		if (mDb == null) {
			mDbHelper = new DatabaseHelper(mCtx);
			mDb = mDbHelper.getWritableDatabase();
		}

		//We reset the database each time the user switches between buy and rent.
		if (refill) {
			mDbHelper.reset(mDb);
		}
		
		// The nulls mean that we want all columns in the table, all data, need
		// no grouping, and will take the default order.
		Cursor c = mDb.query(PROPERTY_TABLE, null, null, null, null, null, null); 
		if (c != null) { // Check that we have actually retrieved some data.			
			if (c.getCount() == 0) { // i.e. if there are no rows in the table.
				Log.d(TAG, "The database is empty.");
	
				// Store the XML property data into a tree data structure. 	
				populateXMLPropertyCatalog(toRent);
				// Write the tree data structure out to the SQLite database.
				writePropertyDataToDatabase();
			}
			c.close(); // Cursor management, to prevent an uncaught exception
			c = null;  // thrown by finalizer/IllegalStateException.
		}
		return this;
	} // open

	/**
	 * close. Closes this database. Call this at the end of use.
	 */
	public void close() {
		Log.d(TAG, "close");
		if (mDb != null) {
			mDb.close();
			mDb = null;	// This is important as garbage collection may
						// not have occured by the time the Activities
						// need to know whether they should recreated the
						// DB.
		}
	} // close
	
	/**
	 * populateXMLPropertyCatalog. This function is responsible for storing the
	 * XML searchResults into a local tree structure. The instantiation to the 
	 * XMLParser is created in this method. The instantiation to the  
	 * XMLPropertyCatalog is created in XMLUnmarshaller.java.
	 * 
	 */
	public void populateXMLPropertyCatalog(boolean toRent) {
		mPropertyXMLParser = new XMLParser();
		mXMLPropertyCatalog = mPropertyXMLParser.createXMLPropertyCatalog(mCtx,
				toRent);
	} // populateXMLPropertyCatalog

	/**
	 * writePropertyDataToDatabase. This takes a minimised set of property
	 * attributes and stores it in the SQLite DB. From this point onwards,
	 * the tree data structure represented by XMLPropertyCatalog is no longer 
	 * used.
	 * 
	 */
	public void writePropertyDataToDatabase () {
		Listing currentListing;
		Property2 currentProperty;
		Address currentAddress;
		Features currentFeatures;
		Feature currentFeature;
		Instruction currentInstruction;
		Images currentImages;
		String address, description, price, type, suburb, region, bedrooms, 
			bathrooms, carspaces, propertyID, image;

		// Get listing details, i.e. propertyID and description.
		for (int i = 0; i < mXMLPropertyCatalog.getListingsSize(); i++) {
			address = "";
			description = "";
			price = "";
			type = "";
			suburb = "";
			region = "";
			bedrooms = "";
			bathrooms = "";
			carspaces = "";
			propertyID = "";
			image = "";
			currentListing = mXMLPropertyCatalog.getListing(i);
			propertyID = currentListing.getId();
			description = currentListing.getHeadline();

			// get property details, i.e. type.
			for (int j = 0; j < currentListing.getProperty2Size(); j++) {
				currentProperty = currentListing.getProperty(j);
				type = currentProperty.getType();

				// get address details, i.e. address, suburb, region.
				for (int k = 0; k < currentProperty.getAddressSize(); k++) {
					currentAddress = currentProperty.getAddress(k);
					address = currentAddress.getStreetNumber() + " " 
						+ currentAddress.getStreet();
					suburb = currentAddress.getSuburb();
					region = currentAddress.getState();
				}

				// get features details. None needed in this level of nesting.
				for (int l = 0; l < currentProperty.getFeaturesSize(); l++) {
					currentFeatures = currentProperty.getFeatures(l);

					// get feature details, i.e. bedrooms, bathrooms,
					// carspaces.
					for (int m = 0; m < currentFeatures.getFeatureSize(); m++) {
						currentFeature = currentFeatures.getFeature(m);

						// this is a bedrooms string, so record it.
						if (currentFeature.getName().equalsIgnoreCase("bedrooms")) {
							bedrooms = currentFeature.getQuantity();
						}
						
						// this is a bathrooms string, so record it.
						else if (currentFeature.getName().equalsIgnoreCase("bathrooms")) {
							bathrooms = currentFeature.getQuantity();
						}

						// this is a carspaces string, so record it.
						else if (currentFeature.getName().equalsIgnoreCase("carspaces")) {
							carspaces = currentFeature.getQuantity();
						}
					}
				}

				// get images details. None needed in this level of nesting.
				for (int n = 0; n < currentProperty.getImagesSize(); n++) {
					currentImages = currentProperty.getImages(n);
					
					// get image details, i.e. thumbUrl.
					for (int o = 0; o < currentImages.getImageSize(); o++) { 
						image = currentImages.getImage(o).getThumbUrl();
						// Log.i(TAG, currentImages.getImage(o).getThumbUrl());
					}
				}
			}
			
			// get instruction details. None needed in this level of
			// nesting.
			for (int n = 0; n < currentListing.getInstructionSize(); n++) {
				currentInstruction = currentListing.getInstruction(n);

				// get price/displayPrice details, i.e. price.
				for (int o = 0; o < currentInstruction.getPriceSize(); o++) {
					price = (currentInstruction.getPrice(o)).getDisplayPrice();
				}
			}
			addProperty(Property.makeProperty(mCtx, address, description, price,
					type, suburb, region, bedrooms, bathrooms, carspaces, 
					propertyID, image));
		}
	} //writePropertyDataToDatabase

	/**
	 * addProperty. Add a property to the database.
	 */
	public void addProperty(Property p) {
		ContentValues propValues = new ContentValues();

		// Build our property values
		propValues.put(KEY_PROPERTY_ID, p.getPropertyID());
		propValues.put(KEY_ADDRESS, p.getAddress());
		propValues.put(KEY_DESCRIPTION, p.getDescription());
		propValues.put(KEY_PRICE, p.getPrice());
		propValues.put(KEY_TYPE, p.getType());
		propValues.put(KEY_SUBURB, p.getSuburb());
		propValues.put(KEY_REGION, p.getRegion());
		propValues.put(KEY_BEDROOMS, p.getBedrooms());
		propValues.put(KEY_BATHROOMS, p.getBathrooms());
		propValues.put(KEY_CARSPACES, p.getCarSpaces());
		
		// Insert them into the database
		mDb.insert(PROPERTY_TABLE, null, propValues);
	} // addProperty

	/**
	 * getProperty. Retrieves a particular property from the database.
	 */
	public Cursor getProperty(String id) {
		Cursor c = mDb.query(PROPERTY_TABLE, null, KEY_PROPERTY_ID + "=" + id, 
				null, null, null, null);
		return c;
	} // getProperty

	public Cursor getCursorToThePropertyList() {
		Cursor c = mDb.query(PROPERTY_TABLE, null, null, null, null, null, null);
		return c;
	} // getCursorToThePropertyList

	/**
	 * DatabaseHelper class.
	 * 
	 * Database helper class to manage connections with the database. 
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) { //This creates the database.
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		} // Constructor

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.d(TAG, "DatabaseHelper onCreate");
			db.execSQL(PROPERTY_TABLE_CREATE);
		} // onCreate

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, 
				int newVersion) {
			Log.d(TAG, "DatabaseHelper onUpgrade");
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " 
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + PROPERTY_TABLE);
			onCreate(db);
		} // onUpgrade

		public void reset(SQLiteDatabase db) {
			Log.d(TAG, "DatabaseHelper reset");
			db.execSQL("DROP TABLE IF EXISTS " + PROPERTY_TABLE);
			onCreate(db);
		} // reset
	} // DatabaseHelper class.

} // PropertyDB class
