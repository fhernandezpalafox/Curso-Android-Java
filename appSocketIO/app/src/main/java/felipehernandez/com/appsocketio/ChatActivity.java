package felipehernandez.com.appsocketio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.nkzawa.socketio.client.Socket;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private Socket mSocket;
    private String Usuario;

    private MessagesListAdapter<Message> adapter;
    private MessagesList messagesList;

    private List<Message> lstMessage;

    private List<IMessage> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messagesList  =  findViewById(R.id.messagesList);


        lstMessage =  new ArrayList<>();


        adapter = new MessagesListAdapter<>("Felipe", null);

        lstMessage.add(new Message("125","Hola mundo",new Date(),new Author("125","Felipe","Felipe890202")));

        adapter.addToEnd(lstMessage,true);

        messagesList.setAdapter(adapter);

        Inicializar();

        Eventos();
    }



    public void Inicializar(){


    }


    public void Eventos(){

    }

}
