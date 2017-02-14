package net.iquesoft.project.iQueCommerce.data.repository;

import net.iquesoft.project.iQueCommerce.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class JSONCountriesReader {

    final SortedMap<String, String> countries = new TreeMap<>();
    final SortedMap<String, List<String>> provinces = new TreeMap<>();
    final SortedMap<String, String> provinceCodes = new TreeMap<>();

    @Inject
    public JSONCountriesReader() {
        init();
    }

    private void init() {
        String jsonString = Constants.countries;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String countryCode = jsonObject.getString("code");
                String countryName = jsonObject.getString("name");
                JSONArray array = jsonObject.optJSONArray("provinces");
                if (array != null) {
                    List<String> provinceNames = new ArrayList<>();
                    for (int j = 0; j < array.length(); j++) {
                        JSONObject jsonObjectProvinces = array.getJSONObject(j);
                        String provinceCode = jsonObjectProvinces.getString("code");
                        String provinceName = jsonObjectProvinces.getString("name");
                        provinceNames.add(provinceName);
                        provinceCodes.put(provinceName, provinceCode);
                    }
                    provinces.put(countryName, provinceNames);
                }
                countries.put(countryCode, countryName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Observable<List<String>> getCountries() {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : countries.entrySet()) {
            list.add(entry.getValue());
        }
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<String>> getProvinces(String country) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(JSONCountriesReader.this.provinces.get(country));
                subscriber.onCompleted();
            }
        });
    }
}
