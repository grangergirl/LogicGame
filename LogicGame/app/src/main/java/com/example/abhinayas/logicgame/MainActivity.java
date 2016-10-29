package com.example.abhinayas.logicgame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public GridView gridView;


    static final String[] numbers = new String[] {
            " ", " ", " ", " "," "," ",
            " "," "," "," "," "," ",
            " "," "," "," "," "," ",
            " "," "," "," "," "," ",
            " "," "," "," "," "," ",
            " "," "," "," "," "," "
            };
     public int[] colorArray=new int[]{
             0,0,0,0,0,0,
             0,0,0,0,0,0,
             0,0,0,0,0,0,
             0,0,0,0,0,0,
             0,0,0,0,0,0,
             0,0,0,0,0,0
     };
public int moves=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView= (GridView) findViewById(R.id.gridview);
       gridView.setAdapter(new GameAdapter(this));



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int i, long id) {
                moves++;
                TextView move=(TextView)findViewById(R.id.moves);
                move.setText(String.format("%d",moves));
                if((i-1)%6!=5&&i>0)
                {
               // colorChanger(i-1);
                    if(colorArray[i-1]==0) {
                        gridView.getChildAt(i-1).setBackgroundColor(Color.YELLOW);
                        colorArray[i-1]=1;
                    }
                    else{
                        gridView.getChildAt(i-1).setBackgroundColor(Color.GREEN);
                        colorArray[i-1]=0;

                    }
                }
                if((i+1)%6!=0)
                {
                   // colorChanger(i+1);
                    if(colorArray[i+1]==0) {
                        gridView.getChildAt(i+1).setBackgroundColor(Color.YELLOW);
                        colorArray[i+1]=1;
                    }
                    else{
                        gridView.getChildAt(i+1).setBackgroundColor(Color.GREEN);
                        colorArray[i+1]=0;

                    }
                }
                if(i-6>=0&&i>0)
                {
                    //colorChanger(i-6);
                    if(colorArray[i-6]==0) {
                        gridView.getChildAt(i-6).setBackgroundColor(Color.YELLOW);
                        colorArray[i-6]=1;
                    }
                    else{
                        gridView.getChildAt(i-6).setBackgroundColor(Color.GREEN);
                        colorArray[i-6]=0;

                    }
                }
                if(i+6<36)
                {
                   // colorChanger(i+6);
                    if(colorArray[i+6]==0) {
                        gridView.getChildAt(i+6).setBackgroundColor(Color.YELLOW);
                        colorArray[i+6]=1;
                    }
                    else{
                        gridView.getChildAt(i+6).setBackgroundColor(Color.GREEN);
                        colorArray[i+6]=0;

                    }
                }
                int l,flag=0;
                for(l=0;l<36;l++)
                {
                  if(colorArray[l]==1)
                  {
                      flag=1;

                  }
                    else
                  {
                      flag=0;
                      break;
                  }
                }
                TextView tv=(TextView)findViewById(R.id.textView);
                if(flag==1)
                { tv.setText("Congratulations!");flag=0;}

            }
        });
        Button reset=(Button)findViewById(R.id.button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


    }

class GameAdapter extends BaseAdapter{
ArrayList<String> spaces;
    Context context;
    GameAdapter(Context ctx)
    {this.context=ctx;
        spaces=new ArrayList<>();
        for(int i=0;i<36;i++)
            spaces.add(" ");
    }
    @Override
    public int getCount() {
        return spaces.size();
    }

    @Override
    public Object getItem(int position) {
        return spaces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder
    {
        TextView textView;
        ViewHolder(View v)
        {
           textView=(TextView)v.findViewById(R.id.tv);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder=null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.singe_grid, parent, false);
            holder=new ViewHolder(row);
            row.setTag(holder);
        } else
        {
holder=(ViewHolder)row.getTag();
        }
        return row;
    }
}

}
