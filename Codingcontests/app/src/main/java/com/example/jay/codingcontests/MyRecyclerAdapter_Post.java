package com.example.jay.codingcontests;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.security.acl.Group;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import DB.DatabaseHelper;

public class MyRecyclerAdapter_Post extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static List<DataObject_post> mList;
    public static final int NO_IMAGE = 0;
    public static final int WITH_IMAGE = 1;

    Context context;

    Date date_Start,date_End,date_Now;


    public MyRecyclerAdapter_Post(List<DataObject_post> list) {
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case NO_IMAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_cardview_home_withoutimage, parent, false);
                context = parent.getContext();
                return new NoimageViewHolder(view);
            case WITH_IMAGE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_cardview_home, parent, false);
                context = parent.getContext();
                return new WithimageViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DataObject_post object = mList.get(position);

        if (object != null) {
            switch (object.getType()) {
                case NO_IMAGE:


                    try {


                        ((NoimageViewHolder) holder).tv_contest_titlte.setText(object.getEvent());
                        String companyname=object.getResource_name();
                        companyname=companyname.substring(0,companyname.indexOf("."));
                        String uper_companyname=companyname.substring(0,1).toUpperCase();
                        companyname=uper_companyname+companyname.substring(1);
                        ((NoimageViewHolder) holder).tv_title_companyname.setText(companyname);


                        //===== status of compition

                        String dtStart = object.getStart();
                        String dtEnd = object.getEnd();
                        SimpleDateFormat format_diffrent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {

                            Date now = new Date();
                            String strDate = format_diffrent.format(now);

                            date_Start = format_diffrent.parse(dtStart);
                            date_End = format_diffrent.parse(dtEnd);
                            date_Now= format_diffrent.parse(strDate);

                            System.out.println(date_Start);
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        if(between(date_Now,date_Start,date_End)==true){
                            ((NoimageViewHolder) holder).tv_status.setText(" Running ");
                        }else if(date_End.before(date_Now)){
                            ((NoimageViewHolder) holder).tv_status.setText(" Completed ");
                        }else{
                            ((NoimageViewHolder) holder).tv_status.setText(" Yet not started ");
                        }


                        ((NoimageViewHolder) holder).tv_link_url.setText(object.getHref());

                        String full_start = object.getStart();
                        DateFormat df_post = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
                        //df_post.setTimeZone(TimeZone.getTimeZone("MST"));

                        Date date_Start = df_post.parse(full_start);
                        full_start = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US).format(date_Start.getTime());

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US);
                        Calendar end_date, cal = null;

                        cal = Calendar.getInstance();
                        cal.setTime(format.parse(full_start));
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH) + 1;
                        int date = cal.get(Calendar.DATE);
                        int day = cal.get(Calendar.DAY_OF_WEEK);
                        String dayOfWeek = getDayOfWeek(day);
                        ((NoimageViewHolder) holder).tv_day.setText(dayOfWeek);


                        ((NoimageViewHolder) holder).tv_onlydate.setText(Integer.toString(date));
                        ((NoimageViewHolder) holder).tv_onlymonth.setText(Integer.toString(month) +" / "+ Integer.toString(year));

                        String onlytime = new SimpleDateFormat("hh:mm aa", Locale.US).format(date_Start.getTime());
                        ((NoimageViewHolder) holder).tv_onlytime.setText(onlytime);


                        String full_end = object.getEnd();


                        Date date_end = df_post.parse(full_end);
                        full_end = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US).format(date_end.getTime());


                        end_date = Calendar.getInstance();
                        end_date.setTime(format.parse(full_end));
                        int enddate_year = end_date.get(Calendar.YEAR);
                        int enddate_month = end_date.get(Calendar.MONTH) + 1;
                        int enddate_date = end_date.get(Calendar.DATE);
                        int enddate_day = end_date.get(Calendar.DAY_OF_MONTH);
                        String enddate_dayOfWeek = getDayOfWeek(Calendar.DAY_OF_WEEK);

                        ((NoimageViewHolder) holder).tv_end_onlydate.setText(Integer.toString(enddate_date));
                        ((NoimageViewHolder) holder).tv_end_onlymonth.setText(Integer.toString(enddate_month) +" / "+ Integer.toString(enddate_year));

                        String onlyendtime = new SimpleDateFormat("hh:mm aa", Locale.US).format(date_end.getTime());
                        ((NoimageViewHolder) holder).tv_end_onlytime.setText(onlyendtime);


                    } catch (Exception dateexceptio) {
                        dateexceptio.printStackTrace();
                    }

                    break;
                case WITH_IMAGE:
                  /*  ((WithimageViewHolder) holder).post_by.setText(object.getmText1());
                    ((WithimageViewHolder) holder).post_details.setText(object.getmText2());
                    ((WithimageViewHolder) holder).post_time.setText(object.getmTime());*/


                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            DataObject_post object = mList.get(position);
            if (object != null) {
                return object.getType();
            }
        }
        return 0;
    }

    public static class NoimageViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title_companyname, tv_contest_titlte, tv_end_onlytime, tv_status, tv_day;
        TextView tv_link_url, tv_onlydate, tv_onlymonth, tv_onlytime, tv_end_onlydate, tv_end_onlymonth;

        public NoimageViewHolder(View itemView) {
            super(itemView);


            tv_title_companyname = (TextView) itemView.findViewById(R.id.tv_title_companyname);
            tv_contest_titlte = (TextView) itemView.findViewById(R.id.tv_contest_titlte);
            tv_end_onlytime = (TextView) itemView.findViewById(R.id.tv_end_onlytime);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            tv_link_url = (TextView) itemView.findViewById(R.id.tv_link_url);
            tv_onlydate = (TextView) itemView.findViewById(R.id.tv_onlydate);
            tv_onlymonth = (TextView) itemView.findViewById(R.id.tv_onlymonth);
            tv_onlytime = (TextView) itemView.findViewById(R.id.tv_onlytime);
            tv_end_onlydate = (TextView) itemView.findViewById(R.id.tv_end_onlydate);
            tv_end_onlymonth = (TextView) itemView.findViewById(R.id.tv_end_onlymonth);


        }
    }

    public static class WithimageViewHolder extends RecyclerView.ViewHolder {

        public WithimageViewHolder(View itemView) {
            super(itemView);


        }
    }

    private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "SUN";
                break;
            case 2:
                day = "MON";
                break;
            case 3:
                day = "TUE";
                break;
            case 4:
                day = "WED";
                break;
            case 5:
                day = "THU";
                break;
            case 6:
                day = "FRI";
                break;
            case 7:
                day = "SAT";
                break;
        }
        return day;
    }


    public static boolean between(Date date, Date dateStart, Date dateEnd) {
        if (date != null && dateStart != null && dateEnd != null) {
            if (date.after(dateStart) && date.before(dateEnd)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}