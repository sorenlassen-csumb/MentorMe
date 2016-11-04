package mentorme.csumb.edu.mentorme.homeScreen;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.data.model.Subjects;
import mentorme.csumb.edu.mentorme.R;
import rx.Subscriber;

/**
 * Contains the functionality for the result that need to be displayed
 */

class HomeLayout extends Subscriber<Subjects> {

    private final String TAG = "HomeLayout";

    private AppCompatActivity mActivity;
    private HomeModel mHomeModel;

    @BindView(R.id.subjects_list) ListView mSubjectsList;

    HomeLayout(AppCompatActivity activity) {

        mActivity = activity;
        mActivity.setContentView(R.layout.activity_home);
        mHomeModel = new HomeModel();

        ButterKnife.bind(this, mActivity);
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
    }

    @Override
    public void onNext(Subjects subjects) {

        ArrayList<HashMap<String, String>> list = mHomeModel.parseResponse(subjects.getSubjects());

        ListAdapter adapter = new SimpleAdapter(
                mActivity, list,
                R.layout.subjects_list,
                new String[] {HomeModel.SUBJECT},
                new int[]{R.id.subject}
        );

        mSubjectsList.setAdapter(adapter);
    }
}
