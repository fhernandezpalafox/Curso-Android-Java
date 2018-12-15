package felipehernandez.com.appsocketio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ChatActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://socket-io-chat.now.sh");
        } catch (URISyntaxException e) {}
    }

    private String Usuario;

    private MessagesListAdapter<Message> adapter;

    private MessagesList messagesList;

    private MessageInput input;

    private List<Message> lstMessage;

    private List<IMessage> messages;

    private String uuid;

    protected final String senderId = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        uuid = UUID.randomUUID().toString();

        lstMessage =  new ArrayList<>();


        mSocket.connect();

        //Mostrar los datos traidos desde la otra Activity
        Bundle extras  =  getIntent().getExtras();
        Usuario = extras.getString("Usuario","");


        //Se manda el usuario que se conecto
        mSocket.emit("add user", Usuario);

        //Se manda un mensaje por default
        mSocket.emit("new message", "Hola a todos, mi nombre es: "+Usuario);


        //Metodo en escucha
        mSocket.on("new message", onNewMessage);


        Inicializar();

        Eventos();


    }


    public void Inicializar(){

        messagesList  =  findViewById(R.id.messagesList);

        input = findViewById(R.id.input);

        adapter = new MessagesListAdapter<>(senderId, null);

        //lstMessage.add(new Message(uuid,"Hola mundo",new Date(),new Author(uuid,Usuario,Usuario+"-"+uuid)));

        //adapter.addToEnd(lstMessage,true);

        messagesList.setAdapter(adapter);

    }


    public void Eventos(){

        Author author =  new Author(uuid,Usuario,Usuario+"-"+uuid);

        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                adapter.addToStart(new Message("0",input.toString(),new Date(),getUser(true)),true);
                //Se manda un mensaje por default
                mSocket.emit("new message", input.toString());

                return true;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        // mSocket.off("new message", onNewMessage);
    }

    private static Author getUser(boolean tipoMsg) {
        return new Author(
                tipoMsg ? "0" : "1",
                tipoMsg ? "Felipe" : "Oscar",
                tipoMsg ? "Felipe89" : "Oscar88");
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

                        String  uuidMessage = UUID.randomUUID().toString();

                        adapter.addToStart(new Message("1",message,new Date(),
                                        getUser(false)),
                                     true);

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
