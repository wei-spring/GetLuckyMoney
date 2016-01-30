package me.chunsheng.hongbao.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.chunsheng.hongbao.R;
import me.chunsheng.hongbao.utils.EditInfoDialog;

public class LuckyMoneyActivity extends Activity {
    private final Intent mAccessibleIntent =
            new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);

    private me.chunsheng.hongbao.utils.CircleImageView iv_luckymoney_from;
    private me.chunsheng.hongbao.utils.CircleImageView iv_001;
    private TextView tv_001;
    private View view_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luckymoney);

        iv_luckymoney_from = (me.chunsheng.hongbao.utils.CircleImageView) findViewById(R.id.iv_luckymoney_from);
        iv_001 = (me.chunsheng.hongbao.utils.CircleImageView) findViewById(R.id.iv_001);
        tv_001 = (TextView) findViewById(R.id.tv_001);
        view_share = findViewById(R.id.view_share);
        view_share.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bitmap bm = takeScreenShot(LuckyMoneyActivity.this);
                savePic(bm, "shareLuckyMoney");
                String imagePath = Environment.getExternalStorageDirectory() + File.separator + "shareLuckyMoney.png";
                //由文件得到uri
                Uri imageUri = Uri.fromFile(new File(imagePath));
                share("哈哈,我刚领取的红包,装一下逼...", imageUri);
                return false;
            }
        });
        handleMIUIStatusBar();
        explicitlyLoadPreferences();
    }

    private void explicitlyLoadPreferences() {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    /**
     * 适配MIUI沉浸状态栏
     */
    private void handleMIUIStatusBar() {
        Window window = getWindow();

        Class clazz = window.getClass();
        try {
            int tranceFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");

            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            tranceFlag = field.getInt(layoutParams);

            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, tranceFlag, tranceFlag);

            ImageView placeholder = (ImageView) findViewById(R.id.main_actiob_bar_placeholder);
            int placeholderHeight = getStatusBarHeight();
            placeholder.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, placeholderHeight));
        } catch (Exception e) {
            // Do nothing
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void performBack(View view) {
        super.onBackPressed();
    }

    public void showLuckyMoneyLog(View view) {
        Toast.makeText(this, "哇哦,红包太大,打不开红包记录了｡◕‿◕｡", Toast.LENGTH_SHORT).show();
    }

    public void editLuckyMoneyFrom(final View view) {
        EditInfoDialog editInfoDialog = new EditInfoDialog(this, R.style.customdialog_exchange_style, R.layout.edit_dialog);
        editInfoDialog.show();
        editInfoDialog
                .setYourListener(new EditInfoDialog.ExchangeDialogButtonListener() {

                    @Override
                    public void setExchangeCoupon(String couponCode) {
                        ((TextView) view).setText(couponCode);
                    }
                });
    }

    public void editLuckyMoneyTitle(final View view) {
        EditInfoDialog editInfoDialog = new EditInfoDialog(this, R.style.customdialog_exchange_style, R.layout.edit_dialog);
        editInfoDialog.show();
        editInfoDialog
                .setYourListener(new EditInfoDialog.ExchangeDialogButtonListener() {

                    @Override
                    public void setExchangeCoupon(String couponCode) {
                        ((TextView) view).setText(couponCode);
                    }
                });
    }

    public void editLuckyMoneyTo(final View view) {
        EditInfoDialog editInfoDialog = new EditInfoDialog(this, R.style.customdialog_exchange_style, R.layout.edit_dialog);
        editInfoDialog.show();
        editInfoDialog
                .setYourListener(new EditInfoDialog.ExchangeDialogButtonListener() {

                    @Override
                    public void setExchangeCoupon(String couponCode) {
                        ((TextView) view).setText(couponCode);
                    }
                });
    }

    public void editLuckyMoneyGetTime(final View view) {
        EditInfoDialog editInfoDialog = new EditInfoDialog(this, R.style.customdialog_exchange_style, R.layout.edit_dialog);
        editInfoDialog.show();
        editInfoDialog
                .setYourListener(new EditInfoDialog.ExchangeDialogButtonListener() {

                    @Override
                    public void setExchangeCoupon(String couponCode) {
                        ((TextView) view).setText(couponCode);
                    }
                });
    }

    public void editLuckyMoneyCount(final View view) {
        EditInfoDialog editInfoDialog = new EditInfoDialog(this, R.style.customdialog_exchange_style, R.layout.edit_dialog);
        editInfoDialog.show();
        editInfoDialog
                .setYourListener(new EditInfoDialog.ExchangeDialogButtonListener() {

                    @Override
                    public void setExchangeCoupon(String couponCode) {
                        ((TextView) view).setText(couponCode);
                        tv_001.setText("1个红包共" + couponCode + ",5秒被抢光");
                    }
                });
    }

    public void editLuckyMoneyFromIcon(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 100);
    }

    public void editLuckyMoneyToIcon(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 101);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                if (data != null) {
                    startPhotoZoom(data.getData(), 200);
                    //iv_luckymoney_from.setImageURI(data.getData());
                }
            }
            if (requestCode == 101) {
                if (data != null)
                    startPhotoZoom(data.getData(), 201);
                //iv_001.setImageURI(data.getData());
            }
            if (requestCode == 200) {
                if (data != null) {
                    Bitmap bm = data.getParcelableExtra("data");
                    iv_luckymoney_from.setImageBitmap(bm);
                }
            }
            if (requestCode == 201) {
                if (data != null) {
                    Bitmap bm = data.getParcelableExtra("data");
                    iv_001.setImageBitmap(bm);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void startPhotoZoom(Uri uri, int flag) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, flag);
    }

    public int getStatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) return getResources().getDimensionPixelSize(resourceId);
        return 0;
    }

    // 获取指定Activity的截屏，保存到png文件
    private static Bitmap takeScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // 去掉标题栏
        // Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    // 保存到sdcard
    private void savePic(Bitmap b, String strFileName) {
        String imagePath = Environment.getExternalStorageDirectory() + File.separator + "shareLuckyMoney.png";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imagePath);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void share(String content, Uri uri) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if (uri != null) { //uri 是图片的地址
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/*");
            //当用户选择短信时使用sms_body取得文字
            shareIntent.putExtra("sms_body", content);
        } else {
            shareIntent.setType("text/plain");
        }
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        //自定义选择框的标题
        startActivity(Intent.createChooser(shareIntent, "邀请好友"));
        //系统默认标题
        startActivity(shareIntent);
    }
}
