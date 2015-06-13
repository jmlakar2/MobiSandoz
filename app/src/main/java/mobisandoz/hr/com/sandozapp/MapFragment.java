package mobisandoz.hr.com.sandozapp;

import android.app.Dialog;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import mobisandoz.hr.com.sandozapp.core.PreuzmiSuradnike;
import mobisandoz.hr.com.sandozapp.types.SuradniciReturnType;

/**
 * Created by Josip on 16.4.2015..
 */
public class MapFragment extends SupportMapFragment {
    private GoogleMap googleMap = null;
//	private Location myLocation = null;

    @Override
    public void onStart() {
        super.onStart();

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getActivity().getBaseContext());

        if(status != ConnectionResult.SUCCESS)
        {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this.getActivity(), requestCode);
            dialog.show();
        }
        else
        {
//			this.googleMap = ((SupportMapFragment) this.getFragmentManager().findFragmentById(R.id.fragment_google_maps)).getMap();
            this.googleMap = this.getMap();
            this.googleMap.setMyLocationEnabled(true);
//			this.googleMap.setOnMyLocationChangeListener(this);
//			this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));

            Location myLocation = null;
            LocationManager locationManager = (LocationManager) this.getActivity().getSystemService(ActionBarActivity.LOCATION_SERVICE);
            List<String> providers = locationManager.getProviders(true);
//			Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            for(String provider : providers)
            {
                myLocation = locationManager.getLastKnownLocation(provider);
                if(myLocation != null)
                {
                    break;
                }
            }

            if(myLocation != null)
            {
                LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLatLng, 16); // zoom: between 0-21
                this.googleMap.animateCamera(update);
            }

        }

        PreuzmiSuradnike preuzmiSuradnike = new PreuzmiSuradnike();
        List<SuradniciReturnType> listaSuradnici = preuzmiSuradnike.vratiSuradnike();

        for (int i = 0; i < listaSuradnici.size(); i++) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(listaSuradnici.get(i).getX_kord(), listaSuradnici.get(i).getY_kord()))
                    .title(listaSuradnici.get(i).getNazivSuradnik()));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
