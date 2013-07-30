/*
 * Innovation Care Team confidential
 * 
 * Source Materials
 * 
 * Copyright Innovation Care 2013, all rights reserved.
 */
package innovationcare.app.antibioticguidelines.ui;

import innovationcare.app.antibioticguidelines.InfectionContent;
import innovationcare.app.antibioticguidelines.CategoryMenu;
import innovationcare.app.antibioticguidelines.R;
import innovationcare.app.antibioticguidelines.R.id;
import innovationcare.app.antibioticguidelines.R.layout;
import innovationcare.app.antibioticguidelines.database.GuidelineDataAccess;
import innovationcare.app.antibioticguidelines.ui.adapter.MenuListAdapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

/*
 * Modification History
 * --------------------
 * 15-Jul-2013  Jiefeng  Initial version.
 * 
 */
/**
 * The activity class for the infection list screen.
 *
 */
public class InfectionListActivity extends Activity {

	/**
	 * Data access object.
	 */
	private final GuidelineDataAccess dao = new GuidelineDataAccess(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infection_list);
		
		Intent intent = getIntent();
		// TODO: change the default id.
		long categoryMenuId = intent.getLongExtra("categoryMenuId", 1);
		String categoryName = intent.getStringExtra("categoryMenuName");
		
		// Set the screen title to the category name.
		setTitle(categoryName);
		
		final ListView menuListView = 
				(ListView) findViewById(R.id.infectionList);
		
		// Open the database.
		dao.open();
		
		// Read all menus from a certain category.
		ArrayList<innovationcare.app.antibioticguidelines.Menu> menuList = 
				dao.readMenusByCategory(categoryMenuId);
		dao.close();
		
		final MenuListAdapter<innovationcare.app.antibioticguidelines.Menu> adapter = 
				new MenuListAdapter<innovationcare.app.antibioticguidelines.Menu>(
						this, R.layout.list_item_row, menuList);
		
		menuListView.setAdapter(adapter);
		
		// Click handler.
		menuListView.setOnItemClickListener(
				new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
						
				 final innovationcare.app.antibioticguidelines.Menu menu = 
						 (innovationcare.app.antibioticguidelines.Menu) parent.getItemAtPosition(position);
				 
				 /**
				  * Check the type to determine to open the infection content list,
				  * or surgery content list.
				  */
				 String type = menu.getType();
				 
				 if ("infection".equalsIgnoreCase(type)) {
					 Intent intent = new Intent(parent.getContext(), 
							 InfectionActivity.class);
					 intent.putExtra("menuId", menu.getId());
					 intent.putExtra("menuName", menu.getName());
					 startActivity(intent);
				 } else if ("surgery".equalsIgnoreCase(type)) {
					 Intent intent = new Intent(parent.getContext(), 
							 SurgeryContentActivity.class);
					 intent.putExtra("menuId", menu.getId());
					 intent.putExtra("menuName", menu.getName());
					 startActivity(intent);
				 } else {
					 // Do nothing. The type not supported. Couldn't open.
				 }
				 
			}
					
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.returnToHomeButton:
	            Intent intent = new Intent(this, MainActivity.class);
	            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
