package com.dss.testapp;

import java.util.Calendar;

import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BillingActivity extends Activity {
	public static final String PUBLISHABLE_KEY = "pk_test_6pRNASCoBOKtIshFeQd4XMUh";

	private ProgressDialog progressFragment;
	SharedPreferences pref;
	SharedPreferences.Editor edit;

	EditText edtFirstName;
	EditText edtLastName;
	EditText edtCardNumber;
	EditText edtCvv;
	TextView txtSelectedDate;
	EditText edtAddressline1;
	EditText edtAddressline2;
	EditText edtCity;
	EditText edtState;
	EditText edtZip;
	EditText edtCountry;
	EditText edtComment;
	TextView txtPay;
	private Calendar cal;
	int day, month, year;
	String months[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billing_activity);

		txtPay = (TextView) findViewById(R.id.txtPay);
		edtFirstName = (EditText) findViewById(R.id.edtFirstName);
		edtLastName = (EditText) findViewById(R.id.edtLastName);
		edtCardNumber = (EditText) findViewById(R.id.edtCardNumber);
		edtCvv = (EditText) findViewById(R.id.edtCvv);
		txtSelectedDate = (TextView) findViewById(R.id.txtSelectedDate);
		edtAddressline1 = (EditText) findViewById(R.id.edtAddressline1);
		edtAddressline2 = (EditText) findViewById(R.id.edtAddressline2);
		edtCity = (EditText) findViewById(R.id.edtCity);
		edtState = (EditText) findViewById(R.id.edtState);
		edtZip = (EditText) findViewById(R.id.edtZip);
		edtCountry = (EditText) findViewById(R.id.edtCountry);
		edtComment = (EditText) findViewById(R.id.edtComment);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		float price = getIntent().getFloatExtra("price", 0);
		txtPay.setText("$ " + price);
		txtSelectedDate.setText(day + " - " + months[month] + " " + year);
		pref = getSharedPreferences("bill", MODE_PRIVATE);
		edit = pref.edit();
		String respo = pref.getString("ui", "");
		if (respo != null && !respo.equals("")) {
			UserInfo ui = new UserInfo(respo);
			edtFirstName.setText(ui.getFname());
			edtLastName.setText(ui.getLname());
			edtCardNumber.setText(ui.getCardNo());
			edtCvv.setText(ui.getCvv());
			txtSelectedDate
					.setText(ui.getExDate() + " - " + months[Integer.parseInt(ui.getExMonth())] + " " + ui.getExYear());
			edtAddressline1.setText(ui.getAdd1());
			edtAddressline2.setText(ui.getAdd2());
			edtCity.setText(ui.getCity());
			edtState.setText(ui.getState());
			edtZip.setText(ui.getZipcode());
			edtCountry.setText(ui.getCountry());
			edtComment.setText(ui.getComment());
		}

		progressFragment = new ProgressDialog(BillingActivity.this);
		((ImageView) findViewById(R.id.imgBack)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(BillingActivity.this, ProductActivity.class));
				finish();
				overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);

			}
		});
		((ImageView) findViewById(R.id.imgDone)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(BillingActivity.this, ProductActivity.class));
				finish();
				overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);

			}
		});
		txtSelectedDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(0);

			}
		});
		txtPay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (isValid()) {
					UserInfo ui = new UserInfo(edtFirstName.getText().toString().trim(),
							edtLastName.getText().toString().trim(), edtCardNumber.getText().toString().trim(),
							edtCvv.getText().toString().trim(), String.valueOf(day), String.valueOf(month + 1),
							String.valueOf(year), edtAddressline1.getText().toString().trim(),
							edtAddressline2.getText().toString().trim(), edtCity.getText().toString().trim(),
							edtState.getText().toString().trim(), edtZip.getText().toString().trim(),
							edtCountry.getText().toString().trim(), edtComment.getText().toString().trim());
					System.out.println(ui.getExMonth() + "/" + ui.getExYear());
					saveCreditCard(ui);
				}

			}
		});

	}

	boolean isValid() {
		if (edtFirstName.getText().toString().trim().equals("")) {
			edtFirstName.setError(Html.fromHtml("<font color='red'>First name</font>"));
		} else if (edtLastName.getText().toString().trim().equals("")) {
			edtLastName.setError(Html.fromHtml("<font color='red'>Enter Last name</font>"));
		} else if (edtCardNumber.getText().toString().trim().equals("")) {
			edtCardNumber.setError(Html.fromHtml("<font color='red'>Card number</font>"));
		} else if (edtCvv.getText().toString().trim().equals("")) {
			edtCvv.setError(Html.fromHtml("<font color='red'>Cvv</font>"));
		} else if (edtAddressline1.getText().toString().trim().equals("")) {
			edtAddressline1.setError(Html.fromHtml("<font color='red'>Address</font>"));
		} else if (edtCity.getText().toString().trim().equals("")) {
			edtCity.setError(Html.fromHtml("<font color='red'>City</font>"));
		} else if (edtState.getText().toString().trim().equals("")) {
			edtState.setError(Html.fromHtml("<font color='red'>State</font>"));
		} else if (edtZip.getText().toString().trim().equals("")) {
			edtZip.setError(Html.fromHtml("<font color='red'>Zip</font>"));
		} else if (edtCountry.getText().toString().trim().equals("")) {
			edtCountry.setError(Html.fromHtml("<font color='red'>Country</font>"));
		} else {
			return true;
		}
		return false;
	}

	public void saveCreditCard(final UserInfo form) {

		Card card = new Card(form.getCardNo(), Integer.parseInt(form.getExMonth()), Integer.parseInt(form.getExYear()),
				form.getCvv());
		progressFragment.setMessage("Wait validation your card...");
		boolean validation = card.validateCard();
		if (validation) {
			startProgress();
			new Stripe().createToken(card, PUBLISHABLE_KEY, new TokenCallback() {
				public void onSuccess(Token token) {

					finishProgress();
					edit.putString("ui", form.getJsonResponse());
					edit.commit();
					((RelativeLayout) findViewById(R.id.relSuccess)).setVisibility(View.VISIBLE);

				}

				public void onError(Exception error) {
					handleError(error.getLocalizedMessage());
					finishProgress();
				}
			});
		} else if (!card.validateNumber()) {
			handleError("The card number that you entered is invalid");
		} else if (!card.validateExpiryDate()) {
			handleError("The expiration date that you entered is invalid");
		} else if (!card.validateCVC()) {
			handleError("The CVC code that you entered is invalid");
		} else {
			handleError("The card details that you entered are invalid");
		}
	}

	private void startProgress() {
		progressFragment.show();
	}

	private void finishProgress() {
		progressFragment.dismiss();
	}

	private void handleError(String error) {
		AlertDialog.Builder alert = new AlertDialog.Builder(BillingActivity.this);
		alert.setMessage(error);
		alert.show();

	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, year, month, day);
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
			day = selectedDay;
			month = selectedMonth;
			year = selectedYear;
			txtSelectedDate.setText(selectedDay + " - " + months[selectedMonth] + " " + selectedYear);
		}
	};

}
