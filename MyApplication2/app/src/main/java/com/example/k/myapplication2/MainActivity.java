package com.example.k.myapplication2;

import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private Button button16;
    private Button button17;
    private Button button18;
    private int buttons[];
    private Button temp;
    private TextView tv;
    private String str1;
    private String str2;
    private String strresult;//记录总结果
    private int flag;
    private float result;
    private String zhuanhuan;//记录进制转换
    private float result0;
    private float result1;
    void DtoM_int(int n){
        int m;
        char ch;
        if(n>0)
        {
            m=n%2;
            n=n/2;
            DtoM_int(n);
            if(m<10)
                ch=(char)('0'+m);
            else
                ch=(char)('A'+m-10);
            zhuanhuan=zhuanhuan+ch;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();

        // 清空按钮的点击事件监听器
        button14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv.setText(" ");
                str1 = "";
                str2 = ""; // 清空记录
                flag = 0;
            }
        });
        // 监听
        for (int i = 0; i < buttons.length; i++) {
            temp = (Button) findViewById(buttons[i]);
            temp.setOnClickListener( // 为Button添加监听器
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (tv.getText().equals(strresult)) {
                                //上次刚操作过，清空tv,并赋值为当前按下的按钮
                                tv.setText(String.valueOf(((Button) v).getText()));
                            } else {
                                str1 = tv.getText().toString().trim();
                                str1 = str1 + String.valueOf(((Button) v).getText());// 获得新输入的值
                                System.out.println("str1" + ":::" + str1);
                                tv.setText(str1);
                            }
                        }
                    });
        }
        //小数点的事件监听
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv.setText(tv.getText().toString()+".");
                str1=tv.getText().toString().trim();
                result1 = Float.parseFloat(str1);
            }
        });
        button18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zhuanhuan="";
                str1=tv.getText().toString().trim();
                result1 = Float.parseFloat(str1);
                DtoM_int((int)result1);
                //DtoM_double((double)result1);
                tv.setText(zhuanhuan);
            }
        });

        buttonListener(button3, 1);  //加
        buttonListener(button8, 2);  //减
        buttonListener(button13, 3); //乘
        buttonListener(button16, 4); //除
       // buttonListener(button18, 5); //十进制转换为二进制


        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(str1);
                result1 =  Float.parseFloat(str1);
                if (flag == 1) {
                    result = result0 + result1;
                    System.out.println(result0 + ":" + result1);
                } else if (flag == 2) {
                    result = result0 - result1;
                } else if (flag == 3) {
                    result = result0 * result1;
                } else if (flag == 4) {
                    try {
                        result = (result0 / result1);
                    } catch (ArithmeticException e) {
                        System.out.println(e.getMessage());
                    }
                }
                strresult = (result + "").trim();
                System.out.println(strresult);
                tv.setText(strresult);
            }
        });

    }


    // 初始化按钮
    public void initButton() { // 初始化控件资源
        tv = (TextView) this.findViewById(R.id.textView2); // 获取文本框控件对象
        str1 = String.valueOf(tv.getText());
        str2 = ""; // 初始化运算输入数值

        button14 = (Button) this.findViewById(R.id.button14); // 获得计算按钮的按钮对象
        button3 = (Button) this.findViewById(R.id.button3);
        button4 = (Button) this.findViewById(R.id.button4);
        button8 = (Button) this.findViewById(R.id.button8);
        button13 = (Button) this.findViewById(R.id.button13);
        button16 = (Button) this.findViewById(R.id.button16);
        button17 = (Button) this.findViewById(R.id.button17);
        button18 = (Button) this.findViewById(R.id.button18);//转换
        //
        buttons = new int[]{ // 记录数值按钮的id
                R.id.button, R.id.button2 ,R.id.button15, R.id.button5,
                R.id.button6, R.id.button7, R.id.button9, R.id.button10,
                R.id.button11, R.id.button12};
    }

    // 按钮监听
    public void buttonListener(Button button, final int id) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = tv.getText().toString().trim();
                result0 = Float.parseFloat(str);
                tv.setText("");
                flag = id;
            }
        });
    }


}
