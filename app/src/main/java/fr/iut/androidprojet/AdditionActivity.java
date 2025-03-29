package fr.iut.androidprojet;

import android.content.Intent;
import android.graphics.Color;
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

    private Random random = new Random();

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
        ArrayList<Addition> additions;
        AdditionActivity additionActivity = this;

        Boolean retour = getIntent().getBooleanExtra("retour", false);
        int nbFois = getIntent().getIntExtra("nbFois", 1);
        if(getIntent().getParcelableExtra("additions") == null){
            additions = new ArrayList<Addition>();
        }else{
            additions = getIntent().getParcelableArrayListExtra("additions");
        }

        compteurView = findViewById(R.id.compteur);
        additionView = findViewById(R.id.addition);
        reponseView = findViewById(R.id.reponse);
        precedentView = findViewById(R.id.precedent);
        suivantView = findViewById(R.id.suivant);

        if(nbFois == 1){
            precedentView.setEnabled(false);
        }

        if(nbFois == 10){
            suivantView.setText("Valider");
            suivantView.setTextColor(Color.RED);
        }
        compteurView.setText(nbFois + "/10");

        if(!retour) {
            Addition addition = new Addition(random.nextInt(10) + 1, random.nextInt(10) + 1);
            additionView.setText(addition.toString());
            additions.add(addition);
        }else{
            additionView.setText(additions.get(nbFois - 1).toString());
            reponseView.setText(additions.get(nbFois - 1).getReponseUtilisateur());
        }

        precedentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(additionActivity, AdditionActivity.class);
                additions.get(nbFois - 1).setReponseUtilisateur(Integer.parseInt(reponseView.getText().toString()));
                intent.putExtra("nbFois", nbFois - 1);
                intent.putParcelableArrayListExtra("additions", additions);
                intent.putExtra("retour", true);
                startActivity(intent);
            }
        });

        suivantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(additionActivity, AdditionActivity.class);
                System.out.println(nbFois + ", " + additions.size());
                additions.get(nbFois - 1).setReponseUtilisateur(Integer.parseInt(reponseView.getText().toString()));
                intent.putExtra("nbFois", nbFois + 1);
                intent.putParcelableArrayListExtra("additions", additions);
                startActivity(intent);
            }
        });
    }
}