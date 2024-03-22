package com.example.musicapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.musicapp.R;
import com.example.musicapp.adapter.SongAdapter;
import com.example.musicapp.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrendingFragment extends Fragment {
    RecyclerView recyclerView;
    private ArrayList<Song> mListSong = new ArrayList<>();
    private SongAdapter songAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        songAdapter = new SongAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);
        getData();
        songAdapter.setData(getContext(), mListSong);

        return view;
    }

    private void getData() {
        String url = "https://mp3.zing.vn/xhr/chart-realtime?songId=0&videoId=0&albumId=0&chart=song&time=-1";
        RequestQueue requestQueue = Volley.newRequestQueue(TrendingFragment.this.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try{
                            JSONObject jsonObject =new JSONObject(s);
                            JSONObject jsonObject_data = jsonObject.getJSONObject("data");
                            JSONArray jsonArray_song = jsonObject_data.getJSONArray("song");
                            for (int i = 0; i < 20; i++) {
                                JSONObject jsonObject_song = jsonArray_song.getJSONObject(i);
                                String id = jsonObject_song.getString("id");
                                String name_song = jsonObject_song.getString("name");
                                String name_artist =  jsonObject_song.getString("artists_names");
                                String thumbnail = jsonObject_song.getString("thumbnail");
                                String position = jsonObject_song.getString("position");
                                String lyric = jsonObject_song.getString("lyric");
                                String code = jsonObject_song.getString("code");
                                Integer duration = jsonObject_song.getInt("duration");
                                // Tìm vị trí của phần "w94_r1x1_jpeg/"
                                int index = thumbnail.indexOf("w94_r1x1_jpeg/");    //Xóa đi để lấy ảnh nét hơn
                                String thumbnail1 = thumbnail.substring(0, index) + thumbnail.substring(index + "w94_r1x1_jpeg/".length());

                                //String url = "http://mp3.zing.vn/xhr/media/get-source?type=audio&key="+;

                                String source = "https://a128-z3.zmdcdn.me/945f3ce83dd0eb820aa0e05cce267c5b?authen=exp=1711281444~acl=/945f3ce83dd0eb820aa0e05cce267c5b/*~hmac=ec658ffa63bca0d97448ca84944927cd&fs=MTmUsICxMTEwODY0NDY1N3x3ZWJWNHwxMDMdUngMTk5LjMzLjM4";

                               mListSong.add(new Song(id, name_song, name_artist,
                                       thumbnail1, position,
                                       lyric, source, code, duration));
                                songAdapter.notifyDataSetChanged();
                            }

                        }catch (JSONException e){
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(stringRequest);
    }
}