package spoonique.com.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;


public class ResendPasswordActivity extends Activity {

    protected Button mResendPasswordButton;
    protected EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_resend_password);

        mEmail = (EditText) findViewById(R.id.userEmail);
        mResendPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        mResendPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ResendPasswordActivity.this);
                    builder.setMessage(R.string.reset_error_message)
                            .setTitle(R.string.reset_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    setProgressBarIndeterminateVisibility(true);
                    ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
                        @Override
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);
                            if (e == null) {
                                //Success
                                Intent intent = new Intent(ResendPasswordActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ResendPasswordActivity.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.reset_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.resend_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
