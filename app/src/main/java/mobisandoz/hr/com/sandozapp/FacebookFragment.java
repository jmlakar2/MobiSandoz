package mobisandoz.hr.com.sandozapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.inputmethod.InputMethodManager;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import mobisandoz.hr.com.sandozapp.core.FbKodServiceAsyncTask;
import mobisandoz.hr.com.sandozapp.core.Login;

/**
 * Created by Josip on 16.4.2015..
 */
public class FacebookFragment extends Fragment {

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Podijeli iskustvo");
        getActivity().invalidateOptionsMenu();
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Sandoz")
                    .setContentDescription(
                            "Kupnjom Lekadol i Operil proizvoda sakupljam bodove koje kasnije mogu zamijeniti za Lekadol i Operil pakete."
                    +" Želiš se pridružiti? Preuzmi aplikaciju SandozApp. Više informacija nalazi se na linku.")
                    .setContentUrl(Uri.parse("https://www.facebook.com/testestud2015"))
                    .build();

            shareDialog.show(linkContent);

        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        FbKodServiceAsyncTask asyncTask = new FbKodServiceAsyncTask();
        Login lo = new Login();
        String user = lo.vratiUser(getActivity());
        String kod = lo.vratiKod(getActivity());
        asyncTask.execute(new Object[] {user, kod});
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        PocetnaFragment mbf = new PocetnaFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mbf)
                .commit();
    }
}
