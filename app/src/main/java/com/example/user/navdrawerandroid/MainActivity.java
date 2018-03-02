package com.example.user.navdrawerandroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableBadgeDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 100000;
    Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new DrawerBuilder().withActivity(this).build();
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(R.drawable.p1).withIdentifier(100);
        final IProfile profile2 = new ProfileDrawerItem().withName("Demo User").withEmail("demo@github.com").withIcon(R.drawable.p2).withIdentifier(101);
        final IProfile profile3 = new ProfileDrawerItem().withName("Max Muster").withEmail("max.mustermann@gmail.com").withIcon(R.drawable.p3).withIdentifier(102);
        final IProfile profile4 = new ProfileDrawerItem().withName("Felix House").withEmail("felix.house@gmail.com").withIcon(R.drawable.p4).withIdentifier(103);
        final IProfile profile5 = new ProfileDrawerItem().withName("Mr. X").withEmail("mister.x.super@gmail.com").withIcon(R.drawable.p5).withIdentifier(104);
        final IProfile profile6 = new ProfileDrawerItem().withName("Batman").withEmail("batman@gmail.com").withIcon(R.drawable.p1).withIdentifier(105);

        headerResult=new AccountHeaderBuilder().withActivity(this).withTranslucentStatusBar(true).withHeaderBackground(R.drawable.header).addProfiles(profile,profile2,profile3,profile4,profile5,profile6,
                new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(100001)

        ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
            @Override
            public boolean onProfileChanged(View view, IProfile profile, boolean current) {

                if (profile instanceof IDrawerItem && profile.getIdentifier() == PROFILE_SETTING) {
                    int count = 100 + headerResult.getProfiles().size() + 1;
                    IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman" + count).withEmail("batman" + count + "@gmail.com").withIcon(R.drawable.p6).withIdentifier(count);
                    if (headerResult.getProfiles() != null) {
                        //we know that there are 2 setting elements. set the new profile above them ;)
                        headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                    } else {
                        headerResult.addProfiles(newProfile);
                    }
                }
                return false;
            }
        }).withSavedInstance(savedInstanceState).build();








        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)

                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("PersistentHeader Drawer").withDescription("Smaller persistent account header").withIcon(GoogleMaterial.Icon.gmd_brightness_5).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("ActionBar Drawer").withDescription("Shows how to use with an actionbar").withIcon(FontAwesome.Icon.faw_home).withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("CrossfadeDrawerLayout").withDescription("2-Step Drawer behavior").withIcon(GoogleMaterial.Icon.gmd_format_bold).withIdentifier(15).withSelectable(false),

                        new ExpandableBadgeDrawerItem().withName("Collapsable Badge").withIcon(GoogleMaterial.Icon.gmd_format_bold).withIdentifier(18).withSelectable(false).withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)).withBadge("100").withSubItems(
                                new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_format_bold).withIdentifier(2000),
                                new SecondaryDrawerItem().withName("CollapsableItem 2").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_format_bold).withIdentifier(2001)
                        ),
                        new ExpandableDrawerItem().withName("Collapsable").withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(19).withSelectable(false).withSubItems(
                                new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2002),
                                new SecondaryDrawerItem().withName("CollapsableItem 2").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_filter_list).withIdentifier(2003)
                        ),
                        new SectionDrawerItem().withName("Section Header"),
                        new SecondaryDrawerItem().withName("Open Source").withIcon(FontAwesome.Icon.faw_github).withIdentifier(20).withSelectable(false),
                        new SecondaryDrawerItem().withName("Contact").withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withIdentifier(21).withTag("Bullhorn")
                        /*,
                        new DividerDrawerItem(),
                        new SwitchDrawerItem().withName("Switch").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener),
                        new SwitchDrawerItem().withName("Switch2").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener).withSelectable(false),
                        new ToggleDrawerItem().withName("Toggle").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener),
                        new DividerDrawerItem(),
                        new SecondarySwitchDrawerItem().withName("Secondary switch").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener),
                        new SecondarySwitchDrawerItem().withName("Secondary Switch2").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener).withSelectable(false),
                        new SecondaryToggleDrawerItem().withName("Secondary toggle").withIcon(Octicons.Icon.oct_tools).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener)
                        */
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 2) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 3) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 4) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 5) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 7) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 8) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 9) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 10) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 11) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 12) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 13) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 14) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 15) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            } else if (drawerItem.getIdentifier() == 20) {
                                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT);
                            }
                            if (intent != null) {
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
//              .withShowDrawerUntilDraggedOpened(true)
                .build();
        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);
            //set the active profile
            headerResult.setActiveProfile(profile3);
        }
        result.updateBadge(4, new StringHolder(10 + ""));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}


