package felipehernandez.com.appsocketio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://socket-io-chat.now.sh");
        } catch (URISyntaxException e) {}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSocket.on("new message", onNewMessage);

        mSocket.connect();

        Inicializar();

        Eventos();
    }


    public void  Inicializar(){
        btnEntrar  =  findViewById(R.id.btnEntrar);
    }


    public void Eventos(){
        btnEntrar.setOnClickListener(v -> {
            Intent i = new Intent(this, ChatActivity.class);
            startActivity(i);
        });


        /*
          btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
         */

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
       // mSocket.off("new message", onNewMessage);
    }


    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");

                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        return;
                    }

                    // add the message to view
                    // addMessage(username, message);
                }
            });
        }
    };
}
