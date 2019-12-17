package com.fpacini.bookstore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fpacini.bookstore.R;
import com.fpacini.bookstore.activities.DetailActivity;
import com.fpacini.bookstore.model.Book;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>
{
    ArrayList<Book> books;
    Context context;

    public BooksAdapter(ArrayList<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public BooksAdapter.BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BooksAdapter.BooksViewHolder holder, int position) {
        final Book book = books.get(position);

        holder.titoloTv.setText(book.getTitolo());
        holder.autoreTv.setText(book.getAutore());
        holder.casaEdTv.setText(book.getCasaEditrice());
        holder.prezzoTv.setText(String.valueOf(book.getPrezzo())+"€");

        holder.isReadCheckbox.setChecked(book.isRead()); // quando ricarica la lista, mostra la checkbox flaggata o meno
        holder.isReadCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                book.setRead(isChecked);

            }
        });

        holder.detailButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("currentBook", book);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titoloTv;
        private TextView autoreTv;
        private TextView casaEdTv;
        private TextView prezzoTv;
        private CheckBox isReadCheckbox;
        private Button detailButton;

        BooksViewHolder(@NonNull View itemView) {
            super(itemView);

            titoloTv = itemView.findViewById(R.id.titolo);
            autoreTv = itemView.findViewById(R.id.autore);
            casaEdTv = itemView.findViewById(R.id.casa_ed);
            prezzoTv = itemView.findViewById(R.id.prezzo);
            isReadCheckbox = itemView.findViewById(R.id.isRead);
            detailButton = itemView.findViewById(R.id.detail_button);

        }
    }
}

