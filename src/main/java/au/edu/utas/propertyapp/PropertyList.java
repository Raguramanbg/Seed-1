package au.edu.utas.propertyapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	PropertyList.java
 * AUTHORS:     Rainer Wasinger
 * 
 * NOTE:    	***THIS CLASS NEEDS MODIFIYING BY THE STUDENTS.***
 * 				In particular, see the methods: . 
 *
 * CLASS DESCRIPTION:
 * PropertyList. This activity class is responsible for presenting the user 
 * with a list of property results (either to buy or to rent).
 * The associated GUI for PropertyList is contained inside propertylisting.xml 
 * (as well as propertylistrow.xml).
 * 
 * The activity retrieves boolean information as to whether it will be 
 * displaying the buy or rent list from the Extras passed to it through an 
 * Intent call.
 * 
 * PropertyListAdapter.	 This is a custom Adapter class that bridges our 
 * ListView and the data in the database.
 * 
 *****************************************************************************/
public class PropertyList extends Activity {
	private static final String TAG = "PropertyList";

	public static final String EXTRA_PROPERTY_ID="au.edu.utas.propertyapp.property_id";
	public static final String EXTRA_TO_RENT="au.edu.utas.propertyapp.to_rent";

	List<Property> mPropertyList = new ArrayList<Property>();
	PropertyListAdapter mPropertyListAdapter = null;
	ListView mListView;
	PropertyDB mDb;
	Cursor cursor;

	/**
	 * onCreate. Called when the activity is started. Also responsible for
	 * setting the content view, and for loading up everything required to show 
	 * a PropertyList.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String toRentStr;
		boolean toRent = false; //Contains whether the user is looking to 'buy' 
								//or 'rent' a property. This information is 
								//obtained from the Extras bundle. 
		
		super.onCreate(savedInstanceState);


		Log.d(TAG, "onCreate");

        //*********************************************************************
    	//* TODO [05]: Set the Activity content from a layout resource, i.e.
        //* link the Activity to the XML GUI. See the onCreate method in
        //* PropertyDetails.java for an example on how this is done.
		//*
        //*********************************************************************

		setContentView(R.layout.propertylisting);

        //*********************************************************************

		/* Get Intent extra's */
        //*********************************************************************
    	//* TODO [06]: Insert Java code required to retrieve the Intent's
		//* Extras (in this case 'EXTRA_TO_RENT'), and store this in the 
		//* local boolean variable 'toRent'.
        //*
        //*********************************************************************

		Bundle extras = getIntent().getExtras();
		toRentStr = extras != null ?
				extras.getString(PropertyApp.EXTRA_TO_RENT) : null;
		toRent = Boolean.parseBoolean(toRentStr);

		//*********************************************************************

		mDb = new PropertyDB(this);
		mDb.open(toRent, false);
		// We let the system manage this cursor, so that the life
		// cycle of the cursor matches the life cycle of the activity that is
		// displaying the cursor's data.
		cursor = mDb.getCursorToThePropertyList();
		startManagingCursor(cursor);
				
		mListView = (ListView) findViewById(R.id.propertyListings);


		// Create the custom PropertyListAdapter to bridge our ListView and 
		// the data in the database.
		mPropertyListAdapter = new PropertyListAdapter();
		retrievePropertiesFromDB();
		mListView.setAdapter(mPropertyListAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Log.d(TAG, "PropertyList.onItemClick()");
		        //*************************************************************
		    	//* TODO [07]: Insert Java code required to trigger the
				//* PropertyDetails Intent, and use the putExtra method to add 
				//* Extra information to the Intent, namely the ID of the 
				//* property to which the user is seeking more details (i.e.
				//* EXTRA_PROPERTY_ID).
		        //*
		        //*************************************************************
				Intent i = new Intent(PropertyList.this, PropertyDetails.class);

				Property item=(Property) mListView.getItemAtPosition(position);

				i.putExtra(PropertyList.EXTRA_PROPERTY_ID, item.getPropertyID());
				i.putExtra(PropertyList.EXTRA_TO_RENT, PropertyApp.EXTRA_TO_RENT);
				startActivity(i);

				//*************************************************************
			}
		});
	} // onCreate

	/**
	 * retrievePropertiesFromDB. Performs all the actions required to start 
	 * showing a particular list.
	 */
	public void retrievePropertiesFromDB() {
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {				
				Property p = createPropertyFromCursor(cursor);
				mPropertyListAdapter.add(p); //NB: When we add a Property, we 
				//need to add it to the ArrayAdapter via add() � the adapter 
				//will, in turn, put it in the ArrayList. Otherwise, if we add 
				//it straight to the ArrayList, the adapter will not know about 
				//the added Property and therefore will not display it
				cursor.moveToNext();
			}
		}
	} // retrievePropertiesFromDB

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
	 * PropertyListAdapter class.
	 * 
	 * This is a custom Adapter that bridges our ListView and the data in the 
	 * database.
	 * 
	 * @author Rainer Wasinger.
	 */
	class PropertyListAdapter extends ArrayAdapter<Property> {
		PropertyListAdapter() {
			super(PropertyList.this, android.R.layout.simple_list_item_1,
					mPropertyList);
		}

		/**
		 * getView. Gets a view that represents a summary of this property. 
		 * Inflated from the propertylistrow.xml file into a real view.
		 */
		public View getView(int position, View row, ViewGroup parent) {
			TableLayout featureTable = null;
			TableRow tr = null;
			TextView label = null, value = null;
						
			LayoutInflater inflater=getLayoutInflater();
			row = inflater.inflate(R.layout.propertylistrow, null);
			Property p = mPropertyList.get(position);

			ImageView photo = (ImageView) row.findViewById(R.id.propertyImage);
			photo.setImageResource(R.drawable.house);

			featureTable = (TableLayout) row.findViewById(R.id.summaryFeatureTable);
			
			if (!p.getPrice().equals("")) {
				tr = new TableRow(PropertyList.this);
				label = new TextView(PropertyList.this);
				value = new TextView(PropertyList.this);
				label.setText("Price:");
				value.setText(p.getPrice());
				tr.addView(label);
				tr.addView(value);
				featureTable.addView(tr);
			}
						
			if (!p.getSuburb().equals("")) {
				tr = new TableRow(PropertyList.this);
				label = new TextView(PropertyList.this);
				value = new TextView(PropertyList.this);
				label.setText("Suburb:");
				value.setText(p.getSuburb());
				tr.addView(label);
				tr.addView(value);
				featureTable.addView(tr);
			}
			
			if (!p.getBedrooms().equals("")) {
				tr = new TableRow(PropertyList.this);
				label = new TextView(PropertyList.this);
				value = new TextView(PropertyList.this);
				label.setText("Bedrooms:");
				value.setText(p.getBedrooms());
				tr.addView(label);
				tr.addView(value);
				featureTable.addView(tr);
			}
			
			if (!p.getBathrooms().equals("")) {
				tr = new TableRow(PropertyList.this);
				label = new TextView(PropertyList.this);
				value = new TextView(PropertyList.this);
				label.setText("Bathrooms:");
				value.setText(p.getBathrooms());
				tr.addView(label);
				tr.addView(value);
				featureTable.addView(tr);
			}
			
			if (!p.getCarSpaces().equals("")) {
				tr = new TableRow(PropertyList.this);
				label = new TextView(PropertyList.this);
				value = new TextView(PropertyList.this);
				label.setText("CarSpaces:");
				value.setText(p.getCarSpaces());
				tr.addView(label);
				tr.addView(value);
				featureTable.addView(tr);
			}

			TextView title = (TextView) row.findViewById(R.id.summaryTitle);
			title.setText(p.getAddress());
			
			return(row);
		} //getView

	} //PropertyListAdapter class
	
} // PropertyList class
