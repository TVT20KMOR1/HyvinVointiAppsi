package mobiili.projekti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataBase extends SQLiteOpenHelper {

    public static final String HVDB = "Hv.db";

    public DataBase(Context context) {
        super(context, HVDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(tunnus TEXT primary key, salasana TEXT)");
        MyDB.execSQL("create Table quotes(quote TEXT primary key)");
        MyDB.execSQL("create Table vesi(aika datetime primary key, tunnus TEXT, vesiml INT)");
        MyDB.execSQL("create Table fiilis(aika datetime primary key, tunnus TEXT, fiilis INT)");
        MyDB.execSQL("create Table uni(aika datetime primary key, tunnus TEXT,tunnit int, minuutit INT)");
        MyDB.execSQL("create Table jumppa(aika datetime primary key, tunnus TEXT,liikuttuh int, liikuttumin INT)");
        MyDB.execSQL("create Table tehtavalista(numero int ,aika datetime primary key, tunnus TEXT, tehtava TEXT, tehty int)");
        MyDB.execSQL("create Table paivakirja(numero int ,aika datetime primary key, tunnus TEXT, merkinta TEXT)");

        MyDB.execSQL("create Table asetukset(tunnus TEXT primary key, vesi int, uni int, fiilis int, tehtava int, paivakirja int, uusi1 int, uusi2 int)");
        MyDB.execSQL("create Table vesimuisti(tunnus TEXT primary key, vesitavoite int, vesijuodaan int)");
        MyDB.execSQL("create Table unimuisti(tunnus TEXT primary key, tavoiteh int, tavoitemin int, nukuttuh int, nukuttumin int)");
        MyDB.execSQL("create Table jumppamuisti(tunnus TEXT primary key, liikuttuh int, liikuttumin int)");

        MyDB.execSQL("insert into quotes(quote) values('HyvinVointia paskoihin päiviin!')");
        MyDB.execSQL("insert into quotes(quote) values('Aina voisi mennä huonomminkin')");
        MyDB.execSQL("insert into quotes(quote) values('Kyllä se siitä')");
        MyDB.execSQL("insert into quotes(quote) values('Mitä muuttaisit elämässäsi, jos et pelkäisi mitään?')");
        MyDB.execSQL("insert into quotes(quote) values('Ihminen joka ei tee virheitä, ei tavallisesti tee mitään')");
        MyDB.execSQL("insert into quotes(quote) values('Yksi asia mitä et voi kierrättää, on hukkaan heitetty aika')");
        MyDB.execSQL("insert into quotes(quote) values('On päiviä jolloin täytyy saada pysytellä mukavuusalueellaan')");
        MyDB.execSQL("insert into quotes(quote) values('Tänään on loppuelämäsi ensimmäinen päivä - nauti siitä')");
        MyDB.execSQL("insert into quotes(quote) values('Pyri edistymään, älä täydellisyyteen')");
        MyDB.execSQL("insert into quotes(quote) values('Vaikeat tiet johtavat kauniisiin kohteisiin')");
        MyDB.execSQL("insert into quotes(quote) values('Yritä olla sateenkaari jonkun pilvessä')");
        MyDB.execSQL("insert into quotes(quote) values('Mikään ei korvaa kovaa työtä')");
        MyDB.execSQL("insert into quotes(quote) values('Ota vastaan se loistava sotku, joka olet')");
        MyDB.execSQL("insert into quotes(quote) values('Älä koskaan anna tunteidesi voittaa älykkyyttäsi')");
        MyDB.execSQL("insert into quotes(quote) values('Jos puhut totta, sinun ei tarvitse muistaa mitään')");
        MyDB.execSQL("insert into quotes(quote) values('Toivo on heräävä unelma')");
        MyDB.execSQL("insert into quotes(quote) values('Yksi päivä kerrallaan')");
        MyDB.execSQL("insert into quotes(quote) values('Älä koskaan lopeta unelmoimista')");
        MyDB.execSQL("insert into quotes(quote) values('Tee jotain tänään, josta tuleva itsesi kiittää sinua')");
        //MyDB.execSQL("insert into quotes(quote) values('')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String tunnus, String salasana) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int i = 1, i2 = 2400, i3 = 200, i4 = 8, i5 = 0;
        cv.put("tunnus", tunnus);
        cv.put("salasana", salasana);
        long result = MyDB.insert("users", null, cv);
        cv.clear();
        cv.put("tunnus", tunnus);
        cv.put("vesi", i);
        cv.put("uni", i);
        cv.put("fiilis", i);
        cv.put("tehtava", i);
        cv.put("paivakirja", i);
        cv.put("uusi1", i);
        cv.put("uusi2", i);
        MyDB.insert("asetukset", null, cv);
        cv.clear();
        cv.put("tunnus", tunnus);
        cv.put("vesitavoite", i2);
        cv.put("vesijuodaan", i3);
        MyDB.insert("vesimuisti", null, cv);
        cv.clear();
        cv.put("tunnus", tunnus);
        cv.put("tavoiteh", i4);
        cv.put("tavoitemin", i5);
        cv.put("nukuttuh", i5);
        cv.put("nukuttumin", i5);
        MyDB.insert("unimuisti", null, cv);
        cv.clear();
        cv.put("tunnus", tunnus);
        cv.put("liikuttuh", i5);
        cv.put("liikuttumin", i5);
        MyDB.insert("jumppamuisti", null, cv);
        return result != -1;
    }

    public Boolean checktunnus(String tunnus) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("select * from users where tunnus =?", new String[]{tunnus});
        boolean count = c.getCount() > 0;
        c.close();
        return count;
    }

    public Boolean checksalasana(String tunnus, String salasana) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("select * from users where tunnus=? and salasana=?", new String[]{tunnus, salasana});
        boolean count = c.getCount() > 0;
        c.close();
        return count;
    }

    public ArrayList<String> randomquotelista() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from quotes order by random()", null);
        c.moveToFirst();
        ArrayList<String> quotelista = new ArrayList<>();
        while (!c.isAfterLast()) {
            if ((c != null) && (c.getCount() > 0)) {
                quotelista.add(c.getString(c.getColumnIndexOrThrow("quote")));
                c.moveToNext();
            }
        }
        c.close();
        return quotelista;
    }

    private String getDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return df.format(date);
    }

    public void addFiilis(String tunnus, int fiilis) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("fiilis", fiilis);
        cv.put("aika", getDateTime());
        MyDB.insert("fiilis", null, cv);
    }

    public ArrayList<String> getFiilisToday(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now', 'start of day')", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> fiilisToday = new ArrayList<>();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                fiilisToday.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        c.close();
        return fiilisToday;
    }

    public ArrayList<String> getFiilisHistoria(String tunnus) {
        int num = 0;
        ArrayList<String> historia = new ArrayList<>();
        ArrayList<String> d1 = new ArrayList<>();
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now','start of day','-1 days') and aika < datetime('now','start of day')", new String[]{tunnus});
        c.moveToFirst();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                d1.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            num += Integer.parseInt(d1.get(i));
        }
        if (d1.size() > 0) {
            historia.add(Integer.toString(num / d1.size()));
        }
        d1.clear();
        c.close();
        c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now','start of day','-2 days') and aika < datetime('now','start of day','-1 days')", new String[]{tunnus});
        c.moveToFirst();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                d1.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            num += Integer.parseInt(d1.get(i));
        }
        if (d1.size() > 0) {
            historia.add(Integer.toString(num / d1.size()));
        }
        d1.clear();
        c.close();
        c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now','start of day','-3 days') and aika < datetime('now','start of day','-2 days')", new String[]{tunnus});
        c.moveToFirst();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                d1.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            num += Integer.parseInt(d1.get(i));
        }
        if (d1.size() > 0) {
            historia.add(Integer.toString(num / d1.size()));
        }
        d1.clear();
        c.close();
        c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now','start of day','-4 days') and aika < datetime('now','start of day','-3 days')", new String[]{tunnus});
        c.moveToFirst();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                d1.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            num += Integer.parseInt(d1.get(i));
        }
        if (d1.size() > 0) {
            historia.add(Integer.toString(num / d1.size()));
        }
        d1.clear();
        c.close();
        c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now','start of day','-5 days') and aika < datetime('now','start of day','-4 days')", new String[]{tunnus});
        c.moveToFirst();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                d1.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            num += Integer.parseInt(d1.get(i));
        }
        if (d1.size() > 0) {
            historia.add(Integer.toString(num / d1.size()));
        }
        d1.clear();
        c.close();
        c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now','start of day','-6 days') and aika < datetime('now','start of day','-5 days')", new String[]{tunnus});
        c.moveToFirst();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                d1.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            num += Integer.parseInt(d1.get(i));
        }
        if (d1.size() > 0) {
            historia.add(Integer.toString(num / d1.size()));
        }
        d1.clear();
        c.close();
        c = MyDB.rawQuery("select * from fiilis where tunnus =? and aika > datetime(" +
                "'now','start of day','-7 days') and aika < datetime('now','start of day','-6 days')", new String[]{tunnus});
        c.moveToFirst();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                d1.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
                c.moveToNext();
            }
        }
        for (int i = 0; i < d1.size(); i++) {
            num += Integer.parseInt(d1.get(i));
        }
        if (d1.size() > 0) {
            historia.add(Integer.toString(num / d1.size()));
        }
        d1.clear();
        c.close();
        return historia;
    }

    public void addVesi(String tunnus, int vesiml) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("vesiml", vesiml);
        cv.put("aika", getDateTime());
        MyDB.insert("vesi", null, cv);
    }

    public ArrayList<String> getVesiToday(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from vesi where tunnus =? and aika > datetime(" +
                "'now', 'start of day')", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> vesiToday = new ArrayList<>();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                vesiToday.add(c.getString(c.getColumnIndexOrThrow("vesiml")));
                c.moveToNext();
            }
        }
        c.close();
        return vesiToday;
    }

    public void setAsetukset(String tunnus, int vesi, int uni, int fiilis, int tehtava, int paivakirja, int uusi1, int uusi2) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("vesi", vesi);
        cv.put("uni", uni);
        cv.put("fiilis", fiilis);
        cv.put("tehtava", tehtava);
        cv.put("paivakirja", paivakirja);
        cv.put("uusi1", uusi1);
        cv.put("uusi2", uusi2);
        MyDB.replace("asetukset", null, cv);
    }

    public ArrayList<String> getAsetukset(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from asetukset where tunnus =?", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> asetukset = new ArrayList<>();
        asetukset.add(c.getString(c.getColumnIndexOrThrow("vesi")));
        asetukset.add(c.getString(c.getColumnIndexOrThrow("uni")));
        asetukset.add(c.getString(c.getColumnIndexOrThrow("fiilis")));
        asetukset.add(c.getString(c.getColumnIndexOrThrow("tehtava")));
        asetukset.add(c.getString(c.getColumnIndexOrThrow("paivakirja")));
        asetukset.add(c.getString(c.getColumnIndexOrThrow("uusi1")));
        asetukset.add(c.getString(c.getColumnIndexOrThrow("uusi2")));
        c.close();
        return asetukset;
    }

    public void setVesiMuisti(String tunnus, int vesitavoite, int vesijuodaan) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("vesitavoite", vesitavoite);
        cv.put("vesijuodaan", vesijuodaan);
        MyDB.replace("vesimuisti", null, cv);
    }

    public ArrayList<String> getVesiMuisti(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from vesimuisti where tunnus =?", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> vesimuisti = new ArrayList<>();
        vesimuisti.add(c.getString(c.getColumnIndexOrThrow("vesitavoite")));
        vesimuisti.add(c.getString(c.getColumnIndexOrThrow("vesijuodaan")));
        c.close();
        return vesimuisti;
    }

    public void setUniMuisti(String tunnus, int unitavoiteh, int unitavoitemin, int nukuttuh, int nukuttumin) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("tavoiteh", unitavoiteh);
        cv.put("tavoitemin", unitavoitemin);
        cv.put("nukuttuh", nukuttuh);
        cv.put("nukuttumin", nukuttumin);
        MyDB.replace("unimuisti", null, cv);
    }

    public ArrayList<String> getUniMuisti(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from unimuisti where tunnus =?", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> unimuisti = new ArrayList<>();
        unimuisti.add(c.getString(c.getColumnIndexOrThrow("tavoiteh")));
        unimuisti.add(c.getString(c.getColumnIndexOrThrow("tavoitemin")));
        unimuisti.add(c.getString(c.getColumnIndexOrThrow("nukuttuh")));
        unimuisti.add(c.getString(c.getColumnIndexOrThrow("nukuttumin")));
        c.close();
        return unimuisti;
    }

    public ArrayList<String> getTehtavalista(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from tehtavalista where tunnus =?", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> tehtavalista = new ArrayList<>();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                tehtavalista.add(c.getString(c.getColumnIndexOrThrow("aika")));
                tehtavalista.add(c.getString(c.getColumnIndexOrThrow("numero")));
                tehtavalista.add(c.getString(c.getColumnIndexOrThrow("tehtava")));
                tehtavalista.add(c.getString(c.getColumnIndexOrThrow("tehty")));
                c.moveToNext();
            }
        }
        c.close();
        return tehtavalista;
    }

    public void addTehtavalista(String tunnus, int numero, String tehtava, int tehty) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("numero", numero);
        cv.put("aika", getDateTime());
        cv.put("tehtava", tehtava);
        cv.put("tehty", tehty);
        MyDB.insert("tehtavalista", null, cv);
    }

    public String muokkaaTehtavalista(String tunnus, int numero, int tehty) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tehty", tehty);
        MyDB.update("tehtavalista", cv, "tunnus =? and numero='"
                + numero + "'", new String[]{tunnus});
        String kirja = "muokattu";
        return kirja;
    }

    public ArrayList<String> getPaivakirja(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from paivakirja where tunnus =?", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> paivakirja = new ArrayList<>();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                paivakirja.add(c.getString(c.getColumnIndexOrThrow("aika")));
                paivakirja.add(c.getString(c.getColumnIndexOrThrow("numero")));
                paivakirja.add(c.getString(c.getColumnIndexOrThrow("merkinta")));
                c.moveToNext();
            }
        }
        c.close();
        return paivakirja;
    }

    public void addPaivakirja(String tunnus, int numero, String merkinta) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("aika", getDateTime());
        cv.put("numero", numero);
        cv.put("merkinta", merkinta);
        MyDB.insert("paivakirja", null, cv);
    }

    public String deletePaivakirja(String tunnus, int numero) {
        SQLiteDatabase MyDB = getWritableDatabase();
        MyDB.delete("paivakirja", "tunnus =? and numero ='" + numero + "'", new String[]{tunnus});
        String kirja = "poistettu";
        return kirja;
    }

    public void addUni(String tunnus, int tunnit, int minuutit) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("tunnit", tunnit);
        cv.put("minuutit", minuutit);
        cv.put("aika", getDateTime());
        MyDB.insert("uni", null, cv);
    }

    public ArrayList<String> getUni(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from uni where tunnus =? and aika > datetime(" +
                "'now', 'start of day')", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> uni = new ArrayList<>();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                uni.add(c.getString(c.getColumnIndexOrThrow("tunnit")));
                uni.add(c.getString(c.getColumnIndexOrThrow("minuutit")));
                c.moveToNext();
            }
        }
        c.close();
        return uni;
    }


    public void addJumppa(String tunnus, int tunnit, int minuutit) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("liikuttuh", tunnit);
        cv.put("liikuttumin", minuutit);
        cv.put("aika", getDateTime());
        MyDB.insert("jumppa", null, cv);
    }

    public ArrayList<String> getJumppa(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from jumppa where tunnus =? and aika > datetime(" +
                "'now', 'start of day')", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> jumppa = new ArrayList<>();
        while ((!c.isAfterLast())) {
            if ((c != null) && (c.getCount() > 0)) {
                jumppa.add(c.getString(c.getColumnIndexOrThrow("liikuttuh")));
                jumppa.add(c.getString(c.getColumnIndexOrThrow("liikuttumin")));
                c.moveToNext();
            }
        }
        c.close();
        return jumppa;
    }

    public void setJumppaMuisti(String tunnus, int liikuttuh, int liikuttumin) {
        SQLiteDatabase MyDB = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tunnus", tunnus);
        cv.put("liikuttuh", liikuttuh);
        cv.put("liikuttumin", liikuttumin);
        MyDB.replace("jumppamuisti", null, cv);
    }

    public ArrayList<String> getJumppaMuisti(String tunnus) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor c = MyDB.rawQuery("select * from jumppamuisti where tunnus =?", new String[]{tunnus});
        c.moveToFirst();
        ArrayList<String> jumppamuisti = new ArrayList<>();
        jumppamuisti.add(c.getString(c.getColumnIndexOrThrow("liikuttuh")));
        jumppamuisti.add(c.getString(c.getColumnIndexOrThrow("liikuttumin")));
        c.close();
        return jumppamuisti;
    }
}
