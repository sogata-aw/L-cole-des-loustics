package fr.iut.androidprojet;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

import fr.iut.androidprojet.model.Addition;

public class AdditionActivity extends AppCompatActivity {

    private TextView compteurView;
    private TextView additionView;
    private EditText reponseView;
    private Button precedentView;
    private Button suivantView;
    private ArrayList<Addition> additions = new ArrayList<Addition>();
    private Random random = new Random();
    private Boolean retour = false;
    private int nbFois = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addition);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        compteurView = findViewById(R.id.compteur);
        additionView = findViewById(R.id.addition);
        reponseView = findViewById(R.id.reponse);
        precedentView = findViewById(R.id.precedent);
        suivantView = findViewById(R.id.suivant);

        compteurView.setText(nbFois + "/10");

        for(int i=0; i <10; i++){
            additions.add(new Addition(random.nextInt(11),random.nextInt(11)));
        }

        updateView();

        precedentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!reponseView.getText().toString().equals("")){
                    additions.get(nbFois - 1).setReponseUtilisateur(Integer.parseInt(reponseView.getText().toString()));
                }
                nbFois--;
                updateView();
            }
        });

        suivantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nbFois == 10){
                    Intent intent = new Intent();
                }else {
                    if (!reponseView.getText().toString().equals("")) {
                        additions.get(nbFois - 1).setReponseUtilisateur(Integer.parseInt(reponseView.getText().toString()));
                    }
                    nbFois++;
                    updateView();
                }
            }
        });
    }

    private void updateView() {
        compteurView.setText(nbFois + "/10");

        Addition addition = additions.get(nbFois - 1);
        additionView.setText(addition.toString());
        reponseView.setText(addition.getReponseUtilisateur() == 0 ? "" : String.valueOf(addition.getReponseUtilisateur()));

        // Gérer les boutons
        precedentView.setEnabled(nbFois > 1);
        if (nbFois == 9) {
            suivantView.setText("Valider");
            suivantView.setTextColor(Color.RED);
        } else {
            suivantView.setText("Suivant");
            suivantView.setTextColor(Color.BLACK);
        }
    }
}