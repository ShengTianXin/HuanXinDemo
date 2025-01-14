package com.feicui.huanxincase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.feicui.huanxincase.apphx.contact.HxContactListFragment;
import com.feicui.huanxincase.apphx.conversation.HxConversationListFragment;
import com.feicui.huanxincase.books.BooksFragment;
import com.feicui.huanxincase.user.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.text_books)
    TextView tvBooks;
    @BindView(R.id.text_contacts)
    TextView tvContacts;
    @BindView(R.id.text_conversations)
    TextView tvConversations;
    @BindView(R.id.text_me)
    TextView tvMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        mViewPager.setAdapter(adapter);
        tvBooks.setSelected(true); // 默认"书友"是选中状态
        mViewPager.addOnPageChangeListener(this);
    }

    private final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new BooksFragment();
                case 1:
                    return new HxContactListFragment();
                case 2:
                    return new HxConversationListFragment();
                case 3:
                    return new UserFragment();
                default:
                    throw new RuntimeException();
            }
        }

        @Override public int getCount() {
            return 4;
        }
    };

    @OnClick({R.id.text_books, R.id.text_contacts, R.id.text_conversations,R.id.text_me})
    public void chooseFragment(View view) {
        switch (view.getId()) {
            case R.id.text_books:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.text_contacts:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.text_conversations:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.text_me:
                mViewPager.setCurrentItem(3, false);
                break;
            default:
                throw new RuntimeException();
        }
    }

    /**
     * start-interface: OnPageChangeListener
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvBooks.setSelected(position == 0);
        tvContacts.setSelected(position == 1);
        tvConversations.setSelected(position == 2);
        tvMe.setSelected(position == 3);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /**
     * end-interface: OnPageChangeListener
     */
}
