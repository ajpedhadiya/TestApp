package com.dss.testapp;

import java.util.ArrayList;

import com.dss.testapp.RecyclerItemClickListener.OnItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends Activity {
	ArrayList<ProductItem> productList = new ArrayList<ProductItem>();
	RecyclerView productGridLayoutRecycler;
	String colorCode[] = { "#5E99FF", "#FA9D4C", "#9C65CA", "#0099CC", "#F2383D", "#607D8B", "#9E9E9E", "#795548",
			"#FF5722", "#8BC34A", "#4CAF50", "#00BCD4", "#2196F3", "#3F51B5", "#673AB7", "#E91E63", "#FFC107",
			"#E91E63", "#2196F3", "#838BF3", "#BB72F3", "#93F33F" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.products);
		for (int i = 0; i < 21; i++) {
			ProductItem p = new ProductItem((i + 1), "Product " + (i + 1), (float) (21 / (i + 1)), colorCode[i]);
			productList.add(p);
		}

		productGridLayoutRecycler = (RecyclerView) findViewById(R.id.productGridLayoutRecycler);
		productGridLayoutRecycler.addItemDecoration(new MarginDecoration(ProductActivity.this));
		productGridLayoutRecycler.setHasFixedSize(true);
		productGridLayoutRecycler.setLayoutManager(new GridLayoutManager(ProductActivity.this, 2));

		productGridLayoutRecycler.setAdapter(new ProductAdater());

		productGridLayoutRecycler.addOnItemTouchListener(new RecyclerItemClickListener(ProductActivity.this,
				productGridLayoutRecycler, new OnItemClickListener() {

					@Override
					public void onItemLongClick(View view, int position) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onItemClick(View view, int position) {
						Intent in = new Intent(ProductActivity.this, BillingActivity.class);
						in.putExtra("price", productList.get(position).getPrice());
						startActivity(in);
						finish();

					}
				}));

	}

	class ProductAdater extends RecyclerView.Adapter<ProductViewHolder> {

		@Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return productList.size();
		}

		@Override
		public void onBindViewHolder(ProductViewHolder holder, int position) {
			holder.txtProductPrice.setText("$ " + productList.get(position).getPrice());
			holder.txtProductName.setText(productList.get(position).getProductName());
			holder.itemView.setBackgroundColor(Color.parseColor(productList.get(position).getColorCode()));

		}

		@Override
		public ProductViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			View view = LayoutInflater.from(arg0.getContext()).inflate(R.layout.product_list_item, arg0, false);
			return new ProductViewHolder(view);

		}

	}

	class ProductViewHolder extends ViewHolder {
		protected TextView txtProductPrice;
		protected TextView txtProductName;
		protected ImageView imgProductImage;

		public ProductViewHolder(View itemView) {
			super(itemView);
			this.txtProductPrice = (TextView) itemView.findViewById(R.id.txtProductPrice);
			this.txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
			this.imgProductImage = (ImageView) itemView.findViewById(R.id.imgProductImage);
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
	}
}
