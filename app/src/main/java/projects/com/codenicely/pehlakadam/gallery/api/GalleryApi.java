package projects.com.codenicely.pehlakadam.gallery.api;


import projects.com.codenicely.pehlakadam.gallery.model.GalleryData;
import projects.com.codenicely.pehlakadam.helper.Urls;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghalagrawal on 03/06/17.
 */

public interface GalleryApi {

    @GET(Urls.SUB_URL_GALLERY_API)
    Call<GalleryData> getGalleryImages(@Query("language_type") int language_type
    );

}
