package fr.iut.androidprojet.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Addition implements Parcelable {
    private int op1;
    private int op2;
    private int reponseUtilisateur;

    public Addition(int op1, int op2) {
        this.op1 = op1;
        this.op2 = op2;
        this.reponseUtilisateur = -1;
    }

    public int getOp1() {
        return op1;
    }

    public void setOp1(int op1) {
        this.op1 = op1;
    }

    public int getOp2() {
        return op2;
    }

    public void setOp2(int op2) {
        this.op2 = op2;
    }

    public int getReponseUtilisateur() {
        return reponseUtilisateur;
    }

    public void setReponseUtilisateur(int reponseUtilisateur) {
        this.reponseUtilisateur = reponseUtilisateur;
    }

    public boolean isCorrect(){
        return op1 + op2 == reponseUtilisateur;
    }

    @Override
    public String toString(){
        return op1 + " + " +op2 + " = ";
    }

    protected Addition(Parcel in) {
        op1 = in.readInt();
        op2 = in.readInt();
        reponseUtilisateur = in.readInt();
    }

    // 🔹 Écriture de l'objet dans le Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(op1);
        dest.writeInt(op2);
        dest.writeInt(reponseUtilisateur);
    }

    // 🔹 Méthode obligatoire, généralement retourne 0
    @Override
    public int describeContents() {
        return 0;
    }

    // 🔹 CREATOR pour recréer l'objet depuis un Parcel
    public static final Creator<Addition> CREATOR = new Creator<Addition>() {
        @Override
        public Addition createFromParcel(Parcel in) {
            return new Addition(in);
        }

        @Override
        public Addition[] newArray(int size) {
            return new Addition[size];
        }
    };
}
