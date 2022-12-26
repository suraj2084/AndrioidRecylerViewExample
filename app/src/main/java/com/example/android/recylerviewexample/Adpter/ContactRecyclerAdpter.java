package com.example.android.recylerviewexample.Adpter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.recylerviewexample.Activity.MainActivity;
import com.example.android.recylerviewexample.Model.StructureModel;
import com.example.android.recylerviewexample.R;

import java.util.ArrayList;

public class ContactRecyclerAdpter extends RecyclerView.Adapter<ContactRecyclerAdpter.ViewHolder> {
    Context context;
    ArrayList<StructureModel> arrContact;
    EditText Editname,Editnumber;
    private int lastpotion=-1;
    public ContactRecyclerAdpter(Context context, ArrayList<StructureModel> arrContact){
        this.context=context;
        this.arrContact=arrContact;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewholder= new ViewHolder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StructureModel model=(StructureModel) arrContact.get(position);
        holder.img.setImageResource(model.img);
        holder.txtContact.setText(model.contactNumber);
        holder.txtName.setText(model.contactName);
        setAnimation(holder.itemView,position);
        holder.LinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_update_lay);
                Editname=dialog.findViewById(R.id.editName);
                Editnumber=dialog.findViewById(R.id.editNumber);
                Editname.setText(arrContact.get(position).contactName);
                Editnumber.setText(arrContact.get(position).contactNumber);
                TextView title=dialog.findViewById(R.id.txttile);
                title.setText("Update Contact");
                Button btnUpdateAction=dialog.findViewById(R.id.btnAction);
                btnUpdateAction.setText("Update");

                btnUpdateAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        if(!Editname.getText().toString().equals("")) {
                            name = Editname.getText().toString();
                        }else{
                            Toast.makeText(context, "Please Enter Contact Name", Toast.LENGTH_SHORT).show();
                        }
                        if(!Editnumber.getText().toString().equals("")) {
                            number = Editnumber.getText().toString();
                        }else{
                            Toast.makeText(context, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }
                        arrContact.set(position,new StructureModel(arrContact.get(position).img,name,number));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.LinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete Contact");
                builder.setMessage("Are you sure want to Delete?");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrContact.remove(position);
                        notifyItemChanged(position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                return true;
            }

        });

    }

    @Override
    public int getItemCount() {
        return arrContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtContact,title;
        ImageView img;
        LinearLayout LinearLayout;
        public ViewHolder(View ItemView){
            super(ItemView);
            txtName=ItemView.findViewById(R.id.ContactName);
            txtContact=ItemView.findViewById(R.id.ContactNumber);
            img=ItemView.findViewById(R.id.ImageContact);
            LinearLayout=ItemView.findViewById(R.id.llRow);
        }


    }
    private void setAnimation(View viewToAnimate, int postion){
        if(postion>lastpotion) {
            Animation slidein = AnimationUtils.loadAnimation(context,R.anim.rcy_anim);
            viewToAnimate.startAnimation(slidein);
            lastpotion=postion;
        }



    }

}
