package com.example.youtubesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity
	implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
    	int id = v.getId();
    	if(id == R.id.btn_search)
    	{
    		String strKeyword = "";
    		EditText edit = (EditText)findViewById(R.id.editKeyword);
    		strKeyword = edit.getText().toString();
    		if(strKeyword.trim().length() > 0)
    		{
    			//結果表示画面を起動
    			Intent intent = new Intent(this, SearchActivity.class);
    			intent.putExtra("keyword", strKeyword);
    			startActivity(intent);
    		}
    	}
    }
}