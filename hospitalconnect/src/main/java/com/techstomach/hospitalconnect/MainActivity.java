package com.techstomach.hospitalconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import me.drakeet.materialdialog.MaterialDialog;

public class MainActivity extends AppCompatActivity {

    MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setBackgroundResource(R.drawable.splash_image);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(MainActivity.this, HomeActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 5000);

//        ImageView rocketImage = (ImageView) findViewById(R.id.imageView);
//        rocketImage.setImageResource(R.drawable.background);

//        AnimationDrawable rocketAnimation = (AnimationDrawable) imageView.getBackground();
//        rocketAnimation.start();
    }

    public void init(View v) {
        mMaterialDialog = new MaterialDialog(this);

        Toast.makeText(getApplicationContext(), "Initializes successfully.", Toast.LENGTH_SHORT).show();
    }

    public void show(View v) {
        if (mMaterialDialog != null) {
            mMaterialDialog.setTitle("MaterialDialog")
                    .setMessage(
                            "Hi! This is a MaterialDialog. It's very easy to use, you just new and show() it " +
                                    "then the beautiful AlertDialog will show automatedly. It is artistic, conforms to Google Material Design." +
                                    " I hope that you will like it, and enjoy it. ^ ^"
                    )
                            //mMaterialDialog.setBackgroundResource(R.drawable.background);
                    .setPositiveButton(
                            "OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Ok", Toast.LENGTH_LONG).show();

                                }
                            }
                    )
                    .setNegativeButton(
                            "CANCLE", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Cancle", Toast.LENGTH_LONG).show();
                                }
                            }
                    )
                    .setCanceledOnTouchOutside(false)
                            // You can change the message anytime.
                            // mMaterialDialog.setTitle("提示");
                    .setOnDismissListener(
                            new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    Toast.makeText(MainActivity.this, "onDismiss", Toast.LENGTH_SHORT).show();
                                }
                            }
                    )
                    .show();
            // You can change the message anytime.
            // mMaterialDialog.setMessage("嗨！这是一个 MaterialDialog. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^");
        } else {
            Toast.makeText(getApplicationContext(), "You should init firstly!", Toast.LENGTH_SHORT).show();
        }
    }

    static int i = 0;

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
