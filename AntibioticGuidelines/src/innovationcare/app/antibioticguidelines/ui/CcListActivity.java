package innovationcare.app.antibioticguidelines.ui;

import innovationcare.app.antibioticguidelines.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
<<<<<<< HEAD
=======
import android.view.MenuItem;
>>>>>>> 756fbce418be78fe01ce0b34109367715cc085e3
import android.view.View;

public class CcListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cc_list);
	}
	public void openob(View v) {
		Intent intent = new Intent(this, CcobActivity.class);
		startActivity(intent);
	}
	public void openno(View v) {
		Intent intent = new Intent(this, CcnoActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
<<<<<<< HEAD
		getMenuInflater().inflate(R.menu.cc_list, menu);
		return true;
	}
=======
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
>>>>>>> 756fbce418be78fe01ce0b34109367715cc085e3

}
