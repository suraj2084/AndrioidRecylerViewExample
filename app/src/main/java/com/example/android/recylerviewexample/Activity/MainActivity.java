package com.example.android.recylerviewexample.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.recylerviewexample.Adpter.ContactRecyclerAdpter;
import com.example.android.recylerviewexample.Model.StructureModel;
import com.example.android.recylerviewexample.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<StructureModel> ArrContact=new ArrayList<>();
    EditText Editname,Editnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton btnAction=findViewById(R.id.btnOpenDialog);
        RecyclerView recyclerView=findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Suraj","9053645721"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Sameen","9876543210"));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Kashish","8765432109"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Karan","7654321098"));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Tushar","6543210987"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Kapil","9874561230"));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Khushboo","9865320147"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Sareet","9764310598"));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Anuj","9632149870"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Priyal","9865320147"));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Jain","9874561230"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Teji","8765432109"));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Ritu","7654321098"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Deepak","9876543210"));
        ArrContact.add(new StructureModel(R.drawable.contact_a,"Dhruv","8765432109"));
        ArrContact.add(new StructureModel(R.drawable.contact_b,"Neshdi","9632149870"));
        ContactRecyclerAdpter adpter=new ContactRecyclerAdpter(this,ArrContact);
        recyclerView.setAdapter(adpter);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog= new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);
                Editname=dialog.findViewById(R.id.editName);
                Editnumber=dialog.findViewById(R.id.editNumber);
                Button btnADDAction=dialog.findViewById(R.id.btnAction);
                btnADDAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        if(!Editname.getText().toString().equals("")) {
                            name = Editname.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                        }
                        if(!Editnumber.getText().toString().equals("")) {
                            number = Editnumber.getText().toString();
                        }else{
                            Toast.makeText(MainActivity.this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }
                        ArrContact.add(new StructureModel(R.drawable.contact_a,name,number));
                        adpter.notifyItemInserted(ArrContact.size()-1);
                        recyclerView.scrollToPosition(ArrContact.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }
}