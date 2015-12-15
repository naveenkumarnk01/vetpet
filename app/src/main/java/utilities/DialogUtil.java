package utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.vetpet.R;

/**
 * Created by Naveen on 15-Dec-15.
 */
public class DialogUtil {
    private static Context context;
    private static LayoutInflater layoutinflater;
    private static Activity appactivity;
    private static AlertDialog alertRequestDesign;
    public static void showCustomAlertDialog(Context ctx, String msg, final int type,
    LayoutInflater infltr,Activity activity){
        try {
            context = ctx;
            layoutinflater = infltr;
            appactivity = activity;
            AlertDialog.Builder builder;
            // AlertDialog alertDialog;

//            Typeface tfNormal = Typeface.createFromAsset(context.getAssets(),
//                    "fonts/DroidSans.ttf");

            Context mContext = context;
            // LayoutInflater inflater = (LayoutInflater) mContext
            // .getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = layoutinflater.inflate(
                    R.layout.custom_alert_yes_or_no,
                    (ViewGroup) appactivity.findViewById(R.id.containerId));
            // final ImageView imageCard = (ImageView) layout
            // .findViewById(R.id.imageError);
            final TextView textMessage = (TextView) layout
                    .findViewById(R.id.textMessage);
            // final ImageView imageError = (ImageView) layout
            // .findViewById(R.id.warning_symbId);
            // final View circleview = (View)
            // layout.findViewById(R.id.circleviewId);
            final Button confirm = (Button) layout.findViewById(R.id.buttonYes);
            final Button cancel = (Button) layout.findViewById(R.id.buttonNo);
            // textMessage.setTypeface(tfNormal);
//            confirm.setTypeface(tfNormal);
//            cancel.setTypeface(tfNormal);
            if (type == 1) {
                confirm.setText("Settings");
                cancel.setText("Ignore");
            }
            if (type == 2) {
                confirm.setText("Settings");
                cancel.setText("Ignore");
            }
            if (type == 0) {
                cancel.setText("Ok");
                confirm.setVisibility(View.GONE);
            }
            if (type == 3) {
                confirm.setText("Update");
                cancel.setText("Later");
            }
            textMessage.setText(msg);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inScaled = false;
            options.inPurgeable = true;
            options.inSampleSize = 8;

            cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (alertRequestDesign != null) {
                        alertRequestDesign.dismiss();
                    }
                }
            });

            confirm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    if (alertRequestDesign != null) {
                        alertRequestDesign.dismiss();
                    }
                    if (type == 0) {
                        ((Activity) context).finish();
                    } else if (type == 1) {
                        Intent netOptionsIntent = new Intent(
                                android.provider.Settings.ACTION_SETTINGS);
                        context.startActivity(netOptionsIntent);
                    } else if (type == 2) {
                        Intent gpsOptionsIntent = new Intent(
                                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(gpsOptionsIntent);

                    }

                }
            });

            builder = new AlertDialog.Builder(mContext);
            builder.setView(layout);
            alertRequestDesign = builder.create();
            alertRequestDesign.show();
            alertRequestDesign.setOnKeyListener(new Dialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    // TODO Auto-generated method stub
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        // ((Activity) context).finish();
                        alertRequestDesign.dismiss();
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
