package com.cs399.i_buy_3;

import android.view.Menu;

import com.cs386.NAUToDo.MyListsActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class unitTest {

    @Mock
    private Menu testMenu;

    private MyListsActivity testList;

    @Before
    public void setUp(){
        testList = new MyListsActivity(  );
    }

    @Test
    public void onCreateOptionsMenuReturnTrue() throws Exception {
        boolean result = testList.onCreateOptionsMenu( testMenu );
        assertTrue( result );
    }

}
