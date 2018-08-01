/**
 * 
 */
package au.edu.utas.propertyapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	PropertyDetails.java
 * AUTHORS:     Rainer Wasinger
 *
 * NOTE:    	***THIS CLASS NEEDS MODIFIYING BY THE STUDENTS.***
 * 				In particular, see the methods: . 
 * 
 * CLASS DESCRIPTION:
 * PropertyDetails. This Activity class is used to display information on the 
 * selected property.
 * 
 *****************************************************************************/
public class PropertyDetails extends Activity {
	private static final String TAG = "PropertyDetails";
	ListView mListView;
	PropertyDB mDb;
	Cursor cursor;
	
	/** 
	 * onCreate. Called when the activity is first created. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		String propertyID = null;
		String toRentStr = null;
		boolean toRent = false; //Contains whether the user is looking to 'buy' 
								//or 'rent' a property. This information is 
								//obtained from the Extras bundle. 
		
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");

		setContentView(R.layout.propertydetails);

		/* Get the property id that we are to view, as too whether the user has
		 * selected to 'rent' or 'buy' properties. */
        //*************************************************************
    	//* TODO [08]: Insert Java code required to retrieve the Intent's
		//* Extras (in this case 'EXTRA_PROPERTY_ID' and 'EXTRA_TO_RENT'), 
		//* and store this in the local String variable propertyID and the local 
		//* boolean variable 'toRent'.
		//*
        //*************************************************************
		Bundle extras = getIntent().getExtras();
		propertyID = extras.getString(PropertyList.EXTRA_PROPERTY_ID);
		toRentStr = extras != null ?
				extras.getString(PropertyList.EXTRA_TO_RENT) : null;
		toRent = Boolean.parseBoolean(toRentStr);

        //*************************************************************

		//Open the database.
		mDb = new PropertyDB(this);
		mDb.open(toRent, false);
		cursor = mDb.getCursorToThePropertyList();
		startManagingCursor(cursor);
		
		Cursor c = mDb.getProperty(propertyID);
		if (c != null) {
			if (c.moveToNext()) {
				Property p = createPropertyFromCursor(c);
				fillInPropertyDetails(p);
			}
			c.close(); //Cursor management.
			c = null;
		}
	} // onCreate

	/** 
	 * fillInPropertyDetails. 
	 */
	public void fillInPropertyDetails(Property p) {
		TableLayout featureTable = null;
		TableRow tr = null;
		TextView label = null, value = null;

		TextView title = (TextView)findViewById(R.id.propertyTitle);
		title.setText(p.getAddress());

		ImageView photo = (ImageView)findViewById(R.id.propertyImage);
		photo.setImageResource(R.drawable.house);

		featureTable = (TableLayout)findViewById(R.id.propertyFeatureTable);
		
		if (!p.getPrice().equals("")) {
			tr = new TableRow(this);
			label = new TextView(this);
			value = new TextView(this);
			label.setText("Price:");
			value.setText(p.getPrice());
			tr.addView(label);
			tr.addView(value);
			featureTable.addView(tr);
		}
		
		if (!p.getSuburb().equals("")) {
			tr = new TableRow(this);
			label = new TextView(this);
			value = new TextView(this);
			label.setText("Suburb:");
			value.setText(p.getSuburb());
			tr.addView(label);
			tr.addView(value);
			featureTable.addView(tr);
		}
		
		if (!p.getBedrooms().equals("")) {
			tr = new TableRow(this);
			label = new TextView(this);
			value = new TextView(this);
			label.setText("Bedrooms:");
			value.setText(p.getBedrooms());
			tr.addView(label);
			tr.addView(value);
			featureTable.addView(tr);
		}
		
		if (!p.getBathrooms().equals("")) {
			tr = new TableRow(this);
			label = new TextView(this);
			value = new TextView(this);
			label.setText("Bathrooms:");
			value.setText(p.getBathrooms());
			tr.addView(label);
			tr.addView(value);
			featureTable.addView(tr);
		}
		
		if (!p.getCarSpaces().equals("")) {
			tr = new TableRow(this);
			label = new TextView(this);
			value = new TextView(this);
			label.setText("CarSpaces:");
			value.setText(p.getCarSpaces());
			tr.addView(label);
			tr.addView(value);
			featureTable.addView(tr);
		}

		TextView description = (TextView)findViewById(R.id.propertyDescription);
		description.setText(p.getDescription());
	} //fillInPropertyDetails
	
	/** 
	 * onDestroy. Called when the activity is destroyed. 
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		mDb.close(); // Close the database to avoid memory leaks.
	} // onDestroy

	/**
	 * createPropertyFromCursor. Creates a property object from the given cursor.
	 */
	public Property createPropertyFromCursor(Cursor c) {
		if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
			return null;
		}
		Property p = new Property();
		p.setAddress(c.getString(c.getColumnIndex(PropertyDB.KEY_ADDRESS)));
		p.setDescription(c.getString(c.getColumnIndex(PropertyDB.KEY_DESCRIPTION)));
		p.setPropertyID(c.getString(c.getColumnIndex(PropertyDB.KEY_PROPERTY_ID)));
		p.setPrice(c.getString(c.getColumnIndex(PropertyDB.KEY_PRICE)));
		p.setType(c.getString(c.getColumnIndex(PropertyDB.KEY_TYPE)));
		p.setSuburb(c.getString(c.getColumnIndex(PropertyDB.KEY_SUBURB)));
		p.setRegion(c.getString(c.getColumnIndex(PropertyDB.KEY_REGION)));
		p.setBedrooms(c.getString(c.getColumnIndex(PropertyDB.KEY_BEDROOMS)));
		p.setBathrooms(c.getString(c.getColumnIndex(PropertyDB.KEY_BATHROOMS)));
		p.setCarSpaces(c.getString(c.getColumnIndex(PropertyDB.KEY_CARSPACES)));
		
		return p;
	} // createPropertyFromCursor
	
} //PropertyDetails class