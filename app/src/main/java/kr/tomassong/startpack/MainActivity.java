package kr.tomassong.startpack;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.tomassong.startpack.base.BaseActivity;
import kr.tomassong.startpack.base.BaseItemClickListener;
import kr.tomassong.startpack.base.BaseRecyclerViewAdapter;
import kr.tomassong.startpack.data.ContentItem;
import kr.tomassong.startpack.data.Item;
import kr.tomassong.startpack.data.Type;
import kr.tomassong.startpack.view.SpacesItemDecoration;
import kr.tomassong.startpack.viewholder.ContentViewHolder;
import kr.tomassong.startpack.viewholder.ViewHolder;

public class MainActivity extends BaseActivity implements
        BaseItemClickListener{

    private Type currentType = Type.TRANSPORT;

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final Map<Type, String> imageMap;

    static {
        imageMap = new HashMap<>();

        imageMap.put(Type.ANIMAL, "http://lorempixel.com/200/200/animals/");
        imageMap.put(Type.CITY, "http://lorempixel.com/600/200/city/");
        imageMap.put(Type.FOOD, "http://lorempixel.com/200/200/food/");
        imageMap.put(Type.TRANSPORT, "http://lorempixel.com/200/200/transport/");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initMenu();
        initRecyclerView();
    }

    private void initMenu(){
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        final NavigationView navigation = (NavigationView) findViewById(R.id.navigation);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawer, getToolbar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                switch(menuItem.getItemId()){
                    case R.id.nav_transport:
                        currentType = Type.TRANSPORT;
                        layoutManager = new GridLayoutManager(MainActivity.this, 3);
                        break;
                    case R.id.nav_food:
                        currentType = Type.FOOD;
                        layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                        break;
                    case R.id.nav_animal:
                        currentType = Type.ANIMAL;
                        layoutManager = new GridLayoutManager(MainActivity.this, 2);
                        break;
                    case R.id.nav_city:
                        currentType = Type.CITY;
                        layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                        break;
                }

                recyclerView.setLayoutManager(layoutManager);
                adapter.setItems(getData(currentType));

                drawer.closeDrawers();
                return true;
            }
        });
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new SpacesItemDecoration(
                getResources().getDimensionPixelOffset(R.dimen.spacing_recyclerview_item)
        ));

        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter();
        adapter.setItemClickListener(this);
        adapter.setItems(getData(currentType));

        recyclerView.setAdapter(adapter);
    }


    private List<Item> getData(Type type){
        List<Item> items = new ArrayList<>();

        for(int i = 0 ; i < 100 ; i++){
            ContentItem item = new ContentItem();
            item.setType(type);
            item.setId(i);
            item.setName("Lorem Ipsum " + String.valueOf(i + 1));
            item.setImage(imageMap.get(type) + String.valueOf(i%11));

            if(type == Type.FOOD){
                item.setDesc(getString(R.string.dummy_desc));
            }

            items.add(item);
        }
        return items;
    }


    /**
     *  RecyclerView Item event
     */
    @Override
    public void onItemClick(View view) {
        int position = recyclerView.getChildAdapterPosition(view);

        Snackbar.make(
                recyclerView,
                String.valueOf(position) + " Selected!",
                Snackbar.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onItemLongClick(View view) {

    }

    @Override
    public void onItemChildClick(View parent, View view) {

    }

    /**
     *  RecyclerView Adapter
     */
    private static final class MyAdapter extends BaseRecyclerViewAdapter{

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
            view.setOnClickListener(this);
            return new ContentViewHolder(Type.valueOf(viewType), view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Item item = getItem(position);
            holder.bindItem(item);
        }
    }
}
