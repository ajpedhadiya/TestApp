package com.dss.testapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SignInActivity extends Activity {
	EditText edtEmail;
	EditText edtPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_activity);
		edtEmail = (EditText) findViewById(R.id.edtEmail);
		edtPassword = (EditText) findViewById(R.id.edtPassword);

		((ImageView) findViewById(R.id.imgSignIn)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isValid()) {
					SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
					Editor edit = pref.edit();
					edit.putBoolean("isLogin", true);
					edit.putString("em", edtEmail.getText().toString().trim());
					edit.putString("psw", edtPassword.getText().toString().trim());
					edit.commit();
					startActivity(new Intent(SignInActivity.this, ProductActivity.class));
					finish();
					overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
				}
			}
		});
		((ImageView) findViewById(R.id.imgBack)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
			}
		});
	}

	boolean isValid() {
		SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
		if (edtEmail.getText().toString().trim().equals("")) {
			edtEmail.setError(Html.fromHtml("<font color='red'>Enter Email</font>"));
		} else if (!edtEmail.getText().toString().trim().equals("")
				&& !android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString().trim()).matches()) {
			edtEmail.setError(Html.fromHtml("<font color='red'>Enter valid email</font>"));

		} else if (edtPassword.getText().toString().trim().equals("")) {
			edtEmail.setError(Html.fromHtml("<font color='red'>Enter password</font>"));

		} else if (!pref.getString("em", "").equals(edtPassword.getText().toString().trim())
				&& !pref.getString("psw", "").equals(edtPassword.getText().toString().trim())) {
			Toast.makeText(SignInActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();

		}

		else {
			return true;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
	}
}
