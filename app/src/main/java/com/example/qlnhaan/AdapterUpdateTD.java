package com.example.qlnhaan;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import Model.ThucDon;

public class AdapterUpdateTD extends FirebaseRecyclerAdapter<ThucDon,AdapterUpdateTD.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    private Context context;
    public AdapterUpdateTD(@NonNull FirebaseRecyclerOptions<ThucDon> options,Context context) {

        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ThucDon model) {

        holder.txtTenTD.setText(model.getTentd());
        String imageUri;
        imageUri=model.getAnhtd();
        Picasso.get().load(imageUri).into(holder.imageView);
        holder.imageSuaTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialogPlus=DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.dialogsuatd))
                        .setExpanded(false).create();
                dialogPlus.show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listtd,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,imageSuaTD,imageXoaTD;
        TextView txtTenTD;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.imageView1);
            imageSuaTD=itemView.findViewById(R.id.imageSuaTD);
            imageXoaTD=itemView.findViewById(R.id.imageXoaTD);
            txtTenTD=itemView.findViewById(R.id.txtTenTD);
        }
    }
}
