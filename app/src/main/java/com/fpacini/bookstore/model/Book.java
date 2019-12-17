package com.fpacini.bookstore.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable
{
    private int id;
    private String titolo;
    private String autore;
    private int user_id;
    private String isbn;
    private float prezzo;
    private String casaEditrice;
    private static int counter=0;
    private boolean isRead;

    public Book( String titolo, String autore,int user_id, String isbn, float prezzo, String casaEditrice) {
        counter++;
        this.id = counter;
        this.titolo = titolo;
        this.autore= autore;
        this.user_id = user_id;
        this.isbn = isbn;
        this.prezzo = prezzo;
        this.casaEditrice = casaEditrice;
        this.isRead=false;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        titolo = in.readString();
        autore = in.readString();
        user_id = in.readInt();
        isbn = in.readString();
        prezzo = in.readFloat();
        casaEditrice = in.readString();
        isRead = in.readByte() != 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>()
    {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getCasaEditrice() {
        return casaEditrice;
    }

    public void setCasaEditrice(String casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(titolo);
        parcel.writeString(autore);
        parcel.writeInt(user_id);
        parcel.writeString(isbn);
        parcel.writeFloat(prezzo);
        parcel.writeString(casaEditrice);
        parcel.writeByte((byte) (isRead ? 1 : 0));
    }
}
