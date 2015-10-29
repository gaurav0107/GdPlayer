package com.dgplayer.gdplayer.player;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dgplayer.gdplayer.GdPlayerLayout;
import com.dgplayer.gdplayer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GdPlayerLayout gdPlayerLayout=(GdPlayerLayout)findViewById(R.id.testplayer);
        gdPlayerLayout.setContentId("test");
        gdPlayerLayout.setContentType(GdPlayerLayout.TYPE_OTHER);
        gdPlayerLayout.setContentUri(Uri.parse("https://r1---sn-npo7zn7z.googlevideo.com/videoplayback?mt=1446097779&mv=u&ms=au&key=yt6&ip=52.74.96.210&mm=31&upn=rnvnKcmpCuM&sparams=dur%2Cid%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&mn=sn-npo7zn7z&id=o-ABo5_Ry8L-T7oDo90IGtmdJesoKjg-pwI2pHmVKS5UgH&lmt=1445424138126564&requiressl=yes&source=youtube&fexp=9407538%2C9408253%2C9408710%2C9412776%2C9414764%2C9415435%2C9415554%2C9415943%2C9415982%2C9416126%2C9417055%2C9417707%2C9418400%2C9419446%2C9420628%2C9421168%2C9422397%2C9422456%2C9422596%2C9423418%2C9423490&dur=14.675&pl=19&itag=18&nh=IgpwcjAxLnNpbjAzKgkxMjcuMC4wLjE&expire=1446120123&sver=3&ratebypass=yes&signature=AB106478D6D934C4EE49C4DAC99D56644F7450CE.2746561649E965D81BD6FA7DCD105C85D0BB400C&mime=video%2Fmp4&ipbits=0"));
        gdPlayerLayout.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
