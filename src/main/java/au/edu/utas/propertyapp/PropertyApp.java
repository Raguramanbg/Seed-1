package au.edu.utas.propertyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

/******************************************************************************
 * KIT305 Mobile Application Development, The University of Tasmania.
 *
 * FILENAME:   	PropertyApp.java
 * AUTHORS:     Rainer Wasinger
 * 
 * NOTE:    	***THIS CLASS NEEDS MODIFIYING BY THE STUDENTS.***
 * 				In particular, see the method: initialiseSearchPageGUI. 
 * 
 * PROJECT DESCRIPTION:
 * This whole project consists of three Activities:
 * 		- PropertyApp. This is the starting activity as defined in the
 * 			AndroidManifest.xml file, i.e. it is the main activity for the
 * 			application ("android.intent.action.MAIN") and is in the LAUNCHER
 * 			category, meaning it is associated with a program icon.
 * 			The associated GUI for PropertyApp is contained in searchpage.xml.
 * 		- PropertyList. This activity is called when the user selects either
 * 			the buy or rent radio button and then clicks the search button.
 * 			The activity is responsible for presenting the user with a list
 * 			of property results (either to buy or to rent).
 * 			The associated GUI for PropertyList is contained inside 
 * 			propertylisting.xml (as well as propertylistrow.xml).
 * 		- PropertyDetails. This activity is called when the user selects one of
 * 			the listed properties. The activity is responsible for providing
 * 			more detailed information on an individual property.
 * 			The associated GUI for PropertyDetails is contained inside
 * 			propertydetails.xml.
 *
 * CLASS DESCRIPTION: 
 * PropertyApp. This is the starting class for the Android application. 
 * In this Activity class:
 *  	- adapters are defined to bind program code to the GUI widgets, and 
 *  		listeners for the GUI widgets (i.e. the search button) are also 
 *  		defined.
 *  	- XML property data files (for either buy or sell; see the directory
 *  		/res/raw for the location of these files) are read (when the search 
 *  		button is clicked) and stores the property information in a Vector 
 *  		data structure. A minimised set of this data is written to a local 
 *  		SQLite database called "propertydata", and properties that are 
 *  		displayed within the various activities are based on information 
 *  		retrieved from this database and stored as a Property object (see 
 *  		Property.java).
 *  	- An Intent is triggered (when the search button is clicked (and after 
 *  		a 'buy' or 'rent' radio box has been selected)). An Intent is 
 *  		basically a message passed to the Android OS saying, e.g. open up 
 *  		a specific activity within this application. Note that for our
 *  		Intent, we include a component (i.e. the class of the activity that 
 *  		is supposed to receive the intent), and we also define extras (i.e.
 *  		a bundle of other information that we want to pass along to the 
 *  		receiver of the intent), in this case, whether the user has 
 *  		requested 'buy' or 'rent' properties to be listed.
 * 
 *****************************************************************************/


public class PropertyApp extends AppCompatActivity implements OnItemSelectedListener {
	private static final String TAG = "PropertyApp";

	// EXTRA_PROPERTY_ID and EXTRA_TO_RENT are used to store Intent Extras.
	// EXTRA_PROPERTY_ID represents the property's ID and EXTRA_TO_RENT
	// can have the values 'true' (i.e. rent) or 'false' (i.e. buy).
	public static final String EXTRA_PROPERTY_ID="au.edu.utas.propertyapp.property_id";
	public static final String EXTRA_TO_RENT="au.edu.utas.propertyapp.to_rent";

    //*************************************************************************
	//* TODO [03]: Insert Java code variables required to populate the search 
	//* page Spinners.
	//* Suggested format: String[] spinnerVar1 = {"", "", ""};, where
	//* 'spinnerVar1' is the name of the string array (e.g. propertyTypeItems,
	//* stateItems, and numberedItems).
	//*
	//* NB: Spinners are the equivalent of the drop-down selector you
	//* might find in other toolkits (e.g. JComboBox in Java/Swing).
    //*
    //*************************************************************************

	String[] spinner01Items = {"---", "Apartment", "House", "Land"};

	//*************************************************************************

	PropertyDB mDb = null;



	/**
     * onCreate. Called when this activity is first created. Also responsible
     * for setting the content view.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Log.d(TAG, "onCreate");
        
        //*********************************************************************
    	//* TODO [02]: Set the Activity content from a layout resource, i.e. 
        //* link the Activity to the XML GUI. See the onCreate method in
        //* PropertyDetails.java for an example on how this is done.
        //*
    	//*********************************************************************

		setContentView(R.layout.searchpage);

    	//*********************************************************************
        
        initialiseSearchPageGUI();
    } //onCreate
	
    /** 
     * initialiseSearchPageGUI. Sets up the GUI components that are relevant to 
     * this project.
     */
    public void initialiseSearchPageGUI() {
        //*********************************************************************
    	//* TODO [04]: Insert Java code required to initialise the application's
        //* search page GUI widgets.
    	//* Suggestion: This is where the Spinners (e.g. PropertyType, state, 
    	//* bedrooms, bathrooms, and carSpaces), RadioGroup (e.g. buyOrRent),
    	//* RadioButtons (e.g. buy, rent), EditTexts (e.g. suburb, priceFrom,
    	//* priceTo), and Buttons (e.g. searchBtn) are created and linked to the 
    	//* ID's that you defined in the searchpage.xml file.
    	//* 
        //* This is also where the Spinner adapters (e.g. ArrayAdapter<String>), 
    	//* and the button listener (e.g. setOnClickListener) are defined. 
    	//* 
    	//* It is also where the Intent is created (e.g. when the user presses
    	//* the search button) to trigger the PropertyList Activity.
        //* 
        //* NB: Adapters are used to link program code to the GUI widgets, e.g.
        //* linking the elements defined to exist inside a Spinner (program 
    	//* code) to the Spinner widget defined in the XML layout file. 
        //* 
    	//* NB: Listeners are used to handle user interaction with the GUI
        //* widgets, e.g. selecting a radio box, and the pressing of a button.
        //*
		//*********************************************************************

    	//04A: Create the Spinners and link them, via the ArrayAdapter, to the String 
    	//arrays (i.e. the data) that you defined above in TODO [03].
		//

		//Spinner
		Spinner spinner01 = (Spinner)findViewById(R.id.spinner01);

		spinner01.setOnItemSelectedListener(this);
		ArrayAdapter<String> spinner01AA = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1,
				spinner01Items);
		spinner01AA.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);

		spinner01.setAdapter(spinner01AA);

    	//
    	//04B: Create the search Button and its associated onClick listener. Within
    	//the onClick method, check which radio button was selected, and then
    	//based on this, call the populatePropertyCatalog(boolean toRent) method,
    	//and trigger an Intent to start the PropertyList Activity. Also use the 
    	//putExtra method to add Extra information to the Intent, namely whether 
    	//the 'buy' or 'rent' radio button was selected (i.e. using the 
    	//EXTRA_TO_RENT class variable). This Extra information 
    	//is used by the PropertyList Activity in determining which XML file 
    	//to use.
		//


		Button button01 = (Button)findViewById(R.id.button);

		button01.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				Log.d(TAG,"button1 has just been pressed");
				RadioGroup myRadioGroup;
				myRadioGroup = (RadioGroup)findViewById(R.id.myRadioGroup);

				Intent i = new Intent(PropertyApp.this, PropertyList.class);


				//Create an intent for a specific component.
				//Intent(Context packageContext, Class<?> cls)
				int checkedID = myRadioGroup.getCheckedRadioButtonId();
				if (checkedID == -1){
					Log.d(TAG, "No radio button was selected.");
				} else if(checkedID == R.id.radioButton1){
					Log.d(TAG, "'Buy' was selected.");
					populatePropertyCatalog(false);
					i.putExtra(PropertyApp.EXTRA_TO_RENT, "false");
					startActivity(i);

				} else if(checkedID == R.id.radioButton2){
					Log.d(TAG,"'Rent' was selected.");
					populatePropertyCatalog(true);
					i.putExtra(PropertyApp.EXTRA_TO_RENT, "true");
					startActivity(i);
				}

			}
		});

		//
    	//*********************************************************************
    } //initialiseSearchPageGUI

	/**
	 * populatePropertyCatalog. This function is responsible for storing the
	 * XML property data into a local tree structure. The  instantiation to the
	 * XMLParser is created in this method. The instantiation to the 
	 * XMLPropertyCatalog is created in XMLUnmarshaller.java.
	 */
    public void populatePropertyCatalog(boolean toRent) {
		Log.d(TAG, "populatePropertyCatalog");

		if (mDb != null) { // Close the previous database instantiation in case 
			mDb.close();   // it is not already closed.
		}

		mDb = new PropertyDB(this);
		mDb.open(toRent, true);
   } //populatePropertyCatalog
    
    /**
     * onItemSelected. This relates to the Spinner widget functionality and
     * is not needed for the KIT305 Android labs.
     */
	public void onItemSelected(AdapterView<?> parent, View v, int position, 
			long id) {
 	} //onItemSelected

    /**
     * onNothingSelected. This relates to the Spinner widget functionality and
     * is not needed for the KIT305 Android labs.
     */
    public void onNothingSelected(AdapterView<?> parent) {
   	} //onNothingSelected

	/**
	 * onDestroy. Called when the activity is destroyed.
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	} // onDestroy

} //PropertyApp
