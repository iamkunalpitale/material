package shrinkcom.user.kaptap.Activities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import shrinkcom.user.kaptap.R;
import shrinkcom.user.kaptap.other.CheckNetworkState;

import static shrinkcom.user.kaptap.volley.Const.URLBASE;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, email, password, mobile;
    private String Username, Email, Password, Mobile,first_name,last_name;
    private CheckBox terms_condition;
    private ImageView signup, facebook_login, gplus_login;
    private TextView login;
    Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mContext = this;
        initViews();
    }

    private void initViews() {
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        mobile = (EditText) findViewById(R.id.mobile);
        terms_condition = (CheckBox) findViewById(R.id.terms_condition);
        signup = (ImageView) findViewById(R.id.signup);
        facebook_login = (ImageView) findViewById(R.id.facebook_login);
        gplus_login = (ImageView) findViewById(R.id.gplus_login);
        login = (TextView) findViewById(R.id.login);
        signup.setOnClickListener(this);
        facebook_login.setOnClickListener(this);
        gplus_login.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.facebook_login:
                Toast.makeText(getApplicationContext(), "Facebook Login", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gplus_login:
                Toast.makeText(getApplicationContext(), "Google Login", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.signup:
                validate();
                break;
        }
    }

    private void validate() {
        Username = username.getText().toString().trim();
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();
        Mobile = mobile.getText().toString().trim();

        if (Username.isEmpty()) {
            username.setError("Username is required");
            username.requestFocus();
        } else if (Email.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
        } else if (Password.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
        } else if (Mobile.isEmpty()) {
            mobile.setError("Mobile is required");
            mobile.requestFocus();
        } else if (!terms_condition.isChecked()) {
            Map<String, String> params = new HashMap<>();
            params.put("action", "register");
            params.put("first_name", "");
            params.put("last_name", "");
            params.put("email", "" + Email);
            params.put("password", "" + Password);
            params.put("phone", "" + Mobile);

            boolean checknetwork = CheckNetworkState.isOnline(mContext);
            if (checknetwork)
                RegisrterUser(params);
        } else {
            CheckNetworkState.showToast(mContext, getResources().getString(R.string.internetconnection));


        }


}

    private void RegisrterUser(final Map<String, String> params) {
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
                        try {
                            JSONObject obj = new JSONObject(response);
                            int resss = obj.getInt("result");
                            if (resss == 1) {
                                pdialog.dismiss();
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {

                                // CheckNetworkState.showToast(RegistrationActivity.this,"Already exist");
                                pdialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
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


}

