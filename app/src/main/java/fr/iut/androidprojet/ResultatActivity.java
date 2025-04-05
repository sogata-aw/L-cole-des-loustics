package fr.iut.androidprojet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import fr.iut.androidprojet.model.Addition;

public class ResultatActivity extends AppCompatActivity {

    private Button btnMenu;
    private Button btnCorriger;
    private Button btnNouvellePartie;
    private TextView nbErreursView;
    private TextView tempsView;

    private ArrayList<Addition> additions = new ArrayList<>();

    public static final String TEMPS = "0.0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnMenu = findViewById(R.id.goToMenu);
        btnCorriger = findViewById(R.id.btnCorriger);
        btnNouvellePartie = findViewById(R.id.btnNouvellePartie);
        nbErreursView = findViewById(R.id.nbErreurs);
        tempsView = findViewById(R.id.temps);

        additions = getIntent().getParcelableArrayListExtra("additions");
        String temps = getIntent().getStringExtra(TEMPS);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, SelectGameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnNouvellePartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, AdditionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        int nbJustes = 0;
        for(Addition addition : additions){
            if(addition.isCorrect()){
                nbJustes++;
            }
        }

        nbErreursView.setText(String.valueOf(nbJustes));
        tempsView.setText("en " + temps + " seconde(s)");

    }
}