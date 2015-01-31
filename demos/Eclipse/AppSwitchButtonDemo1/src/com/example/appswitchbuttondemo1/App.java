package com.example.appswitchbuttondemo1;

//Lamw: Lazarus Android Module Wizard - Version 0.6 - rev. 13 - 29 January - 2015
//
//https://github.com/jmpessoa/lazandroidmodulewizard

//Android Java Interface for Pascal/Delphi XE5 
//and Lazarus by jmpessoa@hotmail.com - december/2013

//Developers
//          Simon,Choi / Choi,Won-sik
//                       simonsayz@naver.com
//                       http://blog.naver.com/simonsayz
//
//          LoadMan    / Jang,Yang-Ho
//                       wkddidgh@naver.com
//                       http://blog.naver.com/wkddidgh


import java.lang.Override;
import java.lang.reflect.Method;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.pm.ActivityInfo; 
import android.widget.RelativeLayout;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;
//import android.view.View;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

// http://stackoverflow.com/questions/16282294/show-title-bar-from-code
public class App extends Activity {
    private Controls       controls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);                            
     
      //by jmpessoa --- fix for http get    
      //ref. http://stackoverflow.com/questions/8706464/defaulthttpclient-to-androidhttpclient 
     if (android.os.Build.VERSION.SDK_INT > 9) {
         StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
         StrictMode.setThreadPolicy(policy);
     }
     
      //Log.i("jApp","01.Activity.onCreate");
      controls             = new Controls();
      controls.activity    = this; 
      controls.appLayout   = new RelativeLayout(this);
      controls.appLayout.getRootView().setBackgroundColor (0x00FFFFFF);
      controls.screenStyle = controls.jAppOnScreenStyle();
      switch( controls.screenStyle ) {
      	case 1  : this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );  break;
      	case 2  : this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  break;
      	default : ; // Device Default , Rotation by Device
      } 	
      this.setContentView(controls.appLayout);
      this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
      
      
      // Event : Java -> Pascal
      //Log.i("jApp","02.Controls.jAppOnCreate");
      controls.jAppOnCreate(this, controls.appLayout);
      
    }
    
    @Override    
    protected void onNewIntent(Intent intent) { super.onNewIntent(intent);
    	                                        controls.jAppOnNewIntent();     }
    
    @Override
    protected void onDestroy()                { super.onDestroy(); 
    	                                        controls.jAppOnDestroy();       }
    
    @Override
    protected void onPause()                  { super.onPause();
                                                //Log.i("jApp","onPause");
    	                                        controls.jAppOnPause();         }    
    
    @Override
    protected void onRestart()                { super.onRestart();
                                                //Log.i("jApp","onRestart");
    	                                        controls.jAppOnRestart();    	  }

    @Override
    protected void onResume()                 { super.onResume();  
    	                                        controls.jAppOnResume();        }

    @Override
    protected void onStart()                  { super.onStart();
                                                //Log.i("jApp","onStart");
    	                                        controls.jAppOnStart();        }
    @Override
    protected void onStop()                   { super.onStop(); 
    	                                        controls.jAppOnStop();          }

    @Override
    public    void onBackPressed()            { controls.jAppOnBackPressed();   }
    
    @Override
    public    void onConfigurationChanged(Configuration newConfig)
    {
    	super.onConfigurationChanged(newConfig);
    	controls.jAppOnRotate(newConfig.orientation);
    	controls.jAppOnConfigurationChanged();
    }	   	
 
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      controls.jAppOnActivityResult(requestCode,resultCode,data);                                     
    }

   //by jmpessoa: option menu support
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
    	controls.jAppOnCreateOptionsMenu(menu);
        return true;
   }

   /*by jmpessoa: Handles menu item selections */
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      String caption = item.getTitle().toString();
      controls.jAppOnClickOptionMenuItem(item, item.getItemId(), caption, item.isChecked());
      return false;
   }

 //by jmpessoa: context menu support -  Context menu items do not support icons!
   @Override    
   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
      super.onCreateContextMenu(menu, v, menuInfo);
      Log.i("App.Java_onCreateContextMenu", "long_pressed!");
      controls.jAppOnCreateContextMenu(menu);              
   }

   /*by jmpessoa: Handles menu item selections*/
   @Override    
   public boolean onContextItemSelected(MenuItem item) {
   	  String caption = item.getTitle().toString();
   	  controls.jAppOnClickContextMenuItem(item, item.getItemId(), caption, item.isChecked());
      return false;
   }
   
   
   /*by jmpessoa: TODO :Handles prepare menu item*/
   @Override
   public boolean onPrepareOptionsMenu(Menu menu) {
       super.onPrepareOptionsMenu(menu);
       //TODO!!!!
       return true;
   }
   
   /*by jmpessoa: TODO :Handles opened menu */
   @Override     
   public boolean onMenuOpened(int featureId, Menu menu) {
	   //TODO!!!!
     return super.onMenuOpened(featureId, menu);
   }
   
   
}
