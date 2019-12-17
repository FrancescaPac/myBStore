package com.fpacini.bookstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fpacini.bookstore.R;
import com.fpacini.bookstore.model.Book;
import com.fpacini.bookstore.utils.PersistenceManager;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity
{
    private TextView titolo;
    private TextView autore;
    private TextView casaEd;
    private TextView isbn;
    private TextView prezzo;
    private TextView isRead;
    private PersistenceManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle data = getIntent().getExtras();
        Book book = data.getParcelable("currentBook");

        titolo = findViewById(R.id.book_title);
        autore = findViewById(R.id.book_author);
        casaEd = findViewById(R.id.casa_ed);
        isbn = findViewById(R.id.isbn);
        prezzo = findViewById(R.id.book_price);
        isRead = findViewById(R.id.is_read);

        titolo.setText(book.getTitolo());
        autore.setText(book.getAutore());
        casaEd.setText(book.getCasaEditrice());
        isbn.setText(book.getIsbn());
        prezzo.setText(String.valueOf(book.getPrezzo())+" euro");
        if(book.isRead()){
            isRead.setText("Hai già letto questo libro!");
        } else {
            isRead.setText("Devi ancora leggere questo libro!");
        }

    }
}
