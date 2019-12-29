package limfiq.inc.crudmysql;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {
    //deklarasi komponen
    EditText nip,nama,posisi,gaji,alamat;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

//mengambil data dari intent yang digunakan
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);
        String intent_nip = data.getStringExtra("nip");
        String intent_nama = data.getStringExtra("nama");
        String intent_posisi = data.getStringExtra("posisi");
        String intent_gaji = data.getStringExtra("gaji");
        String intent_alamat = data.getStringExtra("alamat");

//inisialisasi komponen dengan data yang diambil
        nip = (EditText) findViewById(R.id.inp_nip);
        nama = (EditText) findViewById(R.id.inp_nama);
        posisi = (EditText) findViewById(R.id.inp_posisi);
        gaji= (EditText) findViewById(R.id.inp_gaji);
        alamat= (EditText) findViewById(R.id.inp_alamat);
        btnbatal = (Button) findViewById(R.id.btn_cancel);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(InsertData.this);

        /*kondisi update / insert = */
        if(update == 1)
        {
            btnsimpan.setText("Update Data");
            nip.setText(intent_nip);
            nip.setVisibility(View.GONE);
            nama.setText(intent_nama);
            posisi.setText(intent_posisi);
            gaji.setText(intent_gaji);
            alamat.setText(intent_alamat);

        }


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });

//perintah ketika button diklik
        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(InsertData.this,MainActivity.class);
                startActivity(main);
            }
        });
    }

    //perintah update data pada btnsimpan
    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertData.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nip",nip.getText().toString());
                map.put("nama",nama.getText().toString());
                map.put("posisi",posisi.getText().toString());
                map.put("gaji",gaji.getText().toString());
                map.put("alamat",alamat.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }



    //perintah simpan data di btnsimpan
    private void simpanData()
    {

        pd.setMessage("Menyimpan Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(InsertData.this,MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(InsertData.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("nip",nip.getText().toString());
                map.put("nama",nama.getText().toString());
                map.put("posisi",posisi.getText().toString());
                map.put("gaji",gaji.getText().toString());
                map.put("alamat",alamat.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}

