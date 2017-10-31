package br.com.test.lastjedi.fragment;

import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.test.lastjedi.model.People;

/**
 * Created by Samurai on 31/10/2017.
 */

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private People people;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        people = (People) getArguments().getSerializable("people");
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng posicao = new LatLng(people.getLatitude(), people.getLongitude());
        if(posicao != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicao, 17);
            googleMap.moveCamera(update);

            MarkerOptions marcador = new MarkerOptions();
            marcador.position(posicao);
            marcador.title(people.getName());
            googleMap.addMarker(marcador);
        }
    }
}
