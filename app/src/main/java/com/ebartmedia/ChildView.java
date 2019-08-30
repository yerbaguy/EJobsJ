package com.ebartmedia;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ebartmedia.Model.Categories;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.child_layout)
public class ChildView {
    private static String TAG ="ChildView";

    @View(R.id.child_name)
    TextView textViewChild;

    @View(R.id.child_desc)
    TextView textViewDesc;

   // @View(R.id.child_image)
    ImageView childImage;

    private Context mContext;
//    private Movie movie;
    private Categories categories;

    public ChildView(Context mContext, Categories categories) {
        this.mContext = mContext;
        this.categories = categories;
    }

    @Resolve
    private void onResolve(){
        Log.d(TAG,"onResolve");
        textViewChild.setText(categories.getCatName());
      //  Glide.with(mContext).load(movie.getImageUrl()).apply(RequestOptions.circleCropTransform()).into(childImage);
    }
}
