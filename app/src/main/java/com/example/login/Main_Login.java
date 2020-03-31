package com.example.login;

import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONArray;
import android.view.View;
import org.json.JSONObject;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Main_Login extends AppCompatActivity {
    private EditText Edituser,Editpass;
    private Button Login;
    private String StrUser,StrPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ผูกวิตเจ็ต
        Edituser = (EditText) findViewById(R.id.edituser);
        Editpass = (EditText) findViewById(R.id.editpass);
        Login = (Button) findViewById(R.id.btnLogin);
        //ผูกวิตเจ็ต
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// startActivity(new Intent(Main_login.this,Main_detail.class));
                StrUser = Edituser.getText().toString().trim();
                StrPass = Editpass.getText().toString().trim();
                LoginUser();
            }
            //login
        });
    }
    //on create
    private  void LoginUser(){
        try{
            UserLogin userLogin = new UserLogin(Main_Login.this, StrUser, StrPass);
             userLogin.execute();

            if (userLogin.get().trim().contains("true")) {

                SelectAlldata selectAlldata = new SelectAlldata(Main_Login.this);
                selectAlldata.execute();

                String data = selectAlldata.get();
                JSONArray jsonArray = new JSONArray(data);
                //Style_Code,LayFb_Code,Bind,Bind_Size,Plant_Color,Bind_Amount,Bind_Plant,Bind_Fix,Bind_Qty,QSave
                String[]  EmnIDStrings = new String[jsonArray.length()];
                String[]  EmnameStrings = new String[jsonArray.length()];
                String[]  Style_CodeString = new String[jsonArray.length()];
                String[]  LayFb_CodeStrings = new String[jsonArray.length()];
                String[]  BindString = new String[jsonArray.length()];
                String[]  Bind_SizeStrings = new String[jsonArray.length()];
                String[]  Plant_ColorString = new String[jsonArray.length()];
                String[]  Bind_AmountStrings = new String[jsonArray.length()];
                String[]  Bind_PlantString = new String[jsonArray.length()];
                String[]  Bind_FixStrings = new String[jsonArray.length()];
                String[]  Bind_QtyString = new String[jsonArray.length()];

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    EmnIDStrings[i]=jsonObject.getString("EmID");
                    EmnameStrings[i]=jsonObject.getString("Emname");
                    Style_CodeString[i]=jsonObject.getString("Style_Code");
                    LayFb_CodeStrings[i]=jsonObject.getString("LayFb_Code");
                    BindString[i]=jsonObject.getString("Bind");
                    Bind_SizeStrings[i]=jsonObject.getString("Bind_Size");
                    Plant_ColorString[i]=jsonObject.getString("Plant_Code");
                    Bind_AmountStrings[i]=jsonObject.getString("BindAmount");
                    Bind_PlantString[i]=jsonObject.getString("Bind_Plant");
                    Bind_FixStrings[i]=jsonObject.getString("Bind_Fix");
                    Bind_QtyString[i]=jsonObject.getString("Bind_Qty");
                }

                Intent intent = new Intent(Main_Login.this,Main_Scanner.class);
                intent.putExtra("EmID",EmnIDStrings);
                intent.putExtra("Emname",EmnameStrings);
                intent.putExtra("Style_Code",Style_CodeString);
                intent.putExtra("LayFb_Code",LayFb_CodeStrings);
                intent.putExtra("Bind",BindString);
                intent.putExtra("Bind_Size",Bind_SizeStrings);
                intent.putExtra("Plant_Color",Plant_ColorString);
                intent.putExtra("Bind_Amount",Bind_AmountStrings);
                intent.putExtra("Bind_Plant",Bind_PlantString);
                intent.putExtra("Bind_Fix",Bind_FixStrings);
                intent.putExtra("Bind_Qty",Bind_QtyString);
                startActivity(intent);
                finish();

//login pass
            } else {
                Toast.makeText(Main_Login.this, "Login fail", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        }

}
