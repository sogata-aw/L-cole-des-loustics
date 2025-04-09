package fr.iut.androidprojet.model;

import java.util.ArrayList;

public class Question {
    private String question;
    private String bonneReponse;
    private String mauvaisesReponse1;
    private String mauvaisesReponse2;

    private String reponseUtilisateur;

    public Question(String question, String bonneReponse, String mauvaisesReponse1, String mauvaisesReponse2) {
        this.question = question;
        this.bonneReponse = bonneReponse;
        this.mauvaisesReponse1 = mauvaisesReponse1;
        this.mauvaisesReponse2 = mauvaisesReponse2;
        this.reponseUtilisateur = null;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(String bonneReponse) {
        this.bonneReponse = bonneReponse;
    }

    public String getMauvaisesReponse2() {
        return mauvaisesReponse2;
    }

    public void setMauvaisesReponse2(String mauvaisesReponse2) {
        this.mauvaisesReponse2 = mauvaisesReponse2;
    }

    public String getMauvaisesReponse1() {
        return mauvaisesReponse1;
    }

    public void setMauvaisesReponse1(String mauvaisesReponse1) {
        this.mauvaisesReponse1 = mauvaisesReponse1;
    }

    public String getReponseUtilisateur() {
        return reponseUtilisateur;
    }

    public void setReponseUtilisateur(String reponseUtilisateur) {
        this.reponseUtilisateur = reponseUtilisateur;
    }

    public static ArrayList<Question> initialize(){
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question("Quel est l'antonyme de « sec » ?", "mouillé", "glissant", "chaud"));
        questions.add(new Question("Complétez : « Ils ______ une grande maison. »", "ont", "a", "est"));
        questions.add(new Question("Quel mot désigne un synonyme de « courageux » ?", "brave", "fragile", "lent"));
        questions.add(new Question("Complétez : « Elle ______ chaque matin à 7 heures. »", "se lève", "se levait", "se lèvera"));
        questions.add(new Question("Complétez : « Je ______ bien un café. »", "voudrais", "veux", "voulait"));
        questions.add(new Question("Quel est le contraire de « heureux » ?", "triste", "joyeux", "souriant"));
        questions.add(new Question("Complétez : « Ils ______ à la bibliothèque tous les jours. »", "vont", "iront", "allaient"));
        questions.add(new Question("Complétez : « Nous ______ souvent en famille. »", "mangeons", "mangions", "mangerons"));
        questions.add(new Question("Complétez : « Une personne ______ est souvent gentille. »", "gentille", "gentil", "gentils"));
        questions.add(new Question("Complétez : « Je ______ un peu de repos. »", "prends", "prendre", "prendait"));
        questions.add(new Question("Complétez : « ______-tu un animal chez toi ? »", "As", "Es", "A"));
        questions.add(new Question("Complétez : « Il est ______, il n'a rien fait. »", "innocent", "coupable", "responsable"));
        questions.add(new Question("Complétez : « Elle a ______ son livre sur la table. »", "mis", "mettre", "mettrez"));
        questions.add(new Question("Quel est le contraire de « vieux » ?", "jeune", "ancien", "moderne"));
        questions.add(new Question("Complétez : « Nous ______ le film ce soir. »", "verrons", "voyons", "verrait"));
        questions.add(new Question("Complétez : « L'arbre ______ sous la neige. »", "plie", "pliait", "plier"));
        questions.add(new Question("Complétez : « Il ______ toujours ses devoirs avant le dîner. »", "fait", "faisait", "fera"));
        questions.add(new Question("Complétez : « Nous ______ en vacances cet été. »", "partirons", "partons", "partait"));
        questions.add(new Question("Quel est le synonyme de « intelligent » ?", "brillant", "savant", "diligent"));
        questions.add(new Question("Complétez : « Quand il ______, nous étions tous surpris. »", "est arrivé", "arriva", "arrive"));
        questions.add(new Question("Complétez : « Je n'ai pas ______ de temps pour le faire. »", "beaucoup", "beaucoups", "beaucoupes"));
        questions.add(new Question("Complétez : « Elle est ______ que moi. »", "plus gentille", "plus gentils", "plus gentilles"));
        questions.add(new Question("Complétez : « Il a ______ le document sur le bureau. »", "posé", "poser", "posera"));
        questions.add(new Question("Complétez : « Je ______ un cadeau pour son anniversaire. »", "ai acheté", "acheter", "achète"));
        questions.add(new Question("Complétez : « La voiture ______ vite sur l'autoroute. »", "roule", "roulait", "roulera"));
        questions.add(new Question("Complétez : « Si tu ______ tes devoirs, tu réussiras. »", "fais", "fait", "feras"));
        questions.add(new Question("Complétez : « ______-vous déjà allé à Paris ? »", "Êtes", "Es", "Avez"));
        return questions;
    }

    public static int getQuestionsJuste(ArrayList<Question> questions, ArrayList<Integer> choosen){
        int cpt = 0;
        for(Integer i : choosen){
            if(questions.get(i).getReponseUtilisateur().equals(questions.get(i).getBonneReponse())){
                cpt++;
            }
        }
        return cpt;
    }
}
