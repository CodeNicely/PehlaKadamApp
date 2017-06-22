package projects.com.codenicely.pehlakadam.about_us.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.com.codenicely.pehlakadam.R;
import projects.com.codenicely.pehlakadam.about_us.data.AboutUsDetails;

/**
 * Created by ujjwal on 19/6/17.
 */

public class AboutUsAdapter extends RecyclerView.Adapter{

	private List<AboutUsDetails> aboutUsDetailsList = new ArrayList<>();
	private Context context;
	private LayoutInflater layoutInflater;

	public AboutUsAdapter(Context context) {
	this.context=context;
	layoutInflater=LayoutInflater.from(context);

	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = layoutInflater.inflate(R.layout.about_us_item, parent, false);
		return new MyViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		AboutUsDetails aboutUsDetails= aboutUsDetailsList.get(position);
		MyViewHolder myViewHolder = (MyViewHolder) holder;
		myViewHolder.title.setText(aboutUsDetails.getTitle());

		myViewHolder.description.setText(aboutUsDetails.getDescription());

	}

	@Override
	public int getItemCount() {
		return aboutUsDetailsList.size();
	}

	public void setAboutUsDetailsList(List<AboutUsDetails> aboutUsDetailsList) {
		this.aboutUsDetailsList = aboutUsDetailsList;
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.title)
		TextView title;
		@BindView(R.id.description)
		TextView description;

		public MyViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this,itemView);
		}
	}

}
