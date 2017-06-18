package singlepageapp.mohanty.dinesh.com.booksearch;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book>{


    public BookAdapter(Context context , ArrayList<Book> books)
    {
        super(context  , 0 , books);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView= convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book book = getItem(position);


        TextView textView = (TextView) listItemView.findViewById(R.id.book_name);
        textView.setText(book.getBookName());

        TextView textView4 = (TextView) listItemView.findViewById(R.id.book_title);
        textView4.setText(book.getBookTitle());

        return listItemView;
    }

}
