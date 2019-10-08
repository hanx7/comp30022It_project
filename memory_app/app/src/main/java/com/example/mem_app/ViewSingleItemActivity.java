package com.example.mem_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.String;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mem_app.Utils.SingleItem;

import static com.example.mem_app.EditItemActivity.context;

public class ViewSingleItemActivity extends AppCompatActivity {

    ImageView itemImage;
    TextView itemName;
    TextView itemDescription;
    public static String currentItemID;
    public static String currentItemName;
    public static Bitmap imageBitmap;
    public static CharSequence itemNameText;
    public static CharSequence itemDescriptionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.item_info);
        itemImage = (ImageView)findViewById(R.id.itemInfoImage);
        itemName = (TextView)findViewById(R.id.itemInfoName);
        itemDescription = (TextView)findViewById(R.id.itemInfoStory);
        String itemID = getIntent().getExtras().getString("itemID");

        SingleItem item = MainActivity.user_profile.getItems().get(itemID);
        imageBitmap = StringToBitMap(item.getImage_string());
        itemNameText = item.getItem_name();
        itemDescriptionText = item.getItem_description();
        itemImage.setImageBitmap(imageBitmap);
        itemName.setText(itemNameText);
        itemDescription.setText(itemDescriptionText);
        currentItemID = itemID;
        currentItemName = item.getItem_name();
    }



    private Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public void onGoToEventButtonClick(View view){
        Intent i = new Intent(this, EventCategoryActivity.class);
        startActivity(i);
    }

    public void onEditButtonClick(View view) {
        Intent i = new Intent(this, EditItemActivity.class);
        startActivity(i);
    }

    public void onDeleteButtonClick(View view) {
        final CharSequence[] options = { "Confirm","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleItemActivity.this);
        builder.setTitle("Do you want to delete this item?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Confirm"))
                {
                    final String user_name = MainActivity.user_profile.user_name;
                    final String user_pwd = MainActivity.user_profile.user_pwd;
                    final String item_ID = ViewSingleItemActivity.currentItemID;
                    String resp = MainActivity.processor.deleteItemHttpSend(
                            user_name,
                            user_pwd,
                            item_ID);
                    Log.v("see res", resp);
                    if (resp.equals("###DELETE_ITEM_SUCCESS###")) {
                        final CharSequence[] options = { "OK" };
                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleItemActivity.this);
                        builder.setTitle("Success!");
                        builder.setItems(options, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("OK")) {
                                    dialog.dismiss();
                                    //Intent i = new Intent(this, ViewSingleItemActivity.class);
                                    Intent i = new Intent(HomeActivity.context, HomeActivity.class);
                                    startActivity(i);
                                }
                            }
                        });
                        builder.show();
                    }
                    else if (resp.equals("###DELETE_ITEM_FAILED###")){
                        final CharSequence[] options = { "Cancel" };
                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSingleItemActivity.this);
                        builder.setTitle("Failure!");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("Cancel")) {
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.show();
                    }
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

}
