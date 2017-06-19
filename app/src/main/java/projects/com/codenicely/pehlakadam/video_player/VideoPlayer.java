package projects.com.codenicely.pehlakadam.video_player;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.halilibo.bettervideoplayer.BetterVideoCallback;
import com.halilibo.bettervideoplayer.BetterVideoPlayer;

import projects.com.codenicely.pehlakadam.R;


public class VideoPlayer extends AppCompatActivity implements BetterVideoCallback {

//    ProgressBar progressBar;
//    TextView percent_view;

    private static String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    private BetterVideoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Log.d("res","3");

        TEST_URL=getIntent().getExtras().getString("url").toString();
        Log.d("res","3"+TEST_URL);
        // Grabs a reference to the player view
//        progressBar=(ProgressBar)findViewById(R.id.progressbar_video);
//        percent_view=(TextView)findViewById(R.id.percent_view);
        player = (BetterVideoPlayer) findViewById(R.id.player);

//        progressBar.setVisibility(View.INVISIBLE);
//        percent_view.setVisibility(View.INVISIBLE);
        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        player.setCallback(this);

        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        player.setSource(Uri.parse(TEST_URL));
        player.start();
    }

    @Override
    public void onStarted(BetterVideoPlayer player) {

//        percent_view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPaused(BetterVideoPlayer player) {
        player.pause();
    }

    @Override
    public void onPreparing(BetterVideoPlayer player) {

    }

    @Override
    public void onPrepared(BetterVideoPlayer player) {

    }

    @Override
    public void onBuffering(int percent) {
//        progressBar.setVisibility(View.VISIBLE);
//        percent_view.setVisibility(View.VISIBLE);
//        percent_view.setText(""+percent+"%");
    }

    @Override
    public void onError(BetterVideoPlayer player, Exception e) {
        Toast.makeText(this, "Seems like an error..try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompletion(BetterVideoPlayer player) {

    }

    @Override
    public void onToggleControls(BetterVideoPlayer player, boolean isShowing) {

    }
}
