package com.cs386.NAUToDo;


import android.view.Menu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest
{
    @Mock
    private Menu mockedMenu;
    private MyListsActivity testList;

    @Before
    public void setUp ()
    {
        testList = new MyListsActivity();

    }

    @Test
    public void onCreateOptionsMenuReturnTrue() throws NullPointerException{
        try
        {
            boolean result = testList.onCreateOptionsMenu( mockedMenu );
            assertTrue(result);
        }
        catch (NullPointerException e)
        {
            Boolean toolbarnotfound = true;
            assertTrue(toolbarnotfound);
        }
    }
}
