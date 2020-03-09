
package com.example.buklau4twenty.myfirstgame;

        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.widget.TextView;
        import android.widget.Toast;


public class MenuActivity extends Activity implements View.OnClickListener {

   // PlayGame pg = new PlayGame();


    private Button playBtn, helpBtn, highBtn;
    private String[] levelNames = {"Easy", "Average", "Hard"};
    private int Average = 10, Hard = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        playBtn = (Button)findViewById(R.id.play_btn);
        helpBtn = (Button)findViewById(R.id.help_btn);
        highBtn = (Button)findViewById(R.id.high_btn);


        playBtn.setOnClickListener(this);
        helpBtn.setOnClickListener(this);
        highBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(final View view) {
        if(view.getId()==R.id.play_btn){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose a level")
                    .setSingleChoiceItems(levelNames, 0, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            //Sharedprefs
                            SharedPreferences prefs = getSharedPreferences(
                                    "ArithmeticFile", 0);

                            int scores = prefs.getInt("scores", 0);
                            //PlayGame pg = new PlayGame();

                            switch (which){
                                case 0:{
                                    startPlay(which, levelNames[0]);
                                    //pg.SetLevel("easy");
                                    break;
                                }
                                case 1:{
                                    if(scores >= Average){

                                        startPlay(which, levelNames[1]);
                                        //pg.SetLevel("average");


                                    } else {
                                        //TODO - Alert

                                        showAlertDialog();


                                    }
                                    break;
                                }
                                case 2: {
                                    if (scores >= Hard){
                                        startPlay(which, levelNames[2]);
                                        //pg.SetLevel("Hard");
                                    } else {
                                        //TODO - Alert
                                        showAlertDialog();
                                    }
                                    break;
                                }
                            }
                        }
                    });

            AlertDialog ad = builder.create();
            ad.show();
            //play button
        }
        else if(view.getId()==R.id.help_btn){
            Intent helpIntent = new Intent(this, HowToPlay.class);
            this.startActivity(helpIntent);
            //how to play button
        }
        else if(view.getId()==R.id.high_btn){
            //high scores button
            Intent highIntent = new Intent(this, HighScores.class);
            this.startActivity(highIntent);
        }
    }

    public void showAlertDialog (){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("You need to score more points");
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MenuActivity.this, "GoGo", Toast.LENGTH_SHORT).show();

            }
        });
        alert.create().show();

    }

    private void startPlay(int chosenLevel, String levelName)
    {
        //start gameplay
        Intent playIntent = new Intent(this, PlayGame.class);
        playIntent.putExtra("level", chosenLevel);
        playIntent.putExtra("levelName", levelName);
        this.startActivity(playIntent);
    }
}


