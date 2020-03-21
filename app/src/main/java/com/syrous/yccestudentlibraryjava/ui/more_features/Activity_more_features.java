package com.syrous.yccestudentlibraryjava.ui.more_features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.syrous.yccestudentlibraryjava.R;
import com.syrous.yccestudentlibraryjava.ui.upload.ActivityUpload;

import java.util.ArrayList;

public class Activity_more_features extends AppCompatActivity {

    Context context;
    public static Intent newIntent(Context context) {
        Intent uploadIntent = new Intent(context, Activity_more_features.class);
        return uploadIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_features);



        ListView listView = findViewById(R.id.listview);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Anti Ragging Committee");
        arrayList.add("Anti Ragging Squad");
        arrayList.add("MGI Brochure");
        arrayList.add("Research Portal");
        arrayList.add("Meghwani");
        arrayList.add("Exam info");
        arrayList.add("Format for student grievances");
        arrayList.add("Grading System B.E.");
        arrayList.add("Punishment for Unfair Means");
        arrayList.add("NBA Accrediction status");
        arrayList.add("AICTE Approval");
        arrayList.add("Autonomy");
        arrayList.add("Affiliation");
        arrayList.add("Inside Campus (intranet)");
        arrayList.add("Outside Campus (internet)");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.textcenter,R.id.tvv,arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(-65536);
                builder.setShowTitle(true);
                CustomTabsIntent customTabsIntent = builder.build();

                String url = "www.ycce.edu";

                switch (i){
                    case 0:
                        url = "https://docs.google.com/viewer?url=http://ycce.edu/pdf/anti-ragging-committee.pdf";
                        break;
                    case 1:
                        url="http://docs.google.com/viewer?url=http://ycce.edu/pdf/anti-ragging-squad.pdf";
                        break;
                    case 2:
                        url="https://docs.google.com/viewer?url=http://ycce.edu/pdf/mgi-placement_brochure.pdf";
                        break;
                    case 3:
                        url="http://ycceresearchportal.org/";
                        break;
                    case 4:
                        url="https://docs.google.com/viewer?url=http://mginagpur.com/pdf/meghwani.pdf";
                        break;
                    case 5:
                        url="https://www.ycce.edu/Examinationinfo.php";
                        break;
                    case 6:
                        url="http://docs.google.com/viewer?url=http://ycce.edu/pdf/Grievance_form_Format.pdf";
                        break;
                    case 7:
                        url="https://docs.google.com/viewer?url=http://ycce.edu/pdf/grading_system_2014.pdf";
                        break;
                    case 8:
                        url="http://docs.google.com/viewer?url=http://ycce.edu/pdf/punishment_for_unfair_means.pdf";
                        break;
                    case 9:
                        url="http://docs.google.com/viewer?url=http://ycce.edu/pdf/nba_accreditation_status.pdf";
                        break;
                    case 10:
                        url="http://docs.google.com/viewer?url=http://ycce.edu/pdf/aicte_approvals.pdf";
                        break;
                    case 11:
                        url="http://docs.google.com/viewer?url=http://ycce.edu/pdf/Auto_granted_RTMNU.pdf";
                        break;
                    case 12:
                        url="http://docs.google.com/viewer?url=http://ycce.edu/pdf/UG_perm_affiliation_new.pdf";
                        break;
                    case 13:
                        url="http://172.16.1.19/yccemis/";
                        break;
                    case 14:
                        url="http://118.185.122.67/yccemis/";
                        break;
                    default:
                        url="https://www.ycce.edu";
                }
                customTabsIntent.launchUrl(Activity_more_features.this, Uri.parse(url));

            }


        });


    }

    public void  onclicklink(String i){





    }
}
