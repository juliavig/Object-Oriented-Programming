package com.example.guessmaster3;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.* ;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private String user_input;
    private ImageView entityImage;
    String answer;
    private int numOfEntities;
    private Entity[] entities;
    private int totalTicketNum = 0;
    private int[] tickets;
    private int numOfTickets;
    String entName; //Stores Entity Name
    int entityid;
    int currentTicketWon = 0;
    //constructor
    public MainActivity() {
        numOfEntities = 0;
        entities = new Entity[10];
    }
    public void addEntity(Entity entity) {
        entities[numOfEntities++] = entity.clone();
    }
    public void playGame(int entityId) {
        Entity entity = entities[entityId];
        playGame(entity);
    }
    public void playGame(Entity entity) {
        entityName.setText(entity.getName());

        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "");
        Date date = new Date(answer);

        if (date.precedes(entity.getBorn())) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Incorrect");
            alertDialogBuilder.setMessage("Try a later date");
            alertDialogBuilder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //dismiss the dialog
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();
        } else if (entity.getBorn().precedes(date)) {
            //create AlertDialog for incorrect guess (earlier date)
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Incorrect");
            alertDialogBuilder.setMessage("Try an earlier date");
            alertDialogBuilder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialogBuilder.show();
        } else {
            //Inform user that they have won using AlertDialog
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("You won");
            alertDialogBuilder.setMessage("BINGO! " + entity.closingMessage());
            alertDialogBuilder.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "You won " + entity.getAwardedTicketNumber() + " tickets!", Toast.LENGTH_SHORT).show();
                    ContinueGame();
                    totalTicketNum += entity.getAwardedTicketNumber();
                    ticketsum.setText(String.valueOf(totalTicketNum));
                }
            });
            alertDialogBuilder.show();
        }
    }
    public void ContinueGame() {
        entityid = genRandomEntityId();
        Entity entity = entities[entityid];
        ImageSetter(entities[entityid]);
        entityName.setText(entity.getName());
        userIn.getText().clear();
    }
    public void playGame() {
        entityid = genRandomEntityId();
        playGame(entityid);
    }
    public int genRandomEntityId() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }
    public void changeEntity() {
        userIn.setText("");
        Random randomInt = new Random();
        entityid = randomInt.nextInt(numOfEntities);
        ContinueGame();
    }
    public void ImageSetter(Entity entity) {
        if ("United States".equals(entity.getName())) {
            entityImage.setImageResource(R.drawable.usaflag);
        } else if ("Justin Trudeau".equals(entity.getName())) {
            entityImage.setImageResource(R.drawable.justint);
        } else if ("Celine Dion".equals(entity.getName())) {
            entityImage.setImageResource(R.drawable.celidion);
        } else if ("Julia".equals(entity.getName())){
            entityImage.setImageResource(R.drawable.my_creator_image);
        }
    }
    public void welcomeToGame(Entity entity) {
        AlertDialog.Builder welcomeAlert = new AlertDialog.Builder(MainActivity.this);
        welcomeAlert.setTitle("GuessMaster Game v3");
        welcomeAlert.setMessage(entity.welcomeMessage());
        welcomeAlert.setCancelable(false);
        welcomeAlert.setNegativeButton("START GAME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Game is Starting... Enjoy", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = welcomeAlert.create();
        dialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        guessButton = findViewById(R.id.btnGuess);
        btnclearContent = findViewById(R.id.btnClear);
        userIn = (EditText) findViewById(R.id.guessInput);
        ticketsum = (TextView) findViewById(R.id.ticket);
        entityImage = (ImageView) findViewById(R.id.entityImage);
        entityName = (TextView) findViewById(R.id.entityName);

        //defining objects
        Country usa= new Country(
                "United States",
                new Date("July", 4, 1776),
                "Washingston DC",
                0.1);
        Person myCreator= new Person(
                "Julia",
                new Date(11, 8, 2004),
                "Female",
                1);
        Politician trudeau = new Politician(
                "Justin Trudeau",
                new Date("December",25,1971),
                "Male",
                "Liberal",
                0.25);
        Singer dion= new Singer(
                "Celine Dion",
                new Date("March", 30, 1961),
                "Female",
                "La voix du bon Dieu",
                new Date("November",6,1981),
                0.5);

        addEntity(dion);
        addEntity(trudeau);
        addEntity(usa);
        addEntity(myCreator);

        welcomeToGame(entities[entityid]);
        ImageSetter(entities[entityid]);
        entityName.setText(entities[entityid].getName());

        btnclearContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeEntity();
            }
        });
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(entities[entityid]);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}