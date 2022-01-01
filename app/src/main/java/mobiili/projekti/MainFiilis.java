package mobiili.projekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainFiilis extends AppCompatActivity {

    Button tallenna;
    SeekBar fiilisAsteikko;
    ImageView image;
    ImageButton takaisin, koti, asetukset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fiilis);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent siirto = getIntent();
        String tunnus = siirto.getExtras().getString("tunnus");

        final DataBase DB = new DataBase(this);
        final ArrayList<String> fiilisToday = DB.getFiilisToday(tunnus);

        image = findViewById(R.id.fiilisKuva);
        image.setImageResource(R.drawable.ok);
        fiilisAsteikko = findViewById(R.id.fiilisAsteikko);

        fiilisToday.clear();
        fiilisToday.addAll(DB.getFiilisToday(tunnus));
        int index2 = fiilisToday.size(), num2 = 0, num3 = 0;
        for (int i = 0; i < index2; i++) {
            int num1 = Integer.parseInt(fiilisToday.get(i));
            num2 += num1;
        }
        if (index2 > 0) {
            num3 = num2 / index2;
        }
        if (index2 > 0) {
            String fiilisNyt = fiilisToday.get(index2 - 1);
            int kohta = Integer.parseInt(fiilisNyt);
            fiilisAsteikko.setProgress(kohta);
            naama(kohta);
        } else {
            fiilisAsteikko.setProgress(0);
        }

        TextView fiilisTanaan = findViewById(R.id.fiilisTanaan);
        String fiilisT = Integer.toString(num3);
        if (index2 > 0) {
            fiilisTanaan.setText("Päivän kokonaisfiilis: " + fiilisT);
        } else {
            fiilisTanaan.setText("Päivän kokonaisfiilis: -");
        }
        fiilisAsteikko.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int arvo = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                arvo = i;
                naama(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ProgressBar d1 = findViewById(R.id.d1);
        ProgressBar d2 = findViewById(R.id.d2);
        ProgressBar d3 = findViewById(R.id.d3);
        ProgressBar d4 = findViewById(R.id.d4);
        ProgressBar d5 = findViewById(R.id.d5);
        ProgressBar d6 = findViewById(R.id.d6);
        ProgressBar d7 = findViewById(R.id.d7);
        ArrayList<String> historia = DB.getFiilisHistoria(tunnus);
        int index = historia.size();
        if (index > 0) {
            int eilen = (Integer.parseInt(historia.get(0)) + 100) / 2;
            d1.setProgress(eilen);
            if (Integer.parseInt(historia.get(0)) < 50) {
                d1.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
            }
            if (Integer.parseInt(historia.get(0)) >= 50) {
                d1.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            }
            if (index > 1) {
                int tp = (Integer.parseInt(historia.get(1)) + 100) / 2;
                d2.setProgress(tp);
                if (Integer.parseInt(historia.get(1)) < 50) {
                    d2.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                }
                if (Integer.parseInt(historia.get(1)) >= 50) {
                    d2.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                }
                if (index > 2) {
                    int d3i = (Integer.parseInt(historia.get(2)) + 100) / 2;
                    d3.setProgress(d3i);
                    if (Integer.parseInt(historia.get(2)) < 50) {
                        d3.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                    if (Integer.parseInt(historia.get(2)) >= 50) {
                        d3.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                    }
                    if (index > 3) {
                        int d4i = (Integer.parseInt(historia.get(2)) + 100) / 2;
                        d4.setProgress(d4i);
                        if (Integer.parseInt(historia.get(2)) < 50) {
                            d4.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                        }
                        if (Integer.parseInt(historia.get(2)) >= 50) {
                            d4.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                        }
                        if (index > 4) {
                            int d5i = (Integer.parseInt(historia.get(2)) + 100) / 2;
                            d5.setProgress(d5i);
                            if (Integer.parseInt(historia.get(2)) < 50) {
                                d5.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                            }
                            if (Integer.parseInt(historia.get(2)) >= 50) {
                                d5.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                            }
                            if (index > 5) {
                                int d6i = (Integer.parseInt(historia.get(2)) + 100) / 2;
                                d6.setProgress(d6i);
                                if (Integer.parseInt(historia.get(2)) < 50) {
                                    d6.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                                }
                                if (Integer.parseInt(historia.get(2)) >= 50) {
                                    d6.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                                }
                                if (index > 6) {
                                    int d7i = (Integer.parseInt(historia.get(2)) + 100) / 2;
                                    d7.setProgress(d7i);
                                    if (Integer.parseInt(historia.get(2)) < 50) {
                                        d7.getProgressDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                                    }
                                    if (Integer.parseInt(historia.get(2)) >= 50) {
                                        d7.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                                    }
                                } else {
                                    d7.setProgress(0);
                                }
                            } else {
                                d6.setProgress(0);
                            }
                        } else {
                            d5.setProgress(0);
                        }
                    } else {
                        d4.setProgress(0);
                    }
                } else {
                    d3.setProgress(0);
                }
            } else {
                d2.setProgress(0);
            }
        } else {
            d1.setProgress(0);
        }

        tallenna = findViewById(R.id.tallennaFiilis);
        tallenna.setOnClickListener(v ->
        {
            DB.addFiilis(tunnus, fiilisAsteikko.getProgress());
            Toast.makeText(this, "Fiilis tallennettu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainFiilis.class);
            intent.putExtra("tunnus", tunnus);
            startActivity(intent);
            Toast.makeText(this, "Tallennettu", Toast.LENGTH_SHORT).show();
            overridePendingTransition(
                    R.anim.f_in, R.anim.f_out
            );
        });

        takaisin = findViewById(R.id.takaisin);
        takaisin.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPage.class);
            intent.putExtra("tunnus", tunnus);
            startActivity(intent);
            overridePendingTransition(
                    R.anim.l_in, R.anim.r_out
            );
        });

        koti = findViewById(R.id.koti);
        koti.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainPage.class);
            intent.putExtra("tunnus", tunnus);
            startActivity(intent);
            overridePendingTransition(
                    R.anim.l_in, R.anim.r_out
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

    public void naama(int kohta) {
        if (kohta < -33) {
            image.setImageResource(R.drawable.sad);
        }
        if (kohta >= -33 & kohta <= 33) {
            image.setImageResource(R.drawable.ok);
        }
        if (kohta > 33) {
            image.setImageResource(R.drawable.happy);
        }
    }

    public int dp(float num) {
        float num1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, num, getResources().getDisplayMetrics());
        return Math.round(num1);
    }
}