package com.hackathon.internetradio.internetradiohmi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiPresenter;
import com.hackathon.internetradio.internetradiohmi.Interfaces.IRadioNetHmiView;
import com.hackathon.internetradio.internetradiohmi.Presenter.RadioNetPresenter;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.ServiceInterfaceManager;
import com.hackathon.internetradio.internetradiohmi.domain.hmidata.internetradio.BrowseListItem;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseItem;
import com.hackathon.internetradio.lib.commoninterface.browse.BrowseList;
import com.hackathon.internetradio.lib.commoninterface.constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubListFragment extends Fragment implements IRadioNetHmiView {

    ListView listView;

    Activity mActivity;

    ArrayAdapter<String> adapter;

    IScrollViewManager mCallback;

    protected IRadioNetHmiPresenter mRadioNetHmiPresenter;

    private Map<String, String> nameIdMap = new HashMap<>();

    private List<BrowseListItem> mArrayList;

    private final ServiceInterfaceManager mServiceInterfaceManager =
            ServiceInterfaceManager.getInstance();

    private ArrayList<String> stationArray = new ArrayList<>();

    boolean isListUpdatePending = false;
    BrowseList mBrowseList;

    public SubListFragment() {
        mRadioNetHmiPresenter = new RadioNetPresenter(this);
        mRadioNetHmiPresenter.start();
        mArrayList = new ArrayList<>();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRadioNetHmiPresenter.stop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station_list, container, false);
        listView=view.findViewById(R.id.list);
        // Inflate the layout for this fragment
        mCallback = (IScrollViewManager) getActivity();

        mRadioNetHmiPresenter.start();
        mRadioNetHmiPresenter.getStationListItems(Constants.StationType.ALL_STATIONS);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
                for(String key : nameIdMap.keySet()) {
                    if (key.equals(value)) {
                        String mediaId = nameIdMap.get(value);
                        mRadioNetHmiPresenter.playFromMediaId(mediaId);
                        break;
                    }
                    Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
                    mCallback.handleSelectedListItems();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        if (isListUpdatePending) {
            setListUpdatePending();
        }
    }




    public void onNotifyCategoryListItems(BrowseList browseList) {
        for (int listIndex = 0; listIndex < mArrayList.size(); listIndex++) {
            BrowseListItem browseListItem = mArrayList.get(listIndex);
            String browseListItemName = browseListItem.getListItemName();
            stationArray.add(browseListItemName);
        }
    }

    @Override
    public void onNotifyStationListItems(BrowseList browseList) {
        mBrowseList = browseList;
        stationArray.clear();
        for (int listIndex = 0; listIndex < browseList.getBrowseItemList().size(); listIndex++) {
            BrowseItem browseItem = browseList.getBrowseItemList().get(listIndex);
            String browseListItemName = browseItem.getItemName();
            stationArray.add(browseListItemName);
        }


        if (getActivity() != null) {
            isListUpdatePending = false;
            adapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, android.R.id.text1, stationArray);
            listView.setAdapter(adapter);
        } else if (mActivity != null) {
            isListUpdatePending = false;
            adapter = new ArrayAdapter<>(mActivity,
                    android.R.layout.simple_list_item_1, android.R.id.text1, stationArray);
            listView.setAdapter(adapter);
        } else {
            isListUpdatePending = true;
        }

        for (int i = 0; i < browseList.getBrowseItemList().size(); i++) {
            nameIdMap.put(browseList.getBrowseItemList().get(i).getItemName(), browseList.getBrowseItemList().get(i).getId());
        }
    }

    private void setListUpdatePending() {
        if (mBrowseList != null) {
            if (mActivity != null && listView != null) {
                isListUpdatePending = false;
                stationArray.clear();
                for (int listIndex = 0; listIndex < mBrowseList.getBrowseItemList().size(); listIndex++) {
                    BrowseItem browseItem = mBrowseList.getBrowseItemList().get(listIndex);
                    String browseListItemName = browseItem.getItemName();
                    stationArray.add(browseListItemName);
                }
                adapter = new ArrayAdapter<>(mActivity,
                        android.R.layout.simple_list_item_1, android.R.id.text1, stationArray);
                listView.setAdapter(adapter);
            }
        }
    }
}