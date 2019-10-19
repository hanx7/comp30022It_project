package com.example.mem_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mem_app.Utils.SingleItem;
import com.example.mem_app.Utils.UserProfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyItemActivity extends AppCompatActivity {

    HashMap<String, SingleItem> items;
    HashMap<String, SingleItem> userItems = new HashMap<>();
    String userName;
    TextView itemName1;
    TextView itemName2;
    TextView itemName3;
    TextView itemName4;
    int numOfItem;
    SingleItem item1;
    SingleItem item2;
    SingleItem item3;
    SingleItem item4;
    int page = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_category);
        // get the haspmap storing all items info
        userItems = MainActivity.user_profile.getItemHmap();
        userName = getIntent().getExtras().getString("userName");
        for(Map.Entry<String, SingleItem> item : userItems.entrySet()){
            if(item.getValue().getUpload_username().equals(userName)){
                userItems.put(item.getKey(), item.getValue());
            }
        }
        itemName1 = findViewById(R.id.button2);
        itemName2 = findViewById(R.id.button3);
        itemName3 = findViewById(R.id.button4);
        itemName4 = findViewById(R.id.button5);
        init();
    }


    // initailize the show all item page, rendering items depending on how much items we have
    private void init(){
        numOfItem = userItems.size();
        if(numOfItem >= 1){
            item1 = (new ArrayList<SingleItem>(userItems.values())).get(0);
            String item1Name = item1.getItem_name();
            itemName1.setText(item1Name);
        }
        if(numOfItem >= 2){
            item2 = (new ArrayList<SingleItem>(userItems.values())).get(1);
            String item2Name = item2.getItem_name();
            itemName2.setText(item2Name);
        }
        if(numOfItem >= 3){
            item3 = (new ArrayList<SingleItem>(userItems.values())).get(2);
            String item3Name = item3.getItem_name();
            itemName3.setText(item3Name);
        }
        if(numOfItem >= 4){
            item4 = (new ArrayList<SingleItem>(userItems.values())).get(3);
            String item4Name = item4.getItem_name();
            itemName4.setText(item4Name);
        }
        HomeActivity.alert.dismiss();
    }

    // when image button is clicked on, first to check if the button stores item inside it
    // if yes then render the item info, otherwise do nothing
    public void onItemButton1Click(View view){
        int num = numOfItem - (page-1)*4;
        if(num > 0){
            Intent i = new Intent(this, ViewSingleItemActivity.class);
            i.putExtra("itemID", item1.getItemID());
            startActivity(i);
        }
    }


    public void onItemButton2Click(View view){
        int num = numOfItem - (page-1)*4;
        if(num > 1){
            Intent i = new Intent(this, ViewSingleItemActivity.class);
            i.putExtra("itemID", item2.getItemID());
            startActivity(i);
        }
    }

    public void onItemButton3Click(View view){
        int num = numOfItem - (page-1)*4;
        if(num > 2){
            Intent i = new Intent(this, ViewSingleItemActivity.class);
            i.putExtra("itemID", item3.getItemID());
            startActivity(i);
        }
    }

    public void onItemButton4Click(View view){
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
            item1 = (new ArrayList<SingleItem>(userItems.values())).get((page - 1)*4);
            String item1Name = item1.getItem_name();
            itemName1.setText(item1Name);

            item2 = (new ArrayList<SingleItem>(userItems.values())).get((page - 1)*4+1);
            String item2Name = item2.getItem_name();
            itemName2.setText(item2Name);

            item3 = (new ArrayList<SingleItem>(userItems.values())).get((page - 1)*4+2);
            String item3Name = item3.getItem_name();
            itemName3.setText(item3Name);

            item4 = (new ArrayList<SingleItem>(userItems.values())).get((page - 1)*4+3);
            String item4Name = item4.getItem_name();
            itemName4.setText(item4Name);
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
                item1 = (new ArrayList<SingleItem>(userItems.values())).get(page*4);
                String item1Name = item1.getItem_name();
                itemName1.setText(item1Name);
                imageShown++;
            }
            if(numOfItem > page*4+1){
                item2 = (new ArrayList<SingleItem>(userItems.values())).get(page*4);
                String item2Name = item2.getItem_name();
                itemName2.setText(item2Name);
                imageShown++;
            }
            if(numOfItem > page*4+2){
                item3 = (new ArrayList<SingleItem>(userItems.values())).get(page*4+2);
                String item3Name = item3.getItem_name();
                itemName3.setText(item3Name);
                imageShown++;
            }
            if(numOfItem > page*4+3){
                item4 = (new ArrayList<SingleItem>(userItems.values())).get(page*4+3);
                String item1Name = item4.getItem_name();
                itemName4.setText(item1Name);
                imageShown++;
            }
            if(imageShown == 3){
                itemName4.setText("");
            }
            if(imageShown == 2){

                itemName4.setText("");

                itemName3.setText("");
            }
            if(imageShown == 1){

                itemName4.setText("");

                itemName3.setText("");

                itemName2.setText("");
            }
            page++;
        }else{
            popAlert("No more items! ");
        }

    }

    public void onBackButtonClick(View view){

        Intent i = new Intent(this, UserInformationActivity.class);
        startActivity(i);
    }


    public void onMainButtonClick(View view){

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    // this function is to pop a alert message inside a box
    private void popAlert(String text){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        AlertDialog ad = alertDialogBuilder.create();
        ad.setMessage(text);
        ad.show();
    }
}
