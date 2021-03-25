package br.com.etecia.projetovolleysingletonpatternapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    Button btnAcessarServer;
    TextView txtRespostaWeb;
    String server_url = "http://192.168.100.13/projetovolleyapi/resposta.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRespostaWeb = findViewById(R.id.txtRespostaWEB);
        btnAcessarServer = findViewById(R.id.btnAcessaServer);

        btnAcessarServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                txtRespostaWeb.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtRespostaWeb.setText("Error ao conectar o servidor WEB");
                        error.printStackTrace();
                    }
                });
                MySingleton.getInstance(getApplicationContext()).addToRequestque(stringRequest);
            }
        });
    }
}
