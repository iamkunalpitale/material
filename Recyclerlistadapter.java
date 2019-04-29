package shrinkcom.user.kaptap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shrinkcom.user.kaptap.Activities.ServiceProviderActivity;
import shrinkcom.user.kaptap.Pojo.servicenamepojo.Servicenamepojo;
import shrinkcom.user.kaptap.Pojo.servicenamepojo.UserDatum;
import shrinkcom.user.kaptap.R;

import static shrinkcom.user.kaptap.volley.Const.IMAGEURL;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.MyViewHolder> {
    private List<UserDatum> servicename;
    private Context context;

    public ServiceListAdapter(Context context, Servicenamepojo servicename) {
        this.context = context;
        this.servicename = servicename.getUserData();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.servicelist_row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.servicelist_name.setText(servicename.get(i).getSTitle());
        String strimage = IMAGEURL+servicename.get(i).getSIcon();
        Glide.with(context).load(strimage).into(myViewHolder.servicelist_img);
    }

    @Override
    public int getItemCount() {
        return servicename.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView servicelist_name;
        public ImageView servicelist_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            servicelist_img = (ImageView) itemView.findViewById(R.id.servicelist_img);
            servicelist_name = (TextView) itemView.findViewById(R.id.servicelist_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ServiceProviderActivity.class);
                    context.startActivity(intent);
                   /* Intent intent = new Intent(context, ServiceTypeActivity.class);
                    context.startActivity(intent);*/
                }
            });
        }
    }
}
