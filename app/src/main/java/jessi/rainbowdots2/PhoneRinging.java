package jessi.rainbowdots2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jessi on 23-Mar-17.
 */

public class PhoneRinging extends BroadcastReceiver {
    Context pcontext;

    @Override
    public void onReceive(Context context, Intent intent) {

        TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        MyPhoneStateListener PhoneListener = new MyPhoneStateListener();
        tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private class MyPhoneStateListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {

            Log.d("MyPhoneListener", state + "   incoming no:" + incomingNumber);

            if (state == 1) {

                String msg = "New Phone Call Event. Incomming Number : " + incomingNumber;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(pcontext, msg, duration);
                toast.show();
            }
        }
    }
}

