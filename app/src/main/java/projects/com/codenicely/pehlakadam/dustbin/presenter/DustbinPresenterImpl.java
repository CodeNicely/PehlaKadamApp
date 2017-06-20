package projects.com.codenicely.pehlakadam.dustbin.presenter;

import projects.com.codenicely.pehlakadam.dustbin.DustbinCallback;
import projects.com.codenicely.pehlakadam.dustbin.data.DustbinData;
import projects.com.codenicely.pehlakadam.dustbin.model.DustbinProvider;
import projects.com.codenicely.pehlakadam.dustbin.view.DustbinView;

/**
 * Created by ujjwal on 20/6/17.
 */
public class DustbinPresenterImpl implements DustbinPresenter{
	DustbinView dustbinView;
	DustbinProvider dustbinProvider;

	public DustbinPresenterImpl(DustbinView dustbinView, DustbinProvider dustbinProvider) {
		this.dustbinView = dustbinView;
		this.dustbinProvider = dustbinProvider;
	}

	@Override
	public void getDustbinData(Double latitude,Double longitude) {
		dustbinView.showLoader(true);
		dustbinProvider.getDustbinData(latitude,longitude,new DustbinCallback() {
			@Override
			public void onSuccess(DustbinData dustbinData) {
				dustbinView.showLoader(false);
				if (dustbinData.isSuccess()){
					dustbinView.setData(dustbinData);
				}else {
					dustbinView.showMessage(dustbinData.getMessage());
				}
			}

			@Override
			public void onFailure() {
				dustbinView.showLoader(false);
				dustbinView.showMessage("Unable to connect to our server");
			}
		});
	}
}
