/*
 * Innovation Care Team confidential
 * 
 * Source Materials
 * 
 * Copyright Innovation Care 2013, all rights reserved.
 */
package innovationcare.app.antibioticguidelines.ui;

import innovationcare.app.antibioticguidelines.InfectionCategory;
import innovationcare.app.antibioticguidelines.R;
import innovationcare.app.antibioticguidelines.R.id;
import innovationcare.app.antibioticguidelines.R.layout;
import innovationcare.app.antibioticguidelines.database.GuidelineDataAccess;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Modification History
 * --------------------
 * 15-Jul-2013  Jiefeng  Initial version.
 * 
 */
/**
 * The activity class for the infection category list screen.
 *
 */
public class InfectionCategoryListActivity extends Activity {
	
	/**
	 * Data access object.
	 */
	private final GuidelineDataAccess dao = new GuidelineDataAccess(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_infection_category_list);
		
		final ListView infectionCategoryListView = 
				(ListView) findViewById(R.id.infectionCategoryList);
		
		// Open the database.
		dao.open();
		
		final ArrayList<InfectionCategory> infectionCatList = 
				dao.readAllInfectionCategories();
		
		// Close the database;
		dao.close();
		
		final ArrayAdapter<InfectionCategory> adapter = new ArrayAdapter<InfectionCategory>(this, 
				android.R.layout.simple_list_item_1, 
				infectionCatList);
		
		infectionCategoryListView.setAdapter(adapter);
		
		// Click handler.
		infectionCategoryListView.setOnItemClickListener(
				new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				
				 final InfectionCategory infectionCat = 
						 (InfectionCategory) parent.getItemAtPosition(position);
				 
				 Intent intent = new Intent(parent.getContext(), 
						 InfectionListActivity.class);
				 intent.putExtra("categoryId", infectionCat.getId());
				 intent.putExtra("categoryName", infectionCat.getName());
				 startActivity(intent);
			}
			
		});
	}
}
