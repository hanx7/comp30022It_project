package com.example.mem_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.lang.String;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import java.util.HashMap;
import com.example.mem_app.Utils.SingleItem;

public class ViewAllItemsActivity extends AppCompatActivity {
    // items is a hash map which keeps item id as key and the item instance as value
    HashMap<String, SingleItem> items;
    // image view 1 to 4
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    // textview 1 to 4 are to show item name
    TextView userName1;
    TextView userName2;
    TextView userName3;
    TextView userName4;
    // 4 items correspond to items are going to show in 4 image view
    SingleItem item1;
    SingleItem item2;
    SingleItem item3;
    SingleItem item4;
    // track number of items
    int numOfItem;
    // record which page are we showing now
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Show login page
        setContentView(R.layout.viewitem);
        image1 = (ImageView)findViewById(R.id.viewitemImage1);
        image2 = (ImageView)findViewById(R.id.viewitemImage2);
        image3 = (ImageView)findViewById(R.id.viewitemImage3);
        image4 = (ImageView)findViewById(R.id.viewitemImage4);
        userName1 = (TextView)findViewById(R.id.viewitemUsername1);
        userName2 = (TextView)findViewById(R.id.viewitemUsername2);
        userName3 = (TextView)findViewById(R.id.viewitemUsername3);
        userName4 = (TextView)findViewById(R.id.viewitemUsername4);
        items = MainActivity.user_profile.getItemHmap();
        init();

    }

    // initailize the show all item page, rendering items depending on how much items we have
    private void init(){
        numOfItem = items.size();
        if(numOfItem >= 1){
            item1 = (new ArrayList<SingleItem>(items.values())).get(0);
            String imageString1 = item1.getImage_string();
            image1.setImageBitmap(StringToBitMap(imageString1));
            String userName = item1.getItem_name();
            userName1.setText(userName);
        }
        if(numOfItem >= 2){
            item2 = (new ArrayList<SingleItem>(items.values())).get(1);
            String imageString2 = item2.getImage_string();
            image2.setImageBitmap(StringToBitMap(imageString2));
            String userName = item2.getItem_name();
            userName2.setText(userName);
        }
        if(numOfItem >= 3){
            item3 = (new ArrayList<SingleItem>(items.values())).get(2);
            String imageString3 = item3.getImage_string();
            image3.setImageBitmap(StringToBitMap(imageString3));
            String userName = item3.getItem_name();
            userName3.setText(userName);
        }
        if(numOfItem >= 4){
            item4 = (new ArrayList<SingleItem>(items.values())).get(3);
            String imageString4 = item4.getImage_string();
            image4.setImageBitmap(StringToBitMap(imageString4));
            String userName = item4.getItem_name();
            userName4.setText(userName);
        }
        HomeActivity.alert.dismiss();
    }



    // when image button is clicked on, first to check if the button stores item inside it
    // if yes then render the item info, otherwise do nothing
    public void onImgButton1Click(View view){
        int num = numOfItem - (page-1)*4;
        if(num > 0){
            Intent i = new Intent(this, ViewSingleItemActivity.class);
            i.putExtra("itemID", item1.getItemID());
            startActivity(i);
        }
    }


    public void onImgButton2Click(View view){
        int num = numOfItem - (page-1)*4;
        if(num > 1){
            Intent i = new Intent(this, ViewSingleItemActivity.class);
            i.putExtra("itemID", item2.getItemID());
            startActivity(i);
        }
    }

    public void onImgButton3Click(View view){
        int num = numOfItem - (page-1)*4;
        if(num > 2){
            Intent i = new Intent(this, ViewSingleItemActivity.class);
            i.putExtra("itemID", item3.getItemID());
            startActivity(i);
        }
    }

    public void onImgButton4Click(View view){
        int num = numOfItem - (page-1)*4;
        if(num > 3){
            Intent i = new Intent(this, ViewSingleItemActivity.class);
            i.putExtra("itemID", item4.getItemID());
            startActivity(i);
        }
    }

    // when previous button is clicked on, first to check if we have enough items that can be shown
    // if yes then render the previous 4 items, otherwise pop up a warning message saying no more
    // items
    public void onPreviousButtonClick(View view){
        if(page > 1){
            page--;
            item1 = (new ArrayList<SingleItem>(items.values())).get((page - 1)*4);
            String imageString1 = item1.getImage_string();
            image1.setImageBitmap(StringToBitMap(imageString1));
            String userName = item1.getItem_name();
            userName1.setText(userName);

            item2 = (new ArrayList<SingleItem>(items.values())).get((page - 1)*4+1);
            imageString1 = item2.getImage_string();
            image2.setImageBitmap(StringToBitMap(imageString1));
            userName = item2.getItem_name();
            userName2.setText(userName);

            item3 = (new ArrayList<SingleItem>(items.values())).get((page - 1)*4+2);
            imageString1 = item3.getImage_string();
            image3.setImageBitmap(StringToBitMap(imageString1));
            userName = item3.getItem_name();
            userName3.setText(userName);

            item4 = (new ArrayList<SingleItem>(items.values())).get((page - 1)*4+3);
            imageString1 = item4.getImage_string();
            image4.setImageBitmap(StringToBitMap(imageString1));
            userName = item4.getItem_name();
            userName4.setText(userName);
        }else{
            popAlert("No more items! ");
        }
    }


    // when next page button is clicked on, first to decide how many items can be shown in the next
    // page, and if there are no more items just pop up a warning message
    public void onNextButtonClick(View view){
        if(numOfItem > page*4){
            int imageShown = 0;
            if(numOfItem > page*4){
                item1 = (new ArrayList<SingleItem>(items.values())).get(page*4);
                String imageString1 = item1.getImage_string();
                image1.setImageBitmap(StringToBitMap(imageString1));
                String userName = item1.getItem_name();
                userName1.setText(userName);
                imageShown++;
            }
            if(numOfItem > page*4+1){
                item2 = (new ArrayList<SingleItem>(items.values())).get(page*4+1);
                String imageString1 = item2.getImage_string();
                image2.setImageBitmap(StringToBitMap(imageString1));
                String userName = item2.getItem_name();
                userName2.setText(userName);
                imageShown++;
            }
            if(numOfItem > page*4+2){
                item3 = (new ArrayList<SingleItem>(items.values())).get(page*4+2);
                String imageString1 = item3.getImage_string();
                image3.setImageBitmap(StringToBitMap(imageString1));
                String userName = item3.getItem_name();
                userName3.setText(userName);
                imageShown++;
            }
            if(numOfItem > page*4+3){
                item4 = (new ArrayList<SingleItem>(items.values())).get(page*4+3);
                String imageString1 = item4.getImage_string();
                image4.setImageBitmap(StringToBitMap(imageString1));
                String userName = item4.getItem_name();
                userName4.setText(userName);
                imageShown++;
            }
            if(imageShown == 3){
                image4.setImageResource(R.mipmap.ic_launcher);
                userName4.setText("");
            }
            if(imageShown == 2){
                image4.setImageResource(R.mipmap.ic_launcher);
                userName4.setText("");
                image3.setImageResource(R.mipmap.ic_launcher);
                userName3.setText("");
            }
            if(imageShown == 1){
                image4.setImageResource(R.mipmap.ic_launcher);
                userName4.setText("");
                image3.setImageResource(R.mipmap.ic_launcher);
                userName3.setText("");
                image2.setImageResource(R.mipmap.ic_launcher);
                userName2.setText("");
            }
            page++;
        }else{
            popAlert("No more items! ");
        }

    }

    // convert a string message into bitmap which can be shown as a image later
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

    // this function is to pop a alert message inside a box
    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }

    // when the main button click on, goes back to main page!
    public void onMainButtonClick(View view){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}
