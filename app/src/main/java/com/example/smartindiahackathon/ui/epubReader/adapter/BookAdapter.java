package com.example.smartindiahackathon.ui.epubReader.adapter;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartindiahackathon.R;
import java.util.List;
import io.hamed.htepubreadr.entity.FontEntity;
import io.hamed.htepubreadr.ui.view.EpubView;
import io.hamed.htepubreadr.ui.view.OnHrefClickListener;
import io.hamed.htepubreadr.util.EpubUtil;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private List<String> data;
    private OnHrefClickListener onHrefClickListener;
    private String baseUrl;
    private FontEntity fontEntity;
    private int fontSize = -1;
    final static float move = 200;
    float ratio = 1.0f;
    int baseDist;
    float baseRatio;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public EpubView epubView;

        public MyViewHolder(View v) {
            super(v);
            epubView = v.findViewById(R.id.epub_view);
        }

        public void bind(String content) {
            if (fontSize != -1)
                epubView.setFontSize(fontSize);
            if (fontEntity != null)
                epubView.setFont(fontEntity);
            epubView.setBaseUrl(baseUrl);
            if (onHrefClickListener != null)
                epubView.setOnHrefClickListener(onHrefClickListener);
            epubView.setUp(content);
        }
    }

    public BookAdapter(List<String> data, String baseUrl) {
        this.data = data;
        this.baseUrl = baseUrl;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String content = "Error";
        try {
            content = EpubUtil.getHtmlContent(data.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.bind(content);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public OnHrefClickListener getOnHrefClickListener() {
        return onHrefClickListener;
    }

    public void setOnHrefClickListener(OnHrefClickListener onHrefClickListener) {
        this.onHrefClickListener = onHrefClickListener;
    }

    public FontEntity getFontEntity() {
        return fontEntity;
    }

    public void setFontEntity(FontEntity fontEntity) {
        this.fontEntity = fontEntity;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        return false;
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        if (event.getPointerCount()==2){
//            int action = event.getAction();
//            int mainaction = action&MotionEvent.ACTION_MASK;
//            if (mainaction == MotionEvent.ACTION_POINTER_DOWN){
//                baseDist = getDistance(event);
//                baseRatio = ratio;
//            }else {
//                float scale = (getDistance(event)-baseDist)/move;
//                float factor = (float) Math.pow(2,scale);
//                ratio = Math.min(1024.0f,Math.max(0.1f,baseRatio*factor));
//                //textview.setTextSize(ratio+15);
//
//            }
//        }
//        return true;
//    }
//
//    private int getDistance(MotionEvent event) {
//        int dx = (int) (event.getX( 0)- event.getX(1));
//        int dy = (int) (event.getY( 0)- event.getY( 1));
//        return (int) (Math.sqrt(dx*dx+dy*dy));
//
//    }
}
