package mentorme.csumb.edu.mentorme.mentorScreen;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentors;
import mentorme.csumb.edu.mentorme.mentorScreen.mentorsLyoutAdapter.MentorsAdapter;
import rx.Subscriber;

/**
 * Handles user Interface for Mentors.
 */

public class MentorLayout extends Subscriber<Mentors> {

    private final String TAG = "MentorLayout";
    private MentorActivity mActivity;

    @BindView(R.id.toolbar_back_arrow) ImageView mBackArrow;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_no_menu) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;

    public MentorLayout(MentorActivity activity) {

        mActivity = activity;

        mActivity.setContentView(R.layout.topics_layout);

        ButterKnife.bind(this, mActivity);

        mToolbarTitle.setText(R.string.mentors);
        mActivity.setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.toolbar_back_arrow)
    public void onBackArrowClicked() {
        mActivity.onBackPressed();
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
        mNetworkErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNext(Mentors mentors) {

        MentorsAdapter adapter = new MentorsAdapter(
                mActivity.getApplicationContext(),
                mentors.getMentors());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }
}
