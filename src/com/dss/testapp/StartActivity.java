package com.dss.testapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StartActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
		
		if(pref.getBoolean("isLogin", false))
		{
			startActivity(new Intent(StartActivity.this, ProductActivity.class));
			finish();
			overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
		}
				
		((RelativeLayout) findViewById(R.id.relGo)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(StartActivity.this, SignInActivity.class));
				finish();
				overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

			}
		});
		((TextView) findViewById(R.id.txtSignUp)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(StartActivity.this, SignupActivity.class));
				finish();
				overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
	}

}
