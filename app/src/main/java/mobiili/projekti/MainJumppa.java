package mobiili.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainJumppa extends AppCompatActivity {

    ImageButton takaisin, koti, asetukset;
    WebView webView1;
    WebView webView2;
    WebView webView3;
    WebView webView4;
    Button tallenna;
    int liikuttuH = 0, liikuttuMin = 0, liikuttuH2, liikuttuMin2;
    EditText liikuttuAikaH, liikuttuAikaMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jumppa);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent siirto = getIntent();
        String tunnus = siirto.getExtras().getString("tunnus");

        String path1 = "<head><meta name=\"viewport\" content=\"width=device-width\">" +
                "</head><center><iframe width=" + dp(160) + " height=" + dp(90) + " src=\"https://www.youtube.com/embed/h0Sqc90rUY4\"" +
                " title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                " encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></center>";
        webView1 = findViewById(R.id.jumppavideo1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setLoadWithOverviewMode(true);
        webView1.getSettings().setUseWideViewPort(true);
        webView1.loadData(path1, "text/html", "utf-8");

        String path2 = "<head><meta name=\"viewport\" content=\"width=device-width\">" +
                "</head><center><iframe width=" + dp(160) + " height=" + dp(90) + " src=\"https://www.youtube.com/embed/itJE4neqDJw\"" +
                " title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                " encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></center>";
        webView2 = findViewById(R.id.jumppavideo2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setLoadWithOverviewMode(true);
        webView2.getSettings().setUseWideViewPort(true);
        webView2.loadData(path2, "text/html", "utf-8");

        String path3 = "<head><meta name=\"viewport\" content=\"width=device-width\">" +
                "</head><center><iframe width=" + dp(160) + " height=" + dp(90) + " src=\"https://www.youtube.com/embed/oKfNUOWuZV8\"" +
                " title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                " encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></center>";
        webView3 = findViewById(R.id.jumppavideo3);
        webView3.getSettings().setJavaScriptEnabled(true);
        webView3.getSettings().setLoadWithOverviewMode(true);
        webView3.getSettings().setUseWideViewPort(true);
        webView3.loadData(path3, "text/html", "utf-8");

        String path4 = "<head><meta name=\"viewport\" content=\"width=device-width\">" +
                "</head><center><iframe width=" + dp(160) + " height=" + dp(90) + " src=\"https://www.youtube.com/embed/KFcUwNl7eXo\"" +
                " title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write;" +
                " encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe></center>";
        webView4 = findViewById(R.id.jumppavideo4);
        webView4.getSettings().setJavaScriptEnabled(true);
        webView4.getSettings().setLoadWithOverviewMode(true);
        webView4.getSettings().setUseWideViewPort(true);
        webView4.loadData(path4, "text/html", "utf-8");

        final DataBase DB = new DataBase(this);
        final ArrayList<String> jumppa = DB.getJumppa(tunnus);
        final ArrayList<String> jumppamuisti = DB.getJumppaMuisti(tunnus);

        jumppa.clear();
        jumppamuisti.clear();
        jumppamuisti.addAll(DB.getJumppaMuisti(tunnus));
        liikuttuH = Integer.parseInt(jumppamuisti.get(0));
        liikuttuMin = Integer.parseInt(jumppamuisti.get(1));

        tallenna = findViewById(R.id.liikuttuTallenna);
        liikuttuAikaH = findViewById(R.id.liikuttuAikaH);
        liikuttuAikaH.setText(Integer.toString(liikuttuH));
        liikuttuAikaMin = findViewById(R.id.liikuttuAikaMin);
        liikuttuAikaMin.setText(Integer.toString(liikuttuMin));
        tallenna.setOnClickListener(v ->
        {
            if (liikuttuAikaH.getText().toString().equals("")) {
                liikuttuH2 = 0;
            } else {
                liikuttuH2 = Integer.parseInt(liikuttuAikaH.getText().toString());
            }
            if (liikuttuAikaMin.getText().toString().equals("")) {
                liikuttuMin2 = 0;
            } else {
                liikuttuMin2 = Integer.parseInt(liikuttuAikaMin.getText().toString());
            }

            DB.addJumppa(tunnus, liikuttuH2, liikuttuMin2);
            DB.setJumppaMuisti(tunnus, liikuttuH2, liikuttuMin2);
            Toast.makeText(this, "Tallennettu", Toast.LENGTH_SHORT).show();
        }); //TODO tarkista ettei ole jumpattu yli 24tuntia

        takaisin = findViewById(R.id.takaisin);
        takaisin.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPage.class);
            intent.putExtra("tunnus", tunnus);
            startActivity(intent);
        });

        koti = findViewById(R.id.koti);
        koti.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPage.class);
            intent.putExtra("tunnus", tunnus);
            startActivity(intent);
            overridePendingTransition(
                    R.anim.d_in, R.anim.u_out
            );
        });

        asetukset = findViewById(R.id.asetukset);
        asetukset.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainAsetukset.class);
            intent.putExtra("tunnus", tunnus);
            startActivity(intent);
            overridePendingTransition(
                    R.anim.u_in, R.anim.d_out
            );
        });
    }

    public int dp(float num) {
        float num1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, num, getResources().getDisplayMetrics());
        return Math.round(num1);
    }
}
