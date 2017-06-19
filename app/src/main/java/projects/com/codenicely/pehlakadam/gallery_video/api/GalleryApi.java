package projects.com.codenicely.pehlakadam.gallery_video.api;


import projects.com.codenicely.pehlakadam.gallery_video.model.data.GalleryData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;


public interface GalleryApi {


    @GET(Urls.SUB_URL_GALLERY_VIDEO)
    Call<GalleryData> getImageUrls();

}
