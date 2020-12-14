package com.hackathon.internetradio.internetradiohmi;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiPresenter;
import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiView;
import com.hackathon.internetradio.internetradiohmi.Presenter.RadioNetPresenter;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;
import com.hackathon.internetradio.lib.commoninterface.TrackInfo;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

import static android.content.Context.MODE_PRIVATE;

public class NowplayFragment extends Fragment implements IRadioNetHmiView {

    ImageButton playbtn;

    ImageButton pausebtn;

    ImageButton btnPrevious;

    ImageButton btnNext;

    ImageView songCoverArt;

    TextView songTitle;

    TextView albumName;

    TextView artistName;

    SharedPreferences sharedpreferences;

    protected IRadioNetHmiPresenter mRadioNetHmiPresenter;

    private final ServiceInterfaceManager mServiceInterfaceManager =
            ServiceInterfaceManager.getInstance();

    public NowplayFragment() {
        mRadioNetHmiPresenter = new RadioNetPresenter(this);
        mRadioNetHmiPresenter.start();
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nowplay, container, false);
        songCoverArt = view.findViewById(R.id.songCoverArt);
        playbtn = view.findViewById(R.id.btnPlay);
        pausebtn = view.findViewById(R.id.btnPause);
        btnPrevious = view.findViewById(R.id.btnPrevious);
        btnNext = view.findViewById(R.id.btnNext);
        songTitle = view.findViewById(R.id.songTitle);
        albumName = view.findViewById(R.id.tv_album);
        artistName = view.findViewById(R.id.tv_artist);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("live",1);
        System.out.println("Value Set : " + 1);
        myEdit.commit();


        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausebtn.setVisibility(View.VISIBLE);
                playbtn.setVisibility(View.GONE);
                mRadioNetHmiPresenter.play();
            }
        });

        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausebtn.setVisibility(View.GONE);
                playbtn.setVisibility(View.VISIBLE);
                mRadioNetHmiPresenter.pause();
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioNetHmiPresenter.skip(Constants.SkipDirection.PREV, 1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioNetHmiPresenter.skip(Constants.SkipDirection.NEXT, 1);
            }
        });
        return view;
    }

    @Override
    public void onNotifyPlayStatus(int playStatus) {
        if (playStatus == Constants.PlayStatus.PLAY) {
            pausebtn.setVisibility(View.VISIBLE);
            playbtn.setVisibility(View.GONE);
        } else {
            pausebtn.setVisibility(View.GONE);
            playbtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNotifyTrackChange(TrackInfo trackInfo) {
      if (trackInfo != null) {
          songTitle.setText(trackInfo.getTitle());
          albumName.setText(trackInfo.getAlbumName());
          artistName.setText(trackInfo.getArtist());
          updateCoverArt(trackInfo.getCoverArt());
      }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRadioNetHmiPresenter.stop();
    }

    public void updateCoverArt(String coverArtName) {
        Drawable drawable = null;
        if (coverArtName != null && !TextUtils.isEmpty(coverArtName)) {
            if (coverArtName.equals("album_art_1")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_1, null);
            } else if (coverArtName.equals("album_art_2")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_2, null);
            }  else if (coverArtName.equals("album_art_2")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_3, null);
            }  else if (coverArtName.equals("album_art_3")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_4, null);
            }  else if (coverArtName.equals("album_art_4")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_5, null);
            }  else if (coverArtName.equals("album_art_5")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_6, null);
            }  else if (coverArtName.equals("album_art_6")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_7, null);
            }  else if (coverArtName.equals("album_art_7")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_8, null);
            }  else if (coverArtName.equals("album_art_8")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_9, null);
            }  else if (coverArtName.equals("album_art_7")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_10, null);
            }  else if (coverArtName.equals("album_art_8")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_11, null);
            }  else if (coverArtName.equals("album_art_9")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_12, null);
            }  else if (coverArtName.equals("album_art_10")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_13, null);
            }  else if (coverArtName.equals("album_art_11")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_14, null);
            }  else if (coverArtName.equals("album_art_12")) {
                drawable =  getResources().getDrawable( R.drawable.album_art_15, null);
            } else {
                drawable =  getResources().getDrawable( R.drawable.album_art_15, null);
            }
        }
        if (drawable != null) {
            songCoverArt.setImageDrawable(drawable);
        }
    }
}