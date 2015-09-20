package com.techstomach.hospitalconnect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.avast.android.dialogs.fragment.SimpleDialogFragment;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import me.drakeet.materialdialog.MaterialDialog;

public class SplashScreenActivity extends Activity {

    MaterialDialog mMaterialDialog;
    String now_playing, earned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setBackgroundResource(R.drawable.splash_image_5);

        /**
         * Showing splashscreen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */
        new PrefetchData().execute();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                final Intent mainIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
//                SplashScreenActivity.this.startActivity(mainIntent);
//                SplashScreenActivity.this.finish();
//            }
//        }, 50000);

//        ImageView rocketImage = (ImageView) findViewById(R.id.imageView);
//        rocketImage.setImageResource(R.drawable.background);

//        AnimationDrawable rocketAnimation = (AnimationDrawable) imageView.getBackground();
//        rocketAnimation.start();
    }


    private class PrefetchData extends AsyncTask<Void, Void, Void>
    {
        private final ProgressDialog dialog = new ProgressDialog(SplashScreenActivity.this);
        private boolean connectError = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("MYLOG", "onPreExecute....");

            this.dialog.setMessage("Loading...");
            this.dialog.show();
            // before making http calls
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            Log.i("MYLOG", "doInBackground....");

            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */

            String json = getJSONFromUrl("http://api.androidhive.info/game/game_stats.json");
            Log.e("MYLOG: ", "> " + json);

            if (json != null)
            {
                try
                {
                    JSONObject jObj = new JSONObject(json).getJSONObject("game_stat");
                    now_playing = jObj.getString("now_playing");
                    earned = jObj.getString("earned");

                    Log.e("MYLOG: JSON", "> " + now_playing + earned);
                    return null;
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

            connectError = true;
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            Log.i("MYLOG", "onPostExecute....");

            // After completing http call
            // will close this activity and lauch main activity
            if(connectError == false)
            {
                if (this.dialog.isShowing())
                {
                    this.dialog.dismiss();
                }

                Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                i.putExtra("now_playing", now_playing);
                i.putExtra("earned", earned);
                startActivity(i);

                // close this activity
                finish();
            }
            else
            {
                if (this.dialog.isShowing())
                {
                    this.dialog.dismiss();
                }

                NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(SplashScreenActivity.this);

                dialogBuilder
                        .withTitle("ERROR")
                        .withMessage(R.string.no_internet_message)
                        .withButton1Text("CLOSE")
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SplashScreenActivity.this.finish();
//                                Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .withDialogColor("#F8F8FF")
                        .show();
//                SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setMessage(R.string.no_internet_message).show();
//                this.dialog.setMessage("Internet connection error...");
//                this.dialog.show();
            }
        }

        public String getJSONFromUrl(String url_address)
        {
//            InputStream is = null;
//            JSONObject jObj = null;
//            String json = "";

            // make HTTP request
            try
            {
                Log.i("MYLOG", "getJSONFromUrl, url = " + url_address);
                URL url = new URL(url_address);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

                InputStream in = new BufferedInputStream(conn.getInputStream());
                String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
                Log.i("MYLOG", "response = " + response);

                return response;
//                jObj = new JSONObject(response);
//                return jObj;
            }
            catch (Exception e) {
                e.printStackTrace();
                Log.i("MYLOG", "exception received = " + e.toString());
            }

            return null;

//            try {
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
//                StringBuilder sb = new StringBuilder();
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//                is.close();
//                json = sb.toString();
//
//            } catch (Exception e) {
//                Log.e(TAG, "Error converting result " + e.toString());
//            }
//
//            // try parse the string to a JSON object
//            try {
//                jObj = new JSONObject(json);
//            } catch (JSONException e) {
//                Log.e(TAG, "Error parsing data " + e.toString());
//            }
//
//            // return JSON String
//            return jObj;
        }
    }


//    public void init(View v) {
//        mMaterialDialog = new MaterialDialog(this);
//
//        Toast.makeText(getApplicationContext(), "Initializes successfully.", Toast.LENGTH_SHORT).show();
//    }

//    public void show(View v) {
//        if (mMaterialDialog != null) {
//            mMaterialDialog.setTitle("MaterialDialog")
//                    .setMessage(
//                            "Hi! This is a MaterialDialog. It's very easy to use, you just new and show() it " +
//                                    "then the beautiful AlertDialog will show automatedly. It is artistic, conforms to Google Material Design." +
//                                    " I hope that you will like it, and enjoy it. ^ ^"
//                    )
//                            //mMaterialDialog.setBackgroundResource(R.drawable.background);
//                    .setPositiveButton(
//                            "OK", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    mMaterialDialog.dismiss();
//                                    Toast.makeText(SplashScreenActivity.this, "Ok", Toast.LENGTH_LONG).show();
//
//                                }
//                            }
//                    )
//                    .setNegativeButton(
//                            "CANCLE", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    mMaterialDialog.dismiss();
//                                    Toast.makeText(SplashScreenActivity.this, "Cancle", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                    )
//                    .setCanceledOnTouchOutside(false)
//                            // You can change the message anytime.
//                            // mMaterialDialog.setTitle("提示");
//                    .setOnDismissListener(
//                            new DialogInterface.OnDismissListener() {
//                                @Override
//                                public void onDismiss(DialogInterface dialog) {
//                                    Toast.makeText(SplashScreenActivity.this, "onDismiss", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                    )
//                    .show();
//            // You can change the message anytime.
//            // mMaterialDialog.setMessage("嗨！这是一个 MaterialDialog. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^");
//        } else {
//            Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    static int i = 0;

//    public void setView(View v) {
//        switch (v.getId()) {
//            case R.id.button_set_view: {
//                mMaterialDialog = new MaterialDialog(this);
//                if (mMaterialDialog != null) {
//                    View view = LayoutInflater.from(this).inflate(R.layout.progressbar_item, null);
//                    mMaterialDialog.setView(view).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
//                }
//            }
//            break;
//            case R.id.button_set_background: {
//                mMaterialDialog = new MaterialDialog(this);
//                if (mMaterialDialog != null) {
//                    if (i % 2 != 0) {
//                        mMaterialDialog.setBackgroundResource(R.drawable.background);
//                    } else {
//                        Resources res = getResources();
//                        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.background2);
//                        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bmp);
//                        mMaterialDialog.setBackground(bitmapDrawable);
//                    }
//                    mMaterialDialog.setCanceledOnTouchOutside(true).show();
//                    i++;
//                    Toast.makeText(getApplicationContext(), "Try to click again~", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            }
//            case R.id.button_set_contentView: {
//                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                        this,
//                        android.R.layout.simple_list_item_1
//                );
//                arrayAdapter.add("This is item 0");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                arrayAdapter.add("This is item 1");
//                ListView listView = new ListView(this);
//                listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                float scale = getResources().getDisplayMetrics().density;
//                int dpAsPixels = (int) (8 * scale + 0.5f);
//                listView.setPadding(0, dpAsPixels, 0, dpAsPixels);
//                listView.setDividerHeight(0);
//                listView.setAdapter(arrayAdapter);
//
//                final MaterialDialog alert = new MaterialDialog(this)
//                        .setTitle("MaterialDialog")
//                        .setContentView(listView);
//
//                alert.setPositiveButton(
//                        "OK", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                alert.dismiss();
//                            }
//                        }
//                );
//
//                alert.show();
//                break;
//            }
//            case R.id.button_set_notitile: {
//                final MaterialDialog materialDialog = new MaterialDialog(this);
//                materialDialog.setMessage("This is a dialog without title. This is a dialog without title. This is a dialog without title. This is a dialog without title. This is a dialog without title. ")
//                        .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                materialDialog.dismiss();
//                            }
//                        });
//                materialDialog.show();
//            }
//        }
//    }
//
//    public void buttonPress(View view) {
//        // show imm
//        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(
//                InputMethodManager.SHOW_FORCED,
//                InputMethodManager.HIDE_IMPLICIT_ONLY
//        );
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
