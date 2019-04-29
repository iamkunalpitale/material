package shrinkcom.user.kaptap.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shrinkcom.user.kaptap.Adapter.ServiceAdapter;
import shrinkcom.user.kaptap.Adapter.ServiceListAdapter;
import shrinkcom.user.kaptap.Fragment.HomeFragment;
import shrinkcom.user.kaptap.Model.ListServiceModel;
import shrinkcom.user.kaptap.Pojo.servicenamepojo.Servicenamepojo;
import shrinkcom.user.kaptap.Pojo.serviceprovidetypepojo.Serviceprovidertypepojo;
import shrinkcom.user.kaptap.R;

import static shrinkcom.user.kaptap.volley.Const.URLBASE;

public class ServiceListActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private RecyclerView rv_service_list;
    private List<ListServiceModel> serviceModelList;
    private ServiceListAdapter mAdapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        mContext = this;
        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        serviceModelList = new ArrayList<>();
        rv_service_list = (RecyclerView) findViewById(R.id.rv_service_list);



        Map<String, String> params = new HashMap<>();
        params.put("action", "providerServices");
        params.put("type", "2");
        getservicelistname(params);
       // prepareMovieData();
    }

    private void getservicelistname(final Map<String, String> params) {

        final ProgressDialog pdialog = new ProgressDialog(mContext);
        pdialog.setTitle(getResources().getString(R.string.loading));
        pdialog.setCancelable(true);
        pdialog.show();
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, URLBASE,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        Log.e("CouponResponse", "====>" + response);

                        GsonBuilder builder = new GsonBuilder();
                        Gson gson  = builder.create();

                        Servicenamepojo servicename = gson.fromJson(response,Servicenamepojo.class);
                        if (servicename.getUserData().size()>0){

                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rv_service_list.setLayoutManager(mLayoutManager);
                            rv_service_list.addItemDecoration(new DividerItemDecoration(ServiceListActivity.this, LinearLayoutManager.VERTICAL));
                            rv_service_list.setItemAnimator(new DefaultItemAnimator());
                            mAdapter = new ServiceListAdapter(mContext,servicename);
                            rv_service_list.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }


                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //displaying the error in toast if occurrs
                        Log.e("TAG", "onErrorResponse: " + error.getMessage());
                        Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                pdialog.dismiss();
                Log.e("SENDINGesponse", "--->" + params);
                return params;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(stringRequest);
    }

    private void prepareMovieData() {

        ListServiceModel listServiceModel = new ListServiceModel("Bathroom Refurbishment");
        serviceModelList.add(listServiceModel);
        listServiceModel = new ListServiceModel("Pipework / Replacement");
        serviceModelList.add(listServiceModel);
        listServiceModel = new ListServiceModel("New Plumbing work in Buildings");
        serviceModelList.add(listServiceModel);
        listServiceModel = new ListServiceModel("Turnkey Project in Plumbing");
        serviceModelList.add(listServiceModel);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
