package com.tanvir.tanvirmahmudkhan.foodiesworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class SignUp extends AppCompatActivity {
    EditText uid,name,pass,confirmpass,email;
    Button submit;

    String str_uid,str_name,str_pass,str_confirmpass,str_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        uid = (EditText) findViewById(R.id.UID);
        name = (EditText) findViewById(R.id.Name);
        pass = (EditText) findViewById(R.id.Password);
        confirmpass = (EditText) findViewById(R.id.ConfirmPassword);
        email = (EditText) findViewById(R.id.Email);



        submit =(Button) findViewById(R.id.Submit);

        final MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str_uid = uid.getText().toString();
                str_name = name.getText().toString();
                str_pass = pass.getText().toString();
                str_confirmpass = confirmpass.getText().toString();
                str_email = email.getText().toString();

               /* if(str_uid==null || str_name==null || str_pass==null || str_confirmpass==null || str_email==null ){
                    Toast.makeText(getApplicationContext(),"Please fill the form properly...",Toast.LENGTH_LONG).show();
                }*/
               if(!str_pass.matches(str_confirmpass)){
                    Toast.makeText(getApplicationContext(),"Password does not match..."+str_pass+"  "+str_confirmpass,Toast.LENGTH_LONG).show();
                }
                else if(!str_email.matches("(.*)@gmail.com(.*)")){
                    Toast.makeText(getApplicationContext(),"Email must be in correct pattern & gmail...",Toast.LENGTH_LONG).show();
                }
                else {
                    if(myDatabaseHelper.check_If_Uid_Unique(str_uid)){ // If the uid is unique
                        try{
                            if(myDatabaseHelper.insertUser(str_uid,str_name,str_pass,str_email,"User")!=-1){
                                Toast.makeText(getApplicationContext(),"Successfully registered...",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Something went wrong...",Toast.LENGTH_LONG).show();
                            }


                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Exception: "+e.toString(),Toast.LENGTH_LONG).show();
                        }


                    }
                    else{ // If uid not unique
                        Random random = new Random();
                        int ran = random.nextInt(50)+1;
                        Toast.makeText(getApplicationContext(),"The user name already exists.. You can try '"+str_uid+ran+"'...",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
