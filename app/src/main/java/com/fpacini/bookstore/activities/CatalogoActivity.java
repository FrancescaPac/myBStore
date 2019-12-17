package com.fpacini.bookstore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fpacini.bookstore.R;
import com.fpacini.bookstore.adapters.BooksAdapter;
import com.fpacini.bookstore.utils.PersistenceManager;
import com.fpacini.bookstore.model.Book;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    public static BooksAdapter booksAdapter;
    private ArrayList<Book> userBooks;
    private int userId;
    private PersistenceManager pm;
    private TextView noListPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        pm = PersistenceManager.getInstance();

        recyclerView = findViewById(R.id.recycler_view);
        noListPlaceholder = findViewById(R.id.no_list_placeholder);

        userId = getIntent().getIntExtra("id", 0);
        userBooks = new ArrayList<>();
        for(Book book: pm.getCatalogo()){
            if(book.getUser_id() == userId){
                userBooks.add(book);
            }
        }

        /*per un nuovo utente che si registra
        viene inizialmente visualizzato un placeholder
        poichè non ha nessun catalogo libri associato
        */
        if(userBooks.size() != 0){
            setUpRecyclerView();
        } else {
            recyclerView.setVisibility(View.GONE);
            noListPlaceholder.setVisibility(View.VISIBLE);
        }
    }

    public void setUpRecyclerView(){
        booksAdapter = new BooksAdapter( userBooks, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(booksAdapter);
    }
}
